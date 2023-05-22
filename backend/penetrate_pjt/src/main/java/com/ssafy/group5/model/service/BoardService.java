package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.group5.dto.Board;
import com.ssafy.group5.dto.BoardParameter;
import com.ssafy.group5.util.PageNavigation;

public interface BoardService {
	
	public boolean writeArticle(Board board) throws Exception;
	public List<Board> listArticle(Map<String, ?> param) throws SQLException;
	public PageNavigation makePageNavigation(BoardParameter boardParameter) throws SQLException;
	public Board getArticle(int articleno) throws SQLException;
	public void updateHit(int articleno) throws SQLException;
	public boolean modifyArticle(Board board) throws SQLException;
	public boolean deleteArticle(int articleno) throws SQLException;
}
