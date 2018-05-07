package com.menglin.invest.controller;



import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglin.invest.entity.Information;
import com.menglin.invest.service.impl.InformationService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.PageBean;
import com.menglin.invest.util.Result;
import com.menglin.invest.vo.PageRuslt;
import com.menglin.invest.vo.ResultVN;


@Controller  
@RequestMapping("/xdadmin/information")
public class InformationController {
	
	@Resource 
	private InformationService informationService ;
	
	
	/**
	 * 咨询分页查询 正式
	 * @author CGS
	 * @time 2018年3月14日上午9:40:08
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<Information> findByPage(Integer currentPage, Integer pageSize,Information model,String startTime,String endTime,HttpServletRequest request){
		PageRuslt<Information> pageRuslt =new PageRuslt<Information>();
		PageBean<Information> PageInformation=informationService.findByPage(currentPage, pageSize, model,startTime,endTime);
		pageRuslt.setPageBean(PageInformation);
      return pageRuslt;
       
	}
	
	/**
	 * 新增咨询
	 * @author CGS
	 * @time 2018年4月18日下午2:30:33
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doAdd.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN doAdd(Information model,HttpServletRequest request){
		ResultVN vn= new ResultVN();
			if (CheckData.isNotNullOrEmpty(model)) {
				model.setAddTime(new Date());
				model.setUpdateTime(new Date());
				informationService.save(model);
				vn.setResult(Result.suc("新增成功!!"));
			}else{
				vn.setResult(Result.fal("新增失败!!"));
			}
      return vn;
       
	}
	
	/**
	 * 咨询广告更新
	 * @author CGS
	 * @time 2018年4月18日下午3:57:54
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doUpdate.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN doUpdate(Information model,HttpServletRequest request){
		ResultVN vn= new ResultVN();
		Information information =informationService.get(model.getNewsId());
			if (CheckData.isNotNullOrEmpty(information)) {
				if (CheckData.isNotNullOrEmpty(model.getNewsName())) {
					information.setNewsName(model.getNewsName());
				}
				if (CheckData.isNotNullOrEmpty(model.getNewsClassification())) {
					information.setNewsClassification(model.getNewsClassification());
				}
				if (CheckData.isNotNullOrEmpty(model.getNewsState())) {
					information.setNewsState(model.getNewsState());
				}
				if (CheckData.isNotNullOrEmpty(model.getNewsContent())) {
					information.setNewsContent(model.getNewsContent());
				}
				information.setUpdateTime(new Date());
				informationService.update(information);
				vn.setResult(Result.suc("更新成功!!"));
			}else{
				vn.setResult(Result.fal("更新失败!!"));
			}
      return vn;
       
	}
	
	
	/**
	 * 删除咨询
	 * @author CGS
	 * @time 2018年4月18日下午2:33:18
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doDelete.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN doDelete(Integer newsId,HttpServletRequest request){
		ResultVN vn= new ResultVN();
			if (CheckData.isNotNullOrEmpty(newsId)) {
				informationService.delete(newsId);
				vn.setResult(Result.suc("删除成功!!"));
			}else{
				vn.setResult(Result.fal("删除失败!!"));
			}
      return vn;
       
	}
	
}
