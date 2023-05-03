package com.ssafy.group5.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.group5.dto.Attraction;

public interface AttractionMapper {

	/**
	 * 관광지 전체 목록 조회
	 */
	List<Attraction> selectAll() throws SQLException;

	/**
	 * 관광지 테마별 목록 조회
	 */
	List<Attraction> selectByType(int contentTypeId) throws SQLException;

	
	/**
	 * 관광지 상세 조회
	 */
	Attraction selectById(int contentId) throws SQLException;
}
