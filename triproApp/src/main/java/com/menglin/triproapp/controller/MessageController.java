package com.menglin.triproapp.controller;

import java.util.Date;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglin.triproapp.entity.ActiveRed;
import com.menglin.triproapp.entity.Message;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IMessageService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.PageRuslt;
import com.menglin.triproapp.vo.ResultVN;

/** 
 * @author CGS 
 * @date 2018年2月10日 上午10:59:43 
 */
@Controller  
@RequestMapping("/admin/message")
public class MessageController {
	
	@Resource  
    private IMessageService messageService;
	
	
	/**
	 * 消息列表分页查询
	 * @author CGS
	 * @time 2018年6月13日上午10:25:43
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<Message> findByPage(int currentPage, int pageSize,Message model){
		PageRuslt<Message> pageRuslt =new PageRuslt<Message>();
		PageBean<Message> PageUser=messageService.findByPage(currentPage, pageSize, model);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
	}    
	
	/**
	 * 发布公告消息
	 * @author CGS
	 * @time 2018年6月13日上午10:51:02
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addNoticeInfo.json", method = { RequestMethod.POST })
	public @ResponseBody ResultVN addNoticeInfo(Message model) {
		ResultVN vn = new ResultVN();
		model.setType(1); //消息类型 0私有 1公共 2订单
		model.setState(0);//状态：0未读1已读2跟进
		model.setAddTime(new Date());
		messageService.save(model);
		vn.setResult(Result.suc("公告消息发布成功!!"));
		return vn;
	}
	
	/**
	 * 公告消息更新
	 * @author CGS
	 * @time 2018年6月13日下午1:59:26
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateNoticeInfo.json", method = { RequestMethod.POST })
	public @ResponseBody ResultVN updateNoticeInfo(Message model,Integer messageId) {
		ResultVN vn = new ResultVN();
		Message message =messageService.get(messageId);
		if (CheckData.allfieldIsNotNUll(message)) {
			message.setTitle(model.getTitle());
			message.setContent(model.getContent());
			messageService.update(message);
			vn.setResult(Result.suc("公告消息更新成功!!"));
		}else{
			vn.setResult(Result.fal("更新失败!!"));
		}
		return vn;
	}
	
	/**
	 * 删除公告消息
	 * @author CGS
	 * @time 2018年6月13日上午11:25:19
	 * @param messageId
	 * @return
	 */
	@RequestMapping(value = "/deleteNoticeInfo.json", method = { RequestMethod.POST })
	public @ResponseBody ResultVN deleteNoticeInfo(Integer messageId) {
		ResultVN vn = new ResultVN();
		messageService.delete(messageId);
		vn.setResult(Result.suc("公告消息删除成功!!"));
		return vn;
	}
	
	
}
