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
 * UserFeedBack entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_user_feedback"
    ,catalog="xdtz"
)

public class UserFeedBack  implements java.io.Serializable {

	 private static final long serialVersionUID = 1L;
    // Fields    

     private Integer id;
     private String userPhone;
     private String contentFeedback;
     private String versions;
     private String equipment;
     private Integer sign;
     private Date addTime;


    // Constructors

    /** default constructor */
    public UserFeedBack() {
    }

    
    /** full constructor */
    public UserFeedBack(String userPhone, String contentFeedback, String versions, String equipment, Integer sign, Date addTime) {
        this.userPhone = userPhone;
        this.contentFeedback = contentFeedback;
        this.versions = versions;
        this.equipment = equipment;
        this.sign = sign;
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
    
    @Column(name="user_phone")

    public String getUserPhone() {
        return this.userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    
    @Column(name="content_feedback")

    public String getContentFeedback() {
        return this.contentFeedback;
    }
    
    public void setContentFeedback(String contentFeedback) {
        this.contentFeedback = contentFeedback;
    }
    
    @Column(name="versions")

    public String getVersions() {
        return this.versions;
    }
    
    public void setVersions(String versions) {
        this.versions = versions;
    }
    
    @Column(name="equipment")

    public String getEquipment() {
        return this.equipment;
    }
    
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    
    @Column(name="sign")

    public Integer getSign() {
        return this.sign;
    }
    
    public void setSign(Integer sign) {
        this.sign = sign;
    }
    
    @Column(name="add_time", length=0)

    public Date getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
   








}