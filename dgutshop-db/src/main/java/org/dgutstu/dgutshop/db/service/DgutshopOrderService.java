package org.dgutstu.dgutshop.db.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.dgutstu.dgutshop.db.dao.DgutshopOrderMapper;
import org.dgutstu.dgutshop.db.dao.OrderMapper;
import org.dgutstu.dgutshop.db.domain.DgutshopCategory;
import org.dgutstu.dgutshop.db.domain.DgutshopCategoryExample;
import org.dgutstu.dgutshop.db.domain.DgutshopOrderExample;
import org.dgutstu.dgutshop.db.domain.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 9:17 2020/12/16
 */
@Service
public class DgutshopOrderService {
    @Resource
    DgutshopOrderMapper dgutshopOrderMapperorderMapper;
    @Resource
    private OrderMapper orderMapper;
    @Autowired
    DgutshopUserService userService;


    public Map<String, Object> queryVoSelective(String nickname, String consignee, String code, LocalDateTime start,
                                                LocalDateTime end, List<Short> orderStatusArray, Integer page, Integer limit,
                                                String sort, String order){
        List<String> querys = new ArrayList<>(4);
        if (!StringUtils.isEmpty(nickname)){
            querys.add(" u.nickname like '%" + nickname + "%' ");
        }
        if (!StringUtils.isEmpty(consignee)){
            querys.add(" o.consignee like '%" + consignee + "%' ");
        }
        if (!StringUtils.isEmpty(code)){
            System.out.println(code);
            querys.add(" o.code like '%" + code + "%' ");
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (start != null) {
            querys.add(" o.create_time >= '" + df.format(start) + "' ");
        }
        if (end != null) {
            querys.add(" o.end_time < '" + df.format(end) + "' ");
        }
        if (orderStatusArray != null && orderStatusArray.size() > 0) {
            querys.add(" o.order_status in (" + StringUtils.collectionToDelimitedString(orderStatusArray, ",") + ") ");
        }
        querys.add(" o.deleted = 0 and oi.deleted = 0 ");
        String query = StringUtils.collectionToDelimitedString(querys, "and");
        String orderByClause = null;
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            orderByClause = "o." + sort + " " + order +", o.id desc ";
        }

        PageHelper.startPage(page, limit);
        List<Map> list1 = orderMapper.getOrderIds(query, orderByClause);
        List<Integer> ids = new ArrayList<>();
        System.out.println(list1.toString());
        for (Map map : list1) {
            Integer id = (Integer) map.get("id");
            ids.add(id);
        }

        List<OrderVo> list2 = new ArrayList<>();
        if (!ids.isEmpty()){
            querys.add(" o.id in (" + StringUtils.collectionToDelimitedString(ids, ",") + ") ");
            query = StringUtils.collectionToDelimitedString(querys, "and");
            list2 = orderMapper.getOrderList(query, orderByClause);
        }else {
            System.out.println("null~~");
        }
        Map<String, Object> data = new HashMap<>(5);
        data.put("list", list2);
        data.put("total", list1.size());
        data.put("page", page);
        data.put("limit", limit);
//        data.put("pages", list1.getPages());
        return data;
    }
}
