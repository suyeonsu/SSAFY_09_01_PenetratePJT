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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
	@ApiOperation(value = "로그인", notes = "사용자가 입력한 아이디와 비밀번호를 조회하고 토큰을 발급한다.")
	public ResponseEntity<?> login(@RequestBody User user) throws SQLException, UnsupportedEncodingException{
		User userInfo = userService.getUser(user.getUserid());
		if(userInfo!=null) {
			String token = jwtUtil.createToken(userInfo, 60*60*24*365);
			
			Map<String, String> result = new HashMap<>();
			result.put("token", token);
			
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		}
		else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/check")
	@ApiOperation(value = "아이디/이메일 중복 체크", notes = "입력받은 아이디/이메일의 중복 여부를 반환한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "key", value = "userid(아이디) 또는 email(이메일)", required = true, dataType = "String", paramType = "query", defaultValue = "userid"),
		@ApiImplicitParam(name = "val", value = "사용자 입력값", required = true, dataType = "String", paramType = "query", defaultValue = "")
	})
	public ResponseEntity<?> checkDuplication(@RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "") String val) throws SQLException {
		Map<String, Object> param = new HashMap<>();
		param.put("key", key);
		param.put("val", val);
		log.debug("authController에서 받은 param: {}", param);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("message", userService.checkDuplication(param));
		return new ResponseEntity<Map<String, String>>(resultMap, HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	@ApiOperation(value = "회원 정보 조회", notes = "사용자가 입력한 아이디에 해당하는 유저 정보를 반환한다.")
	public ResponseEntity<?> getUserInfo(@RequestParam String userId) throws SQLException {
		return new ResponseEntity<User>(userService.getUser(userId), HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	@ApiOperation(value = "회원 가입", notes = "이메일 인증이 완료됐을 때만 요청 가능하며, 전달받은 user 정보를 db에 저장한다.")
	public void signup(@RequestBody User user) throws SQLException {
		userService.signUp(user);
	}
			
	@PostMapping("/sendmail")
	@ApiOperation(value = "인증 메일 전송", notes = "'이메일 인증'과 '임시 비밀번호 발급' 중 수행하려는 작업 타입에 따라, 사용자 이메일로 인증 코드가 포함된 메일을 전송한다.", response = Map.class)
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
	
	@GetMapping("/findpw")
	@ApiOperation(value = "비밀번호 찾기", notes = "회원가입시 입력한 아이디, 이메일 정보와 일치하는 사용자 정보를 반환한다.", response = User.class)
	public ResponseEntity<User> findPassword(@RequestParam String userid, @RequestParam String email) throws SQLException {
		User user = new User();
		user.setUserid(userid);
		user.setEmail(email);
		return new ResponseEntity<User>(userService.getUserById(user), HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value = "사용자 정보 수정", notes = "입력받은 정보로 사용자 정보를 갱신한다.")
	public ResponseEntity<?> updateProfile(@RequestBody User user) throws UnsupportedEncodingException {
		userService.updateUserInfo(user);
		User userInfo = userService.getUser(user.getUserid());
		if(userInfo!=null) {
			String token = jwtUtil.createToken(userInfo, 60*60*24*365);
			
			Map<String, String> result = new HashMap<>();
			result.put("token", token);
			
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		}
		else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/updatepw")
	@ApiOperation(value = "비밀번호 변경", notes = "현재 비밀번호가 일치할 경우 새 비밀번호로 갱신한다.")
	public void updatePassword(@RequestBody Map<String, Object> param) throws SQLException {
		userService.changePassword(param);
	}
	
	@DeleteMapping
	@ApiOperation(value = "회원 탈퇴", notes = "비밀번호가 일치할 경우 회원 탈퇴를 진행한다.")
	public ResponseEntity<?> withdrawal(@RequestBody User user) {
		User userInfo = userService.getUserById(user);
		Map<String, Object> resultMap = new HashMap<>();
		if(userInfo != null) {
			userService.deleteUser(user);
			resultMap.put("message", "SUCCESS");
		} else {
			resultMap.put("message", "FAIL");
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
	}
	 
}