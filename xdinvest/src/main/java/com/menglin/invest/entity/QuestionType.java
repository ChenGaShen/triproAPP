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
 * QuestionType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_question_type"
    ,catalog="xdtz"
)

public class QuestionType  implements java.io.Serializable {

	 private static final long serialVersionUID = 1L;
    // Fields    

     private Integer id;
     private String categoryName;
     private Date addTime;


    // Constructors

    /** default constructor */
    public QuestionType() {
    }

    
    /** full constructor */
    public QuestionType(String categoryName, Date addTime) {
        this.categoryName = categoryName;
        this.addTime = addTime;
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
    
    @Column(name="category_name")

    public String getCategoryName() {
        return this.categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    @Column(name="add_time", length=0)

    public Date getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
   








}