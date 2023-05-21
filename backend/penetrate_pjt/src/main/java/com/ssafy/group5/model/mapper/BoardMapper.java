package com.ssafy.group5.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.group5.dto.Board;
import com.ssafy.group5.dto.BoardParameter;

@Mapper
public interface BoardMapper {
	
	public int writeArticle(Board boardDto) throws SQLException;
//	public List<Board> listArticle(BoardParameter boardParameterDto) throws SQLException;
	public List<Board> listArticle(Map<String, ?> boardParameterDto) throws SQLException;
	public int getTotalCount(BoardParameter boardParameterDto) throws SQLException;
	public Board getArticle(int articleno) throws SQLException;
	public void updateHit(int articleno) throws SQLException;
	public int modifyArticle(Board boardDto) throws SQLException;
	public int deleteArticle(int articleno) throws SQLException;
	
}