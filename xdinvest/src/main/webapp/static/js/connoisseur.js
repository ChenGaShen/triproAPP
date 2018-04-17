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
			url : '../admin/user/findByPage.json', // 请求后台的URL（*）
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
			uniqueId : "id", // 每一行的唯一标识，一般为主键列
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
	            width:5 ,
	            align:'center',
	            switchable:false,
	            formatter:function(value,row,index){
	                //return index+1; //序号正序排序从1开始
	                var pageSize=$('#t_table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	                var pageNumber=$('#t_table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
	                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	            }
	        },{
				  field: 'id', // 返回json数据中的name
	              title: 'ID', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              visible: false //不可见，隐藏
			}, {
				  field: 'userPhone', // 返回json数据中的name
	              title: '账户手机', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	            	  
			}, {
				  field: 'phoneBelong', // 返回json数据中的name
	              title: '归属地', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle' // 上下居中
			}, {
				  field: 'identity', // 返回json数据中的name
	              title: '用户身份', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle' ,// 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
                      if (value == 1) {
                          return "个人用户";
                      } 
                      if (value == 2) {
                          return "供销商";
                      } 
                  }
			}, {
				  field: 'state', // 返回json数据中的name
	              title: '账户状态', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle',// 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
                      if (value == 1) {
                          return "启用";
                      } 
                      if (value == 2) {
                          return "禁用";
                      } 
                  }
			}, {
				  field: 'audit', // 返回json数据中的name
	              title: '审核状态', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  if (row.audit == 1) {
	                        return "未审核";
	                    } else if (row.audit == 2) {
	                        return "审核中";
	                    } else if (row.audit == 3) {
	                        return "已审核";
	                    } else if (row.audit == 4) {
	                        return "未通过";
	                    }
                  }
			},{
				  field: 'idCard', // 返回json数据中的name
	              title: '身份证', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              visible: false //不可见，隐藏
			},{
				  field: 'idCardImg', // 返回json数据中的name
	              title: '身份证A', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              visible: false //不可见，隐藏
	            	  
			}, {
				  field: 'idCardImgB', // 返回json数据中的name
	              title: '身份证A', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              visible: false //不可见，隐藏
			}, {
				  field: 'businessImg', // 返回json数据中的name
	              title: '营业执照', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              visible: false //不可见，隐藏
			}, {
				  field: 'remark', // 返回json数据中的name
	              title: '备注', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  return   isNull(value);
	              }  
			}, {
				  field: 'loginTime', // 返回json数据中的name
	              title: '最近登录', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  return   formatDateTime(value);
	              } 
			},
			 {
				  field: 'addTime', // 返回json数据中的name
	              title: '注册时间', // 表格表头显示文字
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
		  
		});
		 
	};

	// 得到查询的参数
	oTableInit.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			/*pageSize: params.limit, // 页面大小 多少条记录
			currentPage : params.pageNumber, // //页码  
*/			pageSize: params.pageSize, // 页面大小 多少条记录
			currentPage : params.pageNumber, // //页码  
			userPhone : $("#userPhone").val(),
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val(),
			audit : $("input[name='auditConent']:checked").val(),
			search:params.search //服务端分页需要加上此参数
		};
		return temp;
	};
	

	
	  //查询按钮事件
    $('#btn_query').click(function(){
        $('#t_table').bootstrapTable('refresh', {url: '../admin/user/findByPage.json'});
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
    $('input:radio[name=auditConent]').attr('checked',false);
    $('#t_table').bootstrapTable('refresh', {url: '../admin/user/findByPage.json'});
   
});

//编辑按钮事件
$('#btn_edit').click(function(){
    var rowData= $("#t_table").bootstrapTable('getSelections'); 
    if(rowData.length<=0){
    	myToast("请选中一条数据");
    }else{
		window.location.href="connoisseurUpdate.html?uid="+rowData[0].id;
    	/*for (var i = 0; i < rowData.length; i++) {  
    		alert(rowData[i].id);
    		alert(rowData[i].userPhone); 
    		alert(rowData[i].phoneBelong); 
    		alert(rowData[i].identity); 
        }*/ 
    }
   });

//审核按钮事件
$('#btn_check').click(function(){
    var rowData= $("#t_table").bootstrapTable('getSelections'); 
    if(rowData.length<=0){
    	myToast("请选中一条数据");
    }else{
    	
    	$("#audit_userPhone").val(rowData[0].userPhone);
    	$("#audit_idCard").val(rowData[0].idCard);
    	$("#audit_uid").val(rowData[0].id);
    	//审核状态
    	switch(rowData[0].audit){
           case 1:
	            $("input[name='styleshoice1'][value=1]").attr("checked",true); 
	           	break;
           case 2:
	           	$("input[name='styleshoice1'][value=2]").attr("checked",true); 
	           	break;
           case 3:
                $("input[name='styleshoice1'][value=3]").attr("checked",true); 
                break;
           case 4:
                $("input[name='styleshoice1'][value=4]").attr("checked",true); 
                break;
           }
    	//资料照片
    	$("#audit_businessImg").attr('src',rowData[0].businessImg); 
    	$("#audit_idCardImg").attr('src',rowData[0].idCardImg); 
    	$("#audit_idCardImgB").attr('src',rowData[0].idCardImgB); 
    	//打开
    	$("#audit_bImg").attr("href",rowData[0].businessImg);
    	$("#audit_IdImgA").attr("href",rowData[0].idCardImg);
    	$("#audit_IdImgB").attr("href",rowData[0].idCardImgB);
    	$("#auditModal").modal("show");//js 控制显示模态框
    }
   });



//审核提交按钮绑定 信息修改
$("#auditButton").click(function () {
    	   var formData = new FormData();
    		formData.append("uid", $("#audit_uid").val());
    		formData.append("audit", $('input[name="styleshoice1"]:checked').val());//审核状态
    		$.ajax({
    		      type: "post",
    		      dataType: "json",
    		      url : "../admin/user/updateUserInfo.json",
    		      data:formData,
    		      processData: false,//必须有
    	          contentType: false,//必须有
    		      success: function (data) {
    		    	  if(data.status==0){
    		    		  $("#auditModal").modal("hide");//js 控制隐藏模态框
    		    		  setTimeout(myToast("处理成功!"),10000);
    		    		  $('#t_table').bootstrapTable('refresh', {url: '../admin/user/findByPage.json'});//数据刷新
    		    	  }else{
    		    		  setTimeout(myToast("处理失败!"),10000);
    		    	  }
    		      },
    		       error : function() {  
    		    	   console.log("数据加载失败");
    	}  
    		});
        
   }); 

//加入黑名单按钮事件
$('#btn_toBlack').click(function(){
    var rowData= $("#t_table").bootstrapTable('getSelections'); 
    if(rowData.length<=0){
    	myToast("请选中一条数据");
    }else if (rowData[0].state==2){
    	myToast("此账户已在黑名单中");
    }else{
    	$("#black_uid").val(rowData[0].id);
    	$("#black_userPhone").html(rowData[0].userPhone);
    	$("#blackModal").modal("show");//js 控制显示模态框}
    }
	
   });


//加入黑名单确认按钮 信息修改
$("#toBlackButton").click(function () {
    	   var formData = new FormData();
    		formData.append("uid", $("#black_uid").val());
    		formData.append("state", 2);//账户状态1启用2禁用
    		$.ajax({
    		      type: "post",
    		      dataType: "json",
    		      url : "../admin/user/updateUserInfo.json",
    		      data:formData,
    		      processData: false,//必须有
    	          contentType: false,//必须有
    		      success: function (data) {
    		    	  if(data.status==0){
    		    		  $("#blackModal").modal("hide");//js 控制隐藏模态框
    		    		  setTimeout(myToast("处理成功!"),10000);
    		    		  $('#t_table').bootstrapTable('refresh', {url: '../admin/user/findByPage.json'});//数据刷新
    		    	  }else{
    		    		  setTimeout(myToast("处理失败!"),10000);
    		    	  }
    		      },
    		       error : function() {  
    		    	   console.log("数据加载失败");
    	}  
    		});
        
   }); 

//取消黑名单按钮事件
$('#btn_outBlack').click(function(){
    var rowData= $("#t_table").bootstrapTable('getSelections'); 
    if(rowData.length<=0){
    	myToast("请选中一条数据");
    }else if (rowData[0].state==1){
    	myToast("此账户不在黑名单中，无法取消");
    }else{
    	$("#out_black_uid").val(rowData[0].id);
    	$("#out_black_userPhone").html(rowData[0].userPhone);
    	$("#outblackModal").modal("show");//js 控制显示模态框}
    }
	
   });
//取消黑名单确认
$("#outBlackButton").click(function () {
    	   var formData = new FormData();
    		formData.append("uid", $("#out_black_uid").val());
    		formData.append("state", 1);//账户状态1启用2禁用
    		$.ajax({
    		      type: "post",
    		      dataType: "json",
    		      url : "../admin/user/updateUserInfo.json",
    		      data:formData,
    		      processData: false,//必须有
    	          contentType: false,//必须有
    		      success: function (data) {
    		    	  if(data.status==0){
    		    		  $("#outblackModal").modal("hide");//js 控制隐藏模态框
    		    		  setTimeout(myToast("处理成功!"),10000);
    		    		  $('#t_table').bootstrapTable('refresh', {url: '../admin/user/findByPage.json'});//数据刷新
    		    	  }else{
    		    		  setTimeout(myToast("处理失败!"),10000);
    		    	  }
    		      },
    		       error : function() {  
    		    	   console.log("数据加载失败");
    	}  
    		});
        
   }); 
//导出查询内容为excel
$("#btn_export").click(function(){
	var userPhone=$("#userPhone").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var auditConent = $("input[name='auditConent']:checked").val();
	var pageNumber=$('#t_table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
			var str="";
        	$.ajax({
        	      type: "post",
        	      dataType: "json",
        	      url : "../admin/user/outExport.json",
        	      data:{"userPhone":userPhone,"startTime":startTime,"endTime":endTime,"audit":auditConent},
        	     
        	      success: function (data) {
        	    	// 循环
        	          $.each(data,function(i){
        	        	  	var ex_userPhone = data[i].userPhone;
        	                var ex_phoneBelong = data[i].phoneBelong;
        	                var identity1 = data[i].identity;
        	                var ex_identity="";
        	                switch(identity1){
        	                case 1:
        	                	ex_identity="个人用户"
        	                	break;
        	                case 2:
        	                	ex_identity="供销商"
        	                	break;
        	                }
        	                var state1 = data[i].state;
        	                var ex_state="";
        	                switch(state1){
        	                case 1:
        	                	ex_state="启用"
        	                	break;
        	                case 2:
        	                	ex_state="禁用"
        	                	break;
        	                }
        	                var audit1 = data[i].audit;
        	                var ex_audit="";
        	                switch(audit1){
        	                case 1:
        	                	ex_audit="未审核"
        	                	break;
        	                case 2:
        	                	ex_audit="审核中"
        	                	break;
        	                case 3:
        	                	ex_audit="已审核"
        	                	break;
        	                case 4:
        	                	ex_audit="未通过"
        	                	break;
        	                }
        	                var ex_remark= isNull(data[i].remark);
        	                var ex_addTime = data[i].addTime;
        	                var ex_loginTime =data[i].loginTime;
        	                	  str+="<tr>+"
        	        					+"<td>"+[i+1+(pageNumber-1)*10]+"</td>"
        	        					+"<td>"+ex_userPhone+"&nbsp;</td>"
        	        					+"<td>"+ex_phoneBelong+"</td>"
        	        					+"<td>"+ex_identity+"</td>"
        	        					+"<td>"+ex_state+"</td>"
        	        					+"<td>"+ex_audit+"</td>"
        	        					+"<td>"+ex_remark+"</td>"
        	        					+"<td>"+formatDateTime(ex_loginTime)+"&nbsp;</td>" //&nbsp;解决导出excel科学计数法的问题，格式改变等问题
        	        					+"<td>"+formatDateTime(ex_addTime)+"&nbsp;</td>"
        	            
        	          });
        	          $("#export_body").html(str);
        	         
        	  	    $("#exportTable").table2excel({  
        	  	        // 不被导出的表格行的CSS class类  
        	  	        exclude: ".noExl",  
        	  	        // 导出的Excel文档的名称  
        	  	        name: "Excel",  
        	  	        // Excel文件的名称  
        	  	        filename: "用户-" + formatDateTime(new Date().getTime()) + ".xls", //文件名称,  
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






