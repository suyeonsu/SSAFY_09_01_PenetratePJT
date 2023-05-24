package com.ssafy.group5.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.group5.dto.User;
import com.ssafy.group5.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(User user) {
		return userMapper.login(user);
	}
	
	@Override
	public User getUser(String userid) {
		return userMapper.getUser(userid);
	}

	@Override
	public void signUp(User user) {
		userMapper.signUp(user);
	}

	@Override
	public User getUserById(User user) {
		return userMapper.getUserById(user);
	}

	@Override
	public void updateUserInfo(Map<String, Object> param) {
		userMapper.updateUserInfo(param);
	}
	

	@Override
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}

	@Override
	public void changePassword(Map<String, Object> param) {
		userMapper.changePassword(param);
	}

}
