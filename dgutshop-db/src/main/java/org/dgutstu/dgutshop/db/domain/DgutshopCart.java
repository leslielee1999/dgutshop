package org.dgutstu.dgutshop.db.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table dgutshop_cart
 */
public class DgutshopCart {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   用户表的用户ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * Database Column Remarks:
     *   饮品表的饮品ID
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.product_id
     *
     * @mbg.generated
     */
    private Integer productId;

    /**
     * Database Column Remarks:
     *   饮品编号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.product_code
     *
     * @mbg.generated
     */
    private String productCode;

    /**
     * Database Column Remarks:
     *   饮品名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.product_name
     *
     * @mbg.generated
     */
    private String productName;

    /**
     * Database Column Remarks:
     *   饮品的价格
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.price
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     * Database Column Remarks:
     *   饮品的数量
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.number
     *
     * @mbg.generated
     */
    private Short number;

    /**
     * Database Column Remarks:
     *   购物车中饮品品是否选择状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.checked
     *
     * @mbg.generated
     */
    private Boolean checked;

    /**
     * Database Column Remarks:
     *   饮品图片
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.picture
     *
     * @mbg.generated
     */
    private String picture;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     * Database Column Remarks:
     *   更新时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     * Database Column Remarks:
     *   逻辑删除
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_cart.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.id
     *
     * @return the value of dgutshop_cart.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.id
     *
     * @param id the value for dgutshop_cart.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.user_id
     *
     * @return the value of dgutshop_cart.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.user_id
     *
     * @param userId the value for dgutshop_cart.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.product_id
     *
     * @return the value of dgutshop_cart.product_id
     *
     * @mbg.generated
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.product_id
     *
     * @param productId the value for dgutshop_cart.product_id
     *
     * @mbg.generated
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.product_code
     *
     * @return the value of dgutshop_cart.product_code
     *
     * @mbg.generated
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.product_code
     *
     * @param productCode the value for dgutshop_cart.product_code
     *
     * @mbg.generated
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.product_name
     *
     * @return the value of dgutshop_cart.product_name
     *
     * @mbg.generated
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.product_name
     *
     * @param productName the value for dgutshop_cart.product_name
     *
     * @mbg.generated
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.price
     *
     * @return the value of dgutshop_cart.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.price
     *
     * @param price the value for dgutshop_cart.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.number
     *
     * @return the value of dgutshop_cart.number
     *
     * @mbg.generated
     */
    public Short getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.number
     *
     * @param number the value for dgutshop_cart.number
     *
     * @mbg.generated
     */
    public void setNumber(Short number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.checked
     *
     * @return the value of dgutshop_cart.checked
     *
     * @mbg.generated
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.checked
     *
     * @param checked the value for dgutshop_cart.checked
     *
     * @mbg.generated
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.picture
     *
     * @return the value of dgutshop_cart.picture
     *
     * @mbg.generated
     */
    public String getPicture() {
        return picture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.picture
     *
     * @param picture the value for dgutshop_cart.picture
     *
     * @mbg.generated
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.create_time
     *
     * @return the value of dgutshop_cart.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.create_time
     *
     * @param createTime the value for dgutshop_cart.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.update_time
     *
     * @return the value of dgutshop_cart.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.update_time
     *
     * @param updateTime the value for dgutshop_cart.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_cart.deleted
     *
     * @return the value of dgutshop_cart.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_cart.deleted
     *
     * @param deleted the value for dgutshop_cart.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", IS_DELETED=").append(IS_DELETED);
        sb.append(", NOT_DELETED=").append(NOT_DELETED);
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", productId=").append(productId);
        sb.append(", productCode=").append(productCode);
        sb.append(", productName=").append(productName);
        sb.append(", price=").append(price);
        sb.append(", number=").append(number);
        sb.append(", checked=").append(checked);
        sb.append(", picture=").append(picture);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DgutshopCart other = (DgutshopCart) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductCode() == null ? other.getProductCode() == null : this.getProductCode().equals(other.getProductCode()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getChecked() == null ? other.getChecked() == null : this.getChecked().equals(other.getChecked()))
            && (this.getPicture() == null ? other.getPicture() == null : this.getPicture().equals(other.getPicture()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductCode() == null) ? 0 : getProductCode().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getChecked() == null) ? 0 : getChecked().hashCode());
        result = prime * result + ((getPicture() == null) ? 0 : getPicture().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    public enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        private final Boolean value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public Boolean getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public Boolean value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table dgutshop_cart
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        userId("user_id", "userId", "INTEGER", false),
        productId("product_id", "productId", "INTEGER", false),
        productCode("product_code", "productCode", "VARCHAR", false),
        productName("product_name", "productName", "VARCHAR", false),
        price("price", "price", "DECIMAL", false),
        number("number", "number", "SMALLINT", true),
        checked("checked", "checked", "BIT", true),
        picture("picture", "picture", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        deleted("deleted", "deleted", "BIT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_cart
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}