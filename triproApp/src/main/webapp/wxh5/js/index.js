$(document).ready(function(){
	getData();
	getCarnum();

});
  var uid=localStorage.getItem("uid");//
var base_url="../web/";
// var base_url="http://192.168.1.109:8080/tripro/web/";
var list;
function getData(){
	$.ajax({
        type: "post",
      dataType: "json",
      url: base_url+"commodity/commodityList.json",
     	 data:{"":""},
        success: function (data) {
        	var str="";
        	
        	if (data.result.status==0) {
        		str=""
        		list=data.resultList;
        		$.each(data.resultList,function(i){
        		var commodityId =  data.resultList[i].commodityId;  
                var name = data.resultList[i].name;
                var price = data.resultList[i].price;
                var discountPrice = data.resultList[i].discountPrice;
                var amount = data.resultList[i].amount;
                var allowance = data.resultList[i].allowance;
                var img = data.resultList[i].img;
                var specification = data.resultList[i].specification;
                var sales = data.resultList[i].sales;
                var state = data.resultList[i].state;
                var addTime = data.resultList[i].addTime;
                var updateTime = data.resultList[i].updateTime;
                var description = data.resultList[i].description;
                var jieshao="";
                if (description.length>=24) {
                  jieshao=description.substring(0,22)+"...";
                }else if(description.length<=12){
                  jieshao=description+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                }else{
                  jieshao=description;
                }
        		if (i!=0) {
      				str+="<div class='shangping2 fl' ><span class='fr'>商品详情<br>规格："+specification+
      				"</span><p><img src="+img+" alt='' class='ddetailimg' onclick='showDetail("+i+")'></p><p style='color:#666' class='jieshao'>"+name+"<br>"+jieshao+"</p><p style='color:#f00' >"+price+"<img src='img/car.png' onClick='addCar("+i+")'></p></div> "

           		 }else{
           		 	$(".img1").html('<img src="'+img+'" alt="">');          		 	$(".product_name1").text(name);
	        		$(".product_detail1").text(description+"。规格："+specification);
	        		$(".product_price").text(price);
	        		
           		 }
            

           


        });
        		$(".shangpin23").html(str);
        	}

          
        }

    });
}
function showDetail(id){
	$(".detail").show();
	$(".product_detailname").text(list[id].name);
	$(".addcar").html("<img src='img/car.png' onClick='addCar("+id+")'>");
	$(".product_detailprice").text(list[id].price);
	$(".product_detailguige").text(list[id].specification);
	document.getElementById("product_img").src=list[id].img;
}
function hideDetail(){
	$(".detail").hide();
}
function addCar(i){
    if (uid==null) {
         window.location.href = 'login.html';
      return;
    }
	var id =list[i].commodityId;
		$.ajax({
  		type: "POST",
     	dataType: "json",
      	url: base_url+"shopping/toShoppingCart.json",
      	data:{"commodityId":id,"num":"1","uid":uid},
      	success: function (data) {
      	if (data.result.status==0) {
      		alert("添加成功");
      		getCarnum();
      	}
      
     
      
      }
   
 
});
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

var i=1;
	var bian;
	$(function(){
		// $(".ttt span:lt(4)").css("margin-right","2px");
		$(".dian span").mouseover(function(){
			var index=$(this).index();
			clearInterval(bian);
			i=index;
			$(".banner li:eq("+i+")").css("z-index","5").siblings("li").css("z-index","1");
			$(".dian span:eq("+i+")").addClass('banner_isck').siblings('span').removeClass('banner_isck');
			i++;
			if (i>4)i=0;
		}).mouseout(function(){
			bian=setInterval("changeImg()",2000);
		});;
		bian=setInterval("changeImg()",2000);
	})
	function changeImg(){
		$(".banner li:eq("+i+")").css("z-index","5").siblings("li").css("z-index","1");
		$(".dian span:eq("+i+")").addClass('banner_isck').siblings('span').removeClass('banner_isck');
		i++;
		if(i>4)i=0;
	}

