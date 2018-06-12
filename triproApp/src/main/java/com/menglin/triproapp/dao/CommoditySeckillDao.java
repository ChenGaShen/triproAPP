package com.menglin.triproapp.dao;

import java.util.HashMap;
import java.util.List;

import com.menglin.triproapp.entity.Commodity;
import com.menglin.triproapp.entity.CommoditySeckill;

public interface CommoditySeckillDao {
    int deleteByPrimaryKey(Integer commodityseckillId);

    int insert(CommoditySeckill record);

    int insertSelective(CommoditySeckill record);

    CommoditySeckill selectByPrimaryKey(Integer commodityseckillId);

    int updateByPrimaryKeySelective(CommoditySeckill record);

    int updateByPrimaryKeyWithBLOBs(CommoditySeckill record);

    int updateByPrimaryKey(CommoditySeckill record);
    
    
    List<CommoditySeckill> selectCommoditySeckillList(HashMap<String,Object> map);
    
    List<CommoditySeckill> selectAllCommoditySeckill();
    
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
    List<CommoditySeckill> findByPage(HashMap<String,Object> map);
}