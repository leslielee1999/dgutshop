package org.dgutstu.dgutshop.db.service;

import org.dgutstu.dgutshop.db.dao.DgutshopOrderItemMapper;
import org.dgutstu.dgutshop.db.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 9:17 2020/12/16
 */
@Service
public class DgutshopOrderItemService {
    @Resource
    private DgutshopOrderItemMapper orderItemMapper;
    @Autowired
    private DgutshopProductService productService;

    /**
     * 增加一个订单单项
     * @param orderItem
     */
    public void create(DgutshopOrderItem orderItem){
        orderItem.setCreateTime(LocalDateTime.now());
        orderItem.setUpdateTime(LocalDateTime.now());
        orderItemMapper.insertSelective(orderItem);
    }

    /**
     * 删除第id个订单单项
     * @param id
     */
    public void deleteById(Integer id){
        orderItemMapper.logicalDeleteByPrimaryKey(id);
    }


    public void fill(List<DgutshopOrder> orderList){
        for (DgutshopOrder order : orderList) {
            fill(order);
        }
    }

    /**
     * 完成订单与其订单项一对多关系的实现
     * @param order
     */
    public void fill(DgutshopOrder order){
        // 根据分类id找出归属于它的所有分类项
        DgutshopOrderItemExample example = new DgutshopOrderItemExample();
        example.createCriteria().andOidEqualTo(order.getId());
        example.setOrderByClause("id desc");
        List<DgutshopOrderItem> orderItems = orderItemMapper.selectByExample(example);
        //  为所有分类项设置饮品属性
        setProduct(orderItems);
        order.setOrderItemList(orderItems);
    }


    public void setProduct(List<DgutshopOrderItem> orderItems){
        for (DgutshopOrderItem orderItem : orderItems) {
            setProduct(orderItem);
        }
    }

    public void setProduct(DgutshopOrderItem orderItem){
        DgutshopProduct product = productService.get(orderItem.getPid());
        orderItem.setProduct(product);
    }

    /**
     * 获取销量和
     * @param pid
     * @return
     */
    public int getSaleCount(int pid){
        DgutshopOrderItemExample example = new DgutshopOrderItemExample();
        example.createCriteria().andPidEqualTo(pid).andOidIsNotNull();
        //  获取所有订单项
        List<DgutshopOrderItem> orderItems = orderItemMapper.selectByExample(example);
        int result = 0;
        //  计算每个订单项的销量和
        for (DgutshopOrderItem orderItem : orderItems) {
            result += orderItem.getSum();
        }
        return result;
    }

}
