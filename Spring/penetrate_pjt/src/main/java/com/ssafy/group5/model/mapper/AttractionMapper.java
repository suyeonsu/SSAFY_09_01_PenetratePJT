package com.ssafy.group5.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.group5.dto.Attraction;

public interface AttractionMapper {

	// 상세조회
	public List<Attraction> getAttractionList(String contentTypeId, String sidoCode, String gugunCode, String title)
			throws SQLException ;
	
	// 범위조회
	public List<Attraction> getAttractionList(List<String> mapAreaInfo) throws SQLException;
	
	
	
}
