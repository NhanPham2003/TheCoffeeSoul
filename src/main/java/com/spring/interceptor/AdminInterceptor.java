package com.spring.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AdminInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object hanObject) throws Exception {
		
		
		Object obj = req.getSession().getAttribute("UserLoggerAdmin");
		
		if (obj != null) {
			System.out.println("preHandle");
			return true;
		}
		res.sendRedirect("/login");
		
		return false;
		
	}
	
	
	@Override 
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception{
		System.out.println("postHandle");
		HandlerInterceptor.super.postHandle(req, res, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex)
	throws Exception{
		System.out.println("afterCompletin");
		
		HandlerInterceptor.super.afterCompletion(req, res, handler, ex);
	}

}
