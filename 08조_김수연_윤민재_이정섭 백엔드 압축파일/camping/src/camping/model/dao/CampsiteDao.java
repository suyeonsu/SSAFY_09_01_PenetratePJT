package camping.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import camping.dto.Campsite;
import camping.util.DBUtil;

public class CampsiteDao {
	private CampsiteDao() {}
	private static CampsiteDao instance = new CampsiteDao();
	public static CampsiteDao getInstance() {
		return instance;
	}
	
	private DBUtil dbUtil = DBUtil.getInstance();
	
	public List<Campsite> selectAll() throws SQLException {
		String sql = "select ai.content_id, ai.title, ai.addr1, ai.sido_code, ai.gugun_code, ai.latitude, ai.longitude, ai.first_image, ad.overview "
				+ "from attraction_info ai, attraction_description ad "
				+ "where ai.content_id = ad.content_id "
				+ "and ai.title like \"%캠핑%\"";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<Campsite> list = new ArrayList<>();
			while(rs.next()) {
				Campsite campsite = new Campsite();
				campsite.setContent_id(rs.getInt("content_id"));
				campsite.setTitle(rs.getString("title"));
				campsite.setAddr(rs.getString("addr1"));
				campsite.setSido_code(rs.getInt("sido_code"));
				campsite.setGugun_code(rs.getInt("gugun_code"));
				campsite.setLatitude(rs.getDouble("latitude"));
				campsite.setLongitude(rs.getDouble("longitude"));
				campsite.setImg(rs.getString("first_image"));
				campsite.setOverview(rs.getString("overview"));
				list.add(campsite);
			}
			return list;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	public List<Campsite> selectBySido(int sido_code) throws SQLException {
		String sql = "select ai.content_id, ai.title, ai.addr1, ai.sido_code, ai.gugun_code, ai.latitude, ai.longitude, ai.first_image, ad.overview "
				+ "from attraction_info ai, attraction_description ad "
				+ "where ai.content_id = ad.content_id "
				+ " and ai.title like \"%캠핑%\""
				+ " and ai.sido_code=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sido_code);
			rs = pstmt.executeQuery();
			
			List<Campsite> list = new ArrayList<>();
			while(rs.next()) {
				Campsite campsite = new Campsite();
				campsite.setContent_id(rs.getInt("content_id"));
				campsite.setTitle(rs.getString("title"));
				campsite.setAddr(rs.getString("addr1"));
				campsite.setSido_code(rs.getInt("sido_code"));
				campsite.setGugun_code(rs.getInt("gugun_code"));
				campsite.setLatitude(rs.getDouble("latitude"));
				campsite.setLongitude(rs.getDouble("longitude"));
				campsite.setImg(rs.getString("first_image"));
				campsite.setOverview(rs.getString("overview"));
				list.add(campsite);
			}
			return list;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	public List<Campsite> selectBySidoGugun(int sido_code, int gugun_code) throws SQLException {
		String sql = "select ai.content_id, ai.title, ai.addr1, ai.sido_code, ai.gugun_code, ai.latitude, ai.longitude, ai.first_image, ad.overview "
				+ "from attraction_info ai, attraction_description ad "
				+ "where ai.content_id = ad.content_id "
				+ " and ai.title like \"%캠핑%\""
				+ " and ai.sido_code=? and ai.gugun_code=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sido_code);
			pstmt.setInt(2, gugun_code);
			rs = pstmt.executeQuery();
			
			List<Campsite> list = new ArrayList<>();
			while(rs.next()) {
				Campsite campsite = new Campsite();
				campsite.setContent_id(rs.getInt("content_id"));
				campsite.setTitle(rs.getString("title"));
				campsite.setAddr(rs.getString("addr1"));
				campsite.setSido_code(rs.getInt("sido_code"));
				campsite.setGugun_code(rs.getInt("gugun_code"));
				campsite.setLatitude(rs.getDouble("latitude"));
				campsite.setLongitude(rs.getDouble("longitude"));
				campsite.setImg(rs.getString("first_image"));
				campsite.setOverview(rs.getString("overview"));
				list.add(campsite);
			}
			return list;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	public Campsite selectById(int content_id) throws SQLException {
		String sql = "select ai.content_id, ai.title, ai.addr1, ai.sido_code, ai.gugun_code, ai.latitude, ai.longitude, ai.first_image, ad.overview "
				+ "from attraction_info ai, attraction_description ad "
				+ "where ai.content_id = ad.content_id "
				+ " and ai.title like \"%캠핑%\""
				+ " and ai.content_id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, content_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Campsite campsite = new Campsite();
				campsite.setContent_id(rs.getInt("content_id"));
				campsite.setTitle(rs.getString("title"));
				campsite.setAddr(rs.getString("addr1"));
				campsite.setImg(rs.getString("first_image"));
				campsite.setSido_code(rs.getInt("sido_code"));
				campsite.setOverview(rs.getString("overview"));
				return campsite;
			}
			return null;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
	
	public static void main(String[] args) throws SQLException {
		CampsiteDao campsiteDao = CampsiteDao.getInstance();
		List<Campsite> list = campsiteDao.selectAll();
		for(Campsite campsite: list) {
			System.out.println(campsite.toString());
		}
	}
}
