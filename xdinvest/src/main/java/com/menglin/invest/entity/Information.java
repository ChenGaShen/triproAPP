package com.menglin.invest.entity;

import java.util.Date;

public class Information {
	
    private Integer newsId;

    private String newsName;

    private Integer newsClassification;
    
    private Integer newsState;

    private Date addTime;

    private Date updateTime;

    private String newsContent;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName == null ? null : newsName.trim();
    }

    public Integer getNewsClassification() {
        return newsClassification;
    }

    public void setNewsClassification(Integer newsClassification) {
        this.newsClassification = newsClassification;
    }
    
    
    public Integer getNewsState() {
		return newsState;
	}

	public void setNewsState(Integer newsState) {
		this.newsState = newsState;
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

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent == null ? null : newsContent.trim();
    }
}