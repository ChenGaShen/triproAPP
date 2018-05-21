package com.menglin.triproapp.dao;

import java.util.List;

import com.menglin.triproapp.entity.Adress;

public interface AdressDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Adress record);

    int insertSelective(Adress record);

    Adress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Adress record);

    int updateByPrimaryKey(Adress record);
    /**
     * 去除默认设置
     * @author CGS
     * @time 2018年2月1日上午10:57:38
     * @param uid 用户ID
     * @return
     */
    int removeDefaultByUid(Integer uid);
    /**
     * 设置默认地址
     * @author CGS
     * @time 2018年2月1日上午10:58:18
     * @param id 地址ID addressId
     * @return
     */
    int setDefaultByPrimaryKey(Integer addressId);
    
    List<Adress> selectAddressList(Integer uid);
    
    /**
     * 根据用户ID获取默认收货地址
     * @author CGS
     * @time 2018年3月13日下午2:46:28
     * @param uid
     * @return
     */
    Adress getDefaultByPrimaryKey(Integer uid);
}