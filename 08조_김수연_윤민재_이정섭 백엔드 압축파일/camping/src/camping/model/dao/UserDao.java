package camping.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import camping.dto.User;
import camping.util.DBUtil;

public class UserDao {

	private UserDao() {}
	private static UserDao instance = new UserDao();
	public static UserDao getInstance() {
		return instance;
	}
	
	private DBUtil dbUtil = DBUtil.getInstance();
	
	public User login(User loginInfo) throws SQLException {

		String sql = "select id, name from user where id=? and pwd=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginInfo.getId());
			pstmt.setString(2, loginInfo.getPwd());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				User result = new User();
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				return result;
			}
			return null;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	public void signUp(User user) throws SQLException {

		String sql = "insert into user values (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getId());
			pstmt.setString(3, user.getPwd());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
}
