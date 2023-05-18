package com.ssafy.group5.model.service;

import java.util.List;

import com.ssafy.group5.dto.Board;
import com.ssafy.group5.dto.BoardParameter;
import com.ssafy.group5.util.PageNavigation;

public interface BoardService {
	public boolean writeArticle(Board boardDto) throws Exception;
	public List<Board> listArticle(BoardParameter boardParameterDto) throws Exception;
	public PageNavigation makePageNavigation(BoardParameter boardParameterDto) throws Exception;
	
	public Board getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(Board boardDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
}
