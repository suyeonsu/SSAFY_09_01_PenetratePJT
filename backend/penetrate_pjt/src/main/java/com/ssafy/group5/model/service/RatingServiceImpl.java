package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.group5.dto.UserRating;
import com.ssafy.group5.model.mapper.RatingMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingMapper ratingMapper;
	
	@Override
	public UserRating getUserRating(UserRating userRating) throws SQLException {
		// user_rating에서 유저가 이전에 점수를 등록했었는지 여부 조회
		UserRating beforeRating = ratingMapper.getUserRating(userRating);
		// 있으면 해당 점수 반환, 없으면 null 반환
		return beforeRating != null ? beforeRating : null;
	}

	@Override
	public void registRating(UserRating userRating) throws SQLException {
		// user_rating에서 유저가 이전에 점수를 등록했었는지 여부 조회
		UserRating beforeRating = ratingMapper.getUserRating(userRating);
		log.debug("db에서 가져온 기존 값: {}", beforeRating);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("attractionId", userRating.getAttractionId());
		// 해당 관광지에 등록한 적이 있으면
		if (beforeRating != null) {
			// rating 수정 (기존 컬럼값 감소, 새 컬럼값 증가)
			// 등록했던 점수 컬럼 깎기
			paramMap.put("col", beforeRating.getStar());
			ratingMapper.decrementStar(paramMap);
			// 새로 등록할 점수 컬럼 증가
			paramMap.put("col", userRating.getStar());
			ratingMapper.incrementStar(paramMap);
			// user_rating 새 점수로 수정	
			ratingMapper.updateUserRating(userRating);
		}
		// 없으면
		else {
			// rating 수정 (새 점수 증가)
			paramMap.put("col", userRating.getStar());
			ratingMapper.incrementStar(paramMap);
			// user_rating에 등록
			ratingMapper.insertUserRating(userRating);
		}
	}
	
}
