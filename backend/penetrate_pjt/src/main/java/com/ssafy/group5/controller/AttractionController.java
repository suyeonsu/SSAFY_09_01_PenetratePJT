package com.ssafy.group5.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.group5.dto.Attraction;
import com.ssafy.group5.model.service.AttractionService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/trip")
@Slf4j
public class AttractionController {

	@Autowired
	private AttractionService attractionService;
	
	@GetMapping
	@ApiOperation(value = "관광지 목록 조회", notes = "페이지 번호, 관광지 타입, 검색어, 지역코드에 따른 관광지 목록을 조회한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "페이지 번호", required = true, dataType = "int", paramType = "query", defaultValue = "1"),
		@ApiImplicitParam(name = "pageSize", value = "페이지 당 글 개수", required = true, dataType = "int", paramType = "query", defaultValue = "10"),
		@ApiImplicitParam(name = "type", value = "관광지 타입", required = true, dataType = "int", paramType = "query", defaultValue = "12"),
		@ApiImplicitParam(name = "keywords", value = "사용자 입력 검색어", required = false, dataType = "String", paramType = "query", defaultValue = ""),
		@ApiImplicitParam(name = "sido", value = "시/도 코드", required = false, dataType = "int", paramType = "query", defaultValue = "0"),
		@ApiImplicitParam(name = "gugun", value = "구/군 코드", required = false, dataType = "int", paramType = "query", defaultValue = "0")
	})
	public ResponseEntity<PageInfo<Attraction>> getAttractionList(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam int type, @RequestParam List<String> keywords, @RequestParam(defaultValue = "0") int sido, @RequestParam(defaultValue = "0") int gugun) throws SQLException {
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("keywords", keywords);
		param.put("sido", sido);
		param.put("gugun", gugun);
		log.debug("getAttractionList에서 전달받은 파라미터: {}", param);
		PageHelper.startPage(pageNum, pageSize);
		return new ResponseEntity<PageInfo<Attraction>>(PageInfo.of(attractionService.getAttractionList(param)), HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	@ApiOperation(value = "관광지 상세 조회", notes = "관광지 id를 통해 관광지를 상세 조회한다.")
	@ApiImplicitParam(name = "id", value = "관광지 id", required = true, dataType = "int", paramType = "query")
	public ResponseEntity<Attraction> getAttractionDetail(@RequestParam int id) throws SQLException {
		return new ResponseEntity<Attraction>(attractionService.getAttractionDetail(id), HttpStatus.OK);
	}

}
