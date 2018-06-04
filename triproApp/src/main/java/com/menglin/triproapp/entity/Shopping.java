package com.menglin.triproapp.entity;

import java.util.Date;

public class Shopping {
    private Integer id;

    private Integer commodityId;

    private Integer num;

    private Integer uid;

    private Integer state;

    private String ip;

    private String ipAddress;

    private Date addTime;

    private Date updateTime;

    private Integer selected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
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

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }
    
    //查询使用
    private String commodityName;
    private String specification;
    private String commodityImg;
    private String commodityPrice;

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getCommodityImg() {
		return commodityImg;
	}

	public void setCommodityImg(String commodityImg) {
		this.commodityImg = commodityImg;
	}

	public String getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(String commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
    
    
}