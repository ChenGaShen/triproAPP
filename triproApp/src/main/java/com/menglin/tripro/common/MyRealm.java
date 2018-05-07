package com.menglin.tripro.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import com.menglin.tripro.entity.Admin;
import com.menglin.tripro.entity.Permission;
import com.menglin.tripro.entity.Role;
import com.menglin.tripro.service.IAdminrService;
import com.menglin.tripro.service.IPermissionrService;
import com.menglin.tripro.service.IRoleService;

/** 
 * shiro 认证授权
 * @author CGS 
 * @date 2018年2月27日 上午10:50:51 
 */
public class MyRealm extends AuthorizingRealm {
	
	
	@Resource  
    private IAdminrService adminrService;
	@Resource  
    private IRoleService roleService;
	@Resource  
    private IPermissionrService permissionrService;
	
	

	
	//验证当前登录的用户，获取认证信息。
	@Override                    //doGetAuthenticationInfo 获取身份验证相关信息
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String adminName = (String)token.getPrincipal();  
		Admin admin = adminrService.findByAdminName(adminName);  
          
        if(admin == null) {  
            throw new UnknownAccountException();//没找到帐号  
        }else{ 
	        this.setSession("currentUser", admin.getAdminName()); 
	        
	        String adminPass=new String( (char[])token.getCredentials());
	        System.out.println("admin.getAdminPass():"+admin.getAdminPass());
	        System.out.println("adminPass-----------:"+adminPass);
	        // （4）盐值：取用户信息中唯一的字段来生成盐值，避免由于两个用户原始密码相同，加密后的密码也相同
	        ByteSource credentialsSalt = ByteSource.Util.bytes(adminName);
	        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现  
	            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
	            		admin.getAdminName(), //用户名  
	            		admin.getAdminPass(),
	            		credentialsSalt,
	                    getName()  //realm name/  
	            ); 
	            return authenticationInfo;
      
        }  
       
	}
	
	//为当前登录成功的用户授予权限和角色，已经登录成功了。 
	@Override                 //doGetAuthorizationInfo 获取授权信息
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
			String adminName = (String)principals.getPrimaryPrincipal(); 
			Admin adminUser = adminrService.findByAdminName(adminName); 
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			//根据用户ID查询角色（role），放入到Authorization里。
		    /*Map<String, Object> map = new HashMap<String, Object>();
		    map.put("user_id", userId);
		    List<SysRole> roleList = sysRoleService.selectByMap(map);
		    Set<String> roleSet = new HashSet<String>();
		    for(SysRole role : roleList){
		        roleSet.add(role.getType());
		    }*/
			List<Role> roles = roleService.findUserByAdmin(adminUser.getId());
			Set<String> roleSet = new HashSet<String>();
			 for(Role role : roles){
			        roleSet.add(role.getRoleName());
			    }
	        authorizationInfo.setRoles(roleSet);  //角色信息Set<String > roles
	        
	      //根据用户ID查询权限（permission），放入到Authorization里。
		    /*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
		    Set<String> permissionSet = new HashSet<String>();
		    for(SysPermission Permission : permissionList){
		        permissionSet.add(Permission.getName());
		    }*/
	        List<Permission> permissionList=permissionrService.findUserByAdmin(adminUser.getId());
	        Set<String> permissionSet = new HashSet<String>();
		    for(Permission permission : permissionList){
		        permissionSet.add(permission.getPermissionUrl());
		    }
	        authorizationInfo.setStringPermissions(permissionSet);  //权限资源信息 Set<String > stringPermissions
	        
	        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址  
	        return authorizationInfo; 
	}
	
	  /** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }
	
	// 清除缓存  
    public void clearCached() {  
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();  
        super.clearCache(principals);  
    }

}
