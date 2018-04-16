package com.menglin.invest.common;

/**
 * 跨域请求过滤器
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {


	    public void init(FilterConfig filterConfig) throws ServletException {
	        	System.out.println(111111111);
	    }

	   
	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	            throws IOException, ServletException {
	        HttpServletResponse response = (HttpServletResponse) res;  
	        response.setHeader("Access-Control-Allow-Origin", "*");  
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
	        response.setHeader("Access-Control-Max-Age", "3600");  
	        response.setHeader("Access-Control-Allow-Headers", "token,Access-Control-Allow-Origin,Access-Control-Allow-   Methods,Access-Control-Max-Age,authorization");
	        chain.doFilter(req, res); 
	        
	    }

	   
	    public void destroy() {
	    }

}
