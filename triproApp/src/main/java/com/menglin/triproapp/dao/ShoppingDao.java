package com.menglin.triproapp.dao;

import java.util.HashMap;
import java.util.List;

import com.menglin.triproapp.entity.Shopping;
import com.menglin.triproapp.entity.User;

public interface ShoppingDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Shopping record);

    int insertSelective(Shopping record);

    Shopping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shopping record);

    int updateByPrimaryKey(Shopping record);
    
    	/**
    	 * 购物车中是否存在此商品
    	 * @author CGS
    	 * @time 2018年2月5日下午3:31:17
    	 * @param uid
    	 * @param commodityId
    	 * @return
    	 */
    Shopping  selectByCont(HashMap<String,Object> map);
    
    /**
     * 删除购物车中的商品
     * @author CGS
     * @time 2018年2月5日下午3:50:10
     * @param map
     * @return
     */
    int delShopCommodity(HashMap<String,Object> map);
    
    /**
     * 清空购物车
     * @author CGS
     * @time 2018年2月5日下午5:32:41
     * @param uid
     * @return
     */
    int  emptyCart(Integer uid);
    
    
    int updateAllselecte(HashMap<String,Object> map);
    
    
    List<Shopping> selectListByUid(Integer uid);
}