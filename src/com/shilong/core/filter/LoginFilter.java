package com.shilong.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shilong.core.constant.Constant;
import com.shilong.core.permission.PermissionCheck;
import com.shilong.nsfw.user.entity.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		String uri=request.getRequestURI();
		
		if(!uri.contains("sys/login_")){ 
			if(request.getSession().getAttribute(Constant.USER)!=null){
				
				if(uri.contains("/nsfw/")){
					User user=(User) request.getSession().getAttribute(Constant.USER);
					
					WebApplicationContext applicationContext=WebApplicationContextUtils//
							.getWebApplicationContext(request.getServletContext());
					
					PermissionCheck pc=(PermissionCheck) applicationContext.getBean("permissionCheck");
					
					if(pc.accessible(user,"nsfw")){
						chain.doFilter(request, response);
					}else{
						response.sendRedirect(request.getContextPath()+"/sys/login_toNoPermissionUI.action");
					}
					
				}else{
					chain.doFilter(request, response);
				}
				
				
				
			}else{
				response.sendRedirect(request.getContextPath()+"/sys/login_toLoginUI.action");
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
