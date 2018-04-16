package com.menglin.invest.entity;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_user"
    ,catalog="xdtz"
)

public class User  implements java.io.Serializable {

	 private static final long serialVersionUID = 1L;
    // Fields    

     private Integer id;
     private String userName;
     private String userPhone;
     private String idCard;
     private Date addTime;
     private Integer onState;
   


    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(String userName, String userPhone, String idCard, Date addTime, Integer onState) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.idCard = idCard;
        this.addTime = addTime;
        this.onState = onState;
       
    }

   
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="user_name", length=155)

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="user_phone", length=155)

    public String getUserPhone() {
        return this.userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    
    @Column(name="id_card", length=155)

    public String getIdCard() {
        return this.idCard;
    }
    
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    
    @Column(name="add_time", length=0)

    public Date getAddTime() {
        return this.addTime;
    }
    
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    
    @Column(name="on_state")

    public Integer getOnState() {
        return this.onState;
    }
    
    public void setOnState(Integer onState) {
        this.onState = onState;
    }

   








}