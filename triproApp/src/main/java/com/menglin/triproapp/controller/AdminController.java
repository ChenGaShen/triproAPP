package com.menglin.triproapp.controller;




import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.menglin.triproapp.entity.Admin;
import com.menglin.triproapp.service.IAdminrService;
import com.menglin.triproapp.service.IPermissionrService;
import com.menglin.triproapp.service.IRoleService;
import com.menglin.triproapp.util.CheckData;
import com.menglin.triproapp.util.MD5;
import com.menglin.triproapp.util.PageBean;
import com.menglin.triproapp.util.Result;
import com.menglin.triproapp.vo.LoginResultVN;
import com.menglin.triproapp.vo.PageRuslt;
import com.menglin.triproapp.vo.ResultVN;

/** 
 * @author CGS 
 * @date 2018年2月24日 上午10:12:14 
 */
@Controller  
@RequestMapping("/admin/index")
public class AdminController {
		
	@Resource  
    private IAdminrService adminrService;
	
	@Resource
	private IPermissionrService permissionrService;
	
	@Resource  
    private IRoleService roleService;
	
	/**
	 * 所有管理员账户信息
	 * @author CGS
	 * @time 2018年3月5日下午4:14:31
	 * @param currentPage
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/findByPage.json",method={RequestMethod.POST})  
    public @ResponseBody PageRuslt<Admin> findByPage(int currentPage, int pageSize,Admin model){
		PageRuslt<Admin> pageRuslt =new PageRuslt<Admin>();
		PageBean<Admin> PageUser=adminrService.findByPage(currentPage, pageSize, model);
		pageRuslt.setPageBean(PageUser);
      return pageRuslt;
       
	}
	
	
	/**
	 * 管理员新增
	 * @author CGS
	 * @time 2018年3月5日下午2:13:36
	 * @param model
	 * @return
	 */
//	@RequestMapping(value="/addAdmin.json",method={RequestMethod.POST})  
//    public @ResponseBody ResultVN addAdmina(Admin model,Integer roleId){
//		ResultVN vn =new ResultVN();
//		adminrService.save(model);
//		Admin  admin=adminrService.findByAdminName(model.getAdminName());
//		adminrService.addEndowRole(roleId,admin.getId());
//		vn.setResult(Result.suc("新管理员及角色赋予添加成功!"));
//      return vn;
//	}
	
	@RequestMapping(value="/addAdmin.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN addAdmina(Admin model){
		ResultVN vn =new ResultVN();
		String adminPass=MD5.md5(model.getAdminPass());
		model.setAdminPass(adminPass);
		adminrService.save(model);
		vn.setResult(Result.suc("新管理员添加成功!"));
      return vn;
	}
	
	/**
	 * 管理员 账户和关联修改
	 * @author CGS
	 * @time 2018年3月5日下午3:28:27
	 * @param model
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value="/updateAdmin.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN updateAdmin(Admin model,Integer roleId){
		ResultVN vn =new ResultVN();
		Admin  admin=adminrService.get(model.getId());
		adminrService.updateEndowRole(roleId,admin.getId());
		vn.setResult(Result.suc("管理员及角色关系修改成功!"));
      return vn;
	}
	
	
	/**
	 * 密码修改
	 * @author CGS
	 * @time 2018年3月5日下午2:30:016
	 * @param newPass 
	 * @param aid
	 * @return
	 */
	@RequestMapping(value="/updateAdminPass.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN updateAdminPass(String newPass, String adminId){
		ResultVN vn =new ResultVN();
		Admin model =adminrService.get(Integer.valueOf(adminId));
		if (CheckData.isNotNullOrEmpty(newPass)) {
			String adminPass=MD5.md5(newPass);
			model.setAdminPass(adminPass);
			adminrService.update(model);
			vn.setResult(Result.suc("密码修改成功!"));
		}
      return vn;
	}
	
	/**
	 * 管理员删除
	 * @author CGS
	 * @time 2018年4月11日下午2:25:19
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value="/deleteAdmin.json",method={RequestMethod.POST})  
    public @ResponseBody ResultVN updateAdminPass( String adminId){
		ResultVN vn =new ResultVN();
		if (CheckData.isNotNullOrEmpty(adminId)) {
			adminrService.delete(Integer.valueOf(adminId));
			vn.setResult(Result.suc("此管理员账户删除成功!"));
		}
      return vn;
	}
	
	/**
	 * 登录验证
	 * @author CGS
	 * @time 2018年3月5日下午2:08:54
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/doLogin.json",method={RequestMethod.POST})
	public @ResponseBody LoginResultVN doLogin(HttpServletRequest request,HttpServletResponse response,Admin model ) {
		
		//以前非shiro登录校验
		
		LoginResultVN vn =new LoginResultVN();
		try {
			model.setAdminPass(MD5.md5(model.getAdminPass()));
			Admin admin =adminrService.checkLogin(model.getAdminName(), model.getAdminPass());
			if (CheckData.isNotNullOrEmpty(admin)) {
				HttpSession session =request.getSession();
				
				session.setAttribute("userName", model.getAdminName());
				session.setMaxInactiveInterval(60 * 60); //session 失效时间为一小时
//				request.getRequestDispatcher("/index.html").forward(request, response);
				vn.setAdminName(model.getAdminName());
				 vn.setResult(Result.suc("登录成功~"));
			}else{
				vn.setResult(Result.fal("账户密码错误!!"));
	            return vn;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vn;
		//shiro 登录验证
		/*ResultVN vn =new ResultVN();
		//MD5加密+解密
		model.setAdminPass(MD5.md5(model.getAdminPass()));
		UsernamePasswordToken token = new UsernamePasswordToken(model.getAdminName(), model.getAdminPass());
		token.setRememberMe(true);
		try {  
            SecurityUtils.getSubject().login(token); 
//            SecurityUtils.getSubject()
            vn.setResult(Result.suc("登录成功~"));
            return  vn;  
        } catch (UnknownAccountException uae) { 
        	vn.setResult(Result.fal("账户错误"));
            return vn;  
        } catch (IncorrectCredentialsException ice) { 
        	vn.setResult(Result.fal("密码错误"));
            return vn;  
        }  */
		
	}
	
	/**
	 * 退出登录 
	 * @author CGS
	 * @time 2018年3月5日下午2:09:13
	 * @return
	 */
    @RequestMapping(value="/logout.json",method=RequestMethod.POST)    
    public  @ResponseBody ResultVN logout(HttpServletRequest request) { 
    	ResultVN vn =new ResultVN();
    	//shiro 退出
//        Subject currentUser = SecurityUtils.getSubject();       
//        currentUser.logout();
//        vn.setResult(Result.suc("退出成功~!!"));
//        return vn;
//        
        // 一般退出
    	HttpSession session =request.getSession();
		session.removeAttribute("userName");
		 vn.setResult(Result.suc("退出成功~!!"));
		 return vn;
    }
    
	
    
    
    
}
