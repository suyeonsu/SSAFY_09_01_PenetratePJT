package com.ssafy.group5.model.mapper;

import java.util.Map;

import com.ssafy.group5.dto.User;

public interface UserMapper {
	
	/**
	 * 유저 정보 반환
	 */
	User getUser(User user);
	
	/**
	 * 회원가입
	 */
	void signUp(Map<String, Object> user);
	
	/**
	 * 아이디와 일치하는 회원 정보 반환
	 */
	User getUserById(Map<String, Object> param);
	
	/**
	 * 회원 정보 수정 
	 */
	void updateUserInfo(Map<String, Object> param);
	
	/**
	 * 회원 정보 삭제 (회원 탈퇴)
	 */
	void deleteUser(User user);
	
	/**
	 * 비밀번호 변경
	 */
	void changePassword(Map<String, Object> param);

	
}
