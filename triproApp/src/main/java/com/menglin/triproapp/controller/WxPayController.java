package com.menglin.triproapp.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglin.triproapp.entity.PayWx;
import com.menglin.triproapp.service.IPayWxService;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.PageRuslt;
import com.menglin.triproapp.vo.PayWxVO;

/** 
 * @author CGS 
 * @date 2018年3月8日 上午9:58:37 
 */
@Controller  
@RequestMapping("/admin/wxpay")
public class WxPayController {

	
	@Resource  
    private IPayWxService payWxService;
	
	/**
	 * 分页查询注册时验证码信息
	 * @author CGS
	 * @time 2018年3月8日上午10:08:00
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return.
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<PayWx> findByPage(Integer currentPage, Integer pageSize,PayWx model,String startTime,String endTime){
		System.out.println("findByPage 方法");
		System.out.println(SystemParam.DOMAIN_NAME);
		PageRuslt<PayWx> pageRuslt =new PageRuslt<PayWx>();
		PageBean<PayWx> PageUser=payWxService.findByPage(currentPage, pageSize, model,startTime,endTime);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
       
	}
	// 导出excel表格操作
	@RequestMapping (value="/outExport.json",method = {RequestMethod.POST})
	public @ResponseBody List<PayWxVO> outExport(PayWx model,String startTime,String endTime){
		List<PayWxVO> reExcelVOs = payWxService.outExport(model,startTime,endTime);
		return reExcelVOs;
	}
	
}
