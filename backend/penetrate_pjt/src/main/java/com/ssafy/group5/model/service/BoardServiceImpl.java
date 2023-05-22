package com.ssafy.group5.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.group5.dto.Board;
import com.ssafy.group5.dto.BoardParameter;
import com.ssafy.group5.model.mapper.BoardMapper;
import com.ssafy.group5.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean writeArticle(Board board) throws Exception {
		if(board.getSubject() == null || board.getContent() == null) {
			throw new Exception();
		}
		return boardMapper.writeArticle(board) == 1;
	}

	@Override
	public List<Board> listArticle(Map<String, ?> param) throws SQLException {
		return boardMapper.listArticle(param);
	}

	@Override
	public PageNavigation makePageNavigation(BoardParameter boardParameter) throws SQLException {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(boardParameter.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = boardMapper.getTotalCount(boardParameter);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / boardParameter.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = boardParameter.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < boardParameter.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public Board getArticle(int articleno) throws SQLException {
		return boardMapper.getArticle(articleno);
	}
	
	@Override
	public void updateHit(int articleno) throws SQLException {
		boardMapper.updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(Board board) throws SQLException {
		return boardMapper.modifyArticle(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws SQLException {
		return boardMapper.deleteArticle(articleno) == 1;
	}
}