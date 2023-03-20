package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.protocol.Message;

import Model.userInList;
import client.Messages;
import client.user;


@WebServlet("/chatMessage")
public class chatMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public chatMessages() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		JSONParser jsonParser = new JSONParser();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Max-Age", "1800");
		response.setHeader("Access-Control-Allow-Headers", "content-type");
		response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
		
		try {
			
			JSONObject msgDetails = (JSONObject) jsonParser.parse(request.getParameter("msgDetails"));
			
			String recievername ="";
			
			user sender = userInList.getCurrentlyLoggedInUser().get((String)msgDetails.get("senderSessionId"));
//			System.out.println(sender +" "+ (String)msgDetails.get("senderSessionId"));
			if(((String)msgDetails.get("recieverSessionId")).equals("everyone")) {
				recievername = "everyone";
			}else {
				user reciever = userInList.getCurrentlyLoggedInUser().get((String)msgDetails.get("recieverSessionId"));
				recievername = reciever.getUsername();
			}
			
			 LocalDateTime myDateObj = LocalDateTime.now(); 
			 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			 String formattedDate = myDateObj.format(myFormatObj);
			   
			
			Messages msg = new Messages(sender.getSessionId(),sender.getUsername(), (String)msgDetails.get("message"), formattedDate, sender.getRoomId(),(String)msgDetails.get("recieverSessionId") , recievername);
			
			sender.composeMsg(msg);
			response.getWriter().append("msg send");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
