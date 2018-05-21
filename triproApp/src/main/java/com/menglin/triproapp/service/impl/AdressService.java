package com.menglin.triproapp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.menglin.triproapp.dao.AdressDao;
import com.menglin.triproapp.entity.Adress;
import com.menglin.triproapp.service.IAdressService;

/** 
 * @author CGS 
 * @date 2018年1月31日 下午3:22:51 
 */
@Service("adressService")
public class AdressService implements IAdressService {
	
	@Resource
	private  AdressDao adressDao;
	
	@Override
	public void delete(Integer id) {
		
		 adressDao.deleteByPrimaryKey(id);
	}

	@Override
	public void save(Adress record) {
		
		 adressDao.insertSelective(record);
	}

	@Override
	public Adress get(Integer id) {
		
		return adressDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(Adress record) {
		
		 adressDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Adress> addressList(Integer uid) {
		
		return adressDao.selectAddressList(uid);
	}

	@Override
	public void setDefault(Integer addressId, Integer uid) {
		adressDao.removeDefaultByUid(uid);
		adressDao.setDefaultByPrimaryKey(addressId);
		
	}
	
	@Override
	public Adress getDefault(Integer uid) {
		return adressDao.getDefaultByPrimaryKey(uid);
		
	}

}
