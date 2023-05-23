package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.group5.dto.Board;

public interface BoardService {
	
	boolean writeArticle(Board board) throws Exception;
	List<Board> listArticle(Map<String, ?> param) throws SQLException;
	Board getArticle(int articleno) throws SQLException;
	void updateHit(int articleno) throws SQLException;
	boolean modifyArticle(Board board) throws SQLException;
	boolean deleteArticle(int articleno) throws SQLException;
}
