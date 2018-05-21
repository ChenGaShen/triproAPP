$(document).ready(function(){

	 if (uid==null) {
         window.location.href = 'login.html';
      return;
    }
	 var Ohref=window.location.href;
 		var arrhref=Ohref.split("=");
 		var status=arrhref[1].split("&")[0];
 		
 		switch(status){
 			case "0":
 				dfk();
 			break;
 			case "1":
 				psz();
 			break;
 			case "2":
 				yqs();
 			break;
 			default:
 				qb();
 				break;
 			
 		}

	
});
 var uid=localStorage.getItem("uid");//
var base_url="../web/";
var type=0;
function qb(){
	$(".span_qb").addClass("span_isck").removeClass("span_isckno");
	$(".span_dfk").addClass("span_isckno").removeClass("span_isck");
	$(".span_psz").addClass("span_isckno").removeClass("span_isck");
	$(".span_yqs").addClass("span_isckno").removeClass("span_isck");
	getData("","");
}
function dfk(){
	$(".span_dfk").addClass("span_isck").removeClass("span_isckno");
	$(".span_qb").addClass("span_isckno").removeClass("span_isck");
	$(".span_psz").addClass("span_isckno").removeClass("span_isck");
	$(".span_yqs").addClass("span_isckno").removeClass("span_isck");
	getData(0,"");
	type=1;
}
function psz(){
	$(".span_psz").addClass("span_isck").removeClass("span_isckno");
	$(".span_dfk").addClass("span_isckno").removeClass("span_isck");
	$(".span_qb").addClass("span_isckno").removeClass("span_isck");
	$(".span_yqs").addClass("span_isckno").removeClass("span_isck");
	getData("",1);
	type=2;
}
function yqs(){
	$(".span_yqs").addClass("span_isck").removeClass("span_isckno");
	$(".span_qb").addClass("span_isckno").removeClass("span_isck");
	$(".span_dfk").addClass("span_isckno").removeClass("span_isck");
	$(".span_psz").addClass("span_isckno").removeClass("span_isck");
	getData("",2);
	type=3;
}
var list;
function getData(state,receiveState){
	$(".orderlist").html("");
	var str="";
	var detail="";
	$.ajax({
  		type: "POST",
     	dataType: "json",
      	url: base_url+"order/toOrderStatus.json",
      	data:{"uid":uid,"state":state,"receiveState":receiveState},
      	success: function (data) {
      	if (data.result.status==0) {
      			list=data.detailVOs;
      		// if (data.detailVOs.result.status==0) {
      			$.each(data.detailVOs,function(i){
      				detail="";
      				
      				var receiveState =data.detailVOs[i].receiveState;
      				var orderId=data.detailVOs[i].orderId+"";
      				var state=data.detailVOs[i].state;
      				var receiveName=data.detailVOs[i].receiveName;
      				var receivePhone=data.detailVOs[i].receivePhone;
      				var addTime=data.detailVOs[i].addTime;
      				var receiveAddress=data.detailVOs[i].receiveAddress;
      				var orderPrice=data.detailVOs[i].orderPrice;
      				$.each(data.detailVOs[i].orderItems,function(j){
      					
      					
	      				var img=data.detailVOs[i].orderItems[j].img;
	      				var commodityName=data.detailVOs[i].orderItems[j].commodityName;
	      				var amount=data.detailVOs[i].orderItems[j].amount;
	      				var price=data.detailVOs[i].orderItems[j].price;
	      				var commodityId=data.detailVOs[i].orderItems[j].commodityId;
	      				var specification=data.detailVOs[i].orderItems[j].specification;
	      				detail+="<div class='order_detail clearfix'><img src='"+img+"' class='fl'><div class='fl order_name'><p>"+commodityName+"<span class='fr'>￥"+price+"</span></p><p>"+specification+"<span class='fr'>X"+amount+"</span></p></div></div><div style='height:3px'></div>"
      				});
      				var buttons="";
      				var statesss="";
      				switch(state){
      					case "已付款":
      						statesss =receiveState;
      						buttons="<div class='order_button clearfix'><button onClick='addCar("+i+")'>再次购买</button></div>";
      					break;
      					case "待付款":
      					statesss=state;
      						buttons="<div class='order_button clearfix'><button onClick='delorder(\""+orderId+"\")'>取消订单</button><button onClick='buynow(\""+orderId+"\")'>立即付款</button></div>";
      					break;
      					case "取消订单":
      						statesss=state;
      						// buttons="<div class='order_button clearfix'><button>取消订单</button><button>立即付款</button><button>再次购买</button></div>";
      					break;
      					case "已失效":
      						statesss=state;
							// buttons="<div class='order_button clearfix'><button>取消订单</button><button>立即付款</button><button>再次购买</button></div>";
      					break;

      				}
      				str+="<p>"+addTime+"<span class='order_status fr'>"+statesss+"</span></p>"+detail+"<p class='order_heji'>合计：<span style='color:#f00'>"+orderPrice+"</span></p><hr>"+buttons+"<div class='borders'></div>";
      			});
      		// }
      		$(".orderlist").html(str);
      	}
      
     
      
      }
   
 
	});
}
function addCar(id){
		
		var s=JSON.stringify( list[id] );
		alert(s);
		
$.ajax({
  		type: "POST",
     	dataType: "json",
      	url: base_url+"shopping/doAgainToCart.json",
      	data:{"uid":uid,"json":s},
      	success: function (data) {
     
     
      
      }
   
 
});
}

function delorder(id){
	
		$.ajax({
  		type: "POST",
     	dataType: "json",
      	url: base_url+"order/doCancellOrder.json",
      	data:{"orderId":id,"uid":uid},
      	success: function (data) {
      	if (data.result.status==0) {
      		alert("订单取消成功");
      		
      	}
      	switch(type){
      		case 0:
      		qb();
      		break;
      		case 1:
      		dfk();
      		break;
      		case 2:
      		psz();
      		break;
      		case 3:
      		yqs();
      		break;
      		default:
      		qb();
      		break;
      	}
     
      
      }
   
 
});
}
function buynow(id){
	
	window.location.href="doorder.html?orderid="+id+"&";
}