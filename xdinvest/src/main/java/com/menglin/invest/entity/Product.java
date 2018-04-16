package com.menglin.invest.entity;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_product")
public class Product  implements java.io.Serializable {

	 private static final long serialVersionUID = 1L;
    // Fields    

     private Integer id;
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


    // Constructors

    /** default constructor */
    public Product() {
    }

    
    /** full constructor */
    public Product(String productName, String serialNumber, String investmentThreshold, String timeEither, String investmentOrientation, String capitalType, String fundType, String organizationalForm, String targetScale, String managementFee, String fundManager, String company, String careerBackgroud, Integer onsalNumber, String fundReferred, Float yearIncome, Float accumulatedIncome, Double cumulativeValue, String trendImg, Date addTime, Date updateTime) {
        this.productName = productName;
        this.serialNumber = serialNumber;
        this.investmentThreshold = investmentThreshold;
        this.timeEither = timeEither;
        this.investmentOrientation = investmentOrientation;
        this.capitalType = capitalType;
        this.fundType = fundType;
        this.organizationalForm = organizationalForm;
        this.targetScale = targetScale;
        this.managementFee = managementFee;
        this.fundManager = fundManager;
        this.company = company;
        this.careerBackgroud = careerBackgroud;
        this.onsalNumber = onsalNumber;
        this.fundReferred = fundReferred;
        this.yearIncome = yearIncome;
        this.accumulatedIncome = accumulatedIncome;
        this.cumulativeValue = cumulativeValue;
        this.trendImg = trendImg;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="product_name", length=155)

    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    @Column(name="serial_number", length=155)

    public String getSerialNumber() {
        return this.serialNumber;
    }
    
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    @Column(name="investment_threshold", length=155)

    public String getInvestmentThreshold() {
        return this.investmentThreshold;
    }
    
    public void setInvestmentThreshold(String investmentThreshold) {
        this.investmentThreshold = investmentThreshold;
    }
    
    @Column(name="time_either")

    public String getTimeEither() {
        return this.timeEither;
    }
    
    public void setTimeEither(String timeEither) {
        this.timeEither = timeEither;
    }
    
    @Column(name="investment_orientation", length=155)

    public String getInvestmentOrientation() {
        return this.investmentOrientation;
    }
    
    public void setInvestmentOrientation(String investmentOrientation) {
        this.investmentOrientation = investmentOrientation;
    }
    
    @Column(name="capital_type", length=155)

    public String getCapitalType() {
        return this.capitalType;
    }
    
    public void setCapitalType(String capitalType) {
        this.capitalType = capitalType;
    }
    
    @Column(name="fund_type", length=155)

    public String getFundType() {
        return this.fundType;
    }
    
    public void setFundType(String fundType) {
        this.fundType = fundType;
    }
    
    @Column(name="organizational_form")

    public String getOrganizationalForm() {
        return this.organizationalForm;
    }
    
    public void setOrganizationalForm(String organizationalForm) {
        this.organizationalForm = organizationalForm;
    }
    
    @Column(name="target_scale")

    public String getTargetScale() {
        return this.targetScale;
    }
    
    public void setTargetScale(String targetScale) {
        this.targetScale = targetScale;
    }
    
    @Column(name="management_fee")

    public String getManagementFee() {
        return this.managementFee;
    }
    
    public void setManagementFee(String managementFee) {
        this.managementFee = managementFee;
    }
    
    @Column(name="fund_manager")

    public String getFundManager() {
        return this.fundManager;
    }
    
    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }
    
    @Column(name="company")

    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    @Column(name="career_backgroud")

    public String getCareerBackgroud() {
        return this.careerBackgroud;
    }
    
    public void setCareerBackgroud(String careerBackgroud) {
        this.careerBackgroud = careerBackgroud;
    }
    
    @Column(name="onsal_number")

    public Integer getOnsalNumber() {
        return this.onsalNumber;
    }
    
    public void setOnsalNumber(Integer onsalNumber) {
        this.onsalNumber = onsalNumber;
    }
    
    @Column(name="fund_referred")

    public String getFundReferred() {
        return this.fundReferred;
    }
    
    public void setFundReferred(String fundReferred) {
        this.fundReferred = fundReferred;
    }
    
    @Column(name="year_income", precision=11)

    public Float getYearIncome() {
        return this.yearIncome;
    }
    
    public void setYearIncome(Float yearIncome) {
        this.yearIncome = yearIncome;
    }
    
    @Column(name="accumulated_income", precision=11)

    public Float getAccumulatedIncome() {
        return this.accumulatedIncome;
    }
    
    public void setAccumulatedIncome(Float accumulatedIncome) {
        this.accumulatedIncome = accumulatedIncome;
    }
    
    @Column(name="cumulative_value", precision=11, scale=4)

    public Double getCumulativeValue() {
        return this.cumulativeValue;
    }
    
    public void setCumulativeValue(Double cumulativeValue) {
        this.cumulativeValue = cumulativeValue;
    }
    
    @Column(name="trend_img")

    public String getTrendImg() {
        return this.trendImg;
    }
    
    public void setTrendImg(String trendImg) {
        this.trendImg = trendImg;
    }
    
    @Column(name="add_time", length=0)
    public Date getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    
    @Column(name="update_time", length=0)

    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
   








}