package org.dgutstu.dgutshop.db.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table dgutshop_product
 */
public class DgutshopProduct {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dgutshop_product
     *
     * @mbg.generated
     */
    public static final Boolean IS_DELETED = Deleted.IS_DELETED.value();

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dgutshop_product
     *
     * @mbg.generated
     */
    public static final Boolean NOT_DELETED = Deleted.NOT_DELETED.value();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * Database Column Remarks:
     *   饮品编号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.code
     *
     * @mbg.generated
     */
    private String code;

    /**
     * Database Column Remarks:
     *   饮品名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * Database Column Remarks:
     *   大杯原价
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.big_original_price
     *
     * @mbg.generated
     */
    private BigDecimal bigOriginalPrice;

    /**
     * Database Column Remarks:
     *   大杯折扣价

     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.big_promote_price
     *
     * @mbg.generated
     */
    private BigDecimal bigPromotePrice;

    /**
     * Database Column Remarks:
     *   中杯原价
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.medium_original_price
     *
     * @mbg.generated
     */
    private BigDecimal mediumOriginalPrice;

    /**
     * Database Column Remarks:
     *   中杯折扣价
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.medium_promote_price
     *
     * @mbg.generated
     */
    private BigDecimal mediumPromotePrice;

    /**
     * Database Column Remarks:
     *   饮品描述
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     * Database Column Remarks:
     *   是否上架【0否 1是】
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.status
     *
     * @mbg.generated
     */
    private Boolean status;

    /**
     * Database Column Remarks:
     *   饮品小图片url
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.picture
     *
     * @mbg.generated
     */
    private String picture;

    /**
     * Database Column Remarks:
     *   饮品详情图片url
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.picture_detail
     *
     * @mbg.generated
     */
    private String pictureDetail;

    /**
     * Database Column Remarks:
     *   销量
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.sales
     *
     * @mbg.generated
     */
    private Integer sales;

    /**
     * Database Column Remarks:
     *   创建时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     * Database Column Remarks:
     *   修改时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     * Database Column Remarks:
     *   是否可制作为热饮【0否 1是】
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.heated
     *
     * @mbg.generated
     */
    private Boolean heated;

    /**
     * Database Column Remarks:
     *   逻辑删除
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dgutshop_product.deleted
     *
     * @mbg.generated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.id
     *
     * @return the value of dgutshop_product.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.id
     *
     * @param id the value for dgutshop_product.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.code
     *
     * @return the value of dgutshop_product.code
     *
     * @mbg.generated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.code
     *
     * @param code the value for dgutshop_product.code
     *
     * @mbg.generated
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.name
     *
     * @return the value of dgutshop_product.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.name
     *
     * @param name the value for dgutshop_product.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.big_original_price
     *
     * @return the value of dgutshop_product.big_original_price
     *
     * @mbg.generated
     */
    public BigDecimal getBigOriginalPrice() {
        return bigOriginalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.big_original_price
     *
     * @param bigOriginalPrice the value for dgutshop_product.big_original_price
     *
     * @mbg.generated
     */
    public void setBigOriginalPrice(BigDecimal bigOriginalPrice) {
        this.bigOriginalPrice = bigOriginalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.big_promote_price
     *
     * @return the value of dgutshop_product.big_promote_price
     *
     * @mbg.generated
     */
    public BigDecimal getBigPromotePrice() {
        return bigPromotePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.big_promote_price
     *
     * @param bigPromotePrice the value for dgutshop_product.big_promote_price
     *
     * @mbg.generated
     */
    public void setBigPromotePrice(BigDecimal bigPromotePrice) {
        this.bigPromotePrice = bigPromotePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.medium_original_price
     *
     * @return the value of dgutshop_product.medium_original_price
     *
     * @mbg.generated
     */
    public BigDecimal getMediumOriginalPrice() {
        return mediumOriginalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.medium_original_price
     *
     * @param mediumOriginalPrice the value for dgutshop_product.medium_original_price
     *
     * @mbg.generated
     */
    public void setMediumOriginalPrice(BigDecimal mediumOriginalPrice) {
        this.mediumOriginalPrice = mediumOriginalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.medium_promote_price
     *
     * @return the value of dgutshop_product.medium_promote_price
     *
     * @mbg.generated
     */
    public BigDecimal getMediumPromotePrice() {
        return mediumPromotePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.medium_promote_price
     *
     * @param mediumPromotePrice the value for dgutshop_product.medium_promote_price
     *
     * @mbg.generated
     */
    public void setMediumPromotePrice(BigDecimal mediumPromotePrice) {
        this.mediumPromotePrice = mediumPromotePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.description
     *
     * @return the value of dgutshop_product.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.description
     *
     * @param description the value for dgutshop_product.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.status
     *
     * @return the value of dgutshop_product.status
     *
     * @mbg.generated
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.status
     *
     * @param status the value for dgutshop_product.status
     *
     * @mbg.generated
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.picture
     *
     * @return the value of dgutshop_product.picture
     *
     * @mbg.generated
     */
    public String getPicture() {
        return picture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.picture
     *
     * @param picture the value for dgutshop_product.picture
     *
     * @mbg.generated
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.picture_detail
     *
     * @return the value of dgutshop_product.picture_detail
     *
     * @mbg.generated
     */
    public String getPictureDetail() {
        return pictureDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.picture_detail
     *
     * @param pictureDetail the value for dgutshop_product.picture_detail
     *
     * @mbg.generated
     */
    public void setPictureDetail(String pictureDetail) {
        this.pictureDetail = pictureDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.sales
     *
     * @return the value of dgutshop_product.sales
     *
     * @mbg.generated
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.sales
     *
     * @param sales the value for dgutshop_product.sales
     *
     * @mbg.generated
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.create_time
     *
     * @return the value of dgutshop_product.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.create_time
     *
     * @param createTime the value for dgutshop_product.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.update_time
     *
     * @return the value of dgutshop_product.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.update_time
     *
     * @param updateTime the value for dgutshop_product.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.heated
     *
     * @return the value of dgutshop_product.heated
     *
     * @mbg.generated
     */
    public Boolean getHeated() {
        return heated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.heated
     *
     * @param heated the value for dgutshop_product.heated
     *
     * @mbg.generated
     */
    public void setHeated(Boolean heated) {
        this.heated = heated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_product
     *
     * @mbg.generated
     */
    public void andLogicalDeleted(boolean deleted) {
        setDeleted(deleted ? Deleted.IS_DELETED.value() : Deleted.NOT_DELETED.value());
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dgutshop_product.deleted
     *
     * @return the value of dgutshop_product.deleted
     *
     * @mbg.generated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dgutshop_product.deleted
     *
     * @param deleted the value for dgutshop_product.deleted
     *
     * @mbg.generated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_product
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", bigOriginalPrice=").append(bigOriginalPrice);
        sb.append(", bigPromotePrice=").append(bigPromotePrice);
        sb.append(", mediumOriginalPrice=").append(mediumOriginalPrice);
        sb.append(", mediumPromotePrice=").append(mediumPromotePrice);
        sb.append(", description=").append(description);
        sb.append(", status=").append(status);
        sb.append(", picture=").append(picture);
        sb.append(", pictureDetail=").append(pictureDetail);
        sb.append(", sales=").append(sales);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", heated=").append(heated);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_product
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
        DgutshopProduct other = (DgutshopProduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getBigOriginalPrice() == null ? other.getBigOriginalPrice() == null : this.getBigOriginalPrice().equals(other.getBigOriginalPrice()))
            && (this.getBigPromotePrice() == null ? other.getBigPromotePrice() == null : this.getBigPromotePrice().equals(other.getBigPromotePrice()))
            && (this.getMediumOriginalPrice() == null ? other.getMediumOriginalPrice() == null : this.getMediumOriginalPrice().equals(other.getMediumOriginalPrice()))
            && (this.getMediumPromotePrice() == null ? other.getMediumPromotePrice() == null : this.getMediumPromotePrice().equals(other.getMediumPromotePrice()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPicture() == null ? other.getPicture() == null : this.getPicture().equals(other.getPicture()))
            && (this.getPictureDetail() == null ? other.getPictureDetail() == null : this.getPictureDetail().equals(other.getPictureDetail()))
            && (this.getSales() == null ? other.getSales() == null : this.getSales().equals(other.getSales()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getHeated() == null ? other.getHeated() == null : this.getHeated().equals(other.getHeated()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dgutshop_product
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getBigOriginalPrice() == null) ? 0 : getBigOriginalPrice().hashCode());
        result = prime * result + ((getBigPromotePrice() == null) ? 0 : getBigPromotePrice().hashCode());
        result = prime * result + ((getMediumOriginalPrice() == null) ? 0 : getMediumOriginalPrice().hashCode());
        result = prime * result + ((getMediumPromotePrice() == null) ? 0 : getMediumPromotePrice().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPicture() == null) ? 0 : getPicture().hashCode());
        result = prime * result + ((getPictureDetail() == null) ? 0 : getPictureDetail().hashCode());
        result = prime * result + ((getSales() == null) ? 0 : getSales().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getHeated() == null) ? 0 : getHeated().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table dgutshop_product
     *
     * @mbg.generated
     */
    public enum Deleted {
        NOT_DELETED(new Boolean("0"), "未删除"),
        IS_DELETED(new Boolean("1"), "已删除");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        private final Boolean value;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        private final String name;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        Deleted(Boolean value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        public Boolean getValue() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        public Boolean value() {
            return this.value;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        public String getName() {
            return this.name;
        }
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table dgutshop_product
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        code("code", "code", "VARCHAR", false),
        name("name", "name", "VARCHAR", true),
        bigOriginalPrice("big_original_price", "bigOriginalPrice", "DECIMAL", false),
        bigPromotePrice("big_promote_price", "bigPromotePrice", "DECIMAL", false),
        mediumOriginalPrice("medium_original_price", "mediumOriginalPrice", "DECIMAL", false),
        mediumPromotePrice("medium_promote_price", "mediumPromotePrice", "DECIMAL", false),
        description("description", "description", "VARCHAR", false),
        status("status", "status", "BIT", true),
        picture("picture", "picture", "VARCHAR", false),
        pictureDetail("picture_detail", "pictureDetail", "VARCHAR", false),
        sales("sales", "sales", "INTEGER", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        heated("heated", "heated", "BIT", false),
        deleted("deleted", "deleted", "BIT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
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
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table dgutshop_product
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
         * This method corresponds to the database table dgutshop_product
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
         * This method corresponds to the database table dgutshop_product
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}