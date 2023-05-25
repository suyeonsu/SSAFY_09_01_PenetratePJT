package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.group5.dto.Attraction;
import com.ssafy.group5.dto.Myplace;

public interface AttractionService {

	/**
	 * 관광지 목록 조회
	 */
	List<Attraction> getAttractionList(Map<String, Object> param) throws SQLException;

	/**
	 * 관광지 상세 조회
	 */
	Attraction getAttractionDetail(int attractionId) throws SQLException;

	/**
	 * 북마크 장소 조회
	 */
	Attraction getMyplace(Myplace myplace);
}
