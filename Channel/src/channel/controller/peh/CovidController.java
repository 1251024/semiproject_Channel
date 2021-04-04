package channel.controller.peh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/covidController")
public class CovidController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		String command = request.getParameter("command");
		if(command.equals("covidInfo_2")) {
			RequestDispatcher rd = request.getRequestDispatcher("covidInfo_2.jsp");
			rd.forward(request, response);
		
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("covidInfo.jsp");
			rd.forward(request, response);
		}
		
		
		
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
