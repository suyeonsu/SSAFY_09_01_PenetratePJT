package com.ssafy.group5.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.group5.dto.UserRating;
import com.ssafy.group5.model.service.RatingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rate")
@Slf4j
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping
	public ResponseEntity<UserRating> getUserRating(@RequestBody UserRating userRating) throws SQLException {
		log.debug("controller의 getUserRating에서 전달받은 파라미터: {}", userRating);
		return new ResponseEntity<UserRating>(ratingService.getUserRating(userRating), HttpStatus.OK);
	}
	
	@PostMapping
	public void registRating(@RequestBody UserRating userRating) throws SQLException {
		log.debug("controller의 registRating에서 전달받은 파라미터: {}", userRating);
			ratingService.registRating(userRating);
	}
	
}
