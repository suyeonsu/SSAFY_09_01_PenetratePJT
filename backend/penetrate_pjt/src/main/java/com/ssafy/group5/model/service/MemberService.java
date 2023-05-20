package com.ssafy.group5.model.service;

import java.sql.SQLException;

import com.ssafy.group5.dto.Member;

public interface MemberService {

	public Member login(Member member) throws SQLException;
	public Member userInfo(String userid) throws SQLException;
	public void saveRefreshToken(String userid, String refreshToken) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleRefreshToken(String userid) throws SQLException;
	
}
