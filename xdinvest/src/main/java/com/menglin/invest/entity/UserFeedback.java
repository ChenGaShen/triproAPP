package com.menglin.invest.entity;

import java.util.Date;

public class UserFeedback {
	
    private Integer userFedId;

    private String userPhone;

    private String contentFeedback;

    private String versions;

    private String equipment;

    private Integer sign;

    private Date addTime;

    public Integer getUserFedId() {
        return userFedId;
    }

    public void setUserFedId(Integer userFedId) {
        this.userFedId = userFedId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getContentFeedback() {
        return contentFeedback;
    }

    public void setContentFeedback(String contentFeedback) {
        this.contentFeedback = contentFeedback == null ? null : contentFeedback.trim();
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions == null ? null : versions.trim();
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment == null ? null : equipment.trim();
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}