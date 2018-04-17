package com.menglin.invest.entity;

import java.util.Date;

public class Product {
	
    private Integer productId;

    private String productName;

    private String serialNumber;

    private String investmentThreshold;

    private String timeEither;

    private String investmentOrientation;

    private String capitalType;

    private String fundType;

    private String organizationalForm;

    private String targetScale;

    private String managementFee;

    private String fundManager;

    private String company;

    private String careerBackgroud;

    private Integer onsalNumber;

    private String fundReferred;

    private Float yearIncome;

    private Float accumulatedIncome;

    private Double cumulativeValue;

    private String trendImg;

    private Date addTime;

    private Date updateTime;

    private Integer productState;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getInvestmentThreshold() {
        return investmentThreshold;
    }

    public void setInvestmentThreshold(String investmentThreshold) {
        this.investmentThreshold = investmentThreshold == null ? null : investmentThreshold.trim();
    }

    public String getTimeEither() {
        return timeEither;
    }

    public void setTimeEither(String timeEither) {
        this.timeEither = timeEither == null ? null : timeEither.trim();
    }

    public String getInvestmentOrientation() {
        return investmentOrientation;
    }

    public void setInvestmentOrientation(String investmentOrientation) {
        this.investmentOrientation = investmentOrientation == null ? null : investmentOrientation.trim();
    }

    public String getCapitalType() {
        return capitalType;
    }

    public void setCapitalType(String capitalType) {
        this.capitalType = capitalType == null ? null : capitalType.trim();
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType == null ? null : fundType.trim();
    }

    public String getOrganizationalForm() {
        return organizationalForm;
    }

    public void setOrganizationalForm(String organizationalForm) {
        this.organizationalForm = organizationalForm == null ? null : organizationalForm.trim();
    }

    public String getTargetScale() {
        return targetScale;
    }

    public void setTargetScale(String targetScale) {
        this.targetScale = targetScale == null ? null : targetScale.trim();
    }

    public String getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(String managementFee) {
        this.managementFee = managementFee == null ? null : managementFee.trim();
    }

    public String getFundManager() {
        return fundManager;
    }

    public void setFundManager(String fundManager) {
        this.fundManager = fundManager == null ? null : fundManager.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCareerBackgroud() {
        return careerBackgroud;
    }

    public void setCareerBackgroud(String careerBackgroud) {
        this.careerBackgroud = careerBackgroud == null ? null : careerBackgroud.trim();
    }

    public Integer getOnsalNumber() {
        return onsalNumber;
    }

    public void setOnsalNumber(Integer onsalNumber) {
        this.onsalNumber = onsalNumber;
    }

    public String getFundReferred() {
        return fundReferred;
    }

    public void setFundReferred(String fundReferred) {
        this.fundReferred = fundReferred == null ? null : fundReferred.trim();
    }

    public Float getYearIncome() {
        return yearIncome;
    }

    public void setYearIncome(Float yearIncome) {
        this.yearIncome = yearIncome;
    }

    public Float getAccumulatedIncome() {
        return accumulatedIncome;
    }

    public void setAccumulatedIncome(Float accumulatedIncome) {
        this.accumulatedIncome = accumulatedIncome;
    }

    public Double getCumulativeValue() {
        return cumulativeValue;
    }

    public void setCumulativeValue(Double cumulativeValue) {
        this.cumulativeValue = cumulativeValue;
    }

    public String getTrendImg() {
        return trendImg;
    }

    public void setTrendImg(String trendImg) {
        this.trendImg = trendImg == null ? null : trendImg.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }
}