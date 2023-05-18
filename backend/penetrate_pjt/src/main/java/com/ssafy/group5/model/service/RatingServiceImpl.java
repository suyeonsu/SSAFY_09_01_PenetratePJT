package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.group5.dto.Rating;
import com.ssafy.group5.model.mapper.RatingMapper;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingMapper ratingMapper;
	
	@Override
	public List<Rating> selectAll() throws SQLException {
		return ratingMapper.selectAll();
	}

	@Override
	public Rating selectById(int contentId) throws SQLException {
		return ratingMapper.selectById(contentId);
	}

	@Override
	public List<Rating> selectBySidoGugun(Map<String, ?> code) throws SQLException {
		return ratingMapper.selectBySidoGugun(code);
	}

	@Override
	public List<Rating> selectByType(int contentTypeId) throws SQLException {
		return ratingMapper.selectByType(contentTypeId);
	}

	@Override
	public List<Rating> selectByStar(int val) throws SQLException {
		return ratingMapper.selectByStar(val);
	}

}
