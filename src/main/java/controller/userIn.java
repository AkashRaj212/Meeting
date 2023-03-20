package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.Session;

import Model.LoadUser;
import Model.userInList;
import client.user;


@WebServlet("/userIn")
public class userIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
   
    public userIn() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	super.doGet(req, resp);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	JSONParser jsonParser = new JSONParser();
	response.setHeader("Access-Control-Allow-Origin", "*");
	response.setHeader("Access-Control-Allow-Credentials", "true");
	response.setHeader("Access-Control-Max-Age", "1800");
	response.setHeader("Access-Control-Allow-Headers", "content-type");
	response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
	
	try {
		
	    JSONObject userDetails = (JSONObject) jsonParser.parse(request.getParameter("userDetails"));
		userInList addUserIn = new userInList();
		
		
		
		if(((String)userDetails.get("sessionId"))==null) {
			String sessionId=UUID.randomUUID().toString();
			user CurrentLoggedInUser = new user(sessionId,(String) userDetails.get("peerId"),(String) userDetails.get("username"),(String) userDetails.get("roomId"),(String) userDetails.get("videoId"));
			addUserIn.userIn(sessionId.toString(), CurrentLoggedInUser);
			
			
			response.getWriter().append(sessionId);
			
		}else {
			
			LoadUser loadUser = new LoadUser();
			loadUser.LoadcurrentUser((String)userDetails.get("sessionId"));
			addUserIn.userLoggedInAgain((String)userDetails.get("sessionId"),(String) userDetails.get("peerId"),(String) userDetails.get("videoId"),(String) userDetails.get("roomId"));
			System.out.println(userInList.getCurrentlyLoggedInUser());
			response.getWriter().append((String)userDetails.get("sessionId"));
			
		}
		
		
	
		
	} catch (ParseException e) {
		System.out.print(e.getMessage());
		e.printStackTrace();
	}
	
		
	
	
	}

	}

