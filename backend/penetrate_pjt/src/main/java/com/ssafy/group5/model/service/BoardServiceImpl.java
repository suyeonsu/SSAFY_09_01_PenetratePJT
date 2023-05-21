package com.ssafy.group5.model.service;

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
	public List<Board> listArticle(Map<String, ?> param) throws Exception {
//		int start = boardParameter.getPg() == 0 ? 0 : (boardParameter.getPg() - 1) * boardParameter.getSpp();
//		boardParameter.setStart(start);
//		return boardMapper.listArticle(boardParameter);
		
		return boardMapper.listArticle(param);
	}

	@Override
	public PageNavigation makePageNavigation(BoardParameter boardParameter) throws Exception {
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
	public Board getArticle(int articleno) throws Exception {
		return boardMapper.getArticle(articleno);
	}
	
	@Override
	public void updateHit(int articleno) throws Exception {
		boardMapper.updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean modifyArticle(Board board) throws Exception {
		return boardMapper.modifyArticle(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteArticle(int articleno) throws Exception {
		return boardMapper.deleteArticle(articleno) == 1;
	}
}