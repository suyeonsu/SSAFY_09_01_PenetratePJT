package com.ssafy.group5.model.service;

import java.util.Map;

import com.ssafy.group5.dto.User;

public interface UserService {
	
	User getUser(User user);
	void signUp(Map<String, Object> user);
	User getUserById(Map<String, Object> param);
	void updateUserInfo(Map<String, Object> param);
	void deleteUser(User user);
	void changePassword(Map<String, Object> param);
	
}
