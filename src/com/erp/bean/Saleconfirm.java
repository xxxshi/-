package com.erp.bean;

import java.math.BigDecimal;

/**
 * @program: MyErp
 * @description: 销售详情表
 * @author: huang zi chun
 * @create: 2018-12-18 10:40
 */
public class Saleconfirm {
    private String itemid;

    private String ordermasterid;

    private String brand;

    private String papertype;

    private String rank;

    private BigDecimal gweight;

    private String specification;

    private String unit;

    private String producttype;

    private BigDecimal quantity;

    private BigDecimal price;

    private int state;

    private int state_ok;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState_ok() {
        return state_ok;
    }

    public void setState_ok(int state_ok) {
        this.state_ok = state_ok;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid == null ? null : itemid.trim();
    }

    public String getOrdermasterid() {
        return ordermasterid;
    }

    public void setOrdermasterid(String ordermasterid) {
        this.ordermasterid = ordermasterid == null ? null : ordermasterid.trim();
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}