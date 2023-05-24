package com.ssafy.group5.model.mapper;

import java.util.List;

import com.ssafy.group5.dto.Attraction;
import com.ssafy.group5.dto.Myplace;

public interface MyplaceMapper {
	List<Attraction> getMyplaceList(String userid);
	void addMyplace(Myplace myplace);
	void deleteMyplace(Myplace myplace);
}
