package org.dgutstu.dgutshop.db.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 20:59 2020/12/19
 */
public class OrderListVo {
//    private Integer userId;
    private Byte type;
    private BigDecimal productPrice;
    private BigDecimal deliveryPrice;
    private BigDecimal orderPrice;
    private String consignee;
    private String consigneeAddress;
    private String consigneeRoom;
    private String consigneePhone;
    private String message;
    private String shopName;
    private String shopAddress;
    private String note;
    private List<DgutshopOrderItem> orderItemList;

    @Override
    public String toString() {
        return "OrderListVo{" +
                "type=" + type +
                ", productPrice=" + productPrice +
                ", deliveryPrice=" + deliveryPrice +
                ", orderPrice=" + orderPrice +
                ", consignee='" + consignee + '\'' +
                ", consigneeAddress='" + consigneeAddress + '\'' +
                ", consigneeRoom='" + consigneeRoom + '\'' +
                ", consigneePhone='" + consigneePhone + '\'' +
                ", message='" + message + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", note='" + note + '\'' +
                ", orderItemList=" + orderItemList +
                '}';
    }

//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeRoom() {
        return consigneeRoom;
    }

    public void setConsigneeRoom(String consigneeRoom) {
        this.consigneeRoom = consigneeRoom;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DgutshopOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<DgutshopOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
