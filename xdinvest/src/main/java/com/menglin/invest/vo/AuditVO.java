package com.menglin.invest.vo;

import com.menglin.invest.util.Result;

/** 
 * @author CGS 
 * @date 2018年3月13日 上午11:08:39 
 */
public class AuditVO {
		private Result result;
		
		private Integer uid;
		
		private Integer audit;

	    private String idCard;

	    private String idCardImg;

	    private String idCardImgB;

	    private String businessImg;

		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}

		public Integer getAudit() {
			return audit;
		}

		public void setAudit(Integer audit) {
			this.audit = audit;
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

		public Result getResult() {
			return result;
		}

		public void setResult(Result result) {
			this.result = result;
		}
	    
	    
}
