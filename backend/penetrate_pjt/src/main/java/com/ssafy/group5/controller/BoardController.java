package com.ssafy.group5.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.group5.dto.Board;
import com.ssafy.group5.model.service.BoardService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;

	@PostMapping
	@ApiOperation(value = "글 작성", notes = "입력받은 정보로 글을 등록한다.")
	public void writeArticle(@RequestBody Board board) throws Exception {
		log.debug("writeArticle에서 전달받은 요청: {}", board);
		boardService.writeArticle(board);
	}
	
	@GetMapping
	@ApiOperation(value = "글 목록 조회", notes = "페이지 번호에 해당하는 게시글을 반환한다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "페이지 번호", required = true, dataType = "int", paramType = "query", defaultValue = "1"),
		@ApiImplicitParam(name = "pageSize", value = "페이지 당 글 개수", required = true, dataType = "int", paramType = "query", defaultValue = "10"),
		@ApiImplicitParam(name = "filter", value = "검색 기준", required = false, dataType = "String", paramType = "query", defaultValue = "subject"),
		@ApiImplicitParam(name = "keywords", value = "검색어", required = false, dataType = "String", paramType = "query", defaultValue = ""),
		@ApiImplicitParam(name = "sort", value = "정렬 기준", required = false, dataType = "Strings", paramType = "query", defaultValue = "")
	})
	public ResponseEntity<PageInfo<Board>> listArticle(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String filter, @RequestParam List<String> keywords, @RequestParam String sort) throws SQLException {
		Map<String, Object> param = new HashMap<>();
		param.put("filter", filter);
		param.put("keywords", keywords);
		param.put("sort", sort);
		log.debug("listArticle에서 전달받은 파라미터: {}", param);
		PageHelper.startPage(pageNum, pageSize);
		return new ResponseEntity<PageInfo<Board>>(PageInfo.of(boardService.listArticle(param)), HttpStatus.OK);
	}
	
	@GetMapping("/{articleno}")
	@ApiOperation(value = "글 상세 조회", notes = "글 번호에 해당하는 게시글을 반환한다.")
	public ResponseEntity<Board> getArticle(@PathVariable("articleno") int articleno) throws SQLException {
		boardService.updateHit(articleno);
		return new ResponseEntity<Board>(boardService.getArticle(articleno), HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value = "글 수정", notes = "글 번호에 해당하는 게시글을 수정한다.")
	public void modifyArticle(@RequestBody Board board) throws SQLException {
		boardService.modifyArticle(board);
	}
	
	@DeleteMapping
	@ApiOperation(value = "글 삭제", notes = "글 번호에 해당하는 게시글을 삭제한다.")
	public void deleteArticle(@RequestParam int articleno) throws SQLException {
		boardService.deleteArticle(articleno);
	}
}