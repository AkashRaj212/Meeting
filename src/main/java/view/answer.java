package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.chatGpt;

/**
 * Servlet implementation class answer
 */
@WebServlet("/answer")
public class answer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public answer() {
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
	
	    
	        String prompt = request.getParameter("query");
	        String model = "text-davinci-003";
	        String apiKey = "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";

	        try {
	            String answer = chatGpt.generateText(prompt, model, apiKey);
	            System.out.println(answer);
	            
	            response.setHeader("Access-Control-Allow-Origin", "*");
	    		response.setHeader("Access-Control-Allow-Credentials", "true");
	    		response.setHeader("Access-Control-Max-Age", "1800");
	    		response.setHeader("Access-Control-Allow-Headers", "content-type");
	    		response.setHeader("Access-Control-Allow-Methods","PUT, POST, GET, DELETE, PATCH, OPTIONS");
	            response.getWriter().append(answer);
	    		
	    		
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    
		
	}

}
