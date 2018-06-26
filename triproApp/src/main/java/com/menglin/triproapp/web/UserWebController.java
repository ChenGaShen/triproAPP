package com.menglin.triproapp.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.mail.Address;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglin.triproapp.entity.ActiveRed;
import com.menglin.triproapp.entity.Adress;
import com.menglin.triproapp.entity.Message;
import com.menglin.triproapp.entity.User;
import com.menglin.triproapp.service.IActiveRedService;
import com.menglin.triproapp.service.IAdressService;
import com.menglin.triproapp.service.IMessageService;
import com.menglin.triproapp.service.IUserService;
import com.menglin.triproapp.service.IValidateService;
import com.menglin.triproapp.util.Base64Utils;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.util.SystemParam;
import com.menglin.triproapp.util.pay.weixin.CommonUtil;
import com.menglin.triproapp.vo.ResultListVN;
import com.menglin.triproapp.vo.ResultMessageObject;
import com.menglin.triproapp.vo.ResultObject;
import com.menglin.triproapp.vo.ResultVN;
import net.sf.json.JSONObject;

/**
 * @author CGS
 * @time 2018年1月31日下午1:51:03
 */
@Controller  
@RequestMapping("/web/wxuser")
public class UserWebController {
	
	
	
	@Resource  
    private IUserService userService;
	
	@Resource  
    private IAdressService adressService;
	
	@Resource  
    private IMessageService messageService;
	
	@Resource
	private IActiveRedService activeRedService;
	
	
	/**
	 * 微信登录保存用户信息
	 * @author CGS
	 * @time 2018年5月31日下午3:13:41
	 * @param request
	 * @param session
	 * @return
	 */
 	@RequestMapping(value="/wxlogin.json",method = {RequestMethod.POST})
    public @ResponseBody ResultObject<User> wxlogin(HttpServletRequest request, HttpSession session){
 		ResultObject<User> resultObject =new ResultObject<User>();
        //获取用户登录传过来的code
        String code=request.getParameter("code");
        String loginName=request.getParameter("loginName");
        System.out.println("code:"+code);
        System.out.println("loginName:"+loginName);
        String URL = "https://api.weixin.qq.com/sns/jscode2session?appid="
				+ SystemParam.WX_APPID + "&secret=" + SystemParam.WX_AppSecret + "&js_code=" + code + "&grant_type=authorization_code";
    	JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
    	System.out.println("jsonObject:"+jsonObject);
    	if (CheckData.isNotNullOrEmpty(jsonObject)) {
    		
    		String openId = jsonObject.getString("openid");
	    	String session_key = jsonObject.getString("session_key");
//	        String token = UUID.randomUUID().toString().replaceAll("-", "");// 随机数
	        String token = Base64Utils.generateNumber2();// 随机数
	        System.out.println(openId+"----"+session_key+"----"+token);
	        if(CheckData.isNotEmptyString(openId)){
	            /*在此处添加自己的逻辑代码，将openid保存在数据库，或是，使用session_key去微信服务器换取用户头像、昵称等信息。我在这里并没有用到，因此我只保存了用户的openid*/
	        	User user =userService.findUserByOpenId(openId);// 根据openId 查找用户
	        	if (CheckData.isNotNullOrEmpty(user)) {
	        		user.setLoginTime(new Date());
	        		user.setOpenid(openId);
	        		user.setSessionKey(session_key);
	        		userService.update(user);
	        		//已经有了用户信息，重新登录
	        		HashMap<String,Object> map = new HashMap<String,Object>();
	        		map.put(token, openId+session_key);
	        		map.put("uid", user.getUserId());
	        		map.put("openId", openId);
	        		resultObject.setObject(map);
		        	resultObject.setResult(Result.suc("登录成功!"));
		        	System.out.println("重新登录!!");
				}else{
					//初次登录
					User newuser=new User();
					newuser.setOpenid(openId);
					newuser.setSessionKey(session_key);
					newuser.setToken(token);
					newuser.setState(1);
					newuser.setAddTime(new Date());
					newuser.setLoginTime(new Date());
					newuser.setLoginName(loginName);
					userService.save(newuser);
					HashMap<String,Object> map = new HashMap<String,Object>();
					User account=userService.findUserByOpenId(openId);
	        		map.put(token, openId+session_key);
	        		map.put("uid", account.getUserId());
	        		resultObject.setObject(map);
		        	resultObject.setResult(Result.suc("登录成功!"));
		        	System.out.println("初次登录!!");
				}
	        	
	        }else{
	        	resultObject.setResult(Result.suc("登录失败!"));
	        	System.out.println("结尾登录失败!");
	        }
    		
		}else {
			resultObject.setResult(Result.fal("登录失败!!"));
    		System.out.println("开始就登录失败!!");
		}
    
        return resultObject;
    }
	
	/**
	 * 地址新增
	 * @author CGS
	 * @time 2018年1月31日下午3:34:37
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/doNewAddress.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultVN doNewAddress(Adress model){

		ResultVN vn =new ResultVN();
		
		List<Adress> aList =adressService.addressList(model.getUid());//根据用户ID查询所有的地址列表
		
		//判断是否第一次添加地址，是，地址默认，否，地址不默认
		if (!CheckData.isNotEmpty(aList)) {
			 model.setIsDefault(1);//默认收货地址0 默认1默认
			 adressService.save(model);
			 vn.setResult(Result.suc("新增成功!!")); 
			 return	vn ;
		}else{
			 model.setIsDefault(0);//默认收货地址0默认1默认
			 adressService.save(model);
			 vn.setResult(Result.suc("新增成功!!")); 
			 return vn;
		}
		 
	 }
	
	/**
	 * 单一地址信息
	 * @author CGS
	 * @time 2018年5月30日下午2:12:13
	 * @param addressId
	 * @return
	 */
	@RequestMapping(value="/findAddressById.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultObject<Adress> findAddressById(Integer addressId){
		ResultObject<Adress> vn =new ResultObject<Adress>();
		Adress adress= adressService.get(addressId);
		if (CheckData.isNotNullOrEmpty(adress.getId())) {
			vn.setObject(adress);
			vn.setResult(Result.suc("查询成功!!"));
		}else{
			 vn.setResult(Result.suc("编辑成功!!"));
		}
		 return vn;
	 }
	
	/**
	 * 地址修改
	 * @author CGS
	 * @time 2018年1月31日下午3:34:37
	 * @param model,addressId
	 * @return
	 */
	@RequestMapping(value="/updateAddress.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultVN updateAddress(Adress model,Integer userId,Integer addressId){
		ResultVN vn =new ResultVN();
		 model.setId(addressId);
		 model.setUid(userId);
		 adressService.update(model);
		 vn.setResult(Result.suc("编辑成功!!"));
		 return vn;
	 }
	
	/**
	 * 设置为默认地址，去除另一个默认
	 * @author CGS
	 * @time 2018年1月31日下午4:30:39
	 * @param userId
	 * @param addressId
	 * @return
	 */
	@RequestMapping(value="/defaultAddress.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultVN defaultAddress( Integer addressId,Integer uid){
		
		ResultVN vn =new ResultVN();
		adressService.setDefault(addressId, uid);
		vn.setResult(Result.suc("编辑成功!!"));
		return vn;
	 }
	
	/**
	 * 地址删除
	 * @author CGS
	 * @time 2018年1月31日下午3:34:37
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deleteAddress.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultVN deleteAddress(Integer addressId){
		
		 ResultVN vn =new ResultVN();
		 Adress model =adressService.get(addressId);
		 if (model.getIsDefault()==1) {//默认收货地址0不是默认1默认
			 vn.setResult(Result.fal("默认地址不能删除!!"));
			 return vn;
		}else{
			 vn.setResult(Result.suc("删除成功!!"));
			 adressService.delete(addressId);
			 return vn;
		}
		
		
	 }
	
	/**
	 * 用户所有地址列表
	 * @author CGS
	 * @time 2018年1月31日下午3:34:37
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/addressList.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultListVN<Adress> addressList(Integer uid){
		
		ResultListVN<Adress> rs=new ResultListVN<Adress>();
		List<Adress> aList =adressService.addressList(uid);
		if (CheckData.isNotNullOrEmpty(aList)) {
			rs.setResultList(aList);
			rs.setResult(Result.suc("查询成功!!"));
		}else{
			rs.setResult(Result.fal("暂无收货地址!!"));
		}
		return rs;
		 
	 }
	
	/**
	 * 根据用户ID 查询红包信息
	 * @author CGS
	 * @time 2018年5月31日下午3:13:21
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/activeRedList.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultListVN<ActiveRed> activeRedList(Integer uid,Integer redState){
		ResultListVN<ActiveRed> rs=new ResultListVN<ActiveRed>();
		List<ActiveRed> activeRedList =activeRedService.activeRedList(uid,redState);
		if (CheckData.isNotNullOrEmpty(activeRedList)) {
			rs.setResultList(activeRedList);
			rs.setResult(Result.suc("查询成功!!"));
		}else{
			rs.setResult(Result.fal("暂无红包信息!!"));
		}
		return rs;
		 
	 }
	
	/**
	 * 用户消息列表
	 * @author CGS
	 * @time 2018年6月14日下午3:45:06
	 * @param uid
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/messAgeList.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultListVN<Message> messAgeList(Integer uid,Integer type){
		ResultListVN<Message> rs=new ResultListVN<Message>();
		List<Message> activeRedList =messageService.messAgeList(uid,type);
		if (CheckData.isNotNullOrEmpty(activeRedList)) {
			rs.setResultList(activeRedList);
			rs.setResult(Result.suc("查询成功!!"));
		}else{
			rs.setResult(Result.fal("暂无消息!!"));
		}
		return rs;
		 
	 }
	/**
	 * 消息更新（未读--已读）
	 * @author CGS
	 * @time 2018年6月15日上午10:59:31
	 * @param uid
	 * @param messageId
	 * @return
	 */
	@RequestMapping(value="/updateMessAge.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultVN updateMessAge(Integer messageId){
		ResultVN vn =new ResultVN();
		Message message =messageService.get(messageId);
		if (CheckData.allfieldIsNotNUll(message)) {
			message.setState(1);//0未读1已读2跟进
			messageService.update(message);
			vn.setResult(Result.suc("更新成功!!"));
		}else{
			vn.setResult(Result.fal("操作失败!!"));
		}
		return vn;
		 
	 }
	
	/**
	 * 用户消息未读条数以及最新首条消息
	 * @author CGS
	 * @time 2018年6月15日下午2:19:58
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/whetherMessage.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultMessageObject<Message> whetherMessage(Integer uid,Integer type){
		ResultMessageObject<Message> vn =new ResultMessageObject<Message>();
		if (type==0) {
			int count= messageService.selectUnreadCount(uid);
			Message message =messageService.firstOne(uid, type);
			if (count>0 && CheckData.allfieldIsNotNUll(message)) {
				vn.setObject(message);
				vn.setUnreadCount(count);
				vn.setResult(Result.suc("查询成功!!"));
			}else{
				vn.setUnreadCount(0);
				vn.setObject(message);
				vn.setResult(Result.suc("查询成功!!"));
			}
		}else if (type==1) {
			Message message =messageService.firstOne(uid, type);
			if (CheckData.allfieldIsNotNUll(message)) {
				vn.setObject(message);
				vn.setResult(Result.suc("查询成功!!"));
			}else{
				vn.setResult(Result.fal("暂无系统消息!!"));
			}
		}else{
			vn.setResult(Result.fal("暂无任何消息!!"));
		}
		return vn;
	 }
	
	/**
	 *用户红包未使用个数
	 * @author CGS
	 * @time 2018年6月15日下午2:28:03
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/whetherActiveRed.json",method = {RequestMethod.POST})
	 public  @ResponseBody ResultObject<String> whetherActiveRed(Integer uid){
		ResultObject<String> vn =new ResultObject<String>();
		int count= activeRedService.selectActiveRedCount(uid);
		if (count>0) {
			vn.setObject(count);
			vn.setResult(Result.suc("查询成功!!"));
		}else{
			vn.setResult(Result.fal("暂无未使用的红包!!"));
		}
		return vn;
	 }
	
		
		
}
