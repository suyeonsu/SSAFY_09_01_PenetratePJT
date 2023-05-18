package com.ssafy.group5.model.service;

<<<<<<< HEAD
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.group5.dto.User;
import com.ssafy.group5.model.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	public User login(User loginInfo) throws SQLException {
		return mapper.login(loginInfo);
	}
	
	public boolean join(User user) throws SQLException{
		return mapper.join(user);
	}
	
	// 로그인, 회원가입 테스트!
	public static void main(String[] args) throws SQLException {
		//로그인 테스트
//		User user1 = new User("admin","admin","김관리","admin@naver.com");
//		User loginUser =  login(user1);
//		System.out.println(loginUser);
	
		// 회원가입 테스트
//		User user2 = new User("haribo","haribo","김하리보","haribo@naver.com");
//		join(user2);
	}
=======
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
	
>>>>>>> debeea6a0411b3bc2e8435947f7a53121eb2824b
}
