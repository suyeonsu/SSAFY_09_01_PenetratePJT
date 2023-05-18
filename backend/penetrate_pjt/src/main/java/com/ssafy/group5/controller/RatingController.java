package com.ssafy.group5.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.group5.dto.Rating;
import com.ssafy.group5.model.service.RatingService;

@RestController
@RequestMapping("/rate")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping
	public ResponseEntity<List<Rating>> getRatingList() throws SQLException {
		return new ResponseEntity<List<Rating>>(ratingService.selectAll(), HttpStatus.OK);
	}
	
	@GetMapping("/area")
	public ResponseEntity<List<Rating>> getRatingListBySidoGugun(@RequestParam Map<String, ?> code) throws SQLException {
		return new ResponseEntity<List<Rating>>(ratingService.selectBySidoGugun(code), HttpStatus.OK);
	}
	
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Rating>> getRatingListByType(@PathVariable int type) throws SQLException {
		return new ResponseEntity<List<Rating>>(ratingService.selectByType(type), HttpStatus.OK);
	}
	
	@GetMapping("/{val}")
	public ResponseEntity<List<Rating>> getRatingListByStar(@PathVariable int val) throws SQLException {
		return new ResponseEntity<List<Rating>>(ratingService.selectByStar(val), HttpStatus.OK);
	}
	
}
