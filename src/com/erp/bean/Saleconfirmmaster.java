package com.erp.bean;

/**
 * @program: MyErp
 * @description: 销售单总表
 * @author: huang zi chun
 * @create: 2018-12-18 10:40
 */

import java.math.BigDecimal;
import java.util.Date;

public class Saleconfirmmaster {
    private String ordermasterid;

    private Date saledate;

    private Date suredate;

    private String buy;

    private String sale;

    private BigDecimal priceCut;

    private BigDecimal total;

    private int state;

    public int getState_ok() {
        return state_ok;
    }

    public void setState_ok(int state_ok) {
        this.state_ok = state_ok;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    private int state_ok;

    public String getOrdermasterid() {
        return ordermasterid;
    }

    public void setOrdermasterid(String ordermasterid) {
        this.ordermasterid = ordermasterid == null ? null : ordermasterid.trim();
    }

    public Date getSaledate() {
        return saledate;
    }

    public void setSaledate(Date saledate) {
        this.saledate = saledate;
    }

    public Date getSuredate() {
        return suredate;
    }

    public void setSuredate(Date suredate) {
        this.suredate = suredate;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy == null ? null : buy.trim();
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale == null ? null : sale.trim();
    }

    public BigDecimal getPriceCut() {
        return priceCut;
    }

    public void setPriceCut(BigDecimal priceCut) {
        this.priceCut = priceCut;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}