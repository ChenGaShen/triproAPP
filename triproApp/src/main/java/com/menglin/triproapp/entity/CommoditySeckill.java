package com.menglin.triproapp.entity;

import java.util.Date;

public class CommoditySeckill {
    private Integer commodityseckillId;

    private String commodityseckillName;

    private Double seckillPrice;

    private Double seckillDiscountprice;

    private Integer seckillAmount;

    private Integer seckillAllowance;

    private String seckillCommodityimg;

    private String seckillSpecification;

    private Integer seckillRealSale;

    private Integer seckillVirtualSales;

    private Integer seckillOnsale;

    private Integer seckillState;

    private Integer seckillClassify;

    private Date addTime;

    private Date startTime;

    private Date endTime;

    private String seckillDescription;

    public Integer getCommodityseckillId() {
        return commodityseckillId;
    }

    public void setCommodityseckillId(Integer commodityseckillId) {
        this.commodityseckillId = commodityseckillId;
    }

    public String getCommodityseckillName() {
        return commodityseckillName;
    }

    public void setCommodityseckillName(String commodityseckillName) {
        this.commodityseckillName = commodityseckillName == null ? null : commodityseckillName.trim();
    }

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Double getSeckillDiscountprice() {
        return seckillDiscountprice;
    }

    public void setSeckillDiscountprice(Double seckillDiscountprice) {
        this.seckillDiscountprice = seckillDiscountprice;
    }

    public Integer getSeckillAmount() {
        return seckillAmount;
    }

    public void setSeckillAmount(Integer seckillAmount) {
        this.seckillAmount = seckillAmount;
    }

    public Integer getSeckillAllowance() {
        return seckillAllowance;
    }

    public void setSeckillAllowance(Integer seckillAllowance) {
        this.seckillAllowance = seckillAllowance;
    }

    public String getSeckillCommodityimg() {
        return seckillCommodityimg;
    }

    public void setSeckillCommodityimg(String seckillCommodityimg) {
        this.seckillCommodityimg = seckillCommodityimg == null ? null : seckillCommodityimg.trim();
    }

    public String getSeckillSpecification() {
        return seckillSpecification;
    }

    public void setSeckillSpecification(String seckillSpecification) {
        this.seckillSpecification = seckillSpecification == null ? null : seckillSpecification.trim();
    }

    public Integer getSeckillRealSale() {
        return seckillRealSale;
    }

    public void setSeckillRealSale(Integer seckillRealSale) {
        this.seckillRealSale = seckillRealSale;
    }

    public Integer getSeckillVirtualSales() {
        return seckillVirtualSales;
    }

    public void setSeckillVirtualSales(Integer seckillVirtualSales) {
        this.seckillVirtualSales = seckillVirtualSales;
    }

    public Integer getSeckillOnsale() {
        return seckillOnsale;
    }

    public void setSeckillOnsale(Integer seckillOnsale) {
        this.seckillOnsale = seckillOnsale;
    }

    public Integer getSeckillState() {
        return seckillState;
    }

    public void setSeckillState(Integer seckillState) {
        this.seckillState = seckillState;
    }

    public Integer getSeckillClassify() {
        return seckillClassify;
    }

    public void setSeckillClassify(Integer seckillClassify) {
        this.seckillClassify = seckillClassify;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSeckillDescription() {
        return seckillDescription;
    }

    public void setSeckillDescription(String seckillDescription) {
        this.seckillDescription = seckillDescription == null ? null : seckillDescription.trim();
    }
}