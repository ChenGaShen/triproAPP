package com.menglin.tripro.entity;

public class Adress {
    private Integer id;

    private Integer uid;

    private String receivedPhone;

    private String receivedName;

    private String receivedProvince;

    private String receivedCity;

    private String receivedCanton;

    private String receivedDetail;

    private Integer isDefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getReceivedPhone() {
        return receivedPhone;
    }

    public void setReceivedPhone(String receivedPhone) {
        this.receivedPhone = receivedPhone == null ? null : receivedPhone.trim();
    }

    public String getReceivedName() {
        return receivedName;
    }

    public void setReceivedName(String receivedName) {
        this.receivedName = receivedName == null ? null : receivedName.trim();
    }

    public String getReceivedProvince() {
        return receivedProvince;
    }

    public void setReceivedProvince(String receivedProvince) {
        this.receivedProvince = receivedProvince == null ? null : receivedProvince.trim();
    }

    public String getReceivedCity() {
        return receivedCity;
    }

    public void setReceivedCity(String receivedCity) {
        this.receivedCity = receivedCity == null ? null : receivedCity.trim();
    }

    public String getReceivedCanton() {
        return receivedCanton;
    }

    public void setReceivedCanton(String receivedCanton) {
        this.receivedCanton = receivedCanton == null ? null : receivedCanton.trim();
    }

    public String getReceivedDetail() {
        return receivedDetail;
    }

    public void setReceivedDetail(String receivedDetail) {
        this.receivedDetail = receivedDetail == null ? null : receivedDetail.trim();
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}