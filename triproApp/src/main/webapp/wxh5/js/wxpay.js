$(document).ready(function(){
    var Ohref=window.location.href;
  
      var arrhref=Ohref.split("=");
      var or=arrhref[1].split("&");
       var co =arrhref[2].split("&");
       price=arrhref[3];
       orderid=or[0];
      getdata(or[0],co[0],arrhref[3]);
      // alert(1);
      // alert(or[0]+"       "+co[0]+"          "+arrhref[3]);
});

var appid="";
var timeStamp="";
var nonceStr="";
var package="";
var paySign="";
var price="";
var orderid="";
var base_url="../web/";
function getdata(orderid,code,price){
      
  $.ajax({
    type: "post",
        dataType: "json",
        url:base_url+"pay/toPay.json?orderId="+orderid+"&code="+code+"&totalFee="+price,
      
        success:function(data){
 // alert(data.wexinPayInfo.appid+
 //            data.wexinPayInfo.timeStamp +
 //            data.wexinPayInfo.nonceStr+
 //            data.wexinPayInfo.packageValue
 //            +data.wexinPayInfo.sign);
        appid=data.appid;
         timeStamp=data.timeStamp;
          nonceStr=data.nonceStr;
           package=data.packageValue;
            paySign=data.sign;
            // alert(data.wexinPayInfo.appid+"111111"+data.wexinPayInfo.timeStamp+"111111"+data.wexinPayInfo.nonceStr+"111111"+data.wexinPayInfo.package+"111111"+data.wexinPayInfo.paySign);
           // alert(appid+"2222222222"+package+"22222222222"+nonceStr+"2222222222"+package+"222222"+paySign);
          // onBridgeReady(data.wexinPayInfo.appid,
          //   data.wexinPayInfo.timeStamp ,
          //   data.wexinPayInfo.nonceStr,
          //   data.wexinPayInfo.packageValue
          //   ,data.wexinPayInfo.sign);
           if (typeof WeixinJSBridge == "undefined"){
   if( document.addEventListener ){
       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
   }else if (document.attachEvent){
       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
   }
}else{
   onBridgeReady();
}
        }
  });
}
//获取url路径的参数
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
function onBridgeReady(){
  // alert(appid);
   WeixinJSBridge.invoke(
       'getBrandWCPayRequest', {
           "appId":appid,     //公众号名称，由商户传入     
           "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数     
           "nonceStr":nonceStr, //随机串     
           "package":package,     
           "signType":"MD5",         //微信签名方式：     
           "paySign":paySign //微信签名 
       },
       function(res){     
           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
             window.location.href="payresult.html?cid="+price+"="+orderid
           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
       }
   ); 
}
