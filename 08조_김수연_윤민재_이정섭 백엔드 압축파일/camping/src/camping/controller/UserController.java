package camping.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import camping.dto.User;
import camping.model.service.UserService;



@WebServlet("/auth")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService userService = UserService.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");
		
		try {
			if(action.equals("logout")){
				logout(request, response);
			}
			else if(action.equals("loginPage")) {
				response.sendRedirect(request.getContextPath()+"/user/login.jsp");
			}
			else if(action.equals("signupPage")) {
				response.sendRedirect(request.getContextPath()+"/user/signup.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();

		session.invalidate();
		
		response.sendRedirect(request.getContextPath());
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		User user = new User();
		user.setId(id);
		user.setPwd(pwd);
		System.out.println(user.toString());
		
		User result = userService.login(user);
		
		if(result!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", result);
			
			if(request.getParameter("isRemember")!=null) {
				Cookie cookie = new Cookie("rememberId", result.getId());
				cookie.setMaxAge(60*60);
				response.addCookie(cookie);
			}
			else {
				Cookie cookie = new Cookie("rememberId", result.getId());
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
			response.sendRedirect(request.getContextPath());
		}
		else {
			response.sendRedirect(request.getContextPath()+"/user/login.jsp");
		}
		
	}
	
	private void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

		String id = request.getParameter("id");
		String pwd  = request.getParameter("pwd");
		String name = request.getParameter("name");
		User user = new User(id, pwd, name);
		
		userService.signUp(user);
		
		response.sendRedirect(request.getContextPath()+"/user/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		try {
			if(action.equals("login")) {
				login(request, response);
			}
			else if(action.equals("signup")) {
				signUp(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
