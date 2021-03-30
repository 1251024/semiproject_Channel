package channel.controller_lyj;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import channel.lyj_member.MemberBiz;
import channel.lyj_member.MemberBizImpl;
import channel.lyj_member.MemberDto;

@WebServlet("/MemberControllerlyj")
public class MemberController_lyj extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MemberController_lyj() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// 세션 전송용 임시 맴버컨트롤러
		MemberBiz biz = new MemberBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		if (command.equals("memberlogin")) {
			response.sendRedirect("login/member_login.jsp");
		} else if (command.equals("checklogin")){
			String member_id = request.getParameter("member_id");
			String member_pw = request.getParameter("member_pw");
			
			MemberDto dto = biz.login(member_id, member_pw);
			HttpSession session = request.getSession();

			if (dto != null) {
				session.setAttribute("loginDto", dto);
				
				if (dto.getMember_type().equals("ADMIN")) {
					response.sendRedirect("RoomControllerlyj?command=channelAdminList");	
				} else {
					response.sendRedirect("RoomControllerlyj?command=channelList&member_id="+member_id);
				}
				
				
			} else {
				response.sendRedirect("home.jsp");
			}
		} else if (command.equals("insertmemberform")) {
			
			response.sendRedirect("member_insert.jsp");
			
		}
	}
	
	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
