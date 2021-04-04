package channel.controller.peh;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import channel.lyj_chat.ChatDto;
import channel.lyj_message.MessageDto;
import channel.member.dto.MemberDto;

@WebServlet("/AlarmController")
public class AlarmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public AlarmController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		if(command.equals("alarmlist")) {
		/*
			List<MessageDto> list = dao.selectList(); 
			HttpSession session = request.getSession(); 
			MemberDto memberDto = (MemberDto)session.getAttribute("loginDto");
			int lastchat_num = dao.selectAlarm(MemberDto.getMember_num());
		*/
		/*
			List<ChatDto> list = dao.callChatList();
			HttpSession session = request.getSession(); 
			MemberDto memberDto = (MemberDto)session.getAttribute("loginDto");
			int lastchat_num = dao.selectAlarm(MemberDto.getMember_num());
		*/	
		/*	
			for(MessageDto dto : list) {
				if(dto.getTo_id().equals(memberDto.getMember_id())) {
					if(dto.getMessage_seq() > lastchat_num) {
						
						
						//System.out.println();
		*/
					}
		/*			
				} 
		
			}
			
		} else if(command.equals("")) {
			
			
		}
		*/
	}

}
