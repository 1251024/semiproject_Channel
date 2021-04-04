package channel.search.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import channel.member.biz.MemberBiz;
import channel.member.dto.MemberDto;
import channel.member.dto.SearchMemberDto;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public SearchController() {
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		MemberBiz biz = new MemberBiz();

		if (command.equals("memberlist")) {			
			HttpSession session = request.getSession();
			MemberDto dto = (MemberDto) session.getAttribute("loginDto");
			
			List<SearchMemberDto> worklist = biz.searchMemberList(dto.getMember_num());
			List<MemberDto> newlist = biz.selectedMemberList(worklist, dto.getMember_num());
			
			Gson gson = new Gson();
			String str = gson.toJson(newlist);
			
			PrintWriter out = response.getWriter();
			out.append(str);
			
			
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
