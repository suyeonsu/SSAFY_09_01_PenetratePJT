package com.ssafy.group5.model.service;

import java.sql.SQLException;

import com.ssafy.group5.dto.UserRating;

public interface RatingService {
	
	UserRating getUserRating(UserRating rating) throws SQLException;
	void registRating(UserRating userRating) throws SQLException;
	
}
