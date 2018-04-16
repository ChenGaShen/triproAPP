package com.menglin.invest.entity;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Questions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_questions"
    ,catalog="xdtz"
)

public class Questions  implements java.io.Serializable {

	 private static final long serialVersionUID = 1L;
    // Fields    

     private Integer id;
     private String questionClassification;
     private String questionContent;
     private Integer questionState;
     private Date updateTime;


    // Constructors

    /** default constructor */
    public Questions() {
    }

    
    /** full constructor */
    public Questions(String questionClassification, String questionContent, Integer questionState, Date updateTime) {
        this.questionClassification = questionClassification;
        this.questionContent = questionContent;
        this.questionState = questionState;
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
    
    @Column(name="question_classification")

    public String getQuestionClassification() {
        return this.questionClassification;
    }
    
    public void setQuestionClassification(String questionClassification) {
        this.questionClassification = questionClassification;
    }
    
    @Column(name="question_content")

    public String getQuestionContent() {
        return this.questionContent;
    }
    
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
    
    @Column(name="question_state")

    public Integer getQuestionState() {
        return this.questionState;
    }
    
    public void setQuestionState(Integer questionState) {
        this.questionState = questionState;
    }
    
    @Column(name="update_time", length=0)

    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
   








}