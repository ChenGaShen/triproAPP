package com.menglin.invest.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.menglin.invest.entity.Product;
import com.menglin.invest.service.IProductService;
import com.menglin.invest.util.CheckData;
import com.menglin.invest.util.PageBean;
import com.menglin.invest.util.Result;
import com.menglin.invest.vo.PageRuslt;
import com.menglin.invest.vo.ResultVN;


@Controller  
@RequestMapping("/xdadmin/product")
public class ProductController {
	@Autowired
	private IProductService productService;
	
	
	/**
	 * 分页查询产品
	 * @author CGS
	 * @time 2018年4月23日下午2:50:29
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<Product> findByPage(Integer currentPage, Integer pageSize,Product model,String startTime,String endTime,HttpServletRequest request){
		PageRuslt<Product> pageRuslt =new PageRuslt<Product>();
		PageBean<Product> PageProduct=productService.findByPage(currentPage, pageSize, model,startTime,endTime);
		pageRuslt.setPageBean(PageProduct);
      return pageRuslt;
       
	}
	
	/**
	 * 产品新增
	 * @author CGS
	 * @time 2018年4月23日下午2:51:01
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doAdd.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN doAdd(Product model,HttpServletRequest request){
		ResultVN vn= new ResultVN();
			if (CheckData.isNotNullOrEmpty(model)) {
				model.setAddTime(new Date());
				model.setUpdateTime(new Date());
				productService.save(model);
				vn.setResult(Result.suc("新增成功!!"));
			}else{
				vn.setResult(Result.fal("新增失败!!"));
			}
      return vn;
       
	}
	
	/**
	 * 产品编辑更新
	 * @author CGS
	 * @time 2018年4月23日下午2:51:18
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doUpdate.json",method={RequestMethod.POST})
	public @ResponseBody ResultVN doUpdate(Product model,HttpServletRequest request){
		ResultVN vn= new ResultVN();
		Product product =productService.get(model.getProductId());
			if (CheckData.isNotNullOrEmpty(product)) {
			/*	if (CheckData.isNotNullOrEmpty(model.getNewsName())) {
					product.setNewsName(model.getNewsName());
				}
				if (CheckData.isNotNullOrEmpty(model.getNewsClassification())) {
					product.setNewsClassification(model.getNewsClassification());
				}
				if (CheckData.isNotNullOrEmpty(model.getNewsState())) {
					product.setNewsState(model.getNewsState());
				}
				if (CheckData.isNotNullOrEmpty(model.getNewsContent())) {
					product.setNewsContent(model.getNewsContent());
				}*/
				product.setUpdateTime(new Date());
				productService.update(product);
				vn.setResult(Result.suc("更新成功!!"));
			}else{
				vn.setResult(Result.fal("更新失败!!"));
			}
      return vn;
       
	}
}
