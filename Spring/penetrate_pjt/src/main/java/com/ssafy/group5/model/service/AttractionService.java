package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.json.JsonParser;

import com.google.gson.JsonElement;
import com.ssafy.group5.dto.Attraction;
import com.ssafy.group5.model.dao.AttractionDao;

@Service
public class AttractionService {
	
	AttractionMapper mapper;
	
	public static AttractionService getInstance() {
		return attractionService;
	}
	public List<Attraction> getAttractionList(String contentTypeId, String sidoCode, String gugunCode, String title)
			throws SQLException {
		return attractionDao.getAttractionList(contentTypeId, sidoCode, gugunCode, title);
	}
	public List<Attraction> getAttractionList(String mapArea)
			throws SQLException {
		//맵 영역 String 파싱
		JsonParser jsonParser = new JsonParser();
		JsonElement elements = jsonParser.parse(mapArea);
		List<String> mapAreaInfo = new ArrayList<>();
		mapAreaInfo.add(elements.getAsJsonObject().get("swLat").getAsString());
		mapAreaInfo.add(elements.getAsJsonObject().get("swLon").getAsString());
		mapAreaInfo.add(elements.getAsJsonObject().get("neLat").getAsString());
		mapAreaInfo.add(elements.getAsJsonObject().get("neLon").getAsString());
		return attractionDao.getAttractionList(mapAreaInfo);
	}
}
