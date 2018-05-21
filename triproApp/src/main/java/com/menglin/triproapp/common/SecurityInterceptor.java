package com.menglin.triproapp.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.menglin.triproapp.util.CheckData;

/** 
 * @author CGS 
 * @date 2018年2月24日 上午10:05:10 
 */
public class SecurityInterceptor implements HandlerInterceptor {
	
	//拦截前处理  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			boolean flag=false;
			HttpSession session=request.getSession();
			Object userName=session.getAttribute("userName");
			if (CheckData.isNotNullOrEmpty(userName)) {
				flag=true;
				System.out.println(userName);
			}else {
				reDirect(request, response); 
			}
		return flag;
	}
	
	//拦截后处理 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		  

	}
	
	 //全部完成后处理  
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		

	}
	 //对于请求是ajax请求重定向问题的处理方法
    public void reDirect(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //获取当前请求的路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTENTPATH", basePath+"/html/log.html");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
            response.sendRedirect(basePath + "/html/log.html");
        }
    }

}
