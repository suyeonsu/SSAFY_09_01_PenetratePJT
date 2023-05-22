package com.ssafy.group5.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.group5.dto.UserRating;

public interface RatingMapper {

	UserRating getUserRating(UserRating userRating) throws SQLException;
	void decrementStar(Map<String, Object> param) throws SQLException;
	void incrementStar(Map<String, Object> param) throws SQLException;
	void updateUserRating(UserRating userRating) throws SQLException;
	void insertUserRating(UserRating userRating) throws SQLException;

}