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
import com.ssafy.group5.model.service.MailService;
import com.ssafy.group5.model.service.UserService;
import com.ssafy.group5.util.JwtUtil;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;
	
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
	
	@ApiOperation(value = "회원 가입", notes = "이메일 인증이 완료됐을 때만 요청 가능하다.")
	@PostMapping("signup")
	public void signup(@RequestBody Map<String, Object> user) throws SQLException {
		userService.signUp(user);
	}
			
	@ApiOperation(value = "이메일 인증코드 전송", notes = "전송한 인증코드를 반환한다.", response = Map.class)
	@PostMapping("/sendmail")
	public ResponseEntity<Map<String, Object>> sendMail(@RequestBody Map<String, String> map) throws SQLException { 
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		String code = mailService.sendMail(map.get("type"), map.get("email"));
		if(code.equals("error")) {
			resultMap.put("message", "FAIL");
			status = HttpStatus.ACCEPTED;
		}else {
			if(map.get("type").equals("findpw")) {
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("email", map.get("email"));
				paramMap.put("newpwd", code);
				userService.changePassword(paramMap);
			}
			resultMap.put("message", "SUCCESS");
			resultMap.put("code", code);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "비밀번호를 찾기 위해 회원가입시 입력한 아이디, 이메일 정보 전달", notes = "입력한 아이디, 이메일 정보와 일치하는 사용자 정보를 반환한다.", response = User.class)
	@GetMapping("/findpw")
	public ResponseEntity<Map<String, Object>> findPassword(@RequestBody Map<String, Object> param) throws SQLException {
		Map<String, Object> resultMap = new HashMap<>();
		User userInfo = userService.getUserById(param);
		if(userInfo != null) {
			resultMap.put("message", "SUCCESS");
		} else {
			resultMap.put("message", "FAIL");
		}
		resultMap.put("userInfo", userInfo);
		return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
	}
	
	@PutMapping
	public void updateProfile(@RequestBody Map<String, Object> param) {
		userService.updateUserInfo(param);
	}
	
	@ApiOperation(value = "비밀번호 변경을 위해 유저아이디, 기존 비밀번호, 새 비밀번호를 전달", notes = "현재 비밀번호가 일치할 경우 비밀번호를 갱신한다.")
	@PutMapping("/updatepw")
	public void updatePassword(@RequestBody Map<String, Object> param) throws SQLException {
		userService.changePassword(param);
	}
	
	@DeleteMapping
	public void withdrawal(@RequestBody User user) {
		userService.deleteUser(user);
	}
	 
}