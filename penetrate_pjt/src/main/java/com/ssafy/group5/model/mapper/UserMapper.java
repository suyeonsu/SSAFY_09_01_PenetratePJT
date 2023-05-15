package com.ssafy.group5.model.mapper;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.group5.dto.User;

public interface UserMapper {

	/**
	 * 로그인
	 */
	public User login(User loginInfo) throws SQLException;
	
	/**
	 * 회원 가입
	 */
	public boolean join(User user) throws SQLException;
	
	
}