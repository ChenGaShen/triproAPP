package com.menglin.invest.common;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @author CGS 
 * @date 2018年2月28日 上午11:02:34 
 */
public class ResourceCheckFilter extends AccessControlFilter {
	
	
	private static String errorUrl="/unauthorized.html";

  
	private static final Logger logger = LoggerFactory.getLogger(ResourceCheckFilter.class);
	
	
	 /**
     * 表示是否允许访问 ，如果允许访问返回true，否则false；
     * @param servletRequest
     * @param servletResponse
     * @param o 表示写在拦截器中括号里面的字符串 mappedValue 就是 [urls] 配置中拦截器参数部分
     * @return
     * @throws Exception
     */
	@Override
	protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object arg2) throws Exception {
		Subject subject = getSubject(servletRequest,servletResponse);//获得认证主体
        String url = getPathWithinApplication(servletRequest);//获得当前请求的 url
        logger.debug("当前用户正在访问的 url => " + url);
        boolean flag=subject.isPermitted(url);
        return flag; 
        //调用了 subject.isPermitted(url) 方法，将 url 这个字符串对象传入,
        //此时我们的流程应该走到 Realm 的授权方法中，通过查询（经过了认证的）用户信息去查询该用户具有的权限信息
	}
	
	
	/**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回 true 表示需要继续处理；如果返回 false 表示该拦截器实例已经处理了，将直接返回即可。
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		 logger.debug("当 isAccessAllowed 返回 false 的时候，才会执行 method onAccessDenied ");

	        HttpServletRequest request =(HttpServletRequest) servletRequest;
	        HttpServletResponse response =(HttpServletResponse) servletResponse;
//	    	request.getRequestDispatcher(errorUrl).forward(request, response);
	        response.sendRedirect(request.getContextPath() + errorUrl);

	        // 返回 false 表示.已经处理，例如页面跳转啥的，表示不在走以下的拦截器了（如果还有配置的话）
	        return false;
	}

}
