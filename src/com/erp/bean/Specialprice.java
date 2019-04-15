package com.erp.bean;

/**
 * @program: MyErp
 * @description: 特价表
 * @author: huang zi chun
 * @create: 2018-12-18 22:55
 */
import java.math.BigDecimal;
import java.util.Date;

public class Specialprice {
    private String specialid;

    private String customer;

    private String itemid;

    private Date affirmdate;

    private String brand;

    private String papertype;

    private String rank;

    private BigDecimal gweight;

    private String specification;

    private String unit;

    private String producttype;

    private BigDecimal price;

    public String getSpecialid() {
        return specialid;
    }

    public void setSpecialid(String specialid) {
        this.specialid = specialid == null ? null : specialid.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid == null ? null : itemid.trim();
    }

    public Date getAffirmdate() {
        return affirmdate;
    }

    public void setAffirmdate(Date affirmdate) {
        this.affirmdate = affirmdate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getPapertype() {
        return papertype;
    }

    public void setPapertype(String papertype) {
        this.papertype = papertype == null ? null : papertype.trim();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    public BigDecimal getGweight() {
        return gweight;
    }

    public void setGweight(BigDecimal gweight) {
        this.gweight = gweight;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype == null ? null : producttype.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
