package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.userInList;
import client.user;

/**
 * Servlet implementation class renderMessage
 */
@WebServlet("/renderMessages")
public class renderMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//		System.out.println(request.getParameter("userSessionId"));
//		System.out.println(userInList.getCurrentlyLoggedInUser());
		user currentlyLoggedInUser = userInList.getCurrentlyLoggedInUser().get(request.getParameter("userSessionId"));
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "1800");
		response.setHeader("Access-Control-Allow-Headers", "content-type");
		response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
		
		response.getWriter().append(currentlyLoggedInUser.getMyMessages().toString());
		}catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		
	}

}
