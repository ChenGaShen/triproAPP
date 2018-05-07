package com.menglin.tripro.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.menglin.tripro.dao.ValidateDao;
import com.menglin.tripro.entity.User;
import com.menglin.tripro.entity.Validate;
import com.menglin.tripro.service.IValidateService;
import com.menglin.tripro.util.CheckData;
import com.menglin.tripro.util.DateUntil;
import com.menglin.tripro.util.PageBean;
import com.menglin.tripro.util.UtilMethod;

@Service("validateService")
public class ValidateService implements IValidateService {
	
	@Resource  
    private ValidateDao validateDao;
	
	
	/**
	 * 是否存在未验证的
	 */
	@Override
	public Validate isNoValidate(String userPhone, int validateType) {
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userPhone", userPhone);
		map.put("validateType", validateType);
		Validate validate =validateDao.selectByCoent(map);
		return validate;
	}
	/**
	 * 发送验证码
	 */
	@Override
	public boolean verification(String phone, int validateType) {
			try {
//				String validateCode = "123456";
//				boolean flag=true;
				String validateCode = (int) Math.ceil(Math.random() * 899999 + 100000)+ "";//生成验证码
				boolean flag =UtilMethod.endpoints(validateCode, phone);
				if (flag) { 
					//短信发送成功
					Validate listEn = new Validate();
					listEn.setPhone(phone);
					listEn.setValidateCode(validateCode);
					listEn.setValidateType(validateType);
					listEn.setState(1);
					listEn.setAddTime(new Date());
					validateDao.insert(listEn);
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				 e.printStackTrace();
			} 
		return false;
	}
	/**
	 * 校验 验证码,注册时候使用
	 */
	@Override
	public Validate doValidateCode(Validate validate) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("userPhone", validate.getPhone());
		map.put("validateType", validate.getValidateType());
		map.put("validateCode", validate.getValidateCode());
		return validateDao.doValidateCode(map);
	}
	
	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	@Override
	public int save(Validate record) {
		 return validateDao.insertSelective(record);
	}
	
	@Override
	public Validate get(Integer id) {
		
		return validateDao.selectByPrimaryKey(id);
	}
	
	@Override
	public int update(Validate record) {
		return validateDao.updateByPrimaryKeySelective(record); //返回受影响的行数 0为操作失败
	}
	
	
	@Override
	public PageBean<Validate> findByPage(Integer currentPage, Integer pageSize,Validate model,String startTime,String endTime) {
		
		
		
		if(CheckData.isNotEmptyString(startTime) && CheckData.isNotEmptyString(endTime)){
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		if (CheckData.isEmpty(startTime)&&CheckData.isNotEmpty(endTime)) {
			endTime=DateUntil.addOneDay(endTime);//查询时 由于00:00 小的缘故，时间增加一天
		}
		//封装每页显示的数据
        HashMap<String,Object> map1 = new HashMap<String,Object>();
        map1.put("userPhone", model.getPhone());
        map1.put("startTime", startTime);
        map1.put("endTime", endTime);
        //总记录数
        int totalCount = validateDao.selectCount(map1);
        
        //封装每页显示的数据
        HashMap<String,Object> map = new HashMap<String,Object>();
        
        //初始化数据
    	if(currentPage == null || currentPage<=0){
    		currentPage=1;
		}
    	if(pageSize == null || pageSize<=0){
    		pageSize=10;//默认显示10条数据
		}
    	
        map.put("start",(currentPage-1)*pageSize);
        map.put("size", pageSize);
        map.put("userPhone", model.getPhone());
        map.put("startTime", startTime);
        map.put("endTime", endTime);
       
        List<Validate> lists = validateDao.findByPage(map);
        
        PageBean<Validate> pageBean = new PageBean<Validate>(currentPage, pageSize,totalCount);
        
        pageBean.setPageList(lists);

        return pageBean;
	}

}
