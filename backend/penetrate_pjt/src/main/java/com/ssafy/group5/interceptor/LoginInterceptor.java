package com.ssafy.group5.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.group5.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler)
			throws Exception {

		
		log.debug("요청 메소드 종류 : {}",request.getMethod());
		//OPTIONS 메소드로 넘어오는 preflight 요청은 true로 넘겨줌.
		if(HttpMethod.OPTIONS.matches(request.getMethod()))return true;
		
		
		final String token = request.getHeader("Authorization");

		log.debug("Authorization 헤더 : {}", token);
		
		//토큰 존재여부 체크
		if(token==null || !token.startsWith("Bearer ")) {
			log.debug("token 정보가 없음");
			response.setStatus(401);
			response.getWriter().append("not Login");
			return false;
		}
		String jwt = token.substring(7);
		log.debug("헤더에서 추출한 토큰 정보 : {}", jwt);
		//토큰의 유효성 체크
		if(!jwtUtil.validateToken(jwt)) {
			log.debug("토큰이 존재하나 유효하지 않음");
			response.setStatus(401);
			response.getWriter().append("not Login");
			return false;
		}
		
		return true;
	}
}
