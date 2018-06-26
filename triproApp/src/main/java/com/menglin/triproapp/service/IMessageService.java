package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.ActiveRed;
import com.menglin.triproapp.entity.Message;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.util.PageBean;


/** 
 * @author CGS 
 * @date 2018年2月10日 上午10:22:54 
 */
public interface IMessageService {
	public Message get(Integer id);
	public void save(Message message);
	public void delete(Integer id);
	public void update(Message message);
	List<Message> messAgeList(Integer uid,Integer type);
	public int selectUnreadCount(Integer uid);
	public Message firstOne(Integer uid,Integer type);
	PageBean<Message> findByPage(Integer currentPage,Integer pageSize,Message model);
}
