package com.ssafy.group5.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.group5.dto.UserRating;
import com.ssafy.group5.model.service.RatingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rate")
@Slf4j
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping
	@ApiOperation(value = "관광지에 사용자가 준 평점 조회", notes = "사용자 id와 관광지 id를 통해 사용자가 해당 관광지에 부여한 평점을 반환한다.")
	public ResponseEntity<UserRating> getUserRating(@RequestParam String userId, @RequestParam int attractionId) throws SQLException {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setAttractionId(attractionId);
		log.debug("controller의 getUserRating에서 전달받은 파라미터: {}", userRating);
		log.debug("service에서 받아온 값: {}", ratingService.getUserRating(userRating));
		return new ResponseEntity<UserRating>(ratingService.getUserRating(userRating), HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value = "관광지에 사용자가 준 평점 등록 및 수정", notes = "관광지에 사용자로부터 받은 평점을 등록하거나 수정한다.")
	public void registRating(@RequestBody UserRating userRating) throws SQLException {
		log.debug("controller의 registRating에서 전달받은 파라미터: {}", userRating);
		ratingService.registRating(userRating);
	}
	
}
