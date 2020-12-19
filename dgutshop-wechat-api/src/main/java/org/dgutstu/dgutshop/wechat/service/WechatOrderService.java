package org.dgutstu.dgutshop.wechat.service;

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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:订单服务
 *
 * 订单状态：
 * 101 订单生成，未支付；102，下单后未支付用户取消；103，下单后未支付超时系统自动取消
 * 201 支付完成，商家未发货；202，订单生产，已付款未发货，但是退款取消；
 * 301 商家发货，用户未确认；
 * 401 用户确认收货； 402 用户没有确认收货超过一定时间，系统自动确认收货；
 *
 * 用户操作：
 * 当101用户未付款时，此时用户可以进行的操作是取消订单，或者付款操作
 * 当201支付完成而商家未发货时，此时用户可以取消订单并申请退款
 * 当301商家已发货时，此时用户可以有确认收货的操作
 * 当401用户确认收货以后，此时用户可以进行的操作是删除订单，评价商品，申请售后，或者再次购买
 * 当402系统自动确认收货以后，此时用户可以删除订单，评价商品，申请售后，或者再次购买
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
 * 502：骑手送达后超过一定时间自动确认
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
     * @param userId
     * @param body
     * @return
     */
//    @Transactional
//    public Object submit(Integer userId, String body){
//        if (userId == null){
//            return ResponseUtil.unlogin();
//        }
//        if (body == null) {
//            return ResponseUtil.badArgument();
//        }
//        Integer cartId = JacksonUtil.parseInteger(body, "cartId");
//        String type = JacksonUtil.parseString(body, "type");
//        Integer addressId = JacksonUtil.parseInteger(body, "addressId");
//        String message = JacksonUtil.parseString(body, "message");
//        if (cartId == null || addressId == null) {
//            return ResponseUtil.badArgument();
//        }
//        //  运费，根据是否外送来决定是否添加
//        DgutshopAddress checkAddress = null;
//        BigDecimal delivery = new BigDecimal(0);
//        if (!type.equals("自取")){
//            //  收货地址
//            checkAddress = addressService.query(userId, addressId);
//            if (checkAddress == null){
//                return ResponseUtil.badArgument();
//            }
//            //  运费，根据是否外送来决定是否添加
//            delivery = SystemConfig.getDelivery();
//        }
//
//        //  饮品价格
//        List<DgutshopCart> checkedProductsList = null;
//        if (cartId.equals(0)){
//            checkedProductsList = cartService.queryByUidAndChecked(userId);
//        }else {
//            DgutshopCart cart = cartService.findById(cartId);
//            checkedProductsList = new ArrayList<>(1);
//            checkedProductsList.add(cart);
//        }
//        if (checkedProductsList.size() == 0){
//            return ResponseUtil.badArgumentValue();
//        }
//        BigDecimal checkedProductsPrice = new BigDecimal(0);
//        for (DgutshopCart checkProducts : checkedProductsList) {
//            checkedProductsPrice = checkedProductsPrice.add(checkProducts.getPrice().multiply(new BigDecimal(checkProducts.getNumber())));
//        }
//
//
//        //  最终支付费用
//        BigDecimal actualPrice = checkedProductsPrice.add(delivery);
//
//        Integer orderId = null;
//        DgutshopOrder order = null;
//        //  订单
//        order = new DgutshopOrder();
//        order.setUserId(userId);
//        order.setCode(orderService.generateOrderCode(userId));
//        order.setOrderStatus(OrderUtil.STATUS_CREATE);
//        if (!type.equals("自取")){
//            order.setConsignee(checkAddress.getUserName());
//            order.setConsigneePhone(checkAddress.getUserPhone());
//            order.setConsigneeAddress(checkAddress.getUserAddress());
//            order.setConsigneeRoom(checkAddress.getUserRoom());
//            order.setDeliveryPrice(delivery);
//        }
//        order.setMessage(message);
//
//    }

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
     * 确认收货
     * <p>
     * 1. 检测当前订单是否能够确认收货；
     * 2. 设置订单确认收货状态。
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
//    public Object confirm(Integer userId, String body) {
//        if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
//        if (orderId == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        DgutshopOrder order = orderService.findById(userId, orderId);
//        if (order == null) {
//            return ResponseUtil.badArgument();
//        }
//        if (!order.getUserId().equals(userId)) {
//            return ResponseUtil.badArgumentValue();
//        }
//
//        OrderHandleOption handleOption = OrderUtil.build(order);
//        if (!handleOption.isConfirm()) {
//            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能确认收货");
//        }
//
//        Short comments = orderGoodsService.getComments(orderId);
//        order.setComments(comments);
//
//        order.setOrderStatus(OrderUtil.STATUS_CONFIRM);
//        order.setConfirmTime(LocalDateTime.now());
//        if (orderService.updateWithOptimisticLocker(order) == 0) {
//            return ResponseUtil.updatedDateExpired();
//        }
//        return ResponseUtil.ok();
//    }
}
