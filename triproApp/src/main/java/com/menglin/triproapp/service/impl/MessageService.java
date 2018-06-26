package com.menglin.triproapp.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menglin.triproapp.dao.MessageDao;
import com.menglin.triproapp.dao.OrderDao;
import com.menglin.triproapp.entity.Message;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IMessageService;
import com.menglin.triproapp.util.PageBean;

/** 
 * @author CGS 
 * @date 2018年2月10日 上午10:25:11 
 */
@Service("messageService")
public class MessageService implements IMessageService {

	@Resource
	private  MessageDao messageDao;
	
	@Override
	public Message get(Integer id) {
		
		return messageDao.selectByPrimaryKey(id);
	}

	@Override
	public void save(Message message) {
		messageDao.insertSelective(message);
		
	}

	@Override
	public void delete(Integer id) {
		messageDao.deleteByPrimaryKey(id);

	}

	@Override
	public void update(Message message) {
		messageDao.updateByPrimaryKeySelective(message);

	}

	@Override
	public PageBean<Message> findByPage(Integer currentPage, Integer pageSize, Message model) {
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("type", model.getType());//消息类型 0私有 1公共 2订单
        map1.put("state", model.getState());//消息状态 状态：1未读2已读3跟进
        map1.put("title", model.getTitle());//消息标题   供销商认证    订单处理
        //总记录数
        int totalCount = messageDao.selectCount(map1);
        
        //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        
        //初始化数据
    	if(currentPage<=0){
    		currentPage=1;
		}
    	if(pageSize<=0){
    		pageSize=10;//默认显示10条数据
		}
    	
        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageSize);
        map.put("type", model.getType());//消息类型 0私有 1公共 2订单
        map.put("state", model.getState());//消息状态 状态：1未读2已读3跟进
        map.put("title", model.getTitle());//消息标题   供销商认证    订单处理
       
        List<Message> lists = messageDao.findByPage(map);
        
        PageBean<Message> pageBean = new PageBean<Message>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
	}

	@Override
	public List<Message> messAgeList(Integer uid, Integer type) {
		 HashMap<String,Object> map = new HashMap<String,Object>();
		 	map.put("uid",uid);
	        map.put("type", type);
		return messageDao.selectMessAgeList(map);
	}

	@Override
	public int selectUnreadCount(Integer uid) {
		 HashMap<String,Object> map = new HashMap<String,Object>();
		 	map.put("uid",uid);
		return messageDao.selectUnreadCount(map);
	}

	@Override
	public Message firstOne(Integer uid, Integer type) {
		 HashMap<String,Object> map = new HashMap<String,Object>();
		 	map.put("uid",uid);
	        map.put("type", type);
		return messageDao.firstOne(map);
	}

}
