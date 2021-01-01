package org.dgutstu.dgutshop.wechat.service;

import com.github.binarywang.wxpay.bean.order.WxPayMwebOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import io.swagger.models.auth.In;
import org.dgutstu.dgutshop.core.task.TaskService;
import org.dgutstu.dgutshop.core.util.JacksonUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopOrder;
import org.dgutstu.dgutshop.db.domain.DgutshopOrderItem;
import org.dgutstu.dgutshop.db.domain.OrderListVo;
import org.dgutstu.dgutshop.db.service.DgutshopAddressService;
import org.dgutstu.dgutshop.db.service.DgutshopCartService;
import org.dgutstu.dgutshop.db.service.DgutshopOrderItemService;
import org.dgutstu.dgutshop.db.service.DgutshopOrderService;
import org.dgutstu.dgutshop.db.util.OrderHandleOption;
import org.dgutstu.dgutshop.db.util.OrderUtil;
import org.dgutstu.dgutshop.wechat.task.OrderUnpaidTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:订单服务
 *
 * 订单状态
 * 【101待支付、201制作中、301待取货、401派送中、501已完成】
 *
 * 订单状态
 * 自取与外卖服务共有：
 * 101【待支付】：订单生成，未支付：102，下单后未支付用户取消；103，下单后未支付超时系统自动取消
 * 201【制作中】：支付完成，奶茶正在制作中
 * 501【已完成】：用户点击已确认收货
 *
 * 自取：
 * 301【待取货】：奶茶已制作完毕，生成取件码
 *
 * 外卖：
 * 401【派送中】：派送员正在派送；
 * 402：骑手已到达
 * 502：骑手送达或待取货的状态超过一定时间自动确认
 *
 * @Date: Create in 17:03 2020/12/18
 */
@Service
public class WechatOrderService {

    @Autowired
    private DgutshopOrderService orderService;
    @Autowired
    private DgutshopOrderItemService orderItemService;
    @Autowired
    private DgutshopAddressService addressService;
    @Autowired
    private DgutshopCartService cartService;
    @Autowired
    private TaskService taskService;

    public Object list(Integer userId, String sort, String order){
        if (userId == null){
            return ResponseUtil.unlogin();
        }
        List<DgutshopOrder> list = orderService.list(userId, sort, order);
        orderItemService.fill(list);
        return list;
    }

    /**
     * 微信用户提交订单
     * @param orderListVo
     * @return
     */
    @Transactional
    public Object submit(OrderListVo orderListVo){
        Integer userId = orderListVo.getUserId();
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (orderListVo == null) {
            return ResponseUtil.badArgument();
        }

        Byte type = orderListVo.getType();
        String message = orderListVo.getMessage();
        BigDecimal productPrice = orderListVo.getProductPrice();
        BigDecimal orderPrice = orderListVo.getOrderPrice();
        List<DgutshopOrderItem> itemList = orderListVo.getOrderItemList();
        String consignee = orderListVo.getConsignee();
        String consigneePhone = orderListVo.getConsigneePhone();
        String consigneeAddress = orderListVo.getConsigneeAddress();
        String consigneeRoom = orderListVo.getConsigneeRoom();
        BigDecimal deliveryPrice = orderListVo.getDeliveryPrice();

        Integer orderId = null;
        DgutshopOrder order = null;
        order = new DgutshopOrder();
        order.setUserId(userId);
        order.setCode(orderService.generateOrderCode(userId));
        order.setType(type);
        order.setOrderStatus(OrderUtil.STATUS_CREATE);
        order.setConsignee(consignee);
        order.setConsigneePhone(consigneePhone);
        order.setConsigneeAddress(consigneeAddress);
        order.setConsigneeRoom(consigneeRoom);
        order.setMessage(message);
        order.setDeliveryPrice(deliveryPrice);
        order.setProductPrice(productPrice);
        order.setOrderPrice(orderPrice);
        order.setOrderItemList(itemList);
        order.setPayDate(LocalDateTime.of(1900,01,01,1,1,1));

        //  添加订单表项
        orderService.create(order);
        orderId = order.getId();

        //  添加订单单项
        for (DgutshopOrderItem orderItem : itemList) {
            DgutshopOrderItem dgutshopOrderItem = new DgutshopOrderItem();
            dgutshopOrderItem.setOid(orderId);
            dgutshopOrderItem.setPid(orderItem.getProduct().getId());
            dgutshopOrderItem.setProductName(orderItem.getProduct().getName());
            dgutshopOrderItem.setSize(orderItem.getSize());
            dgutshopOrderItem.setProductOriginalPrice(orderItem.getProductOriginalPrice());
            dgutshopOrderItem.setProductPromotePrice(orderItem.getProductPromotePrice());
            dgutshopOrderItem.setToppingPrice(orderItem.getToppingPrice());
            dgutshopOrderItem.setProductActualPrice(orderItem.getProductActualPrice());
            dgutshopOrderItem.setProductPicture(orderItem.getProduct().getPicture());
            dgutshopOrderItem.setSum(orderItem.getSum());
            dgutshopOrderItem.setNote(orderItem.getNote());

            orderItemService.create(dgutshopOrderItem);
        }

        //  设置订单支付超期任务
//        taskService.addTask(new OrderUnpaidTask(orderId));

        Map<String, Object> data = new HashMap<>();
        data.put("orderId", orderId);
        return ResponseUtil.ok(data);
    }

    /**
     * 取消订单
     * <p>
     * 1. 检测当前订单是否能够取消；
     * 2. 设置订单取消状态；
     * 3. 商品货品库存恢复；
     * 4. 返还优惠券；
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @Transactional
    public Object cancel(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        DgutshopOrder order = orderService.findById(userId, orderId);
        if (order == null) {
            return ResponseUtil.badArgumentValue();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        LocalDateTime preUpdateTime = order.getUpdateTime();

        System.out.println(order.getOrderStatus());
        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isCancel()) {
            return ResponseUtil.fail(725, "订单不能取消");
        }

        // 设置订单已取消状态
        order.setOrderStatus(OrderUtil.STATUS_CANCEL);
        order.setEndTime(LocalDateTime.now());
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            throw new RuntimeException("更新数据已失效");
        }

        return ResponseUtil.ok();
    }


    /**
     * 确认收货
     * <p>
     * 1. 检测当前订单是否能够确认收货；
     * 2. 设置订单确认收货状态。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    public Object confirm(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        DgutshopOrder order = orderService.findById(userId, orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isConfirm()) {
            return ResponseUtil.fail(725, "订单不能确认收货");
        }

//        Short comments = orderGoodsService.getComments(orderId);
//        order.setComments(comments);

        order.setOrderStatus(OrderUtil.STATUS_CONFIRM);
        order.setConfirmTime(LocalDateTime.now());
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            return ResponseUtil.updatedDateExpired();
        }
        return ResponseUtil.ok();
    }


    /**
     * 删除订单
     * <p>
     * 1. 检测当前订单是否可以删除；
     * 2. 删除订单。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    public Object delete(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        DgutshopOrder order = orderService.findById(userId, orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isDelete()) {
            return ResponseUtil.fail(725, "订单不能删除");
        }

        // 订单order_status没有字段用于标识删除
        // 而是存在专门的delete字段表示是否删除
        orderService.deleteById(orderId);
        // 售后也同时删除
//        aftersaleService.deleteByOrderId(userId, orderId);

        return ResponseUtil.ok();
    }


    /**
     * 微信H5支付
     *
     * @param userId
     * @param body
     * @return
     */
    @Transactional
    public Object pay(Integer userId, String body) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        DgutshopOrder order = orderService.findById(userId, orderId);
        if (order == null) {
            return ResponseUtil.badArgumentValue();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isPay()) {
            return ResponseUtil.fail(725, "订单不能支付");
        }

//        WxPayMwebOrderResult result = null;
//        try {
//            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
//            orderRequest.setOutTradeNo(order.getOrderSn());
//            orderRequest.setTradeType("MWEB");
//            orderRequest.setBody("订单：" + order.getOrderSn());
//            // 元转成分
//            int fee = 0;
//            BigDecimal actualPrice = order.getActualPrice();
//            fee = actualPrice.multiply(new BigDecimal(100)).intValue();
//            orderRequest.setTotalFee(fee);
//            orderRequest.setSpbillCreateIp(IpUtil.getIpAddr(request));
//
//            result = wxPayService.createOrder(orderRequest);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        order.setOrderStatus(OrderUtil.STATUS_PAY);
        order.setPayDate(LocalDateTime.now());

        return ResponseUtil.ok();
    }

}
