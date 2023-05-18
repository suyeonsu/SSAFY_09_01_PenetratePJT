package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.group5.dto.Hotplace;

public interface HotplaceService {

	/**
	 * 핫플레이스 전체 목록 조회
	 */
	List<Hotplace> selectAll() throws SQLException;
	
	/**
	 * 핫플레이스 상세 조회
	 */
	Hotplace selectById(int contentId) throws SQLException;
	
	/** 
	 * 지역별 핫플레이스 조회
	 */
	List<Hotplace> selectBySidoGugun(Map<String, Integer> code) throws SQLException;
	
	/**
	 * 관광지 테아별 핫플레이스 조회
	 */
	List<Hotplace> selectByType(int contentTypeId) throws SQLException;
	
	/**
	 * 별점별 핫플레이스 조회
	 * ex) 4.0 이상, 3.0이상, ...
	 */
	List<Hotplace> selectByStar(int val) throws SQLException;
	
}
