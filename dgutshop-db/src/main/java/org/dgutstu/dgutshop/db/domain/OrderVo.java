package org.dgutstu.dgutshop.db.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderVo {
    private Integer id;
    private String code;
    private String type;
    private Integer orderStatus;
    private BigDecimal productPrice;
    private BigDecimal deliveryPrice;
    private BigDecimal orderPrice;
    private LocalDateTime createTime;
    private Integer userId;
    private String userName;
    private String consignee;
    private String consigneeAddress;
    private String consigneeRoom;
    private String consigneePhone;
    private String deliveryCompany;
    private String deliveryCode;
    private String message;
    private LocalDateTime payDate;
    private List<DgutshopOrderItem> orderItemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(BigDecimal deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDateTime payDate) {
        this.payDate = payDate;
    }

    public List<DgutshopOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<DgutshopOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", orderStatus=" + orderStatus +
                ", productPrice=" + productPrice +
                ", orderPrice=" + orderPrice +
                ", deliveryPrice=" + deliveryPrice +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", consignee='" + consignee + '\'' +
                ", consigneeAddress='" + consigneeAddress + '\'' +
                ", consigneeRoom='" + consigneeRoom + '\'' +
                ", consigneePhone='" + consigneePhone + '\'' +
                ", deliveryCompany='" + deliveryCompany + '\'' +
                ", deliveryCode='" + deliveryCode + '\'' +
                ", message='" + message + '\'' +
                ", payDate=" + payDate +
                ", orderItemList=" + orderItemList +
                '}';
    }
}
