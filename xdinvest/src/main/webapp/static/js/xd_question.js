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
			url : '../xdadmin/questions/findByPage.json', // 请求后台的URL（*）
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
			uniqueId : "questionId", // 每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			singleSelect: true, //开启单选
			columns : [ {
				 checkbox: true, // 显示一个勾选框
	             align: 'center', // 居中显示
			}, {
	            field: 'number',
	            title: '序号',
	            align:'center',
	            valign: 'middle', // 上下居中
	            switchable:false,
	            formatter:function(value,row,index){
	                //return index+1; //序号正序排序从1开始
	                var pageSize=$('#t_table').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
	                var pageNumber=$('#t_table').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
	                return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
	            }
	        },{
				  field: 'questionId', // 返回json数据中的name
	              title: 'ID', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              visible: false //不可见，隐藏
			}, {
				  field: 'questionClassification', // 返回json数据中的name
	              title: '问题类别', // 表格表头显示文字
	              width: '60px',
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  switch (value) { //问题分类(0 投资问题 1流转问题 2收益问题3其他问题))
					case 0:
						return "投资问题";
						break;
					case 1:
						return "流转问题";
						break;
					case 2:
						return "收益问题";
						break;
					case 3:
						return "其他问题";
						break;
					default:
						break;
					}
                  }
			},  {
				  field: 'questionContent', // 返回json数据中的name
	              title: '问题内容', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//          		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  if(value!=null && value != ''){
	            		  if(value.length >= 18){
		            		  value=value.slice(0,18)+"...";
		            	  }
	            	  }
	            	  return  value;
	              } 
	            	  
			},{
				  field: 'questionAnswer', // 返回json数据中的name
	              title: '问题答案', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//          		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  if(value!=null){
	            		  if(value.length >= 18){
		            		  value=value.slice(0,18)+"...";
		            	  }
	            	  }
	            	  return  value;
	              } 
			}, {
				  field: 'questionState', // 返回json数据中的name
	              title: '咨询状态', // 表格表头显示文字
	              width: '60px',
	              align: 'center', // 左右居中
	              valign: 'middle' ,// 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标, 
                      if (value == 0) { //问题状态0已发布 1保存草稿
                          return "已发布";
                      } 
                      if (value == 1) {
                          return "保存草稿";
                      } 
                  }
			}, {
				  field: 'updateTime', // 返回json数据中的name
	              title: '最近更新时间', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  return   formatDateTime(value);
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
			startTime : $("#startTime").val(),
			endTime : $("#endTime").val(),
			questionClassification : $("input[name='questionClassification']:checked").val(),
			questionState : $("input[name='questionState']:checked").val()
			
		};
		return temp;
	};
	
	  //查询按钮事件
    $('#btn_query').click(function(){
        $('#t_table').bootstrapTable('refresh', {url: '../xdadmin/questions/findByPage.json'});
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
        $('input:radio[name=questionClassification]').attr('checked',false);
        $('input:radio[name=questionState]').attr('checked',false);
        $('#t_table').bootstrapTable('refresh', {url: '../xdadmin/questions/findByPage.json'});
       
    });

//新增按钮事件
$('#btn_add').click(function(){
	$("#addModal").modal("show");//js 控制显示模态框
	//表单验证
	$('#form_data').bootstrapValidator({
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
        	add_questionContent: {
                message: '问题内容不合法',
                validators: {
                    notEmpty: {
                        message: '问题内容不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 100,
                        message: '请输入3到100个字符'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9\u4e00-\u9fa5]+$/,
                        message: '标题只能由字母、数字和汉字组成 '
                    }
                }
            }, 
            add_questionClassification: {
            	  message:'类别输入有误',
                  validators: {
                      notEmpty: {
                          message: '类别不能为空'
                      },
                  }
            }, //备注
            add_questionAnswer: {
                validators: {
                	  notEmpty: {
                          message: '问题答案不能为空'
                      }, stringLength: {
                          max: 300,
                          message: '请输入小于300个字符'
                      }
                }
            },
        }
    });
	//表单验证结束
   });

//新增确认按钮绑定 咨询公告新增
$("#addButton").click(function () {
	  var bootstrapValidator = $("#form_data").data('bootstrapValidator');
	   bootstrapValidator.validate(); //提交验证
	  if ($("#form_data").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码 
    	   var formData=new FormData();//使用FormData提交表单并上传文件  var formData=new FormData($('#form_data')[0]);
    	   formData.append('questionClassification', $("#add_questionClassification option:selected").val()); //获取select的选中的value
    	   formData.append('questionContent', $("textarea[name='add_questionContent']").val());
    	   formData.append('questionAnswer', $("textarea[name='add_questionAnswer']").val());
           formData.append('questionState', $("input[name='add_questionState']:checked").val());
    		$.ajax({
    		      type: "post",
    		      dataType: "json",
    		      url : "../xdadmin/questions/doAdd.json",
    		      data:formData,
    		      processData: false,//必须有
    	          contentType: false,//必须有
    		      success: function (data) {
    		    	  if(data.result.status==0){
    		    		  $('#form_data').bootstrapValidator('resetForm', true);//重置表单
    		    		  $("#addModal").modal("hide");//js 控制隐藏模态框
    		    		  setTimeout(myToast(data.result.msg),10000);
    		    		  setTimeout($('#t_table').bootstrapTable('refresh', {url: '../xdadmin/questions/findByPage.json'}),10000);//数据刷新
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

//咨询删除按钮事件
$('#btn_delete').click(function(){
    var rowData= $("#t_table").bootstrapTable('getSelections'); 
    if(rowData.length<=0){
    	myToast("请选中一条数据");
    }else{
    	$("#delete_questionId").val(rowData[0].questionId);
    	$("#delete_questionContent").html(rowData[0].questionContent);
    	$("#deleteModal").modal("show");//js 控制显示模态框
    }
	
   });


//删除咨询确认按钮 信息修改
$("#deleteButton").click(function () {
    	   var formData = new FormData();
    		formData.append("questionId", $("#delete_questionId").val());
    		$.ajax({
    		      type: "post",
    		      dataType: "json",
    		      url : "../xdadmin/questions/doDelete.json",
    		      data:formData,
    		      processData: false,//必须有
    	          contentType: false,//必须有
    		      success: function (data) {
    		    	  if(data.result.status==0){
    		    		  $("#deleteModal").modal("hide");//js 控制隐藏模态框
    		    		  setTimeout(myToast(data.result.msg),10000);
    		    		  $('#t_table').bootstrapTable('refresh', {url: '../xdadmin/questions/findByPage.json'});//数据刷新
    		    	  }else{
    		    		  setTimeout(myToast(data.result.msg),10000);
    		    	  }
    		      },
    		       error : function() {  
    		    	   console.log("数据加载失败");
    	}  
    		});
        
   }); 



//编辑按钮事件
$('#btn_edit').click(function(){
	  var rowData= $("#t_table").bootstrapTable('getSelections'); 
	    if(rowData.length<=0){
	    	myToast("请选中一条数据");
	    }else{
	    	if (rowData[0].questionClassification!=null) {
	    		$("#update_questionClassification").find("option[value='"+rowData[0].questionClassification+"']").attr("selected",true); //select 赋值
			}else{
				$("#update_questionClassification").find("option[value='']").attr("selected",true);
			}
	    	$("#update_questionId").val(rowData[0].questionId);
	    	$("#update_questionContent").val(rowData[0].questionContent);
	    	$("#update_questionAnswer").val(rowData[0].questionAnswer);
	    	$("input[name='update_questionState'][value="+rowData[0].questionState+"]").attr("checked",true);  //radio 赋值
	   
	$("#updateModal").modal("show");//js 控制显示模态框
	//表单验证
	$('#update_form_data').bootstrapValidator({
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
    	  update_questionContent: {
                message: '问题内容不合法',
                validators: {
                    notEmpty: {
                        message: '问题内容不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 100,
                        message: '请输入3到100个字符'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9\u4e00-\u9fa5]+$/,
                        message: '标题只能由字母、数字和汉字组成 '
                    }
                }
            }, 
            update_questionClassification: {
            	  message:'类别输入有误',
                  validators: {
                      notEmpty: {
                          message: '类别不能为空'
                      },
                  }
            }, //备注
            update_questionAnswer: {
                validators: {
                	  notEmpty: {
                          message: '问题答案不能为空'
                      }, stringLength: {
                          max: 300,
                          message: '请输入小于300个字符'
                      }
                }
            },
      }
  });
 }
	//表单验证结束
});





//编辑确认按钮绑定 商品新增
$("#updateButton").click(function () {
	   var bootstrapValidator = $("#update_form_data").data('bootstrapValidator');
	   bootstrapValidator.validate();
	  if (($("#update_form_data").data('bootstrapValidator')).isValid()) {//获取验证结果，如果成功，执行下面代码 
  	   var formData=new FormData();//使用FormData提交表单并上传文件  var formData=new FormData($('#form_data')[0]);
		  	   formData.append('questionId', $("input[name='update_questionId']").val());
		  	   formData.append('questionClassification', $("#update_questionClassification option:selected").val()); //获取select的选中的value
	    	   formData.append('questionContent', $("textarea[name='update_questionContent']").val());
	    	   formData.append('questionAnswer', $("textarea[name='update_questionAnswer']").val());
	           formData.append('questionState', $("input[name='update_questionState']:checked").val());
  		$.ajax({
  		      type: "post",
  		      dataType: "json",
  		      url : "../xdadmin/questions/doUpdate.json",
  		      data:formData,
  		      processData: false,//必须有
  	          contentType: false,//必须有
  		      success: function (data) {
  		    	  if(data.result.status==0){
//  		    		  $('#update_form_data').bootstrapValidator('resetForm', true);//重置表单
  		    		  $("#updateModal").modal("hide");//js 控制隐藏模态框
  		    		  setTimeout(myToast(data.result.msg),10000);
  		    		  setTimeout($('#t_table').bootstrapTable('refresh', {url: '../xdadmin/questions/findByPage.json'}),10000);//数据刷新
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

	return oTableInit;
};






