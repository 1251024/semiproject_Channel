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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MemberBiz biz = new MemberBiz();
		String command = request.getParameter("command");
		
		if (command.equals("member_login_page")) {
			response.sendRedirect("jsp/member_login.jsp");
			
		} else if (command.equals("member_insert_page")) {
			response.sendRedirect("jsp/member_insert.jsp");
			
		} else if (command.equals("member_insert")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String re_pw = request.getParameter("re_pw");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String pscode = request.getParameter("pscode");
			String addr = request.getParameter("addr");
			String addrdt = request.getParameter("addrdt");
			
			MemberDto dto = new MemberDto();
			if (pw.equals(re_pw)) {
				dto.setMember_id(id);
				dto.setMember_pw(pw);
				dto.setMember_name(name);
				dto.setMember_email(email);
				dto.setMember_phone(phone);
				dto.setMember_pscode(pscode);
				dto.setMember_addr(addr);
				dto.setMemeber_addrdt(addrdt);
				
			} else {
				response.sendRedirect("MemberController?command=member_insert_page");
			}
			
			int res = biz.insertUser(dto);
			
			if(res > 0) {
				response.sendRedirect("MemberController?command=member_login_page");
			} else {
				response.sendRedirect("index.html");
			}
			
		} else if (command.equals("member_update")) {
			System.out.println("회원정보 업데이트");
			
		} else if (command.equals("member_update_pw")){
			response.sendRedirect("jsp/member_update_pw.jsp");
			System.out.println("비밀번호 업데이트");			
		} else if (command.equals("member_login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDto dto = biz.login(id, pw);
			HttpSession session = request.getSession();
			
			if (dto != null) {
				session.setAttribute("loginDto", dto);
				response.sendRedirect("jsp/main.jsp");
			} else {
				response.sendRedirect("MemberController?command=member_login_page");
			}					
		} 
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
