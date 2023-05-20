package com.ssafy.group5.model.mapper;

import com.ssafy.group5.dto.User;

public interface UserMapper {
	
	/**
	 * 로그인
	 */
	User login(User user);
	
	/**
	 * 회원가입
	 */
	void signUp(User user);
	
	/**
	 * 회원 정보 수정 
	 */
	void updateUserInfo(User user);
	
	/**
	 * 회원 정보 삭제 (회원 탈퇴)
	 */
	void deleteUser(User user);
	
}
