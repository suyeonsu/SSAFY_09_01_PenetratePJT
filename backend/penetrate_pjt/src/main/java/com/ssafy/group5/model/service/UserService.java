package com.ssafy.group5.model.service;

import java.util.Map;

import com.ssafy.group5.dto.User;

public interface UserService {
	
	/**
	 * 로그인
	 */
	User login(User user);
	
	/**
	 * 회원가입
	 */
	void signUp(Map<String, ?> user);
	
	/**
	 * 회원 정보 수정 
	 */
	void updateUserInfo(User user);
	
	/**
	 * 회원 정보 삭제 
	 */
	void deleteUser(User user);
	
}
