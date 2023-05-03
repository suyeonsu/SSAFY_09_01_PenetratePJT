package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.group5.dto.Hotplace;
import com.ssafy.group5.model.mapper.HotplaceMapper;

@Service
public class HotplaceServiceImpl implements HotplaceService {

	@Autowired
	private HotplaceMapper hotplaceMapper;
	
	@Override
	public List<Hotplace> selectAll() throws SQLException {
		return hotplaceMapper.selectAll();
	}

	@Override
	public Hotplace selectById(int contentId) throws SQLException {
		return hotplaceMapper.selectById(contentId);
	}

	@Override
	public List<Hotplace> selectBySidoGugun(int sidoCode, int gugunCdoe) throws SQLException {
		return hotplaceMapper.selectBySidoGugun(sidoCode, gugunCdoe);
	}

	@Override
	public List<Hotplace> selectByType(int contentTypeId) throws SQLException {
		return hotplaceMapper.selectByType(contentTypeId);
	}

	@Override
	public List<Hotplace> selectByStar(int val) throws SQLException {
		return hotplaceMapper.selectByStar(val);
	}

	@Override
	public void registerHotplace(Hotplace hotplace) throws SQLException {
		hotplaceMapper.registerHotplace(hotplace);
	}

}
