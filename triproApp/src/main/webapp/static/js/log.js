$(function() {	
	 //回车提交事件
	$("body").keydown(function() {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	    	$('#loginButton').click();
	    }
	}); 
//登录按钮事件
    $('#loginButton').click(function(){
    		  var adminName=$("#adminName").val();
    		  var adminPass=$("#adminPass").val();
    	             $.ajax({
    	             //几个参数需要注意一下
    	                 type: "POST",//方法类型
    	                 dataType: "json",//预期服务器返回的数据类型
    	                 url: "../admin/index/doLogin.json" ,//url
    	                 data:{"adminName":adminName,"adminPass":adminPass},
    	                 success: function (data) {
    	                     console.log(data);//打印服务端返回的数据(调试用)
    	                      if (data.result.status == 0) {
    	                    	  sessionStorage.setItem("adminName",data.adminName); 
    	                    	  window.location.href = "index.html";
    	                      }else{
    	                    	  myToast(data.result.msg);
    	                      };         
    	                 },
    	                  error : function() {
    	                	  console.log("登录失败，请重试!!");
    	                 }
    	             });
  });

});