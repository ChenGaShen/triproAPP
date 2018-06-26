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
			url : '../admin/commodity/findByPage.json', // 请求后台的URL（*）
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
			uniqueId : "commodityId", // 每一行的唯一标识，一般为主键列
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
				  field: 'commodityId', // 返回json数据中的name
	              title: 'ID', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              visible: false //不可见，隐藏
			}, {
				  field: 'classify', // 返回json数据中的name
	              title: '种类', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//	       		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	           	  if (value == 0) { //种类：0宠粮1营养保健2日常用品3清洁美容4健康零食
	                       return "宠粮";
	                   } else if (value == 1) {
	                       return "营养保健";
	                   } else if (value == 2) {
	                       return "日常用品";
	                   } else if (value == 3) {
	                       return "清洁美容";
	                   }else if (value == 4) {
	                       return "健康零食";
	                   }
	             }
			},{
				  field: 'commodityName', // 返回json数据中的name
	              title: '商品名称', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	            	  
			}, {
				  field: 'price', // 返回json数据中的name
	              title: '普通价格(元)', // 表格表头显示文字
	              width: '60px',
	              align: 'center', // 左右居中
	              valign: 'middle' // 上下居中
			}, {
				  field: 'discountPrice', // 返回json数据中的name
	              title: '折后价格(元)', // 表格表头显示文字
	              width: '60px',
	              align: 'center', // 左右居中
	              valign: 'middle' ,// 上下居中
			}, {
				  field: 'amount', // 返回json数据中的name
	              title: '总数量(件/包)', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle',// 上下居中
			}, {
				  field: 'allowance', // 返回json数据中的name
	              title: '库存(件/包)', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
			},{
				  field: 'realSale', // 返回json数据中的name
	              title: '实际销量(件/包)', // 表格表头显示文字
	              align: 'center', // 左右居中
	              width: '60px',
	              valign: 'middle', // 上下居中
			},{
				  field: 'virtualSales', // 返回json数据中的name
	              title: '虚拟销量(件/包)', // 表格表头显示文字
	              align: 'center', // 左右居中
	              width: '60px',
	              valign: 'middle', // 上下居中
			},
			{
				  field: 'specification', // 返回json数据中的name
	              title: '商品规格', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
			},{
				  field: 'description', // 返回json数据中的name
	              title: '商品描述', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//          		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  if(value.length >= 18){
	            		  value=value.slice(0,18)+"...";
	            	  }
	            	  return  value;
	              } 
			}, {
				  field: 'commodityImg', // 返回json数据中的name
	              title: '商品图片', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//          		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  return  '<img src="'+value+'" style="display:inline-block; width:150px; height:100px;">';
	              } 
			},{
				  field: 'updateTime', // 返回json数据中的name
	              title: '更新时间', // 表格表头显示文字
	              align: 'center', // 左右居中
	              valign: 'middle', // 上下居中
	              formatter : function(value, row, index) { 
//            		  value:代表当前单元格中的值，row：代表当前行, index:代表当前行的下标,
	            	  return   formatDateTime(value);
	              } 
			},
			 {
				  field: 'addTime', // 返回json数据中的name
	              title: '添加时间', // 表格表头显示文字
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
			
		};
		return temp;
	};
	


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
        	add_commodityName: {
                message: '商品名不合法',
                validators: {
                    notEmpty: {
                        message: '商品名不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 16,
                        message: '请输入3到16个字符'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9\u4e00-\u9fa5]+$/,
                        message: '商品名只能由字母、数字和汉字组成 '
                    }
                }
            }, 
            add_classify: {
                message: '类目不合法',
                validators: {
                    notEmpty: {
                        message: '类目不能为空'
                    }
                }
            }, 
            add_price: {
            	  message:'普通价格不合法',
                  validators: {
                      notEmpty: {
                          message: '普通价格不能为空'
                      },
                      regexp: {
                          regexp: /[0-9]+(\.[0-9]{1})[0-9]?$/,
                          message: '请输入 非负数 小数点后保留两位'
                      },
                  }
            }, 
            add_discountPrice: {
          	  message:'折后价格不合法',
                validators: {
                    notEmpty: {
                        message: '折后价格不能为空'
                    },
                    
                    regexp: {
                        regexp: /[0-9]+(\.[0-9]{1})[0-9]?$/,
                        message: '请输入 非负数 小数点后保留两位'
                    },
                  /*  callback: {自定义，可以在这里与其他输入项联动校验
                        message: '供销商价格不能大于普通价格',
                        callback:function(value, validator,$field){
                      	  if(add_price==undefined ||add_discountPrice==undefined){
                                return true;
                            }else{
                            	return add_discountPrice<=add_price;
                            }
                        }
                    }*/
                }
          },
          add_amount: {
	        	  message:'总数量输入有误',
	              validators: {
	                  notEmpty: {
	                      message: '总数量不能为空'
	                  },
	                  stringLength: {
	                        max: 5,
	                        message: '输入需小于5个字符'
	                    },
	                  regexp: {
	                      regexp: /^\d+(\.{0,1}\d+){0,1}$/,
	                      message: '请输入正确的总数量 非负数'
	                  },
	              }
	        }, 
	        add_allowance: {
	      	  message:'库存输入不合法',
	            validators: {
	                notEmpty: {
	                    message: '库存不能为空'
	                },
	                stringLength: {
                        max: 5,
                        message: '输入需小于5个字符'
                    },
	                regexp: {
	                    regexp: /^\d+(\.{0,1}\d+){0,1}$/,
	                    message: '请输入正确的库存 非负数'
	                },
	              /*  callback: {自定义，可以在这里与其他输入项联动校验
	                    message: '库存数量不能大于总数量',
	                    callback:function(value, validator,$field){
	                  	  if(scope.add_amount==undefined ||scope.add_allowance==undefined){
	                            return true;
	                        }else{
	                        	return scope.add_allowance<=scope.add_amount;
	                        }
	                    }
	                }*/
	            }
	      },
	      add_virtualSales: {
        	  message:'虚拟销量输入有误',
              validators: {
                  notEmpty: {
                      message: '虚拟销量不能为空'
                  },
                  stringLength: {
                        max: 5,
                        message: '输入需小于5个字符'
                    },
                  regexp: {
                      regexp: /^\d+(\.{0,1}\d+){0,1}$/,
                      message: '请输入正确的虚拟销量 非负数'
                  },
              }
        }, 
            add_specification: {
                validators: {
                    notEmpty: {
                        message: '商品规格不能为空'
                    }, stringLength: {
                        min: 2,
                        max: 15,
                        message: '请输入2到15个字符'
                    }
                }
            },
            //备注
            add_description: {
                validators: {
                	  notEmpty: {
                          message: '商品描述不能为空'
                      }, stringLength: {
                          max: 100,
                          message: '请输入小于100个字符'
                      }
                }
            },
            file: {
                validators: {
                    notEmpty: {
                        message: '上传图片不能为空'
                    },
                    file: {
                        extension: 'png,jpg,jpeg',
                        type: 'image/png,image/jpg,image/jpeg',
                        message: '注意格式，请重新选择图片'
                    }
                }
            },
        }
    });
	//表单验证结束
   });





//新增确认按钮绑定 商品新增
$("#addButton").click(function () {
		var bootstrapValidator = $("#form_data").data('bootstrapValidator');
	    bootstrapValidator.validate();
	  if ($("#form_data").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码 
		  $("#loadingModal").modal("show");//js 控制隐藏模态框
    	   var formData=new FormData($('#form_data'));//使用FormData提交表单并上传文件  var formData=new FormData($('#form_data')[0]);
    	   formData.append('commodityName', $("input[name='add_commodityName']").val());
    	   formData.append('classify', $("#add_classify").val());
           formData.append('price', $("input[name='add_price']").val());
           formData.append('discountPrice', $("input[name='add_discountPrice']").val());
           formData.append('amount', $("input[name='add_amount']").val());
           formData.append('allowance', $("input[name='add_allowance']").val());
           formData.append('virtualSales', $("input[name='add_virtualSales']").val());
           formData.append('specification', $("input[name='add_specification']").val());
           formData.append('description', $("textarea[name='add_description']").val());
           formData.append('file', $('#file')[0].files[0]);
    		$.ajax({
    		      type: "post",
    		      dataType: "json",
    		      url : "../admin/commodity/addCommodity.json",
    		      data:formData,
    		      processData: false,//必须有
    	          contentType: false,//必须有
    		      success: function (data) {
    		    	  $("#loadingModal").modal("hide");//js 控制隐藏模态框
    		    	  if(data.result.status==0){
    		    		  $('#form_data').bootstrapValidator('resetForm', true);//重置表单
    		    		  $("#imgPre").attr('src',"../static/img/noimage.png"); //图片回复默认
    		    		  $("#addModal").modal("hide");//js 控制隐藏模态框
    		    		  setTimeout(myToast(data.result.msg),10000);
    		    		  setTimeout($('#t_table').bootstrapTable('refresh', {url: '../admin/commodity/findByPage.json'}),10000);//数据刷新
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

//商品删除按钮事件
$('#btn_delete').click(function(){
    var rowData= $("#t_table").bootstrapTable('getSelections'); 
    if(rowData.length<=0){
    	myToast("请选中一条数据");
    }else{
    	$("#delete_commodityId").val(rowData[0].commodityId);
    	$("#delete_name").html(rowData[0].commodityName);
    	$("#deleteModal").modal("show");//js 控制显示模态框
    }
	
   });


//删除商品确认按钮 信息修改
$("#deleteButton").click(function () {
    	   var formData = new FormData();
    		formData.append("commodityId", $("#delete_commodityId").val());
    		$.ajax({
    		      type: "post",
    		      dataType: "json",
    		      url : "../admin/commodity/deleteCommodity.json",
    		      data:formData,
    		      processData: false,//必须有
    	          contentType: false,//必须有
    		      success: function (data) {
    		    	  if(data.result.status==0){
    		    		  $("#deleteModal").modal("hide");//js 控制隐藏模态框
    		    		  setTimeout(myToast(data.result.msg),10000);
    		    		  $('#t_table').bootstrapTable('refresh', {url: '../admin/commodity/findByPage.json'});//数据刷新
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
	    	$("#update_commodityId").val(rowData[0].commodityId);
	    	$("#update_commodityName").val(rowData[0].commodityName);
	    	$("#update_price").val(rowData[0].price);
	    	$("#update_discountPrice").val(rowData[0].discountPrice);
	    	$("#update_amount").val(rowData[0].amount);
	    	$("#update_allowance").val(rowData[0].allowance);
	    	$("#update_virtualSales").val(rowData[0].virtualSales);
	    	$("#update_specification").val(rowData[0].specification);
	    	$("#update_description").val(rowData[0].description);
	    	$("#update_imgPre").attr('src',rowData[0].commodityImg);
	    	if (rowData[0].classify!=null) {
	    		$("#update_classify").find("option[value='"+rowData[0].classify+"']").attr("selected",true);
			}else{
				$("#update_classify").find("option[value='']").attr("selected",true);
			}
	   
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
    	  update_commodityName: {
              message: '商品名不合法',
              validators: {
                  notEmpty: {
                      message: '商品名不能为空'
                  },
                  stringLength: {
                      min: 3,
                      max: 16,
                      message: '请输入3到16个字符'
                  },
                  regexp: {
                      regexp: /^[A-Za-z0-9\u4e00-\u9fa5]+$/,
                      message: '商品名只能由字母、数字和汉字组成 '
                  }
              }
          }, 
          update_classify: {
              message: '类目不合法',
              validators: {
                  notEmpty: {
                      message: '类目不能为空'
                  }
              }
          },  
          update_price: {
          	  message:'普通价格不合法',
                validators: {
                    notEmpty: {
                        message: '普通价格不能为空'
                    },
                    regexp: {
                        regexp: /[0-9]+(\.[0-9]{1})[0-9]?$/,
                        message: '请输入 非负数 小数点后保留两位'
                    },
                }
          }, 
          update_discountPrice: {
        	  message:'折后价格不合法',
              validators: {
                  notEmpty: {
                      message: '折后价格不能为空'
                  },
                  
                  regexp: {
                      regexp: /[0-9]+(\.[0-9]{1})[0-9]?$/,
                      message: '请输入 非负数 小数点后保留两位'
                  },
                /*  callback: {自定义，可以在这里与其他输入项联动校验
                      message: '供销商价格不能大于普通价格',
                      callback:function(value, validator,$field){
                    	  if(add_price==undefined ||add_discountPrice==undefined){
                              return true;
                          }else{
                          	return add_discountPrice<=add_price;
                          }
                      }
                  }*/
              }
        },
        update_amount: {
	        	  message:'总数量输入有误',
	              validators: {
	                  notEmpty: {
	                      message: '总数量不能为空'
	                  },
	                  stringLength: {
	                        max: 5,
	                        message: '输入需小于5个字符'
	                    },
	                  regexp: {
	                      regexp: /^\d+(\.{0,1}\d+){0,1}$/,
	                      message: '请输入正确的总数量 非负数'
	                  },
	              }
	        }, 
	        update_allowance: {
	      	  message:'库存输入不合法',
	            validators: {
	                notEmpty: {
	                    message: '库存不能为空'
	                },
	                stringLength: {
                      max: 5,
                      message: '输入需小于5个字符'
                  },
	                regexp: {
	                    regexp: /^\d+(\.{0,1}\d+){0,1}$/,
	                    message: '请输入正确的库存 非负数'
	                },
	              /*  callback: {自定义，可以在这里与其他输入项联动校验
	                    message: '库存数量不能大于总数量',
	                    callback:function(value, validator,$field){
	                  	  if(scope.add_amount==undefined ||scope.add_allowance==undefined){
	                            return true;
	                        }else{
	                        	return scope.add_allowance<=scope.add_amount;
	                        }
	                    }
	                }*/
	            }
	      },
	      update_virtualSales: {
        	  message:'虚拟销量输入有误',
              validators: {
                  notEmpty: {
                      message: '虚拟销量不能为空'
                  },
                  stringLength: {
                        max: 5,
                        message: '输入需小于5个字符'
                    },
                  regexp: {
                      regexp: /^\d+(\.{0,1}\d+){0,1}$/,
                      message: '请输入正确的虚拟销量 非负数'
                  },
              }
        }, 
	      update_specification: {
              validators: {
                  notEmpty: {
                      message: '商品规格不能为空'
                  }, stringLength: {
                      min: 2,
                      max: 15,
                      message: '请输入2到15个字符'
                  }
              }
          },
          update_description: {
              validators: {
              	  notEmpty: {
                        message: '商品描述不能为空'
                    }, stringLength: {
                        max: 100,
                        message: '请输入小于100个字符'
                    }
              }
          },
          file1:{
              validators: {
//                  notEmpty: {
//                      message: '上传图片不能为空'
//                  },
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
      }
  });
 }
	//表单验证结束
});





//编辑确认按钮绑定 商品更新
$("#updateButton").click(function () {
	   var bootstrapValidator = $("#update_form_data").data('bootstrapValidator');
	   bootstrapValidator.validate();
	  if (($("#update_form_data").data('bootstrapValidator')).isValid()) {//获取验证结果，如果成功，执行下面代码 
		  $("#loadingModal").modal("show");//js 控制隐藏模态框
  	   var formData1=new FormData($('#update_form_data'));//使用FormData提交表单并上传文件  var formData=new FormData($('#form_data')[0]);
  	     formData1.append('commodityid', $("input[name='update_commodityId']").val());
  	   	 formData1.append('commodityName', $("input[name='update_commodityName']").val());
  	   	 formData1.append('classify', $("#update_classify").val());
         formData1.append('price', $("input[name='update_price']").val());
         formData1.append('discountPrice', $("input[name='update_discountPrice']").val());
         formData1.append('amount', $("input[name='update_amount']").val());
         formData1.append('allowance', $("input[name='update_allowance']").val());
         formData1.append('virtualSales', $("input[name='update_virtualSales']").val());
         formData1.append('specification', $("input[name='update_specification']").val());
         formData1.append('description', $("textarea[name='update_description']").val());
         if($('#file1')[0].files[0] != undefined){
        	 formData1.append('file1', $('#file1')[0].files[0]);//非常重要
         }
  		$.ajax({
  		      type: "post",
  		      dataType: "json",
  		      url : "../admin/commodity/updateCommodity.json",
  		      data:formData1,
  		      processData: false,//必须有
  	          contentType: false,//必须有
  		      success: function (data) {
  		    	 $("#loadingModal").modal("hide");//js 控制隐藏模态框
  		    	  if(data.result.status==0){
  		    		  $('#update_form_data').bootstrapValidator('resetForm', true);//重置表单
  		    		  $("#update_imgPre").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		  $("#updateModal").modal("hide");//js 控制隐藏模态框
  		    		  setTimeout(myToast(data.result.msg),10000);
  		    		  setTimeout($('#t_table').bootstrapTable('refresh', {url: '../admin/commodity/findByPage.json'}),10000);//数据刷新
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

//商品详情图按钮事件
$('#btn_detailImg').click(function(){
	  var rowData= $("#t_table").bootstrapTable('getSelections'); 
	    if(rowData.length<=0){
	    	myToast("请选中一条数据");
	    }else{
	    	$("#detailImg_detailsId").val("");
			$.ajax({
			      type: "post",
			      dataType: "json",
			      url : "../admin/commodity/commodityDetailImg.json",
			      data: {commodityId:rowData[0].commodityId},
			/*      processData: false,//必须有
		          contentType: false,//必须有
*/			      success: function (data) {
			    	  if(data.result.status==0){
			    		  	 $("#detailImg_detailsId").val(data.object.detailsId);
			    		  	 if (data.object.details01 !=null && data.object.details01 !='') {
			    		  		$("#detailImg_imgPre01").attr('src',data.object.details01); //图片回显
							} 
			    		  	 if (data.object.details02 !=null && data.object.details02 !='') {
								$("#detailImg_imgPre02").attr('src',data.object.details02); //图片回显
							} 
			    		  	 if (data.object.details03 !=null && data.object.details03 !='') {
			    		  		 $("#detailImg_imgPre03").attr('src',data.object.details03); //图片回复默认
							} 
			    		  	 if (data.object.details04 !=null && data.object.details04 !='') {
			    		  		 $("#detailImg_imgPre04").attr('src',data.object.details04); //图片回复默认
							} 
			    		  	 if (data.object.details05 !=null && data.object.details05 !='') {
			    		  		$("#detailImg_imgPre05").attr('src',data.object.details05); //图片回复默认
							} 
			    		  	 if (data.object.details06 !=null && data.object.details06 !='') {
			    		  		 $("#detailImg_imgPre06").attr('src',data.object.details06); //图片回复默认
							} 
			    		  	 if (data.object.details07 !=null && data.object.details07 !='') {
			    		  		 $("#detailImg_imgPre07").attr('src',data.object.details07); //图片回复默认
							} 
			    		  	 if (data.object.details08 !=null && data.object.details08 !='') {
			    		  		 $("#detailImg_imgPre08").attr('src',data.object.details08); //图片回复默认
							} 
			    		  	 if (data.object.details09 !=null && data.object.details09 !='') {
			    		  		 $("#detailImg_imgPre09").attr('src',data.object.details09); //图片回复默认
							} 
			    		  	 if (data.object.details10 !=null && data.object.details10 !='') {
			    		  		 $("#detailImg_imgPre10").attr('src',data.object.details10); //图片回复默认
							} 
			    	  }
			      },
			});
			var isDetailsId= $("#detailImg_detailsId").val();
			if (isDetailsId==null||isDetailsId==undefined||isDetailsId=="") {
				 	$("#detailImg_imgPre01").attr('src',"../static/img/noimage.png"); //图片回复默认
		    		 $("#detailImg_imgPre02").attr('src',"../static/img/noimage.png"); //图片回复默认
		    		 $("#detailImg_imgPre03").attr('src',"../static/img/noimage.png"); //图片回复默认
		    		 $("#detailImg_imgPre04").attr('src',"../static/img/noimage.png"); //图片回复默认
		    		 $("#detailImg_imgPre05").attr('src',"../static/img/noimage.png"); //图片回复默认
		    		 $("#detailImg_imgPre06").attr('src',"../static/img/noimage.png"); //图片回复默认
		    		 $("#detailImg_imgPre07").attr('src',"../static/img/noimage.png"); //图片回复默认
		    		 $("#detailImg_imgPre08").attr('src',"../static/img/noimage.png"); //图片回复默认
		    		 $("#detailImg_imgPre09").attr('src',"../static/img/noimage.png"); //图片回复默认
		    		 $("#detailImg_imgPre10").attr('src',"../static/img/noimage.png"); //图片回复默认
			}
	    	$("#detailImg_commodityId").val(rowData[0].commodityId);
	    	$("#detailImg_commodityName").val(rowData[0].commodityName);
	   
	   
	$("#detailImgModal").modal("show");//js 控制显示模态框
	//表单验证
	$('#detailImg_form_data').bootstrapValidator({
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
          file01:{
              validators: {
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
          file02:{
              validators: {
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
          file03:{
              validators: {
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
          file04:{
              validators: {
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
          file05:{
              validators: {
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
          
          file06:{
              validators: {
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
          file07:{
              validators: {
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
          file08:{
              validators: {
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
          file09:{
              validators: {
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
          file10:{
              validators: {
                  file: {
                      extension: 'png,jpg,jpeg',
                      type: 'image/png,image/jpg,image/jpeg',
                      message: '注意格式，请重新选择图片'
                  }
              }
          },
      }
  });
 }
	//表单验证结束
});

//商品详情确认认按钮绑定 
$("#detailImgButton").click(function () {
	   var bootstrapValidator = $("#detailImg_form_data").data('bootstrapValidator');
	   bootstrapValidator.validate();
	  if (($("#detailImg_form_data").data('bootstrapValidator')).isValid()) {//获取验证结果，如果成功，执行下面代码
		  $("#loadingModal").modal("show");//js 控制隐藏模态框
  	   var formData=new FormData($('#detailImg_form_data'));//使用FormData提交表单并上传文件  var formData=new FormData($('#form_data')[0]);
  	   formData.append('commodityId', $("input[name='detailImg_commodityId']").val());//商品ID
  	   formData.append('detailsId', $("input[name='detailImg_detailsId']").val());//商品详情图ID
         if($('#file01')[0].files[0] != undefined){
        	 formData.append('file01', $('#file01')[0].files[0]);//非常重要
         }
         if($('#file02')[0].files[0] != undefined){
        	 formData.append('file02', $('#file02')[0].files[0]);//非常重要
         }
         if($('#file03')[0].files[0] != undefined){
        	 formData.append('file03', $('#file03')[0].files[0]);//非常重要
         }
         if($('#file04')[0].files[0] != undefined){
        	 formData.append('file04', $('#file04')[0].files[0]);//非常重要
         }
         
         if($('#file05')[0].files[0] != undefined){
        	 formData.append('file05', $('#file05')[0].files[0]);//非常重要
         }
         if($('#file06')[0].files[0] != undefined){
        	 formData.append('file06', $('#file06')[0].files[0]);//非常重要
         }
         if($('#file07')[0].files[0] != undefined){
        	 formData.append('file07', $('#file07')[0].files[0]);//非常重要
         }
         if($('#file08')[0].files[0] != undefined){
        	 formData.append('file08', $('#file08')[0].files[0]);//非常重要
         }
         
         if($('#file09')[0].files[0] != undefined){
        	 formData.append('file09', $('#file09')[0].files[0]);//非常重要
         }
         if($('#file10')[0].files[0] != undefined){
        	 formData.append('file10', $('#file10')[0].files[0]);//非常重要
         }
  		$.ajax({
  		      type: "post",
  		      dataType: "json",
  		      url : "../admin/commodity/addCommodityDetailImg.json",
  		      data:formData,
  		      processData: false,//必须有
  	          contentType: false,//必须有
  		      success: function (data) {
  		    	 $("#loadingModal").modal("hide");//js 控制隐藏模态框
  		    	  if(data.result.status==0){
  		    		 $('#detailImg_form_data').bootstrapValidator('resetForm', true);//重置表单
  		    		 $("#detailImg_imgPre01").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		 $("#detailImg_imgPre02").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		 $("#detailImg_imgPre03").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		 $("#detailImg_imgPre04").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		 $("#detailImg_imgPre05").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		 $("#detailImg_imgPre06").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		 $("#detailImg_imgPre07").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		 $("#detailImg_imgPre08").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		 $("#detailImg_imgPre09").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		 $("#detailImg_imgPre10").attr('src',"../static/img/noimage.png"); //图片回复默认
  		    		  $("#detailImgModal").modal("hide");//js 控制隐藏模态框
  		    		  setTimeout(myToast(data.result.msg),10000);
  		    		  setTimeout($('#t_table').bootstrapTable('refresh', {url: '../admin/commodity/findByPage.json'}),10000);//数据刷新
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






