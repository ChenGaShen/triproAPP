package com.menglin.invest.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.menglin.invest.entity.UserFeedback;
import com.menglin.invest.service.impl.UserFeedBackService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.PageBean;
import com.menglin.invest.util.Result;
import com.menglin.invest.vo.PageRuslt;
import com.menglin.invest.vo.ResultVN;




@Controller  
@RequestMapping("/xdadmin/userFeedBack")
public class UserFeedBackController {
	@Resource
	private UserFeedBackService userFeedBackService;
	
	
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<UserFeedback> findByPage(Integer currentPage, Integer pageSize,UserFeedback model,String startTime,String endTime,HttpServletRequest request){
		PageRuslt<UserFeedback> pageRuslt =new PageRuslt<UserFeedback>();
		PageBean<UserFeedback> PageUserFeedback=userFeedBackService.findByPage(currentPage, pageSize, model,startTime,endTime);
		pageRuslt.setPageBean(PageUserFeedback);
      return pageRuslt;
       
	}
	
	/**
	 * 批量删除/单选删除
	 * @author CGS
	 * @time 2018年4月20日下午4:18:20
	 * @param userFedIdS
	 * @return
	 */
	@RequestMapping(value="/batchRemove.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN batchRemove(int[] userFedIdS){
		ResultVN vn =new ResultVN();
		boolean flag=userFeedBackService.deleteAll(userFedIdS);
		if (flag) {
			vn.setResult(Result.suc("删除成功"));
		}else{
			vn.setResult(Result.fal("删除失败"));
		}
      return vn;
       
	}
	
	
	@RequestMapping(value="/updateUserFeedbackInfo.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN updateUserFeedbackInfo(UserFeedback model){
		ResultVN vn =new ResultVN();
		UserFeedback feedback =userFeedBackService.get(model.getUserFedId());
		if (CheckData.isNotNullOrEmpty(feedback)) {
		
			if (CheckData.isNotNullOrEmpty(model.getSign())) {
				feedback.setSign(model.getSign());
			}
			userFeedBackService.update(feedback);
			vn.setResult(Result.suc("标记成功!!"));
	      return vn;
		}else{
			vn.setResult(Result.fal("标记失败!!"));
		}
      return vn;
       
	}
	
	
	
	// 导出excel表格操作
	@RequestMapping ( value="/outExport.json",method = {RequestMethod.POST})
	public @ResponseBody List<UserFeedback> outExport(UserFeedback model,String startTime,String endTime){
		List<UserFeedback> reExcelVOs = userFeedBackService.outExport(model,startTime,endTime);
		return reExcelVOs;
	}
	
	
}
