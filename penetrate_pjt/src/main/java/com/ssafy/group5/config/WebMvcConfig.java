package com.ssafy.group5.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.group5.interceptor.SessionConfirmInterceptor;


@Configuration // 설정파일 bean 등록
@EnableAspectJAutoProxy // aop AutoProxy 설정
@MapperScan(basePackages = {"com.ssafy.group5.model.mapper"}) 	// Mapper 인터페이스 bean등록
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	SessionConfirmInterceptor sessionConfirmInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		
		registry.addInterceptor(sessionConfirmInterceptor)
		.addPathPatterns("/group5/**"); // 접근제어 필요한 url
	}
	
}
