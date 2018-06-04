package com.menglin.triproapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.menglin.triproapp.dao.CommodityDetailsDao;
import com.menglin.triproapp.dao.UserDao;
import com.menglin.triproapp.entity.CommodityDetails;
import com.menglin.triproapp.service.ICommodityDetailImgService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.CommodityDetailImgVO;

/**
 * 商品详情图
 * @author CGS
 * @time 2018年5月22日下午2:51:45
 */
@Service("commodityDetailImgService")
public class CommodityDetailImgService implements ICommodityDetailImgService {
	
	@Resource
	private  CommodityDetailsDao commodityDetailsDao;
	
	@Resource  
    private UserDao userDao;
	
	
	@Override
	public void deleteByPrimaryKey(Integer id) {
		
		commodityDetailsDao.deleteByPrimaryKey(id);

	}

	@Override
	public void save(CommodityDetails record) {
		commodityDetailsDao.insertSelective(record);

	}

	@Override
	public CommodityDetails get(Integer id) {
		
		return commodityDetailsDao.selectByPrimaryKey(id);
	}

	@Override
	public void update(CommodityDetails record) {
		commodityDetailsDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<String> findByCommodityId(Integer commodityId) {
		List<String> imgVos= new ArrayList<String>();
		 CommodityDetails commodityDetails =commodityDetailsDao.findByCommodityId(commodityId);
		if (CheckData.isNotNullOrEmpty(commodityDetails)) {
			
				if (CheckData.isNotNullOrEmpty(commodityDetails.getDetails01())) {
					imgVos.add(SystemParam.DOMAIN_NAME+commodityDetails.getDetails01());
				} if (CheckData.isNotNullOrEmpty(commodityDetails.getDetails02())) {
					imgVos.add(SystemParam.DOMAIN_NAME+commodityDetails.getDetails02());
				} if (CheckData.isNotNullOrEmpty(commodityDetails.getDetails03())) {
					imgVos.add(SystemParam.DOMAIN_NAME+commodityDetails.getDetails03());
				} if (CheckData.isNotNullOrEmpty(commodityDetails.getDetails04())) {
					imgVos.add(SystemParam.DOMAIN_NAME+commodityDetails.getDetails04());
				} if (CheckData.isNotNullOrEmpty(commodityDetails.getDetails05())) {
					imgVos.add(SystemParam.DOMAIN_NAME+commodityDetails.getDetails05());
				} if (CheckData.isNotNullOrEmpty(commodityDetails.getDetails06())) {
					imgVos.add(SystemParam.DOMAIN_NAME+commodityDetails.getDetails06());
				} if (CheckData.isNotNullOrEmpty(commodityDetails.getDetails07())) {
					imgVos.add(SystemParam.DOMAIN_NAME+commodityDetails.getDetails07());
				} if (CheckData.isNotNullOrEmpty(commodityDetails.getDetails08())) {
					imgVos.add(SystemParam.DOMAIN_NAME+commodityDetails.getDetails08());
				} if (CheckData.isNotNullOrEmpty(commodityDetails.getDetails09())) {
					imgVos.add(SystemParam.DOMAIN_NAME+commodityDetails.getDetails09());
				} if (CheckData.isNotNullOrEmpty(commodityDetails.getDetails10())) {
					imgVos.add(SystemParam.DOMAIN_NAME+commodityDetails.getDetails10());
				}
		}
		return imgVos;
	}

	

}
