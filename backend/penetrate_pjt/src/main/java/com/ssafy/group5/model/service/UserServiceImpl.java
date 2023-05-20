package com.ssafy.group5.model.service;

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
	public void signUp(User user) {
		userMapper.signUp(user);
	}

	@Override
	public void updateUserInfo(User user) {
		userMapper.updateUserInfo(user);
	}

	@Override
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}

}
