package com.menglin.triproapp.service;

import java.util.List;

import com.menglin.triproapp.entity.Shopping;

/** 
 * @author CGS 
 * @date 2018年2月5日 下午3:20:18 
 */
public interface IShoppingService {
	
	void delete(Integer id);

    void save(Shopping record);

    Shopping get(Integer id);

    void update(Shopping record);
    
    Shopping isExitCommodity(Integer commodityId,Integer uid);
    
    void deleteCommodity(Integer commodityId,Integer uid);
    
    void  emptyCart(Integer uid);
    
    void updateAllselecte(Integer selected,Integer uid);
    
    List<Shopping> ShoppingListByUid(Integer uid);
}
