package com.ssafy.group5.model.mapper;

<<<<<<< HEAD
import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;
=======
import java.util.Map;
>>>>>>> debeea6a0411b3bc2e8435947f7a53121eb2824b

import com.ssafy.group5.dto.User;

public interface UserMapper {
<<<<<<< HEAD

	/**
	 * 로그인
	 */
	public User login(User loginInfo) throws SQLException;
	
	/**
	 * 회원 가입
	 */
	public boolean join(User user) throws SQLException;
	
=======
	
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
	 * 회원 정보 삭제 (회원 탈퇴)
	 */
	void deleteUser(User user);
>>>>>>> debeea6a0411b3bc2e8435947f7a53121eb2824b
	
}
