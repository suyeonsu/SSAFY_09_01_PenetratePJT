package com.ssafy.group5.controller;

import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.group5.dto.Board;
import com.ssafy.group5.model.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;

	@PostMapping
	public void writeArticle(@RequestBody Board board) throws Exception {
		log.debug("writeArticle에서 전달받은 요청: {}", board);
		boardService.writeArticle(board);
	}
	
	@GetMapping
	public ResponseEntity<List<Board>> listArticle(@RequestBody Map<String, ?> param) throws SQLException {
		log.debug("listArticle에서 전달받은 파라미터: {}", param);
		return new ResponseEntity<List<Board>>(boardService.listArticle(param), HttpStatus.OK);
	}
	
	@GetMapping("/{articleno}")
	public ResponseEntity<Board> getArticle(@PathVariable("articleno") int articleno) throws SQLException {
		boardService.updateHit(articleno);
		return new ResponseEntity<Board>(boardService.getArticle(articleno), HttpStatus.OK);
	}
	
	@PutMapping
	public void modifyArticle(@RequestBody Board board) throws SQLException {
		boardService.modifyArticle(board);
	}
	
	@DeleteMapping("/{articleno}")
	public void deleteArticle(@PathVariable("articleno") int articleno) throws SQLException {
		boardService.deleteArticle(articleno);
	}
}