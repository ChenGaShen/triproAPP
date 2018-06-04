package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.vo.UserVO;


public interface IUserService   {
	public User get(int id);
	public void save(User user);
	public void delete(int id);
	public void update(User user);
	public User findUserByPhone(String userPhone);
	public User findUserByOpenId(String openId);
	public List<User> selectUserList();
	PageBean<User> findByPage(Integer currentPage,Integer pageSize,User model,String startTime,String endTime);
	List<UserVO> outExport(User model,String startTime,String endTime);
}
