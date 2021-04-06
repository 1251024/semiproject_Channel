package channel.controller.peh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import channel.alarm.AlarmDao;
import channel.alarm.AlarmDaoImpl;
import channel.chat.ChatBiz;
import channel.chat.ChatBizImpl;
import channel.chat.ChatDto;
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		ChatBiz biz = new ChatBizImpl();
		AlarmDao alarmdao = new AlarmDaoImpl();
		
		if(command.equals("messageAlarm")) {
			
			HttpSession session = request.getSession(); 
			MemberDto memberDto = (MemberDto)session.getAttribute("loginDto");
			System.out.println(memberDto.getMember_num());

			List<ChatDto> list = alarmdao.chatAlarm(memberDto.getMember_num()); 
			//System.out.println(list);
			
            PrintWriter out = response.getWriter();
            out.append(list.size() + "");
            
            
            
		} else if(command.equals("messageAlarmList")) {
			HttpSession session = request.getSession(); 
			MemberDto memberDto = (MemberDto)session.getAttribute("loginDto");
			
			List<ChatDto> list = alarmdao.chatAlarmList(memberDto.getMember_num());
			
			//ChatDto dto = new ChatDto(chat_num, channel_num, member_num, member_id, member_name, chat_content, chat_regdate);
			//list.add(dto);

			Gson gson = new Gson();
            String str = gson.toJson(list);
			System.out.println(str);

            PrintWriter out = response.getWriter();
            out.append(str);
		}
	}

}
