package com.menglin.tripro.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.menglin.tripro.entity.Adress;
import com.menglin.tripro.entity.Message;
import com.menglin.tripro.entity.User;
import com.menglin.tripro.entity.Validate;
import com.menglin.tripro.service.IAdressService;
import com.menglin.tripro.service.IMessageService;
import com.menglin.tripro.service.IUserService;
import com.menglin.tripro.service.IValidateService;
import com.menglin.tripro.util.CheckData;
import com.menglin.tripro.util.Format;
import com.menglin.tripro.util.ImgPressThread;
import com.menglin.tripro.util.PhoneUtils;
import com.menglin.tripro.util.Result;
import com.menglin.tripro.util.SystemParam;
import com.menglin.tripro.util.file;
import com.menglin.tripro.vo.AuditVO;
import com.menglin.tripro.vo.ResultListVN;
import com.menglin.tripro.vo.ResultVN;
import com.menglin.tripro.vo.UserLoginVO;

/**
 * @author CGS
 * @time 2018年1月31日下午1:51:03
 */
@Controller  
@RequestMapping("/web/user")
public class UserWebController {
	
	
	@Resource  
    private IValidateService validateService;
	
	@Resource  
    private IUserService userService;
	
	@Resource  
    private IAdressService adressService;
	
	@Resource  
    private IMessageService messageService;
	
	/**
	 * 
	 * 发送注册验证码
	 * 
	 * @author CGS
	 * 
	 * @param userPhone
	 *            手机号
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(value="/doUserRegisterSMS",method = {RequestMethod.POST})
	public @ResponseBody ResultVN doRegisterSMS(String userPhone) throws Exception {
		ResultVN vn =new ResultVN();
//		String replacePhoneStr=Base64Utils.replacePhoneStr(phoneStr);
//		System.out.println(replacePhoneStr);
//		byte[] encodedData=Base64Utils.decode(replacePhoneStr);
//        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, SystemParam.PRIVATE_KEY);
//        String userPhone = new String(phoneStr);  
//        System.out.println("解密后文字: \r\n" + userPhone);
		if (userPhone != null) {
			boolean isMobile = CheckData.isMobilephone(userPhone);
			if (isMobile) {
				// 设定类型为用户注册时发送验证码
				int validateType = 2;
				// 判断是否存在未验证的
				Validate v = validateService.isNoValidate(userPhone, validateType);
				if (v != null) {
					if (Format.formatTest(v.getAddTime(), 2)) {// 2==多长时间
						vn.setResult(Result.fal("短信已发送,两分钟后可再次发送!"));
						return vn;
					}
					v.setState(3);
					validateService.update(v);
					boolean flag = validateService.verification(userPhone,validateType);
					if (flag) {
						vn.setResult(Result.suc("短信发送成功!"));
						return vn;
					}
					vn.setResult(Result.fal("短信发送失败!"));
					return vn;
				}
				boolean flag = validateService.verification(userPhone, validateType);
				if (flag) {
					vn.setResult(Result.suc("短信发送成功!"));
					return vn ;
				}
				vn.setResult(Result.fal("短信发送失败!"));
				return vn;
			}
			vn.setResult(Result.fal("手机号格式错误"));
			return vn;
		}
		vn.setResult(Result.fal("手机号为空"));
		return vn;
	}
	
	/**
	 * 
	 * 用户登录+注册
	 * 
	 * @author CGS
	 * 
	 * @param userPhone
	 *            手机号
	 * @param code
	 *            验证码
	 */
	@RequestMapping(value="/doLogin",method = {RequestMethod.POST})
	public @ResponseBody
	UserLoginVO doLogin(String userPhone, String code) {
		UserLoginVO userLoginVO = new UserLoginVO();
		if (userPhone != null && code != null) {
			boolean isMobile = CheckData.isMobilephone(userPhone);
			if (isMobile) {
				Validate validate = new Validate();
				validate.setPhone(userPhone);
				validate.setValidateCode(code);
				validate.setValidateType(2);
				validate.setState(1);
				Validate flag = validateService.doValidateCode(validate);
				if (flag != null) {
					if (Format.formatTest(flag.getAddTime(), 2)) {// 2min失效
						User u = userService.findUserByPhone(userPhone);
						if (u != null) {
							Validate va = validateService.get(flag.getId());
							va.setValidateType(1);//验证类型1用户登录2用户注册
							va.setState(2);
							validateService.update(va);
							String token =UUID.randomUUID().toString().replaceAll("\\-","");
//							u.setToken(token);
//							userService.update(u);
							userLoginVO.setUid(Integer.parseInt(u.getId().toString()));
							userLoginVO.setUserPhone(u.getUserPhone());
							userLoginVO.setHeadImg(SystemParam.DOMAIN_NAME+ u.getHeadImg());
							userLoginVO.setToken(u.getToken());
							userLoginVO.setIdentity(u.getIdentity());
							userLoginVO.setAudit(u.getAudit());
//							userLoginVO.setLoginPass(u.getLoginPass());
							userLoginVO.setResult(Result.suc("登录成功!"));
							u.setLoginTime(new Date());
							userService.update(u);
							return userLoginVO;
						} else {
							Validate va = validateService.get(flag.getId());
							va.setValidateType(2);//验证类型1用户登录2用户注册
							va.setState(2);
							validateService.update(va);

							User model = new User();
							String token = UUID.randomUUID().toString().replaceAll("\\-", "");
							model.setToken(token);
							model.setUserPhone(userPhone);
							model.setState(1);// 用户状态为正常2禁用
							model.setHeadImg("/static/img/default_head.png");// 系统默认头像
							model.setAddTime(new Date());
							model.setLoginTime(new Date());
							model.setIdentity(1);//用户身份：1未审核为普通用户2供销商
							model.setAudit(1);//审核状态1未审核2审核中3已审核4未通过
							String belong = null;
							try {
								belong = PhoneUtils.getBelonging(userPhone);
							} catch (Exception e) {
								
								e.printStackTrace();
							}
							
							model.setPhoneBelong(belong);
							userService.save(model);

							model = userService.findUserByPhone(userPhone);
						
					    	
							userLoginVO.setUid(Integer.parseInt(model.getId().toString()));
							userLoginVO.setUserPhone(model.getUserPhone());
							userLoginVO.setHeadImg(SystemParam.DOMAIN_NAME+ model.getHeadImg());
							userLoginVO.setToken(token);
							userLoginVO.setIdentity(model.getIdentity());
							userLoginVO.setAudit(model.getAudit());
//							userLoginVO.setLoginPass(model.getLoginPass());
						
							userLoginVO.setResult(Result.suc("登录成功!"));
							return userLoginVO;
						}
					} else {
						Validate va = validateService.get(flag.getId());
						va.setState(3);
						validateService.update(va);
						userLoginVO.setResult(Result.fal("验证码已过期!!"));
					}
				} else {
					userLoginVO.setResult(Result.fal("验证码错误!!"));
				}
			} else {
				userLoginVO.setResult(Result.fal("手机号格式错误!!"));
			}
		}
		return userLoginVO;
	}
	
	/**
	 * 地址新增
	 * @author CGS
	 * @time 2018年1月31日下午3:34:37
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/doNewAddress",method = {RequestMethod.POST})
	 public  @ResponseBody ResultVN doNewAddress(Adress model){
//		Adress model =new Adress ();
		ResultVN vn =new ResultVN();
//		model.setUid(uid);
//		model.setReceivedPhone(receivedPhone);
//		model.setReceivedName(receivedName);
//		model.setReceivedAddress(receivedAddress);
		
		List<Adress> aList =adressService.addressList(model.getUid());//根据用户ID查询所有的地址列表
		
		//判断是否第一次添加地址，是，地址默认，否，地址不默认
		System.out.println(!CheckData.isNotEmpty(aList));
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
	 * 地址修改
	 * @author CGS
	 * @time 2018年1月31日下午3:34:37
	 * @param model,addressId
	 * @return
	 */
	@RequestMapping(value="/updateAddress",method = {RequestMethod.POST})
	 public  @ResponseBody ResultVN updateAddress(Adress model,Integer addressId){
		ResultVN vn =new ResultVN();
		 model.setId(addressId);
		 adressService.update(model);
		 vn.setResult(Result.suc("编辑成功!!"));
		 return vn;
	 }
	
	/**
	 * 设置为默认地址，去除另一个默认
	 * @author CGS
	 * @time 2018年1月31日下午4:30:39
	 * @param uid
	 * @param addressId
	 * @return
	 */
	@RequestMapping(value="/defaultAddress",method = {RequestMethod.POST})
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
	@RequestMapping(value="/deleteAddress",method = {RequestMethod.POST})
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
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/addressList",method = {RequestMethod.POST})
	 public  @ResponseBody ResultListVN<Adress> addressList(Integer uid){
		
		ResultListVN<Adress> rs=new ResultListVN<Adress>();
		List<Adress> aList =adressService.addressList(uid);
		List<Adress> aList1 =new ArrayList<Adress>();
		System.out.println(aList);
		System.out.println(aList1);
		if (!CheckData.isNotEmpty(aList)) {
			rs.setResult(Result.fal("暂无收货地址!!"));
			 return rs;
		}else{
			rs.setResultList(aList);
			rs.setResult(Result.suc("查询成功!!"));
			return rs;
		}
		 
	 }
	
	/**
	 * 供销商审核
	 * @author CGS
	 * @time 2018年3月13日上午11:01:01
	 * @param uid
	 * @param request
	 * @param f
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/addAuditData",method={RequestMethod.POST})
	public @ResponseBody ResultVN addAuditData(Integer uid, MultipartHttpServletRequest request,file f) throws IOException {
		ResultVN vn =new ResultVN();
		
				// 本地
				List<String> lis=new ArrayList<String>();
				Iterator<String> iter = request.getFileNames();
				User model= userService.get(uid);
				while (iter.hasNext()) {
					MultipartFile file = request.getFile(iter.next());
					if (file != null) {
						
						if (file.getName().equals("file1")) {
							String fileName = file.getOriginalFilename();
							if (fileName.trim() != "") {
								long  startTime=System.currentTimeMillis();
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
								String timestamp = sdf.format(date);
//								String basePath = request.getSession().getServletContext().getRealPath("/");
								String basePath = "E:\\images\\upload\\";
								fileName=fileName.substring(fileName.lastIndexOf("."));
								String path =timestamp+model.getId().toString()+"cardA_ml"+fileName;// 文件保存路径
								File localFile = new File(basePath + path);
								file.transferTo(localFile);
//								Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
								new ImgPressThread(localFile).start();//多线程压缩图片
								lis.add(path);
								model.setIdCardImg("/img/"+path);//身份证正面
								System.out.println(SystemParam.DOMAIN_NAME+model.getIdCardImg());
							
								long  endTime=System.currentTimeMillis();
								System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
							   
							}
						}
						if (file.getName().equals("file2")) {
							String fileName = file.getOriginalFilename();
							if (fileName.trim() != "") {
								long  startTime=System.currentTimeMillis();
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
								String timestamp = sdf.format(date);
//								String basePath = request.getSession().getServletContext().getRealPath("/");
								String basePath = "E:\\images\\upload\\";
								fileName=fileName.substring(fileName.lastIndexOf("."));
								String path =timestamp+model.getId().toString()+"cardB_ml"+fileName;// 文件保存路径
								File localFile = new File(basePath + path);
								file.transferTo(localFile);
//								Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
								new ImgPressThread(localFile).start();//多线程压缩图片
								lis.add(path);
								model.setIdCardImgB("/img/"+path);//身份证反面
								System.out.println(SystemParam.DOMAIN_NAME+model.getIdCardImg());
							
								long  endTime=System.currentTimeMillis();
								System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
							   
							}
						}
						if (file.getName().equals("file3")) {
							String fileName = file.getOriginalFilename();
							if (fileName.trim() != "") {
								long  startTime=System.currentTimeMillis();
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
								String timestamp = sdf.format(date);
//								String basePath = request.getSession().getServletContext().getRealPath("/");
								String basePath = "E:\\images\\upload\\";
								fileName=fileName.substring(fileName.lastIndexOf("."));
								String path =timestamp+model.getId().toString()+"business_ml"+fileName;// 文件保存路径
								File localFile = new File(basePath + path);
								file.transferTo(localFile);
//								Thumbnails.of(localFile).scale(1f).outputQuality(0.25f).toFile(localFile);
								new ImgPressThread(localFile).start();//多线程压缩图片
								lis.add(path);
								model.setBusinessImg("/img/"+path);//营业执照
								System.out.println(SystemParam.DOMAIN_NAME+model.getIdCardImg());
							
								long  endTime=System.currentTimeMillis();
								System.out.println("1采用多线程的运行时间："+String.valueOf(endTime-startTime)+"ms");
							   
							}
						}
						
				}
		
			
	 }			model.setAudit(2);//审核状态1未审核2审核中3已审核
				userService.update(model);
				//消息添加
				Message message =new Message();
				message.setUid(model.getId());
				message.setTitle("供销商认证");
				message.setContent("手机号为："+model.getUserPhone()+" 的用户于 "+new Date()+" 时提交认证审核，请及时处理!!");
				message.setState(1);//状态：1未读2已读3跟进
				message.setAddtime(new Date());
				messageService.save(message);
				vn.setResult(Result.suc("材料已提交，请耐心等待审核"));
				return vn;
  }
	
	/**
	 * 跳转到审核信息页面
	 * @author CGS
	 * @time 2018年3月13日上午11:16:00
	 * @param uid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/toAuditData",method={RequestMethod.POST})
	public @ResponseBody AuditVO toAuditData(Integer uid) throws IOException {
		
		User model= userService.get(uid);
		AuditVO auditVO =new AuditVO();
		auditVO.setUid(model.getId());
		auditVO.setAudit(model.getAudit());
		switch (model.getAudit()) {
		case 1:
			auditVO.setResult(Result.suc("未审核"));
			break;
		case 2:
			auditVO.setIdCard(model.getIdCard());
			auditVO.setIdCardImg(SystemParam.DOMAIN_NAME+model.getIdCardImg());
			auditVO.setIdCardImgB(SystemParam.DOMAIN_NAME+model.getIdCardImgB());
			auditVO.setBusinessImg(SystemParam.DOMAIN_NAME+model.getBusinessImg());
			auditVO.setResult(Result.suc("审核中"));
			break;
		case 3:
			auditVO.setIdCard(model.getIdCard());
			auditVO.setIdCardImg(SystemParam.DOMAIN_NAME+model.getIdCardImg());
			auditVO.setIdCardImgB(SystemParam.DOMAIN_NAME+model.getIdCardImgB());
			auditVO.setBusinessImg(SystemParam.DOMAIN_NAME+model.getBusinessImg());
			auditVO.setResult(Result.suc("审核通过"));
			break;
		case 4:
			auditVO.setIdCard(model.getIdCard());
			auditVO.setIdCardImg(SystemParam.DOMAIN_NAME+model.getIdCardImg());
			auditVO.setIdCardImgB(SystemParam.DOMAIN_NAME+model.getIdCardImgB());
			auditVO.setBusinessImg(SystemParam.DOMAIN_NAME+model.getBusinessImg());
			auditVO.setResult(Result.suc("审核驳回"));
			break;
		}
		return auditVO;
	}
}
