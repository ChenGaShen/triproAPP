package com.menglin.tripro.vo;

public class WeiXinShareInfo {
		private String appId;
		private String timestamp;
		private String nonceStr;
		private String signature;
		private String jsapi_ticket;
		public String getJsapi_ticket() {
			return jsapi_ticket;
		}
		public void setJsapi_ticket(String jsapiTicket) {
			jsapi_ticket = jsapiTicket;
		}
		public String getAppId() {
			return appId;
		}
		public void setAppId(String appId) {
			this.appId = appId;
		}
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public String getNonceStr() {
			return nonceStr;
		}
		public void setNonceStr(String nonceStr) {
			this.nonceStr = nonceStr;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
}
