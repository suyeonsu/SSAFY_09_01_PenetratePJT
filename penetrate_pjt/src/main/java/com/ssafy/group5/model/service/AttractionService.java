package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.group5.dto.Attraction;

public interface AttractionService {

	/**
	 * 관광지 전체 목록 조회
	 */
	List<Attraction> selectAll() throws SQLException;

	/**
	 * 관광지 테마별 목록 조회
	 */
	List<Attraction> selectByType(int contentTypeId) throws SQLException;
	
	/**
	 * 지역별 관광지 목록 조회
	 */
	List<Attraction> selectBySidoGugun(Map<String, Integer> code) throws SQLException;
	
	/**
	 * 관광지 상세 조회
	 */
	Attraction selectById(int contentId) throws SQLException;

}
