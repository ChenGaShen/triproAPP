var token=localStorage.getItem("token");
var shopid=localStorage.getItem("shopid");
$(document).ready(function(){
   if (token!=null) {
      // window.location.href = 'index.html';
    }
});

// http://duoqubao.hzmenglin.com/web/user/doLogin.json
// http://duoqubao.hzmenglin.com/web/user/doUserRegisterSMS.json
var ad="../web/";

/**
 * 短信验证码
 * @return {[type]} [description]
 */
function getCertifycode(){
	var tel;
	 tel=$(" input[ name='tell' ] ").val();
	if (tel.length!=11) {
		$(".tell_p").text("手机号有误");
		return;
	}
	
	$.ajax({
  		type: "POST",
     	dataType: "json",
      	url: ad+"user/doUserRegisterSMS.json",
      	data:{"userPhone":tel},
      	success: function (data) {
      	if (data.result.status==0) {
			console.log(data.result.msg);
			sendemail();

      	}else{
      		alert(data.result.msg);
      	}
      
     
      
      }
   
 
});
	
}
/**
 * 倒计时
 * @type {Number}
 */
var countdown=60; 
function sendemail(){
    var obj = $(".code_button");
    settime(obj);
    
    }
function settime(obj) { //发送验证码倒计时
    if (countdown == 0) { 
        obj.attr('disabled',false); 
        //obj.removeattr("disabled"); 
        obj.val("免费获取验证码");
        countdown = 60; 
        return;
    } else { 
        obj.attr('disabled',true);
        obj.val("重新发送(" + countdown + ")");
        countdown--; 
    } 
setTimeout(function() { 
    settime(obj) }
    ,1000) 
}
/**
 * 
 */
function doLogin(){
	var  tel=$(" input[ name='tell' ] ").val();
	var  pwd=$("input[ name='certifycode' ]").val();
	if (tel.length!=11) {
		$(".tell_p").text("手机号有误");
		return;
	}
	if (pwd.length!=6) {
		$(".tell_p").text("验证码有误");
		return;
	}
	$.ajax({
		type: "POST",
     	dataType: "json",
      	url: ad+"user/doLogin.json",
      	data:{"userPhone":tel,"code":pwd},
      	success: function (data) {
      	if (data.result.status==0) {

      		console.log(data);
  			localStorage.setItem("uid",data.uid);
        localStorage.setItem("tel",data.userPhone);
  			// localStorage.setItem("shopid",data.shopLoginVO.id);
  			//   localStorage.setItem("token",data.shopLoginVO.token);
          // token=localStorage.getItem("token");
          //  shopid=localStorage.getItem("shopid");//
  			  window.location.href = 'index.html';
      	}else{
      		$(".tell_p").text(data.result.msg);
      	}
      } 	
	});
};


