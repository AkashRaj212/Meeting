package server;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
		// TODO Auto-generated method stub
		
		
          	
		    
		    
		    try {
		    	String user = request.getParameter("username");
	          	String password = request.getParameter("password");
			    String email = request.getParameter("email");
		    	DBconnection dBconnection = DBconnection.getInstance();
		    	
				PreparedStatement pt = DBconnection.dbConnection.prepareStatement("insert into user values(?,?,?)");
				pt.setString(1, user);
				pt.setString(2, password);
				pt.setString(3, email);
				
				int result = pt.executeUpdate();
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.print(e.getMessage());
				e.printStackTrace();
			}
//			PreparedStatement pt = DBconnection.getInstance()		
	}

}
