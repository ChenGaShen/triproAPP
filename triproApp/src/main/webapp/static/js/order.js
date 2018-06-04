$(function() {
	
	// 时间控件
	$('#startTime').cxCalendar();
	$('#endTime').cxCalendar();
	// 1.初始化Table
	var oTable = new TableInit();
	oTable.Init();

});

var TableInit = function() {
	var oTableInit = new Object();
	// 初始化Table
	
	oTableInit.Init = function() {
		$('#t_table').bootstrapTable({
			url : '../admin/order/findByPage.json', // 请求后台的URL（*）
			method : 'post', // 请求方式（*）
			data :[],
			dataType: 'json',
			toolbar : '#toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色
			cache : false, // 设置为 false 禁用 AJAX 数据缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sortable : false, // 是否启用排序
			sortOrder : "asc", // 排序方式
			queryParams : oTableInit.queryParams,// 传递参数（*）
			queryParamsType:'',//查询参数组织方式
			contentType: "application/x-www-form-urlencoded", //很重要，后台能获取查询参数
			sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*） 客户端模式较为简单，它是把数据一次性加载出来放到界面上，然后根据你设置的每页记录数，自动生成分页
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 15, 20, 25 ], // 可供选择的每页的行数（*）
//			search : true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			showColumns : true, // 是否显示所有的列
//			showRefresh : true, // 是否显示刷新按钮
			toolbarAlign:'left',//工具栏对齐方式
	        buttonsAlign:'right',//按钮对齐方式
			minimumCountColumns : 2, // 最少允许的列数
			clickToSelect : true, // 是否启用点击选中行
			height : 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			undefinedText : '---', //未定义时显示
			uniqueId : "orderId", // 每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			singleSelect: true, //开启单选
			/*showExport: true,  //是否显示导出按钮  
			exportTypes:['excel'],  //导出文件类型
			exportDataType : "all", //basic'导出当前页, 'all'导出全部, 'selected'导出选中项.
			exportOptions:{  
		           ignoreColumn: [0,1],  //忽略某一列的索引  
		           fileName: '用户列表',  //文件名称设置  
		           worksheetName: 'sheet1',  //表格工作区名称  
		           tableName: '用户列表',  
		           excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],  
		          
		     }, */
			columns : [ {
				 checkbox: true, // 显示一个勾选框
	             align: 'center' // 居中显示
			}, {
	            field: 'number',
	            title: '序号',
	            width: 2,
	            align:'center',
	            valign: 'middle', // 上下居中
	            switchable:false,
	            formatter:function(value,row,index){
	                //return index+1; //序号正序排序从1开始
	                var pageSize=$('#t_table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	                var pageNumber=$('#t_table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
	                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	            }
	        }, {
				  field: 'userPhone', // 返回json数据中的name
	              title: '下单账户', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	            	  
			},{
				  field: 'orderId', // 返回json数据中的name
	              title: '订单号', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
			},{
				  field: 'orderItems', // 返回json数据中的name
	              title: '商品信息', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              width:255,
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  var detail_name="";
	            	  $.each(value,function(i){
	                      var commodityName = value[i].commodityName;
	                      var amount = value[i].amount;
	                      var price = value[i].price;
	                      detail_name+= commodityName+"<span style='color: #d70fb8;'>&nbsp;"+price+"</span> <span style='color: blue;'> X </span><span ><b>"+amount+"</b>&nbsp;</span> <br>"
	                });
	            	  return detail_name;
                  }
	            	  
			}, {
				  field: 'orderPrice', // 返回json数据中的name
	              title: '订单总额', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle' // 上下居中
			}, {
				  field: 'redMoney', // 返回json数据中的name
	              title: '使用红包金额', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle' // 上下居中
			},{
				  field: 'state', // 返回json数据中的name
	              title: '支付状态', // 表格表头显示文字  
	              align: 'center', // 左右居中
	              valign: 'middle' ,// 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  if (value == 0) { //状态：0待付款1已付款2取消订单3已失效
	                        return "待付款";
	                    } else if (value == 1) {
	                        return "已付款";
	                    } else if (value == 2) {
	                        return "取消订单";
	                    } else if (value == 3) {
	                        return "已失效";
	                    }
                  }
			}, {
				  field: 'receiveState', // 返回json数据中的name
	              title: '货运状态', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle',// 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  if (value == 0) { //货运状态0待发货1配送中2已签收
	                        return "待发货";
	                    } else if (value == 1) {
	                        return "配送中";
	                    } else if (value == 2) {
	                        return "已签收";
	                    } else if (value == 3) {
	                        return "已失效";
	                    }
                  }
			}, {
				  field: 'receiveName', // 返回json数据中的name
	              title: '收件人姓名', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              width: 5,
//	              visible: false //不可见，隐藏
			},{
				  field: 'receivePhone', // 返回json数据中的name
	              title: '收件人电话', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
//	              visible: false //不可见，隐藏
			}, {
				  field: 'receiveAddress', // 返回json数据中的name
	              title: '收件人地址', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
//	              visible: false //不可见，隐藏
			},{
				  field: 'air', // 返回json数据中的name
	              title: '运单号', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              visible: false //不可见，隐藏
	            	  
			}, {
				  field: 'company', // 返回json数据中的name
	              title: '物流公司', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              visible: false //不可见，隐藏
			},{
				  field: 'remark', // 返回json数据中的name
	              title: '备注', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  return   isNull(value);
	              }  
			}, {
				  field: 'payTime', // 返回json数据中的name
	              title: '支付时间', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              visible: false ,//不可见，隐藏
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  if (value!=null) {
	            		  return   formatDateTime(value);
					}
	              } 
			},
			 {
				  field: 'addTime', // 返回json数据中的name
	              title: '下单时间', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//          		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  return  formatDateTime(value);//时间戳格式化
	              } 
			},],
			//根据接口返回的数据转化为bootstrap 所需的数据
		   responseHandler : function(res) {  
	            return {  
	                total : res.pageBean.count, //结果总记录
	                rows : res.pageBean.pageList //结果记录
	            };
	            console.log(total);
	   		    console.log(rows);
		   },
		   onLoadSuccess:function(){
			   $(".bs-checkbox").css({'text-align':'center','vertical-align':'middle'}); //复选框水平居中
	         }
		  
		});
		 
	};

	// 得到查询的参数
	oTableInit.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			pageSize: params.pageSize, // 页面大小 多少条记录
			currentPage : params.pageNumber, // //页码  
			userPhone : $("#userPhone").val(),
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val(),
			state : $("input[name='search_state']:checked").val(),
			receiveState : $("input[name='search_receiveState']:checked").val(),
			search:params.search //服务端分页需要加上此参数
		};
		return temp;
	};
	

	
	  //查询按钮事件
    $('#btn_query').click(function(){
        $('#t_table').bootstrapTable('refresh', {url: '../admin/order/findByPage.json'});
       /* 
        再点击查询按钮时 
        （分页后重新搜素问题） 
        $(‘#mytab’).bootstrapTable(‘refreshOptions’,{pageNumber:1,pageSize:10});//便可以了
*/    });

  //重置按钮事件 
$("#btn_reset").off().on("click",function(){ 
	$("#startTime").val("");  
    $("#endTime").val("");  
    $("#userPhone").val("");  
    $('input:radio[name=search_state]').attr('checked',false);
    $('input:radio[name=search_receiveState]').attr('checked',false);
    $('#t_table').bootstrapTable('refresh', {url: '../admin/order/findByPage.json'});
   
});

//物流录入按钮事件
$('#btn_phyc').click(function(){
    var rowData= $("#t_table").bootstrapTable('getSelections'); 
    if(rowData.length<=0){
    	myToast("请选中一条数据");
    }else if(rowData[0].state!=1){
    	myToast("此订单暂未支付，无法录入");
    }else{
    	$("#phyc_orderId").val(rowData[0].orderId);
    	$("#phyc_userPhone").val(rowData[0].userPhone);
    	$("#phyc_receivePhone").val(rowData[0].receivePhone);
    	$("#phyc_receiveName").val(rowData[0].receiveName);
    	$("#phyc_receiveAddress").val(rowData[0].receiveAddress);
    	$("#phyc_company").val(rowData[0].company);
    	if (rowData[0].company!=null) {
    		$("#phyc_company").find("option[value='"+rowData[0].company+"']").attr("selected",true);
		}else{
			$("#phyc_company").find("option[value='']").attr("selected",true);
		}
    	$("#phyc_air").val(rowData[0].air);
    	$("#phycModal").modal("show");//js 控制显示模态框
    }
   });


//物流录入按钮绑定
$("#phycButton").click(function () {
  	   var formData = new FormData();
  		formData.append("orderId", $("#phyc_orderId").val());
  		formData.append("company", $("#phyc_company").val());//物流公司
  		formData.append("air", $("#phyc_air").val());//物流单号
  		$.ajax({
  		      type: "post",
  		      dataType: "json",
  		      url : "../admin/order/doAirAndCompany.json",
  		      data:formData,
  		      processData: false,//必须有
  	          contentType: false,//必须有
  		      success: function (data) {
  		    	  if(data.result.status==0){
  		    		  $("#phycModal").modal("hide");//js 控制隐藏模态框
  		    		  setTimeout(myToast(data.result.msg),10000);
  		    		  $('#t_table').bootstrapTable('refresh', {url: '../admin/order/findByPage.json'});//数据刷新
  		    	  }else{
  		    		  setTimeout(myToast(data.result.msg),10000);
  		    	  }
  		      },
  		       error : function() {  
  		    	   console.log("数据加载失败");
  	}  
  		});
      
 }); 

//备注信息按钮事件
$('#btn_remark').click(function(){
    var rowData= $("#t_table").bootstrapTable('getSelections'); 
    if(rowData.length<=0){
    	myToast("请选中一条数据");
    }else{
    	$("#remark_orderId").val(rowData[0].orderId);
    	$("#remark_userPhone").val(rowData[0].userPhone);
    	$("#remark_receiveName").val(rowData[0].receiveName);
    	$("#remark_receivePhone").val(rowData[0].receivePhone);
    	$("#remark_receiveAddress").val(rowData[0].receiveAddress);
    	$("input[name='remark_state'][value="+rowData[0].state+"]").attr("checked",true); 
    	if (rowData[0].receiveState!=null) {
    		$("input[name='remark_receiveState'][value="+rowData[0].receiveState+"]").attr("checked",true); 
		}else{
			$("input[name='remark_receiveState'][value='']").attr("checked",true);
		}
    	$("#remarkModal").modal("show");//js 控制显示模态框
    	
    	//表单验证
    	$('#remark_form_data').bootstrapValidator({
            message: '输入值不合法',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            /**
             * 生效规则（三选一）
             * enabled 字段值有变化就触发验证
             * disabled,submitted 当点击提交时验证并展示错误信息
             */
             live: 'enabled',
             /**
              * 指定提交的按钮，例如：'.submitBtn' '#submitBtn'
              * 当表单验证不通过时，该按钮为disabled
              */
            submitButtons: 'button[type="submit"]',
            fields: {
            	remark_receiveName: {
                    message: '输入有误',
                    validators: {
                        notEmpty: {
                            message: '收件人姓名不能为空'
                        },
                        stringLength: {
                            max: 10,
                            message: '请输入小于10个字符'
                        },
                    }
                }, 
                remark_receivePhone: {
                	  message:'输入有误',
                      validators: {
                          notEmpty: {
                              message: '收件人手机号不能为空'
                          },
                          regexp: {
                              regexp: /^0?(13[0-9]|15[0-9]|18[0-9]|14[57]|17[0-9]|19[89]|16[6])[0-9]{8}$/,
                              message: '请输入 正确的手机号'
                          },
                      }
                }, 
                remark_receiveAddress: {
              	  message:'输入有误',
                    validators: {
                        notEmpty: {
                            message: '收件地址不能为空'
                        },
                    }
              },
              remark_remark: {
    	        	  message:'输入有误',
    	              validators: {
    	                  stringLength: {
    	                        max: 100,
    	                        message: '请输入小于100个字符'
    	                    }
    	              }
    	        }, 
            }
        });
    	//表单验证结束
    }
   });


//备注信息确认按钮
$("#remarkButton").click(function () {
	   var bootstrapValidator = $("#remark_form_data").data('bootstrapValidator');
	   bootstrapValidator.validate(); //提交验证
	 if ($("#remark_form_data").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码 
  	   var formData = new FormData();
  		formData.append("orderId", $("#remark_orderId").val());
  		formData.append("receiveName", $("#remark_receiveName").val());
  		formData.append("receivePhone", $("#remark_receivePhone").val());
  		formData.append("receiveAddress", $("#remark_receiveAddress").val());
  		formData.append("remark", $("#remark_remark").val());//备注
	  		$.ajax({
	  		      type: "post",
	  		      dataType: "json",
	  		      url : "../admin/order/updateRemark.json",
	  		      data:formData,
	  		      processData: false,//必须有
	  	          contentType: false,//必须有
	  		      success: function (data) {
	  		    	  if(data.result.status==0){
	  		    		  $("#remarkModal").modal("hide");//js 控制隐藏模态框
	  		    		  setTimeout(myToast(data.result.msg),10000);
	  		    		  $('#t_table').bootstrapTable('refresh', {url: '../admin/order/findByPage.json'});//数据刷新
	  		    	  }else{
	  		    		  setTimeout(myToast(data.result.msg),10000);
	  		    	  }
	  		      },
	  		       error : function() {  
	  		    	   console.log("数据加载失败");
	  	}  
	  		});
	 }	
      
 }); 

//导出查询内容为excel
$("#btn_export").click(function(){
	var userPhone=$("#userPhone").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var state = $("input[name='search_state']:checked").val();
	var receiveState = $("input[name='search_receiveState']:checked").val();
	var pageNumber=$('#t_table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
			var str="";
        	$.ajax({
        	      type: "post",
        	      dataType: "json",
        	      url : "../admin/order/doExport.json",
        	      data:{"userPhone":userPhone,"startTime":startTime,"endTime":endTime,"state":state,"receiveState":receiveState},
        	     
        	      success: function (data) {
        	    	// 循环
        	          $.each(data,function(i){
        	                var ex_orderId = data[i].orderId;
        	                var ex_detail_name="";
        	                   $.each(data[i].orderItems,function(j){
        	                	   var commodityName = data[i].orderItems[j].commodityName;
         	                      var amount = data[i].orderItems[j].amount;
         	                      var price = data[i].orderItems[j].price;
         	                     ex_detail_name+= commodityName+"<span style='color: #d70fb8;'>&nbsp;"+price+"</span> <span style='color: blue;'> X </span><span ><b>"+amount+"</b>&nbsp;</span> <br>"
        	              });
        	                var state1 = data[i].state;
        	                var ex_state="";
        	                switch(state1){ ////状态：0待付款1已付款2取消订单3已失效
        	                case "0":
        	                	ex_state="待付款"
        	                	break;
        	                case "1":
        	                	ex_state="已付款"
        	                	break;
        	                case "2":
        	                	ex_state="取消订单"
        	                	break;
        	                case "3":
        	                	ex_state="已失效"
        	                	break;
        	                default:
        	                	ex_state="---"
        	                	break;
        	                }
        	                var receiveState1 = data[i].receiveState;
        	                var ex_receiveState="";
        	                switch(receiveState1){ //订单状态0待发货1配送中2已签收
        	                case "0":
        	                	ex_receiveState="待发货"
        	                	break;
        	                case "1":
        	                	ex_receiveState="配送中"
        	                	break;
        	                case "2":
        	                	ex_receiveState="已签收"
        	                	break;
        	                default:
        	                	ex_receiveState="---"
        	                	break;
        	                }
        	                var ex_receiveName= isNull(data[i].receiveName);
        	                var ex_receivePhone= isNull(data[i].receivePhone);
        	                var ex_receiveAddress= isNull(data[i].receiveAddress);
        	                var ex_air= isNull(data[i].air);
        	                var ex_company= isNull(data[i].company);
        	                var ex_addTime = data[i].addTime;
        	                var ex_payTime =isNull(data[i].payTime);
        	                var ex_remark = isNull(data[i].remark);
        	                var ex_orderPrice =isNull(data[i].orderPrice);
        	                var ex_redMoney =isNull(data[i].redMoney);
        	            	var ex_userPhone = data[i].userPhone;
        	                	  str+="<tr>+"
        	        					+"<td>"+[i+1+(pageNumber-1)*10]+"</td>"
        	        					+"<td>"+ex_userPhone+"&nbsp;</td>"
        	        					+"<td>"+ex_orderId+"&nbsp;</td>"
        	        					+"<td>"+ex_detail_name+"</td>"
        	        					+"<td>"+ex_orderPrice+"&nbsp;</td>"
        	        					+"<td>"+ex_redMoney+"&nbsp;</td>"
        	        					+"<td>"+ex_state+"</td>"
        	        					+"<td>"+ex_receiveState+"</td>"
        	        					+"<td>"+ex_receiveName+"</td>"
        	        					+"<td>"+ex_receivePhone+"&nbsp;</td>"
        	        					+"<td>"+ex_receiveAddress+"</td>"
        	        					+"<td>"+ex_company+"</td>"
        	        					+"<td>"+ex_air+"&nbsp;</td>"
        	        					+"<td>"+ex_remark+"</td>"
        	        					+"<td>"+ex_payTime+"&nbsp;</td>" //&nbsp;解决导出excel科学计数法的问题，格式改变等问题
        	        					+"<td>"+ex_addTime+"&nbsp;</td>" //&nbsp;解决导出excel科学计数法的问题，格式改变等问题
        	          });
        	          $("#export_body").html(str);
        	         
        	  	    $("#exportTable").table2excel({  
        	  	        // 不被导出的表格行的CSS class类  
        	  	        exclude: ".noExl",  
        	  	        // 导出的Excel文档的名称  
        	  	        name: "Excel",  
        	  	        // Excel文件的名称  
        	  	        filename: "订单-" + formatDateTime(new Date().getTime()) + ".xls", //文件名称,  
        	  	        // 是否导出图片  
        	  	        exclude_img: true,  
        	  	        // 是否导出超链接  
        	  	        exclude_links: true,  
        	  	        // 是否导出输入框中的内容  
        	  	        exclude_inputs: true  
        	  	    }); 
        	      },
        	       error : function() {  
        	    	   console.log("数据加载失败"); 
        }  
        	});
	 
});



	return oTableInit;
};






