$(document).ready(function(){

	  var Ohref=window.location.href;
		  var arrhref=Ohref.split("=");
	
		 cid=arrhref[1];
   orderid=arrhref[1].split("&")[0];
        getdata(arrhref[1].split("&")[0]);

});

var  addressid=0;
var orderid="";
var orderprice="";
function getdata(orderid){

  $.ajax({
      type: "POST",
      dataType: "json",
        url: base_url+"order/toOrderDetail.json",
        data:{"orderId":orderid},
        success: function (data) {
          var str="";
          if (data.result.status==0) {
            if (data.receivePhone==null) {
               addressid=0;
                 $(".paddress").text("请添加收货地址");
            }else{
               addressid=1;
              $(".footer label").text(data.orderPrice);
              $(".paddress").text(data.receiveAddress);
               $(".pname").html(data.receiveName+"<span class='fr'>"+data.receivePhone+"</span>");
            }
          orderprice=data.orderPrice;
              $.each(data.orderItems,function(i){
            var orderId =  data.orderItems[i].orderId;  
                var img = data.orderItems[i].img;
                var commodityName = data.orderItems[i].commodityName;
                var amount = data.orderItems[i].amount;
                var price = data.orderItems[i].price;
                var commodityId = data.orderItems[i].commodityId;
                var specification = data.orderItems[i].specification;
              str+="<div class='borders'></div><div class='detail clearfix'><img src='"+img+"' class='fl'><div class='jiehsao fl'><p>"+commodityName+"<span class='fr sp1'>￥"+price+"</span></p><p class='guige'>规格"+specification+"<span class='fr'>x"+amount+"</span></p></div></div>";

           


        });
          $(".items").html(str);
          }
      
      
      }
   
 
  });
};
var base_url="../web/";

function doOrder(){

  // $.ajax({
  //     type: "POST",
  //     dataType: "json",
  //       url: base_url+"pay/toPay.json",
  //       data:{"orderid":orderid},
  //       success: function (data) {
        if (addressid==0) {
          alert("请添加收货地址");
          return;
        }
      var weixinUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe389698d15192553&redirect_uri=http://suishoumai.hzmenglin.com/withselling/suishoumai/wxpay.html?orderId="+orderid+"&response_type=code&scope=snsapi_userinfo&state="+orderprice+"#wechat_redirect";
          
        
        window.location.href=encodeURI(weixinUrl);
        
      
      
  //     }
   
 
  // });
};
function setAddress(){
     window.location.href="chooseAddress.html?orderid="+orderid+"&";
}