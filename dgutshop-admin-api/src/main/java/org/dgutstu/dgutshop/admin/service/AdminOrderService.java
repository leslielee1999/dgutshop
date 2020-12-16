package org.dgutstu.dgutshop.admin.service;

import org.dgutstu.dgutshop.core.util.ResponseUtil;
import org.dgutstu.dgutshop.db.service.DgutshopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
}
