package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.group5.dto.Attraction;
import com.ssafy.group5.model.mapper.AttractionMapper;

@Service
public class AttractionServiceImpl implements AttractionService {

	@Autowired
	private AttractionMapper attractionMapper;
	
	@Override
	public List<Attraction> selectAll() throws SQLException {
		return attractionMapper.selectAll();
	}

	@Override
	public List<Attraction> selectByType(int contentTypeId) throws SQLException {
		return attractionMapper.selectByType(contentTypeId);
	}
	
	@Override
	public List<Attraction> selectBySidoGugun(Map<String, Integer> code) throws SQLException {
		return attractionMapper.selectBySidoGugun(code);
	}

	@Override
	public Attraction selectById(int contentId) throws SQLException {
		return attractionMapper.selectById(contentId);
	}


}
