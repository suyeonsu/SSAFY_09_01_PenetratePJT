package camping.model.service;

import camping.dto.Board;
import camping.model.dao.BoardDao;
import java.util.*;

public class BoardService {
	private static final BoardDao boardDao = BoardDao.getInstance();
	private BoardService() {}
	private static final BoardService boardService = new BoardService();
	public static BoardService getInstance() { return boardService; }
	public List<Board> getAllBoard(){
		return boardDao.getAllBoard();
	}
	public Board getBoardById(int id) {
		return boardDao.getBoardById(id);
	}
	public void addBoard(Board board) {
		boardDao.addBoard(board);
	}
}
