$(document).ready(function(){
	
	//截取路径参数
	var uid = getUrlParam('uid');
	if(uid!=null && uid !=""){
		loadInfo(uid);//数据载入
	}
	
	//表单验证
	$('#t_form').bootstrapValidator({
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
            /*loginname: {
                message: '用户名不合法',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: '请输入3到30个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                        message: '用户名只能由字母、数字、点、下划线和汉字组成 '
                    }
                }
            }, */
            idCard: {
            	  message:'身份证验证失败',
                  validators: {
                     /* notEmpty: {
                          message: '身份证不能为空'
                      },*/
                      stringLength: {
                          min: 15,
                          max: 18,
                          message: '请输入15位或者18位身份证号码'
                      },
                      regexp: {
                          regexp: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,
                          message: '请输入正确的身份证号码'
                      }
                  }
            }, 
            userPhone: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    isPhone : true
                }
            }, 
            phoneBelong: {
                validators: {
                    notEmpty: {
                        message: '地址不能为空'
                    }, stringLength: {
                        min: 2,
                        max: 15,
                        message: '请输入2到15个字符'
                    }
                }
            },
            //备注
            remark: {
                message: '备注验证失败',
                validators: {
                    stringLength: {
                        max: 50,
                        message: '备注长度必须小于50个字符'
                    }
                }
            },
          /*  file: {
                validators: {
                    notEmpty: {
                        message: '上传图片不能为空'
                    },
                    file: {
                        extension: 'png,jpg,jpeg',
                        type: 'image/png,image/jpg,image/jpeg',
                        message: '请重新选择图片'
                    }
                }
            },*/
        }
    });
	//表单验证结束
	
	//提交按钮绑定 信息修改
	$("#to_submit").click(function () {
			var bootstrapValidator = $("#t_form").data('bootstrapValidator');
		    bootstrapValidator.validate();
//	       $("#form-test").bootstrapValidator('validate');//提交验证   //非submit按钮点击后进行验证，如果是submit则无需此句直接验证  
	       if ($("#t_form").data('bootstrapValidator').isValid()) {//获取验证结果，如果成功，执行下面代码  
	    	   var formData = new FormData();
	    		formData.append("uid", $("#uid").val());
	    		formData.append("remark", $("textarea[name='remark']").val());
	    		formData.append("idCard", $("#idCard").val());
	    		formData.append("state", $('input[name="styleshoice2"]:checked').val());// 账户状态
	    		$.ajax({
	    		      type: "post",
	    		      dataType: "json",
	    		      url : "../admin/user/updateUserInfo.json",
	    		      data:formData,
	    		      processData: false,//必须有
	    	          contentType: false,//必须有
	    		      success: function (data) {
	    		    	  if(data.status==0){
	    		    		  myToast(data.msg);
	    		    	  }else{
	    		    		  myToast(data.msg);
	    		    	  }
	    		      },
	    		       error : function() {  
	    		    	   console.log("数据加载失败");
	    	}  
	    		});
	       }  
	   }); 
});

function loadInfo(uid) {
	$.ajax({
	      type: "post",
	      dataType: "json",
	      url : "../admin/user/showUser.json",
	      data:{"uid":uid},
	      success: function (data) {
	    	  $("#uid").val(data.uid);
	    	  $("#userPhone").val(data.userPhone);
	    	  $("#phoneBelong").val(data.phoneBelong);
	    	  switch(data.identity){
                case 1:
        	    	  $("#identity").val("个人用户");
                	break;
                case 2:
        	    	  $("#identity").val("供销商");
                	break;
                }
	    	  $("#remark").val(data.remark);
	    	  $("#addTime").val(formatDateTime(data.addTime));
	    	  $("#loginTime").val(formatDateTime(data.loginTime));
	    	  $("#idCard").val(data.idCard);
	    	  
	    	  //账户状态
	    	switch(data.state){
              case 1:
                $("input[name='styleshoice2'][value=1]").attr("checked",true); 
              	break;
              case 2:
              	 $("input[name='styleshoice2'][value=2]").attr("checked",true); 
              	break;
              }
	    	//审核状态
	    	switch(data.audit){
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
	    	if(null!=data.businessImg || null!=data.idCardImg || null!=data.idCardImgB ){
	    		$("#businessImg").attr('src',data.businessImg); 
		    	$("#idCardImg").attr('src',data.idCardImg); 
		    	$("#idCardImgB").attr('src',data.idCardImgB); 
		    	//打开
		    	$("#bImg").attr("href",data.businessImg);
		    	$("#IdImgA").attr("href",data.idCardImg);
		    	$("#IdImgB").attr("href",data.idCardImgB);
	    	}
	      },
	       error : function() {  
	    	   console.log("数据加载失败");
}  
	});
}


