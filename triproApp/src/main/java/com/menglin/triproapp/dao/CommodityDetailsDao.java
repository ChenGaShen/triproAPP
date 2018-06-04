package com.menglin.triproapp.dao;

import com.menglin.triproapp.entity.CommodityDetails;

public interface CommodityDetailsDao {
    int deleteByPrimaryKey(Integer detailsid);

    int insert(CommodityDetails record);

    int insertSelective(CommodityDetails record);

    CommodityDetails selectByPrimaryKey(Integer detailsid);

    int updateByPrimaryKeySelective(CommodityDetails record);

    int updateByPrimaryKey(CommodityDetails record);
    
    CommodityDetails findByCommodityId(Integer commodityId);
    
    
}