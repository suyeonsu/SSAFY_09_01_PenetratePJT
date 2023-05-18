package com.ssafy.group5.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.group5.dto.Rating;

public interface RatingMapper {

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
	 * val 이상인 평점 조회
	 */
	List<Rating> selectByStar(int val) throws SQLException;
	
//	/**
//	 * 관광지 평점 등록
//	 */
//	void registRating(int val) throws SQLException;
//	
//	/**
//	 * 관광지 평점 수정
//	 */
//	void updateRating(int val) throws SQLException;
//	
	
}
