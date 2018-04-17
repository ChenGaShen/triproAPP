$(function() {
	
	// 1.初始化Table
	var oTable = new TableInit();
	oTable.Init();

});

var TableInit = function() {
	var oTableInit = new Object();
	// 初始化Table
	
	oTableInit.Init = function() {
		$('#t_table').bootstrapTable({
			url : '../admin/index/findByPage.json', // 请求后台的URL（*）
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
				  field: 'adminName', // 返回json数据中的name
	              title: '账户名称', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	            	  
			}, {
				  field: 'adminPass', // 返回json数据中的name
	              title: '账户密码', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle' ,// 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
                      if (value != null && value!='') {
                          return "******";
                      } 
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
			search:params.search //服务端分页需要加上此参数
		};
		return temp;
	};
	
	
	//新增管理员按钮事件
	$('#btn_add').click(function(){
	    	$("#addModal").modal("show");//js 控制显示模态框
	    	//提交表单验证
	    	$('#add_form_data').bootstrapValidator({
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
	    	        add_adminName: {
	    	            message: '用户名不合法',
	    	            validators: {
	    	                notEmpty: {
	    	                    message: '用户名不能为空'
	    	                },
	    	                stringLength: {
	    	                    min: 3,
	    	                    max: 10,
	    	                    message: '请输入3到10个字符'
	    	                },
	    	                regexp: {
	    	                    regexp: /^[a-zA-Z]+$/,
	    	                    message: ' 用户名只能输入英文字母 '
	    	                }
	    	            }
	    	        }, 
	    	        add_adminPass: {
	    	            validators: {
	    	                notEmpty: {
	    	                    message: '请填写密码'
	    	                },
	    	                stringLength: {
	    	                	 min: 3,
	    	                     max: 6,
	    	                    message: '密码需3到6位字符'
	    	                },
	    	                regexp: {
	    	                    regexp: /^[A-Za-z0-9]+$/,
	    	                    message: '密码只能由英文字母和数字组成 '
	    	                }
	    	            }
	    	        },
	    	        aff_adminPass: {
	    	            validators: {
	    	                notEmpty: {
	    	                    message: '请填写确认密码'
	    	                },
	    	                identical: {
	    	                    field: 'add_adminPass',
	    	                    message: '确认密码与密码不一致'
	    	                },
	    	            }
	    	        },
	    	    }
	    	});
	    	//表单验证结束
	}); 
	
	$("#addButton").click(function () {
    	//确认新增按钮绑定 信息修改
			 	var bootstrapValidator = $("#add_form_data").data('bootstrapValidator');
			    bootstrapValidator.validate(); //提交验证
    	       if ($('#add_form_data').data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
    	    	   var formData = new FormData();
    	    		formData.append("adminName", $("#add_adminName").val());
    	    		formData.append("adminPass", $("#add_adminPass").val());
    	    		$.ajax({
    	    		      type: "post",
    	    		      dataType: "json",
    	    		      url : "../admin/index/addAdmin.json",
    	    		      data:formData,
    	    		      processData: false,//必须有
    	    	          contentType: false,//必须有
    	    		      success: function (data) {
    	    		    	  if(data.result.status==0){
    	    		    		  $("#addModal").modal("hide");//js 控制隐藏模态框
    	    		    		  setTimeout(myToast(data.result.msg),10000);
    	    		    		  $('#t_table').bootstrapTable('refresh', {url: '../admin/index/findByPage.json'});//数据刷新
    	    		    	  }else{
    	    		    		  setTimeout(myToast(data.result.msg),10000);
    	    		    	  }
    	    		      },
    	    		       error : function() {  
    	    		    	   console.log("数据加载失败");
    	    	}  
    	    		});
    	       };
    		});
	
	//修改密码按钮事件
	$('#btn_changePass').click(function(){
		  var rowData= $("#t_table").bootstrapTable('getSelections'); 
		    if(rowData.length<=0){
		    	myToast("请选中一条数据");
		    }else{
		    	$("#update_adminName").val(rowData[0].adminName);
		    	$("#adminId").val(rowData[0].id);
		    	//审核状态
	    	$("#changePassModal").modal("show");//js 控制显示模态框
	    	//提交表单验证
	    	$('#changePass_form_data').bootstrapValidator({
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
	    	    	update_adminName: {
	    	            message: '用户名不合法',
	    	            validators: {
	    	                notEmpty: {
	    	                    message: '用户名不能为空'
	    	                },
	    	                stringLength: {
	    	                    min: 3,
	    	                    max: 10,
	    	                    message: '请输入3到10个字符'
	    	                },
	    	                regexp: {
	    	                    regexp: /^[a-zA-Z]+$/,
	    	                    message: ' 用户名只能输入英文字母 '
	    	                }
	    	            }
	    	        }, 
	    	        update_adminPass: {
	    	            validators: {
	    	                notEmpty: {
	    	                    message: '请填写密码'
	    	                },
	    	                stringLength: {
	    	                	 min: 3,
	    	                     max: 6,
	    	                    message: '密码需3到6位字符'
	    	                },
	    	                regexp: {
	    	                    regexp: /^[A-Za-z0-9]+$/,
	    	                    message: '密码只能由英文字母和数字组成 '
	    	                }
	    	            }
	    	        },
	    	        update_aff_adminPass: {
	    	            validators: {
	    	                notEmpty: {
	    	                    message: '请填写确认密码'
	    	                },
	    	                identical: {
	    	                    field: 'update_adminPass',
	    	                    message: '确认密码与密码不一致'
	    	                },
	    	            }
	    	        },
	    	    }
	    	});
	    	//表单验证结束
		  }	
	}); 
	
	$("#changePassButton").click(function () {
    	//确认新增按钮绑定 信息修改
				var bootstrapValidator = $("#changePass_form_data").data('bootstrapValidator');
			    bootstrapValidator.validate(); //提交验证
    	       if ($("#changePass_form_data").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
    	    	   var formData = new FormData();
    	    	   	formData.append("adminId", $("#adminId").val());
    	    		formData.append("newPass", $("#update_adminPass").val());
    	    		$.ajax({
    	    		      type: "post",
    	    		      dataType: "json",
    	    		      url : "../admin/index/updateAdminPass.json",
    	    		      data:formData,
    	    		      processData: false,//必须有
    	    	          contentType: false,//必须有
    	    		      success: function (data) {
    	    		    	  if(data.result.status==0){
    	    		    		  $("#changePassModal").modal("hide");//js 控制隐藏模态框
    	    		    		  setTimeout(myToast(data.result.msg),10000);
    	    		    		  $('#t_table').bootstrapTable('refresh', {url: '../admin/index/findByPage.json'});//数据刷新
    	    		    	  }else{
    	    		    		  setTimeout(myToast(data.result.msg),10000);
    	    		    	  }
    	    		      },
    	    		       error : function() {  
    	    		    	   console.log("数据加载失败");
    	    	}  
    	    		});
    	       };
    		});
	
	//删除按钮事件
	$('#btn_delete').click(function(){
	    var rowData= $("#t_table").bootstrapTable('getSelections'); 
	    if(rowData.length<=0){
	    	myToast("请选中一条数据");
	    }else{
	    	$("#delete_adminId").val(rowData[0].id);
	    	$("#delete_adminName").html(rowData[0].adminName);
	    	$("#deleteModal").modal("show");//js 控制显示模态框}
	    }
	   });
	//删除确认按钮
	$("#deleteButton").click(function () {
	    	   var formData = new FormData();
	    		formData.append("adminId", $("#delete_adminId").val());
	    		$.ajax({
	    		      type: "post",
	    		      dataType: "json",
	    		      url : "../admin/index/deleteAdmin.json",
	    		      data:formData,
	    		      processData: false,//必须有
	    	          contentType: false,//必须有
	    		      success: function (data) {
	    		    	  alert(data.result.status);
	    		    	  if(data.result.status==0){
	    		    		  alert(data.result.msg);
	    		    		  $("#deleteModal").modal("hide");//js 控制隐藏模态框
	    		    		  setTimeout(myToast(data.result.msg),10000);
	    		    		  $('#t_table').bootstrapTable('refresh', {url: '../admin/index/findByPage.json'});//数据刷新
	    		    	  }else{
	    		    		  setTimeout(myToast(data.result.msg),10000);
	    		    	  }
	    		      },
	    		       error : function() {  
	    		    	   console.log("数据加载失败");
	    	}  
	    		});
	        
	   }); 


	return oTableInit;
};

