package server;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/chats")
public class chats extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("a");
    	DBconnection.getInstance();
       String userId = request.getParameter("userId");	 
       String username = request.getParameter("username");	 
       String message = request.getParameter("message");
       String roomId = request.getParameter("roomId");
       String reciever = request.getParameter("reciever");
//        String 
		
//        LocalDateTime myDateObj = LocalDateTime.now();
//        DateTimeFormatter myForma0tObj = DateTimeFormatter.ofPattern("HH:mm");
        
        try {
			PreparedStatement pt = DBconnection.dbConnection.prepareStatement("insert into chat_messages values(?,?,?,?,?,?)");
		    pt.setString(1, userId);
		    pt.setString(2, username);
		    pt.setString(3, message);
		    pt.setString(4, null);
		    pt.setString(5, roomId);
		    pt.setString(6,reciever);
		    pt.executeUpdate();
        
        System.out.println("Aa");
        } catch (SQLException e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
        
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "1800");
        response.setHeader("Access-Control-Allow-Headers", "content-type");
		response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
		response.getWriter().append("success");
		
	}

}
