package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.group5.dto.Member;
import com.ssafy.group5.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Member login(Member member) throws SQLException {
		if (member.getUserid() == null || member.getUserpwd() == null)
			return null;
		return memberMapper.login(member);
	}

	@Override
	public Member userInfo(String userid) throws SQLException {
		return memberMapper.userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws SQLException {
		return memberMapper.getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}

}
