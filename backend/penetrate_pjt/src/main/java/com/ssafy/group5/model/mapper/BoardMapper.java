package com.ssafy.group5.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.group5.dto.Board;

@Mapper
public interface BoardMapper {
	
	public int writeArticle(Board board) throws SQLException;
	public List<Board> listArticle(Map<String, ?> param) throws SQLException;
	public int getTotalCount(Map<String, ?> param) throws SQLException;
	public Board getArticle(int articleno) throws SQLException;
	public void updateHit(int articleno) throws SQLException;
	public int modifyArticle(Board board) throws SQLException;
	public int deleteArticle(int articleno) throws SQLException;
	
}