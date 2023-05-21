package com.ssafy.group5.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.group5.dto.User;
import com.ssafy.group5.model.service.UserService;
import com.ssafy.group5.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws SQLException, UnsupportedEncodingException{
		User userInfo = userService.getUser(user);
		if(userInfo!=null) {
			String token = jwtUtil.createToken(userInfo, 60*60*24*365);
			
			Map<String, String> result = new HashMap<>();
			result.put("token", token);
			
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		}
		else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping
	public ResponseEntity<?> getUserInfo(@RequestBody User user) throws SQLException {
		return new ResponseEntity<User>(userService.getUser(user), HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public void signUp(@RequestBody User user) {
		userService.signUp(user);
	}
	
	@PutMapping
	public void updateProfile(@RequestBody Map<String, ?> param) {
		userService.updateUserInfo(param);
	}
	
	@DeleteMapping
	public void withdrawal(@RequestBody User user) {
		userService.deleteUser(user);
	}
}