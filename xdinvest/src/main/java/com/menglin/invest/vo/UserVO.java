package com.menglin.invest.vo;

import java.util.Date;

import com.menglin.invest.util.Result;

/** 
 * @author CGS 
 * @date 2018年3月22日 下午4:55:49 
 */
public class UserVO {
	
		private Result result;
		
		private Integer userId;
	 
		private String userName;

		private String userPhone;

		private String userPass;

		private String idCard;

		private Date addTime;

		private Integer onState;

		private Integer identity;

		public Result getResult() {
			return result;
		}

		public void setResult(Result result) {
			this.result = result;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserPhone() {
			return userPhone;
		}

		public void setUserPhone(String userPhone) {
			this.userPhone = userPhone;
		}

		public String getUserPass() {
			return userPass;
		}

		public void setUserPass(String userPass) {
			this.userPass = userPass;
		}

		public String getIdCard() {
			return idCard;
		}

		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}

		public Date getAddTime() {
			return addTime;
		}

		public void setAddTime(Date addTime) {
			this.addTime = addTime;
		}

		public Integer getOnState() {
			return onState;
		}

		public void setOnState(Integer onState) {
			this.onState = onState;
		}

		public Integer getIdentity() {
			return identity;
		}

		public void setIdentity(Integer identity) {
			this.identity = identity;
		}
		
		

		
	    

}
