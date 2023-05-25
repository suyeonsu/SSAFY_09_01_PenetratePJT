package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.group5.dto.Attraction;
import com.ssafy.group5.dto.Myplace;
import com.ssafy.group5.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {

	@Autowired
	private AttractionMapper attractionMapper;
	
	@Override
	public List<Attraction> getAttractionList(Map<String, Object> param) throws SQLException {
		return attractionMapper.attractionList(param);
	}

	@Override
	public Attraction getAttractionDetail(int attractionId) throws SQLException {
		return attractionMapper.attractionDetail(attractionId);
	}

	@Override
	public Attraction getMyplace(Myplace myplace) {
		return attractionMapper.getMyplace(myplace);
	}

}
