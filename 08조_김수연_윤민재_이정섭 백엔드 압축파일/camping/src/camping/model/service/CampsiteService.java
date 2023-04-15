package camping.model.service;

import java.sql.SQLException;
import java.util.List;

import camping.dto.Campsite;
import camping.model.dao.CampsiteDao;

public class CampsiteService {
	private CampsiteService() {}
	private static CampsiteService instance = new CampsiteService();
	public static CampsiteService getInstance() {
		return instance;
	}
	
	private CampsiteDao campsiteDao = CampsiteDao.getInstance();
	
	public List<Campsite> selectAll() throws SQLException {
		return campsiteDao.selectAll();
	}
	
	public List<Campsite> selectBySido(int sido_code) throws SQLException {
		return campsiteDao.selectBySido(sido_code);
	}
	
	public List<Campsite> selectBySidoGugun(int sido_code, int gugun_code) throws SQLException {
		return campsiteDao.selectBySidoGugun(sido_code, gugun_code);
	}
	
	public Campsite selectById(int content_id) throws SQLException {
		return campsiteDao.selectById(content_id);
	}
	
}
