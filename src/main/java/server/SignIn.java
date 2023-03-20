package server;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.cj.xdevapi.JsonParser;
import com.mysql.cj.xdevapi.Result;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	String user = request.getParameter("username");	
	String password = request.getParameter("password");	
		
	DBconnection dBconnection = DBconnection.getInstance();
	
	PreparedStatement pt;
	try {
		pt = DBconnection.dbConnection.prepareStatement("select username,password from user where username = ?");
		pt.setString(1, user);
		
		ResultSet userDetials = pt.executeQuery();
	    
		if(userDetials.next() && userDetials.getString(1).equals(user) && userDetials.getString(2).equals(password)) {
			response.getWriter().append("Login Successfull");
		}else {
			response.getWriter().append("Login not Successfull");
		}
	    
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.print(e.getMessage());
	}

	
		
		
		
	}

}
