package com.menglin.triproapp.entity;

import java.util.Date;

public class ActiveRed {
    private Integer activeid;

    private Integer uid;

    private Double redMoney;

    private Integer redState;

    private Date addTime;

    private String orderId;

    public Integer getActiveid() {
        return activeid;
    }

    public void setActiveid(Integer activeid) {
        this.activeid = activeid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getRedMoney() {
        return redMoney;
    }

    public void setRedMoney(Double redMoney) {
        this.redMoney = redMoney;
    }

    public Integer getRedState() {
        return redState;
    }

    public void setRedState(Integer redState) {
        this.redState = redState;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }
    
    //查询使用
    private String  loginName;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}