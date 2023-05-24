package com.ssafy.group5.model.service;

import java.util.List;

import com.ssafy.group5.dto.Attraction;
import com.ssafy.group5.dto.Myplace;

public interface MyplaceService {
	List<Attraction> getMyplaceList(String userid);
	void addMyplace(Myplace myplace);
	void deleteMyplace(Myplace myplace);
}
