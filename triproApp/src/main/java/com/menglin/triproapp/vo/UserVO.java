package com.menglin.triproapp.vo;

import java.util.Date;

import com.menglin.triproapp.util.Result;

/** 
 * @author CGS 
 * @date 2018年3月22日 下午4:55:49 
 */
public class UserVO {
	
		private Result result;
		
		private Integer uid;
	 
	    private String loginName;

	    private Integer state;

	    private String phoneBelong;

	    private Date loginTime;

	    private Date addTime;


	    private String idCard;

	    private String idCardImg;

	    private String idCardImgB;

	    private String businessImg;
	    
	    private String remark;

		public Result getResult() {
			return result;
		}

		public void setResult(Result result) {
			this.result = result;
		}

		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}

		


		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public Integer getState() {
			return state;
		}

		public void setState(Integer state) {
			this.state = state;
		}

		public String getPhoneBelong() {
			return phoneBelong;
		}

		public void setPhoneBelong(String phoneBelong) {
			this.phoneBelong = phoneBelong;
		}

		public Date getLoginTime() {
			return loginTime;
		}

		public void setLoginTime(Date loginTime) {
			this.loginTime = loginTime;
		}

		public Date getAddTime() {
			return addTime;
		}

		public void setAddTime(Date addTime) {
			this.addTime = addTime;
		}


		public String getIdCard() {
			return idCard;
		}

		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}

		public String getIdCardImg() {
			return idCardImg;
		}

		public void setIdCardImg(String idCardImg) {
			this.idCardImg = idCardImg;
		}

		public String getIdCardImgB() {
			return idCardImgB;
		}

		public void setIdCardImgB(String idCardImgB) {
			this.idCardImgB = idCardImgB;
		}

		public String getBusinessImg() {
			return businessImg;
		}

		public void setBusinessImg(String businessImg) {
			this.businessImg = businessImg;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
	    
	    

}
