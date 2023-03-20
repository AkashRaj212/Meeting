package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.userInList;
import client.user;
/**
 * Servlet implementation class loadParticipantsList
 */
@WebServlet("/loadParticipantsList")
public class loadParticipantsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "1800");
		response.setHeader("Access-Control-Allow-Headers", "content-type");
		response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
		String roomId = request.getParameter("roomId");
		@SuppressWarnings("unchecked")
		List<String> participantsList =userInList.currentlyLoggedInUserList.values().stream().filter(c -> c.getRoomId().equals(roomId)).map(b->b.getUsername()).toList();
		System.out.print(participantsList);
		response.getWriter().append(participantsList.toString());
		
		
	}

}
