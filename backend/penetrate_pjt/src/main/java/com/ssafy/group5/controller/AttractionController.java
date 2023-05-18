package com.ssafy.group5.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
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
	public ResponseEntity<List<Attraction>> getAttractionList() throws SQLException {
		return new ResponseEntity<List<Attraction>>(attractionService.selectAll(), HttpStatus.OK);
	}
	
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Attraction>> getAttractionListByType(@PathVariable int type) throws SQLException {
		return new ResponseEntity<List<Attraction>>(attractionService.selectByType(type), HttpStatus.OK);
	}
	
	@GetMapping("/area")
	public ResponseEntity<List<Attraction>> getAttractionListBySigoGugun(@RequestParam Map<String, ?> code) throws SQLException {
		return new ResponseEntity<List<Attraction>>(attractionService.selectBySidoGugun(code), HttpStatus.OK);
	}
	
	@GetMapping("/keywords")
	public ResponseEntity<List<Attraction>> getAttractionListByKeyword(@RequestParam String[] keywords) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		map.put("keywords", keywords);
		log.debug("keywords: {}", Arrays.toString(keywords));
		return new ResponseEntity<List<Attraction>>(attractionService.selectByKeyword(map), HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Attraction> getAttractionDetail(@PathVariable int id) throws SQLException {
		return new ResponseEntity<Attraction>(attractionService.selectById(id), HttpStatus.OK);
	}

}
