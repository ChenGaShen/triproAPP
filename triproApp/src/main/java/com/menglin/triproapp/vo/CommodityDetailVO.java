package com.menglin.triproapp.vo;



public class CommodityDetailVO {
	
    private Integer commodityId;

    private String commodityName;

    private String price;

    private String discountPrice;

    private Integer amount;

    private Integer allowance;

    private String commodityImg;

    private String specification;

    private Integer realSale;

    private Integer virtualSales;

    private Integer state;
    
    private Integer classify;

    private String addTime;

    private String updateTime;

    private String description;

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}



	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}



	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	

	public Integer getRealSale() {
		return realSale;
	}

	public void setRealSale(Integer realSale) {
		this.realSale = realSale;
	}

	public Integer getVirtualSales() {
		return virtualSales;
	}

	public void setVirtualSales(Integer virtualSales) {
		this.virtualSales = virtualSales;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityImg() {
		return commodityImg;
	}

	public void setCommodityImg(String commodityImg) {
		this.commodityImg = commodityImg;
	}

	public Integer getClassify() {
		return classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
	}

   
}