package com.menglin.tripro.service;

import java.util.List;

import com.menglin.tripro.entity.Adress;

/** 
 * @author CGS 
 * @date 2018年1月31日 下午3:21:08 
 */
public interface IAdressService {
	void delete(Integer id);

    void save(Adress record);

    Adress get(Integer id);

    void update(Adress record);
    /**
     * 设置默认收货地址
     * @author CGS
     * @time 2018年2月1日上午10:59:35
     * @param id
     * @param uid
     * @return
     */
    void  setDefault(Integer id,Integer uid);
    List<Adress> addressList(Integer uid);

    Adress getDefault(Integer uid);

}
