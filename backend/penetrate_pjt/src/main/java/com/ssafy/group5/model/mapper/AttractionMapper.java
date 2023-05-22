package com.ssafy.group5.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.group5.dto.Attraction;

public interface AttractionMapper {

	/**
	 * 관광지 목록 조회
	 */
	List<Attraction> attractionList(Map<String, ?> param) throws SQLException;

	/**
	 * 관광지 상세 조회
	 */
	Attraction attractionDetail(int contentId) throws SQLException;
}
