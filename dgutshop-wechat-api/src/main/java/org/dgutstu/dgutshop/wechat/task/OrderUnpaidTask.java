package org.dgutstu.dgutshop.wechat.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dgutstu.dgutshop.core.system.SystemConfig;
import org.dgutstu.dgutshop.core.task.Task;
import org.dgutstu.dgutshop.core.util.BeanUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopOrder;
import org.dgutstu.dgutshop.db.service.DgutshopDeliveryService;
import org.dgutstu.dgutshop.db.service.DgutshopOrderItemService;
import org.dgutstu.dgutshop.db.service.DgutshopOrderService;
import org.dgutstu.dgutshop.db.service.DgutshopProductService;
import org.dgutstu.dgutshop.db.util.OrderUtil;
import org.dgutstu.dgutshop.wechat.service.WechatOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 21:32 2020/12/19
 */
public class OrderUnpaidTask extends Task {
    private final Log logger = LogFactory.getLog(OrderUnpaidTask.class);
    private int orderId = -1;

    public OrderUnpaidTask(Integer orderId, long delayInMilliseconds){
        super("OrderUnpaidTask-" + orderId, delayInMilliseconds);
        this.orderId = orderId;
    }

    public OrderUnpaidTask(Integer orderId){
        super("OrderUnpaidTask-" + orderId, SystemConfig.getOrderUnpaid() * 60 * 1000);
        this.orderId = orderId;
    }


    @Override
    public void run() {
        logger.info("系统开始处理延时任务---订单超时未付款---" + this.orderId);
        DgutshopOrderService orderService = BeanUtil.getBean(DgutshopOrderService.class);

        DgutshopOrder order = orderService.findById(this.orderId);
        if(order == null){
            return;
        }
        if(!OrderUtil.isCreateStatus(order)){
            return;
        }

        // 设置订单已取消状态
        order.setOrderStatus(OrderUtil.STATUS_AUTO_CANCEL);
        order.setEndTime(LocalDateTime.now());
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            throw new RuntimeException("更新数据已失效");
        }

        logger.info("系统结束处理延时任务---订单超时未付款---" + this.orderId);
    }
}
