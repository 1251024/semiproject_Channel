package channel.controller_lyj;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MessageController")
public class MessageController_lyj extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageController_lyj() {
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 메세지 창 추가할 함수
		
		// 메세지 저장할 함수
		
		// 메세지 로그 출력할 함수
		
	}

}
