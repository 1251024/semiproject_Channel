package channel.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import channel.member.biz.MemberBiz;
import channel.member.dto.MemberDto;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MemberController() {
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MemberBiz biz = new MemberBiz();
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		if (command.equals("memberlogin")) {
			
			String member_id = request.getParameter("member_id");
			String member_pw = request.getParameter("member_pw");
			
			MemberDto dto = biz.login(member_id, member_pw);
			HttpSession session = request.getSession();
			
			if (dto != null) {
				session.setAttribute("loginDto", dto);
				
				if (dto.getMember_type().equals("ADMIN")) {
					response.sendRedirect("ChatController?command=channelAdminList&member_id="+member_id);	
				} else {
					response.sendRedirect("ChatController?command=channelList&member_id="+member_id);
				}
				
				
			} else {
				response.sendRedirect("login.html");
			}
			
		} else if (command.equals("memberinsertform")) {
			
			response.sendRedirect("member_insert.jsp");
			
		} else if (command.equals("memberinsert")) {
			
			
			
		}
				
	}

}
