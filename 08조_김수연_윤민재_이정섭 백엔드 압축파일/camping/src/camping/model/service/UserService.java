package camping.model.service;

import java.sql.SQLException;

import camping.dto.User;
import camping.model.dao.UserDao;

public class UserService {

	private UserService() {}
	private static UserService instance = new UserService();
	public static UserService getInstance() {
		return instance;
	}
	
	private UserDao userDao = UserDao.getInstance();
	
	public User login(User loginInfo) throws SQLException {
		return userDao.login(loginInfo);
	}
	
	public void signUp(User user) throws SQLException {
		userDao.signUp(user);
	}
}
