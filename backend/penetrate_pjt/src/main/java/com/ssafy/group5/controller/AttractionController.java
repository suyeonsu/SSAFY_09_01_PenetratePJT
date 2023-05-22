package com.ssafy.group5.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.group5.dto.Attraction;
import com.ssafy.group5.model.service.AttractionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/trip")
@Slf4j
public class AttractionController {

	@Autowired
	private AttractionService attractionService;
	
	@GetMapping
	public ResponseEntity<List<Attraction>> getAttractionList(@RequestBody Map<String, Object> param) throws SQLException {
		log.debug("getAttractionList에서 전달받은 파라미터: {}", param);
		return new ResponseEntity<List<Attraction>>(attractionService.getAttractionList(param), HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Attraction> getAttractionDetail(@PathVariable int id) throws SQLException {
		return new ResponseEntity<Attraction>(attractionService.getAttractionDetail(id), HttpStatus.OK);
	}

}
