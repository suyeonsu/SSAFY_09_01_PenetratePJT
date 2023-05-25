package com.ssafy.group5.model.service;

import java.util.Map;

import com.ssafy.group5.dto.User;

public interface UserService {
	
	String checkDuplication(Map<String, Object> param);
	User getUser(String userId);
	void signUp(User user);
	User getUserById(User user);
	void updateUserInfo(User user);
	void deleteUser(User user);
	void changePassword(Map<String, Object> param);
	
}
