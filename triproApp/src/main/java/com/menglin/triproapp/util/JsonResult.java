
package com.menglin.triproapp.util;

public class JsonResult {
	 private String resultcode;
	  private Object result;
	  private String reason;
	  private Integer error_code;

	  public String getResultcode()
	  {
	    return this.resultcode;
	  }
	  public void setResultcode(String resultcode) {
	    this.resultcode = resultcode;
	  }
	  public Object getResult() {
	    return this.result;
	  }
	  public void setResult(Object result) {
	    this.result = result;
	  }
	  public String getReason() {
	    return this.reason;
	  }
	  public void setReason(String reason) {
	    this.reason = reason;
	  }
	  public Integer getError_code() {
	    return this.error_code;
	  }
	  public void setError_code(Integer error_code) {
	    this.error_code = error_code;
	  }
}
