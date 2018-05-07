package com.menglin.tripro.vo;

public class TokenJson {
	// 获取到的凭证  
	  private String access_token;  
	   
    // 凭证有效时间，单位：秒  
	  private int expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String accessToken) {
		access_token = accessToken;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expiresIn) {
		expires_in = expiresIn;
	} 
   
	  
}
