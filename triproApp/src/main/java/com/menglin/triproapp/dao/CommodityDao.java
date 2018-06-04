package com.menglin.triproapp.dao;

import java.util.HashMap;
import java.util.List;

import com.menglin.triproapp.entity.Admin;
import com.menglin.triproapp.entity.Commodity;
import com.menglin.triproapp.entity.CommodityDetails;

public interface CommodityDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKeyWithBLOBs(Commodity record);

    int updateByPrimaryKey(Commodity record);
    
    int soldOutByIds(Integer [] ids);
    
    List<Commodity> selectCommodityList(HashMap<String,Object> map);
    
    /**
     * 查询用户记录总数(条件)
     * @return
     */
    int selectCount(HashMap<String,Object> map);
    
    /**
     * 分页操作，调用findByPage limit分页方法
     * @param map
     * @return
     */
    List<Commodity> findByPage(HashMap<String,Object> map);
}