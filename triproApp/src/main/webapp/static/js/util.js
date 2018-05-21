//初始化ajax请求，让它结束之后运行completer后面的函数初始化ajax请求，让它结束之后运行completer后面的函数
$.ajaxSetup( {    
        //设置ajax请求结束后的执行动作    
        complete : function(XMLHttpRequest, textStatus) {  
            // 通过XMLHttpRequest取得响应头，REDIRECT    
            var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");//若HEADER中含有REDIRECT说明后端想重定向  
            if (redirect == "REDIRECT") {  
                var win = window;    
                while (win != win.top){  
                    win = win.top;  
                }  
                console.log(XMLHttpRequest.getResponseHeader("CONTENTPATH"));
              //将后端重定向的地址取出来,使用win.location.href去实现重定向的要求  
              win.location.href= XMLHttpRequest.getResponseHeader("CONTENTPATH");    
            }  
        }    
    });

	
//消息提示插件
function myToast(message){
	var top=$(window).height()*0.3;
	$('body').append('<div class="myToast">'+message+'</div>');
	console.log($('.myToast').outerWidth())
	var top=($(window).height()-$('.myToast').height())/2;
	var left=($('body').width()-$('.myToast').width())/2;
	$('.myToast').css({'top':top+'px','left':left+'px'});
	setTimeout(function(){
		$('.myToast').fadeOut(200);
		setTimeout(function(){
			$('.myToast').remove();
		},200)
	},1000)
}

//为空转换符
function isNull(data){    
	return (data==null || data =="")? "-----" : data;
}; 


//时间戳格式化
function formatDateTime(inputTime) {    
    var date = new Date(inputTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
}; 

//查找url中对应的参数
function getUrlParam(name) {
	 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	 var r = window.location.search.substr(1).match(reg); //匹配目标参数
	 if (r != null) return unescape(r[2]); return null; //返回参数值
	}






