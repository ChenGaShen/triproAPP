package com.menglin.invest.entity;

import java.util.Date;

public class Questions {
    private Integer questionId;

    private Integer questionClassification;

    private String questionContent;

    private String questionAnswer;

    private Integer questionState;

    private Date updateTime;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

  

    public Integer getQuestionClassification() {
		return questionClassification;
	}

	public void setQuestionClassification(Integer questionClassification) {
		this.questionClassification = questionClassification;
	}

	public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent == null ? null : questionContent.trim();
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer == null ? null : questionAnswer.trim();
    }

    public Integer getQuestionState() {
        return questionState;
    }

    public void setQuestionState(Integer questionState) {
        this.questionState = questionState;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}