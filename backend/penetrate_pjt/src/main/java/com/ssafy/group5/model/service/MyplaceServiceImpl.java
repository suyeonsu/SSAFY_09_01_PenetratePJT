package com.ssafy.group5.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.group5.dto.Attraction;
import com.ssafy.group5.dto.Myplace;
import com.ssafy.group5.model.mapper.MyplaceMapper;

@Service
public class MyplaceServiceImpl implements MyplaceService {

	@Autowired
	private MyplaceMapper myplaceMapper;
	
	@Override
	public List<Attraction> getMyplaceList(String userid) {
		return myplaceMapper.getMyplaceList(userid);
	}

	@Override
	public void addMyplace(Myplace myplace) {
		myplaceMapper.addMyplace(myplace);
	}

	@Override
	public void deleteMyplace(Myplace myplace) {
		myplaceMapper.deleteMyplace(myplace);
	}
}
