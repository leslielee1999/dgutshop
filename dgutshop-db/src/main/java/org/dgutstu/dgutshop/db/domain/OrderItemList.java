package org.dgutstu.dgutshop.db.domain;

import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 11:04 2020/12/17
 */
public class OrderItemList {

    private List<DgutshopOrderItem> dgutshopOrderItemList;

    public List<DgutshopOrderItem> getDgutshopOrderItemList() {
        return dgutshopOrderItemList;
    }

    public void setDgutshopOrderItemList(List<DgutshopOrderItem> dgutshopOrderItemList) {
        this.dgutshopOrderItemList = dgutshopOrderItemList;
    }

    @Override
    public String toString() {
        return "OrderItemList{" +
                "dgutshopOrderItemList=" + dgutshopOrderItemList +
                '}';
    }
}
