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

import com.ssafy.group5.dto.Hotplace;
import com.ssafy.group5.model.service.HotplaceService;

@RestController
@RequestMapping("/hotpl")
public class HotplaceController {
	
	@Autowired
	private HotplaceService hotplaceService;
	
	@GetMapping
	public ResponseEntity<List<Hotplace>> getHotplaceList() throws SQLException {
		return new ResponseEntity<List<Hotplace>>(hotplaceService.selectAll(), HttpStatus.OK);
	}
	
	@GetMapping("/area")
	public ResponseEntity<List<Hotplace>> getHotplaceListBySidoGugun(@RequestParam Map<String, Integer> code) throws SQLException {
		return new ResponseEntity<List<Hotplace>>(hotplaceService.selectBySidoGugun(code), HttpStatus.OK);
	}
	
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Hotplace>> getHotplaceListByType(@PathVariable int type) throws SQLException {
		return new ResponseEntity<List<Hotplace>>(hotplaceService.selectByType(type), HttpStatus.OK);
	}
	
	@GetMapping("/rate/{val}")
	public ResponseEntity<List<Hotplace>> getHotplaceListByStar(@PathVariable int val) throws SQLException {
		return new ResponseEntity<List<Hotplace>>(hotplaceService.selectByStar(val), HttpStatus.OK);
	}
	
}
