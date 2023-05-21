package com.ssafy.group5.model.service;

import java.util.Map;

import com.ssafy.group5.dto.User;

public interface UserService {
	
	/**
	 * 유저 정보 반환
	 */
	User getUser(User user);
	
	/**
	 * 회원가입
	 */
	void signUp(User user);
	
	/**
	 * 회원 정보 수정 
	 */
	void updateUserInfo(Map<String, ?> param);
	
	/**
	 * 회원 정보 삭제 
	 */
	void deleteUser(User user);
	
}
