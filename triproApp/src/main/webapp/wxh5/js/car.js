$(document).ready(function(){

	getCar();
		 // getdadd();
	

});
  var uid=localStorage.getItem("uid");//
var base_url="../web/";
var list;
function getCar(){
	var num;
	var s={"uid":uid};
	$.ajax({
  		type: "POST",
     	dataType: "json",
      	  	url: base_url+"shopping/cartList.json",
      	data:s,
      	success: function (data) {
      		var str="";
      	if (data.result.status==0) {
      		list=data.resultList;
      			$.each(data.resultList,function(i){
        		var commodityId =  data.resultList[i].commodityId;  
                var id = data.resultList[i].id;
                var num = data.resultList[i].num;
                var uid = data.resultList[i].uid;
                var state = data.resultList[i].state;
                var ip = data.resultList[i].ip;
                var img = data.resultList[i].img;
                var ipAddress = data.resultList[i].ipAddress;
                var addTime = data.resultList[i].addTime;
                var updateTime = data.resultList[i].updateTime;
                var name = data.resultList[i].name;
                var specification = data.resultList[i].specification;
                var commodityPrice = data.resultList[i].commodityPrice;
        		str+="<div class='borders'></div><div class='item clearfix'><label class='my_protocol'><input type='checkbox' class='input_agreement_protocol fl ck check"+i+"' onClick='isck("+i+")'><span></span></label><img src='"+img+"' class='fl'><div class='shangpin fl'><p>"+name+"<img class='fr' src='img/car_del.png' onclick='del("+commodityId+")'></p><p>"+specification+"</p><div ><p>￥"+commodityPrice+"</p><div class='count fr'><span class='span_add"+i+"  span_add' onClick='add("+i+")'>+</span><span class='span_num"+i+"  span_num'>1</span><span class='span_cut"+i+"  span_cut' onclick='cut("+i+")'>-</span></div></div></div></div>";
            

           


        });
      		
      	}else{
      		str="<div style='background:#eee;width:30% ;margin:0 auto;margin-top:200px' ><img src='img/car_no.png'><p>购物车空空如也</p><div>";
      	}
      	
     	$(".body").html(str);
      
      }
   
 
	});
}
function isck(o){
	var listts=new Array();
	var total=0;
	for (var i = 0; i < list.length; i++) {
		if ($(".check"+i).prop("checked")) {
			// list[i].ip=0;
			total+=list[i].commodityPrice*list[i].num;
			
		}else{
			// list[i].ip=1;
		}

	}
	$(".jiesuan b").text(total);
}
function doorder(){
	var listts=new Array();
	var lss;
	for (var i = 0; i < list.length; i++) {
		if ($(".check"+i).prop("checked")) {
			// list[i].ip=0;
			listts.push(list[i]);
		}else{
			// list[i].ip=1;
		}
	}
	if (listts.length==0) {
		alert("请选择后结算");
		return;
	}
		var s;
		s={};
		lss={"resultList":listts};
		var ss=JSON.stringify( lss );
			
		switch(listts.length){
			case 0:
				alert("请选择后结算");
			break;
			case 1:
				s={"uid":uid,"orderItems[0].commodityId":listts[0].commodityId,"orderItems[0].amount":listts[0].num};
			break;
			case 2:
				s={"uid":uid,"orderItems[0].commodityId":listts[0].commodityId,"orderItems[0].amount":listts[0].num,"orderItems[1].commodityId":listts[1].commodityId,"orderItems[1].amount":listts[1].num};

			break;
			case 3:
				s={"uid":uid,"orderItems[0].commodityId":listts[0].commodityId,"orderItems[0].amount":listts[0].num,"orderItems[1].commodityId":listts[1].commodityId,"orderItems[1].amount":listts[1].num,"orderItems[2].commodityId":listts[2].commodityId,"orderItems[2].amount":listts[2].num};

			break;
			case 4:
				s={"uid":uid,"orderItems[0].commodityId":listts[0].commodityId,"orderItems[0].amount":listts[0].num,"orderItems[1].commodityId":listts[1].commodityId,"orderItems[1].amount":listts[1].num,"orderItems[2].commodityId":listts[2].commodityId,"orderItems[2].amount":listts[2].num,"orderItems[3].commodityId":listts[3].commodityId,"orderItems[3].amount":listts[3].num};

			break;
			case 5:
				s={"uid":uid,"orderItems[0].commodityId":listts[0].commodityId,"orderItems[0].amount":listts[0].num,"orderItems[1].commodityId":listts[1].commodityId,"orderItems[1].amount":listts[1].num,"orderItems[2].commodityId":listts[2].commodityId,"orderItems[2].amount":listts[2].num,"orderItems[3].commodityId":listts[3].commodityId,"orderItems[3].amount":listts[3].num,"orderItems[4].commodityId":listts[4].commodityId,"orderItems[4].amount":listts[4].num};

			break;
			case 6:
				s={"uid":uid,"orderItems[0].commodityId":listts[0].commodityId,"orderItems[0].amount":listts[0].num,"orderItems[1].commodityId":listts[1].commodityId,"orderItems[1].amount":listts[1].num,"orderItems[2].commodityId":listts[2].commodityId,"orderItems[2].amount":listts[2].num,"orderItems[3].commodityId":listts[3].commodityId,"orderItems[3].amount":listts[3].num,"orderItems[4].commodityId":listts[4].commodityId,"orderItems[4].amount":listts[4].num,"orderItems[5].commodityId":listts[5].commodityId,"orderItems[5].amount":listts[5].num};

			break;
			case 7:
			s={"uid":uid,"orderItems[0].commodityId":listts[0].commodityId,"orderItems[0].amount":listts[0].num,"orderItems[1].commodityId":listts[1].commodityId,"orderItems[1].amount":listts[1].num,"orderItems[2].commodityId":listts[2].commodityId,"orderItems[2].amount":listts[2].num,"orderItems[3].commodityId":listts[3].commodityId,"orderItems[3].amount":listts
			[3].num,"orderItems[4].commodityId":listts[4].commodityId,"orderItems[4].amount":listts[4].num,"orderItems[5].commodityId":listts[5].commodityId,"orderItems[5].amount":listts[5].num,"orderItems[6].commodityId":listts[6].commodityId,"orderItems[6].amount":listts[6].num};

			break;

		}

		//,"orderItems[0].commodityId":listts.[0].commodityId,"orderItems[0].amount":listts.[0].num
	$.ajax({
  		type: "POST",
     	dataType: "json",
       	url: base_url+"order/doCartOrder.json",
      	data:{"uid":uid,"json":ss},
      	success: function (data) {
      		
  			if (data.result.status==0) {
  				  window.location.href="doorder.html?orderid="+data.orderId+"&";
  			}else{
  				alert(data.result.msg);
  			}
      	
     	
      
      }
   
 
	});
	

}
function add(i){
		var count= this.$(".span_num"+i).text();
		var num=parseInt(count)+1;
		list[i].num=num;
		
		$(this.$(".span_num"+i)).text(num);
		//alert($count);
	var total=0;
	for (var i = 0; i < list.length; i++) {
		if ($(".check"+i).prop("checked")) {
			// list[i].ip=0;
			total+=list[i].commodityPrice*list[i].num;
			
		}else{
			// list[i].ip=1;
		}

	}
	$(".jiesuan b").text(total);
}
function cut(i){
	var count= this.$(".span_num"+i).text();
		if (count<=1) {
			return;
		}
		var num=parseInt(count)-1;
		list[i].num=num;
			$(this.$(".span_num"+i)).text(num);
			var total=0;
	for (var i = 0; i < list.length; i++) {
		if ($(".check"+i).prop("checked")) {
			// list[i].ip=0;
			total+=list[i].commodityPrice*list[i].num;
			
		}else{
			// list[i].ip=1;
		}

	}
	$(".jiesuan b").text(total);
}

function getdadd(){
	$.ajax({
  		type: "POST",
     	dataType: "json",
     	url: base_url+"order/doCartOrder.json",
      	data:{"uid":uid},
      	success: function (data) {
      		
     	var s=data.uid;
     	alert(s);
      	
     	
      
      }
   
 
	});
}
function del(id){
	$.ajax({
  		type: "POST",
     	dataType: "json",
      	url: base_url+"shopping/delShoppingCommodity.json",
      	data:{"uid":uid,"commodityId":id},
      	success: function (data) {
      		if (data.result.status==0) {
      			alert("删除成功");
      			getCar();
      		}
     	
      
      }
   
 
	});
}