package camping.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import camping.dto.Board;
import camping.model.service.BoardService;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final BoardService boardService = BoardService.getInstance();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if(action.equals("detail")) { // 글 1개 선택 조회
			String id = request.getParameter("id");
			request.setAttribute("board", boardService.getBoardById(Integer.parseInt(id)));
			request.getRequestDispatcher("/board/boardDetail.jsp").forward(request, response);
		}else if(action.equals("write")) {
			// 글 작성 템플릿
			request.getRequestDispatcher("/board/boardWrite.jsp").forward(request, response);
		}
		else if(action.equals("list")) {// 글 전체 조회
			request.setAttribute("list", boardService.getAllBoard());
			request.getRequestDispatcher("/board/boardMain.jsp").forward(request, response);
		}else { // 잘못된 경로 입력 시 일단 글 전체 조회로 매핑
			request.setAttribute("list", boardService.getAllBoard());
			request.getRequestDispatcher("/board/boardMain.jsp").forward(request, response);
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("query");
		if(action.equals("add")) { // 게시판 글 등록 로직
			try {
				Board board = new Board();
				board.setAuthor(request.getParameter("author"));
				board.setTitle(request.getParameter("title"));
				board.setText(request.getParameter("text"));
				boardService.addBoard(board);
			}catch(Exception e) {
				System.out.println("BoardController/add Exception : " + e.toString());
			}
			response.sendRedirect(request.getContextPath()+"/board?action=list"); // 등록이 완료 혹은 예외처리가 되면 게시판 글 전체로 매핑
		}
	}

}
