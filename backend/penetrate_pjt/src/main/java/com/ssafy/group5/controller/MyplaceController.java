package com.ssafy.group5.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.group5.dto.Attraction;
import com.ssafy.group5.dto.Myplace;
import com.ssafy.group5.model.service.MyplaceService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/myplace")
@Slf4j
public class MyplaceController {
	
	@Autowired
	private MyplaceService myplaceService;
	
	@GetMapping
	@ApiOperation(value = "북마크 목록 조회", notes = "사용자가 저장한 북마크 목록을 조회한다.")
	public ResponseEntity<PageInfo<Attraction>> getMyplaceList(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String userid) throws SQLException {
		PageHelper.startPage(pageNum, pageSize);
		return new ResponseEntity<PageInfo<Attraction>>(PageInfo.of(myplaceService.getMyplaceList(userid)), HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value = "북마크 등록", notes = "관광지를 사용자 북마크 목록에 저장한다.")
	public void addMyplace(@RequestParam String userId, @RequestParam int attractionId) throws SQLException {
		Myplace myplace = new Myplace();
		myplace.setUserId(userId);
		myplace.setAttractionId(attractionId);
		log.debug("myplaceController의 addMyplace 받은 파라미터: {}", myplace);
		myplaceService.addMyplace(myplace); 
	}
	
	@DeleteMapping
	@ApiOperation(value = "북마크 취소", notes = "사용자 북마크 목록에서 관광지를 삭제한다.")
	public void deleteMyplace(@RequestParam String userId, @RequestParam int attractionId) throws SQLException {
		Myplace myplace = new Myplace();
		myplace.setUserId(userId);
		myplace.setAttractionId(attractionId);
		log.debug("myplaceController의 deleteMyplace에서 받은 파라미터: {}", myplace);
		myplaceService.deleteMyplace(myplace); 
	}
	
}