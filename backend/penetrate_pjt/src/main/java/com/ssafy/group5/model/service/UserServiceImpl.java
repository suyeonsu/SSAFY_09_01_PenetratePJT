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
	public void signUp(User user) {
		userMapper.signUp(user);
	}

	@Override
	public void updateUserInfo(Map<String, ?> param) {
		userMapper.updateUserInfo(param);
	}

	@Override
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}

}
