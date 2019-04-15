package com.erp.bean;

import java.math.BigDecimal;
import java.util.Date;

public class CutOutstoragemaster { //分切成品出仓主表
    private String outstorageid;

    private String ordermasterid;

    private String customer;

    private Date outDate;

    private Date orderDate;

    private BigDecimal total;

    private Integer sheetAll;

    private BigDecimal quantityAll;

    public String getOutstorageid() {
        return outstorageid;
    }

    public void setOutstorageid(String outstorageid) {
        this.outstorageid = outstorageid == null ? null : outstorageid.trim();
    }

    public String getOrdermasterid() {
        return ordermasterid;
    }

    public void setOrdermasterid(String ordermasterid) {
        this.ordermasterid = ordermasterid == null ? null : ordermasterid.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getSheetAll() {
        return sheetAll;
    }

    public void setSheetAll(Integer sheetAll) {
        this.sheetAll = sheetAll;
    }

    public BigDecimal getQuantityAll() {
        return quantityAll;
    }

    public void setQuantityAll(BigDecimal quantityAll) {
        this.quantityAll = quantityAll;
    }
}