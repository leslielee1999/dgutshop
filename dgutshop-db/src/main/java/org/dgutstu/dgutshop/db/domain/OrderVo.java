package org.dgutstu.dgutshop.db.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderVo {
    private Integer id;
    private String code;
    private Byte type;
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
    private String takeCode;
    private LocalDateTime endTime;
    private LocalDateTime payDate;
    private List<DgutshopOrderItem> orderItemList;
    private String shopName;
    private String shopAddress;
}
