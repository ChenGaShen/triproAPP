package com.menglin.triproapp.util;

public class Result {
	
	  private int status;// 0操作成功 1操作失败
	  private String msg;
	  private static volatile Result result = new Result();
	  
	  private static Result out(String msg)
	  {
	    result.setMsg(msg);
	    return result;
	  }

	  public static Result suc(String msg)
	  {
	    result.setStatus(0);
	    return out(msg);
	  }

	  public static Result fal(String msg)
	  {
	    result.setStatus(1);
	    return out(msg);
	  }
	  
	  public Result()
	  {
	  }

	  public Result(int status)
	  {
	    this.status = status;
	    this.msg = (status == 0 ? "操作成功" : "操作失败");
	  }

	  public Result(int status, String msg) {
	    this.status = status;
	    this.msg = msg;
	  }

	  public int getStatus()
	  {
	    return this.status;
	  }

	  public void setStatus(int status) {
	    this.status = status;
	  }

	  public String getMsg() {
	    return this.msg;
	  }

	  public void setMsg(String msg) {
	    this.msg = msg;
	  }
}
