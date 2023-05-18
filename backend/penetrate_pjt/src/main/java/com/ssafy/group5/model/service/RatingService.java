package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.group5.dto.Rating;

public interface RatingService {

	/**
	 * 관광지 전체 평점 조회
	 */
	List<Rating> selectAll() throws SQLException;
	
	/**
	 * 특정 관광지 평점 조회
	 */
	Rating selectById(int contentId) throws SQLException;
	
	/** 
	 * 지역별 관광지 평점 조회
	 */
	List<Rating> selectBySidoGugun(Map<String, ?> code) throws SQLException;
	
	/**
	 * 테마별 관광지 평점 조회
	 */
	List<Rating> selectByType(int contentTypeId) throws SQLException;
	
	/**
	 * 특정 값 이상의 평점 조회
	 * ex) 4.0 이상, 3.0이상, ...
	 */
	List<Rating> selectByStar(int val) throws SQLException;
	
}
