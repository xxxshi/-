package com.erp.bean;

/**
 * @program: MyErp
 * @description: 订单表
 * @author: huang zi chun
 * @create: 2018-12-18 17:01
 */
import java.util.Date;

public class Ordermaster {
    private String ordermasterid;

    private Date ordertime;

    private Date suretime;

    private String customer;

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOrdermasterid() {
        return ordermasterid;
    }

    public void setOrdermasterid(String ordermasterid) {
        this.ordermasterid = ordermasterid == null ? null : ordermasterid.trim();
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Date getSuretime() {
        return suretime;
    }

    public void setSuretime(Date suretime) {
        this.suretime = suretime;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }
}
