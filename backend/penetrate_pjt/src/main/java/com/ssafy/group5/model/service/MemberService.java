package com.ssafy.group5.model.service;

import com.ssafy.group5.dto.Member;

public interface MemberService {

	public Member login(Member member) throws Exception;
	public Member userInfo(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	
}
