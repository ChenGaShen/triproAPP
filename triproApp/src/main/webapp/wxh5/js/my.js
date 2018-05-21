 var tel=localStorage.getItem("tel");
 var uid=localStorage.getItem("uid");
$(document).ready(function(){
	 if (uid==null) {
        
    }else{
    	$(".user b").text(tel);
    }
	getCarnum();
});
var base_url="../web/";
function loginnow(){
	if (uid==null) {
		   window.location.href="login.html";
	}else{

	}
}
function getCarnum(){
	var num;
	var s={"uid":uid};
	$.ajax({
  		type: "POST",
     	dataType: "json",
      	url: base_url+"shopping/cartList.json",
      	data:s,
      	success: function (data) {
      	if (data.result.status==0) {
      		num=data.resultList.length;
      		$(".car_num").text(num);
      		
      		if (num==0) {
      			$(".car_num").hide();
      			
      		}
      	}else{
            $(".car_num").hide();
        }
      
     
      
      }
   
 
	});
}
  function logout(){
    localStorage.removeItem('uid');
    localStorage.clear();
    $(".user b").text("立即登录");
  }