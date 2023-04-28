package com.ssafy.group5.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class SessionConfirmInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(request.getMethod().equals("GET")) return true;
		
		if(session.getAttribute("userInfo")==null) {
			response.setContentType("text/plain;charset=utf-8");
			response.getWriter().append("로그인이 필요합니다.");
			response.setStatus(401);
			return false;
		}
		return true;
	}
	
}
