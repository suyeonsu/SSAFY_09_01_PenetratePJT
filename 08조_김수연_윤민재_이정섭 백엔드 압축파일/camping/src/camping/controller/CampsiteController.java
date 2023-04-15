package camping.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import camping.dto.Campsite;
import camping.model.service.CampsiteService;

@WebServlet("/camp")
public class CampsiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CampsiteService campsiteService = CampsiteService.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			if(action.equals("sidoList")) {
				getListBySido(request, response);
			} else if(action.equals("detail")) {
				getCampsiteDetail(request, response);
			} else if(action.equals("campList")) {
				getCampsiteList(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	private void getCampsiteList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Campsite> campList = campsiteService.selectAll();
		
		request.setAttribute("campList", campList);
		request.getRequestDispatcher("campsite/campsiteList.jsp").forward(request, response);
	}

	private void getCampsiteDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int content_id = Integer.parseInt(request.getParameter("content_id"));
		Campsite campsite = campsiteService.selectById(content_id);
		request.setAttribute("campsite", campsite);
		request.getRequestDispatcher("campsite/campsiteDetail.jsp").forward(request, response);
	}

	private void getListBySido(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int sido_code = Integer.parseInt(request.getParameter("sido_code"));
		List<Campsite> campsiteList = campsiteService.selectBySido(sido_code);
		
		request.setAttribute("campsiteList", campsiteList);
		request.getRequestDispatcher("/campsiteList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
