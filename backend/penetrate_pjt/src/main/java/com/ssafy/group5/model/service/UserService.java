package com.ssafy.group5.model.service;

import java.util.Map;

import com.ssafy.group5.dto.User;

public interface UserService {
	
	User login(User user);
	String checkDuplication(Map<String, Object> param);
	User getUser(String userId);
	void signUp(User user);
	User getUserById(User user);
	void updateUserInfo(Map<String, Object> param);
	void deleteUser(User user);
	void changePassword(Map<String, Object> param);
	
}
