package com.menglin.tripro.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.menglin.tripro.entity.Order;
import com.menglin.tripro.service.IOrderService;
import com.menglin.tripro.util.CheckData;
import com.menglin.tripro.util.Export;
import com.menglin.tripro.util.ExportUtils;
import com.menglin.tripro.util.PageBean;
import com.menglin.tripro.util.Result;
import com.menglin.tripro.vo.OrederDetailVO;
import com.menglin.tripro.vo.PageRuslt;
import com.menglin.tripro.vo.ResultVN;

/** 
 * @author CGS 
 * @date 2018年2月7日 下午3:48:00 
 */
@Controller  
@RequestMapping("/admin/order")
public class OrderController {
	
	
	@Resource  
    private IOrderService orderService;
	
	
	/**
	 * 订单列表分页
	 * @author CGS
	 * @time 2018年3月22日下午2:02:57
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<OrederDetailVO> findByPage(Integer currentPage, Integer pageSize,Order model,String orderId,String startTime,String endTime){
		PageRuslt<OrederDetailVO> pageRuslt =new PageRuslt<OrederDetailVO>();
		PageBean<OrederDetailVO> PageUser=orderService.findByPage(currentPage, pageSize, model,orderId,startTime,endTime);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
       
	}
	
	
	/**
	 * 发货时运单号和公司录入
	 * @author CGS
	 * @time 2018年2月7日下午4:15:08
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/doAirAndCompany.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN doAirAndCompany(Order model,String orderId){
		ResultVN vn =new ResultVN();
		Order order=orderService.get(orderId);
		if (CheckData.isNotNullOrEmpty(order)) {
				if (CheckData.isNotNullOrEmpty(model.getAir()) && CheckData.isNotNullOrEmpty(model.getCompany()) ) {
				order.setAir(model.getAir());// 运单号
				order.setCompany(model.getCompany());// 快递公司 eg:yd，sf,不能是韵达，顺丰，必须是简称
				order.setReceiveState(1);
				orderService.update(order);
				vn.setResult(Result.suc("录入成功!!"));
				return vn;
				}else{
					vn.setResult(Result.fal("物流录入信息不能为空"));
					return vn;
				}
		}else{
			vn.setResult(Result.fal("订单号有误!!"));
			return vn;
		}
        
       
	}
	@RequestMapping(value="/updateRemark.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN updateRemark(String orderId,Order model){
		ResultVN vn =new ResultVN();
		Order order=orderService.get(orderId);
		if (CheckData.isNotNullOrEmpty(order)) {
			 if (CheckData.isNotNullOrEmpty(model.getReceiveName())) {
				 order.setReceiveName(model.getReceiveName());
			}
			 if (CheckData.isNotNullOrEmpty(model.getReceivePhone())) {
				 order.setReceivePhone(model.getReceivePhone());
			}
			 if (CheckData.isNotNullOrEmpty(model.getReceiveAddress())) {
				 order.setReceiveAddress(model.getReceiveAddress());
			}
			 if (CheckData.isNotNullOrEmpty(model.getRemark())) {
				 order.setRemark(model.getRemark());
			}
			orderService.update(order);
			vn.setResult(Result.suc("信息编辑成功!!"));
		}else{
			vn.setResult(Result.fal("订单号有误!!"));
		}
        return vn;
       
	}
	
	/**
	 * 根据订单ID ,查询订单详情
	 * @author CGS
	 * @time 2018年2月7日上午10:45:36
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/toOrderDetail.json",method = {RequestMethod.POST})
	public @ResponseBody OrederDetailVO toOrderDetail(String orderId){
		OrederDetailVO orederDetailVO=new OrederDetailVO();
		orederDetailVO=orderService.findByOrederId(orderId);
		orederDetailVO.setResult(Result.suc("查询成功!!"));
		return orederDetailVO;
	}
	
	/**
	 * 导出excel表格操作
	 * @author CGS
	 * @time 2018年4月16日上午10:32:32
	 * @param model
	 * @param orderId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value="/doExport.json",method = {RequestMethod.POST})
	public @ResponseBody List<OrederDetailVO> doExport(Order model,String orderId,String startTime,String endTime) {
			List<OrederDetailVO> reExcelVOs = orderService.export(model,orderId,startTime,endTime);
				return reExcelVOs;
		}
}
