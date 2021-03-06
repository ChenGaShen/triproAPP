package com.menglin.triproapp.web;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.menglin.triproapp.entity.Shopping;
import com.menglin.triproapp.service.IShoppingService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.vo.OrderJsonVO;
import com.menglin.triproapp.vo.ResultListVN;
import com.menglin.triproapp.vo.ResultVN;
import com.menglin.triproapp.vo.OrderJsonVO.OrderItemsBean;

import net.sf.json.JSONObject;

/** 
 * @author CGS 
 * @date 2018年2月5日 上午11:04:04 
 */
@Controller  
@RequestMapping("/web/wxshopping")
public class ShoppingWebController {
	
	
	@Resource  
    private IShoppingService shoppingService;
	
	
	/**
	 * 添加到购物车
	 * @author CGS
	 * @time 2018年2月5日下午3:08:53
	 * @param commodityId
	 * @param num 商品数量
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/toShoppingCart.json",method = {RequestMethod.POST})
	public @ResponseBody ResultVN toShoppingCart(Integer commodityId, Integer num,Integer uid){
		
		ResultVN vn=new ResultVN();
		//添加购物车
		
		Shopping shopCommodity=shoppingService.isExitCommodity(commodityId, uid);
		
		if (null!=shopCommodity) {
			shopCommodity.setUpdateTime(new Date());
			shopCommodity.setNum(num+shopCommodity.getNum());
			shoppingService.update(shopCommodity);//更新购物车商品
		}else{
			Shopping shopping =new Shopping();
			shopping.setCommodityId(commodityId);
			shopping.setUid(uid);
			shopping.setAddTime(new Date());
			shopping.setUpdateTime(new Date());
			shopping.setNum(num);
			shopping.setSelected(1);//0未选中 1选中
			shoppingService.save(shopping);// 新增购物车商品
		}
		vn.setResult(Result.suc("添加成功!!"));
		
		return vn;
	
	}
	
	/**
	 * 购物车列表更新商品数目
	 * @author CGS
	 * @time 2018年5月29日上午10:57:25
	 * @param commodityId
	 * @param num
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/updateShoppingNum.json",method = {RequestMethod.POST})
	public @ResponseBody ResultVN updateShoppingNum(Integer commodityId, Integer updateNum,Integer uid){
		
		ResultVN vn=new ResultVN();
		Shopping shopCommodity=shoppingService.isExitCommodity(commodityId, uid);
		if (null!=shopCommodity) {
			shopCommodity.setUpdateTime(new Date());
			shopCommodity.setNum(updateNum);
			shoppingService.update(shopCommodity);//更新购物车商品
			vn.setResult(Result.suc("更新成功!!"));
		}else{
			vn.setResult(Result.fal("更新失败!!"));
		}
		
		
		return vn;
	
	}
	/**
	 * 修改购物车选中状态
	 * @author CGS
	 * @time 2018年5月29日上午11:20:51
	 * @param commodityId
	 * @param selected
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/updateShoppingSelected.json",method = {RequestMethod.POST})
	public @ResponseBody ResultVN updateShoppingSelected(Integer commodityId, Integer selected,Integer uid){
		ResultVN vn=new ResultVN();
		Shopping shopCommodity=shoppingService.isExitCommodity(commodityId, uid);
		if (null!=shopCommodity) {
			shopCommodity.setUpdateTime(new Date());
			shopCommodity.setSelected(selected);
			shoppingService.update(shopCommodity);//更新购物车商品
			vn.setResult(Result.suc("修改成功!!"));
		}else{
			vn.setResult(Result.fal("修改失败!!"));
		}
		return vn;
	}
	
	/**
	 * 购物车批量 选中/取消
	 * @author CGS
	 * @time 2018年5月30日下午3:37:30
	 * @param selected
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/updateShoppingAllSelected.json",method = {RequestMethod.POST})
	public @ResponseBody ResultVN updateShoppingAllSelected(Integer selected,Integer uid){
		ResultVN vn=new ResultVN();
		if (CheckData.isNotNullOrEmpty(selected) && CheckData.isNotNullOrEmpty(uid)) {
			shoppingService.updateAllselecte(selected,uid);//更新购物车商品
			vn.setResult(Result.suc("修改成功!!"));
		}else{
			vn.setResult(Result.suc("修改失败!!"));
		}
		return vn;
	}
	
	/**
	 * 购物车列表
	 * @author CGS
	 * @time 2018年2月6日下午1:43:55
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/cartList.json",method = {RequestMethod.POST})
	public @ResponseBody ResultListVN<Shopping> cartList(Integer uid){
		ResultListVN<Shopping> rs=new ResultListVN<Shopping>();
		List<Shopping> shoppingList=shoppingService.ShoppingListByUid(uid);
		if (CheckData.isNotNullOrEmpty(shoppingList)) {
			List<Shopping> shoppings =new ArrayList<Shopping>();
			for (int i = 0; i < shoppingList.size(); i++) {
				Shopping shop=shoppingList.get(i);
				shop.setCommodityImg(SystemParam.DOMAIN_NAME+shop.getCommodityImg());
				shoppings.add(shop);
			}
			rs.setResultList(shoppings);
			rs.setResult(Result.suc("查询成功!!"));
		}else{
			rs.setResult(Result.fal("购物车空空如也，赶紧去添加吧!"));
		}
		return rs;
	}
	
	/**
	 * 购物车删除商品
	 * @author CGS
	 * @time 2018年2月5日下午3:56:35
	 * @param commodityId
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/delShoppingCommodity.json",method = {RequestMethod.POST})
	public @ResponseBody ResultVN delShoppingCommodity(Integer commodityId,Integer uid){
		ResultVN vn=new ResultVN();
		shoppingService.deleteCommodity(commodityId,uid);
		vn.setResult(Result.suc("购物车去除成功!!"));
		return vn;
	}
	
	/**
	 * 重新购买-添加至购物车
	 * @author CGS
	 * @time 2018年3月15日下午3:10:13
	 * @param json
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/doAgainToCart.json",method = {RequestMethod.POST})
	public @ResponseBody ResultVN doAgainToCart(String json ,Integer uid){
		  Gson gson =new Gson();
		  ResultVN vn=new ResultVN();
		  JSONObject object=JSONObject.fromObject(json);
		  OrderJsonVO orderJsonVO =new OrderJsonVO();
          orderJsonVO=gson.fromJson(object.toString(), OrderJsonVO.class);
          if (null!=orderJsonVO) {
        	  List<OrderItemsBean> itemsBeans =new ArrayList<OrderItemsBean>();
        	  itemsBeans=orderJsonVO.getOrderItems();
        	  for (int i = 0; i < itemsBeans.size(); i++) {
            	  Integer commodityId=itemsBeans.get(i).getCommodityId();
            	  Integer num=itemsBeans.get(i).getAmount();
            	  toShoppingCart(commodityId,num,uid);
    		}
        	  vn.setResult(Result.suc("已重新添加至购物车!!"));
              return vn;
		}
          vn.setResult(Result.fal("再次购买失败!!"));
          return vn;
	}
	
}
