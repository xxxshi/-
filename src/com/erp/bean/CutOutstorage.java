package com.erp.bean;

import java.math.BigDecimal;

public class CutOutstorage { //分切成品出仓明细表
    private String ordermasterid;

    private String brand;

    private String papertype;

    private String rank;

    private BigDecimal gweight;

    private String specification;

    private String unit;

    private BigDecimal quantity;

    private Integer sheetNum;

    private BigDecimal price;

    private BigDecimal priceSingle;

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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getSheetNum() {
        return sheetNum;
    }

    public void setSheetNum(Integer sheetNum) {
        this.sheetNum = sheetNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceSingle() {
        return priceSingle;
    }

    public void setPriceSingle(BigDecimal priceSingle) {
        this.priceSingle = priceSingle;
    }
}