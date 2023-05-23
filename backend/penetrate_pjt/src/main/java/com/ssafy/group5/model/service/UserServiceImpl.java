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
	public User getUser(User user) {
		return userMapper.getUser(user);
	}

	@Override
	public void signUp(Map<String, Object> user) {
		userMapper.signUp(user);
	}

	@Override
	public User getUserById(Map<String, Object> param) {
		return userMapper.getUserById(param);
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
