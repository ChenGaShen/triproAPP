package com.menglin.triproapp.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.menglin.triproapp.dao.ShoppingDao;
import com.menglin.triproapp.entity.Shopping;
import com.menglin.triproapp.service.IShoppingService;


/** 
 * @author CGS 
 * @date 2018年2月5日 下午3:21:16 
 */
@Service("shoppingService")
public class ShoppingService implements IShoppingService {

	
	@Resource
	private  ShoppingDao shoppingDao;
	
	
	@Override
	public void delete(Integer id) {
		shoppingDao.deleteByPrimaryKey(id);
	}

	@Override
	public void save(Shopping record) {
		shoppingDao.insertSelective(record);

	}

	@Override
	public Shopping get(Integer id) {
		
		return shoppingDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(Shopping record) {
		shoppingDao.updateByPrimaryKeySelective(record);

	}

	@Override
	public Shopping isExitCommodity(Integer commodityId,Integer uid) {
		
		
		 HashMap<String,Object> map = new HashMap<String,Object>();
		 map.put("commodityId", commodityId);
		 map.put("uid", uid);
		
		return shoppingDao.selectByCont(map);
	}

	@Override
	public void deleteCommodity(Integer commodityId, Integer uid) {
		
		 HashMap<String,Object> map = new HashMap<String,Object>();
		 map.put("commodityId", commodityId);
		 map.put("uid", uid);
		 shoppingDao.delShopCommodity(map);
	}

	@Override
	public void emptyCart(Integer uid) {
		shoppingDao.emptyCart(uid);
		
	}

	@Override
	public List<Shopping> ShoppingListByUid(Integer uid) {
		return shoppingDao.selectListByUid(uid);
	}

}
