package camping.model.dao;

import java.util.*;
import java.sql.*;

import camping.dto.Board;
import camping.util.DBUtil;

public class BoardDao {
	private static final DBUtil dbUtil = DBUtil.getInstance();
	private BoardDao() {}
	private static BoardDao boardDao = new BoardDao();
	public static BoardDao getInstance() { return boardDao; }
	public List<Board> getAllBoard(){
		List<Board> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM board";
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setAuthor(rs.getString("author"));
				board.setTitle(rs.getString("title"));
				board.setText(rs.getString("text"));
				list.add(board);
			}
			return list;
		}catch(Exception e) {
			System.out.println("BoardDao/getAllBoard Exception! : " + e.toString());
			return null;
		}finally {
			dbUtil.close(rs,pstmt,conn);
		}
	}
	public Board getBoardById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM board where id = ?";
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setAuthor(rs.getString("author"));
				board.setTitle(rs.getString("title"));
				board.setText(rs.getString("text"));
				return board;
			}else return null;
		}catch(Exception e) {
			System.out.println("BoardDao/getBoardById Exception! : " + e.toString());
			return null;
		}finally {
			dbUtil.close(rs,pstmt,conn);
		}
	}
	public void addBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO board(author, title, text) values(?,?,?)";
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,board.getAuthor());
			pstmt.setString(2,board.getTitle());
			pstmt.setString(3,board.getText());
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("BoardDao/addBoard Exception! : " + e.toString());
		}finally {
			dbUtil.close(pstmt,conn);
		}
	}
}
