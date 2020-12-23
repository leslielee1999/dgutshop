package org.dgutstu.dgutshop.admin.service;

import org.dgutstu.dgutshop.core.util.JacksonUtil;
import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopOrder;
import org.dgutstu.dgutshop.db.service.DgutshopOrderService;
import org.dgutstu.dgutshop.db.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.dgutstu.dgutshop.admin.util.AdminResponseCode.ORDER_CONFIRM_NOT_ALLOWED;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 10:51 2020/12/16
 */
@Service
public class AdminOrderService {

    @Autowired
    private DgutshopOrderService orderService;

    /**
     * 查询订单
     * @param nickname
     * @param consignee
     * @param code
     * @param start
     * @param end
     * @param orderStatusArray
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public Object list(String nickname, String consignee, String code, LocalDateTime start, LocalDateTime end, List<Short> orderStatusArray,
                       Integer page, Integer limit, String sort, String order) {
        Map<String, Object> data = (Map)orderService.queryVoSelective(nickname, consignee, code, start, end, orderStatusArray, page, limit, sort, order);
        return ResponseUtil.ok(data);
    }


    /**
     * 发货
     * 1. 检测当前订单是否能够发货
     * 2. 设置订单发货状态
     *
     * @param body 订单信息，{ orderId：xxx, deliverymanName：xxx, deliverymanPhone：xxx }
     * @return 订单操作结果
     * 成功则 { errno: 0, errmsg: '成功' }
     * 失败则 { errno: XXX, errmsg: XXX }
     */
    public Object delivery(String body) {
        Integer orderId = JacksonUtil.parseInteger(body, "orderId");
        String deliverymanName = JacksonUtil.parseString(body, "deliverymanName");
        String deliverymanPhone = JacksonUtil.parseString(body, "deliverymanPhone");
        if (orderId == null) {
            return ResponseUtil.badArgument();
        }

        DgutshopOrder order = orderService.findById(orderId);
        if (order == null) {
            return ResponseUtil.badArgument();
        }

        // 如果订单不是已付款状态，则不能发货
        if (!order.getOrderStatus().equals(OrderUtil.STATUS_PAY)) {
            return ResponseUtil.fail(ORDER_CONFIRM_NOT_ALLOWED, "订单不能确认收货");
        }

        String deliveryCompany = "自家配送";
        order.setOrderStatus(OrderUtil.STATUS_SHIP);
        order.setDeliveryCompany(deliveryCompany);
        order.setDeliverymanName(deliverymanName);
        order.setDeliverymanPhone(deliverymanPhone);
        order.setDeliveryTime(LocalDateTime.now());

        /*
        *
        * 采用基于update_time的乐观锁机制来处理并发更新问题
        *  不过这里是奶茶店一般不会有这种需求，先作保留，后续若有库存等问题可以直接使用
        *
        */
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            return ResponseUtil.updatedDateExpired();
        }

//        T`ODO 发送邮件和短信通知，这里采用异步发送
//         发货会发送通知短信给用户:
//         "您的订单已经发货，快递公司 {1}，快递单 {2} ，请注意查收"
//        notifyService.notifySmsTemplate(order.getMobile(), NotifyType.SHIP, new String[]{shipChannel, shipSn});

//        logHelper.logOrderSucceed("发货", "订单编号 " + order.getOrderSn());
        return ResponseUtil.ok();
    }
}
