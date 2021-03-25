package channel.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import channel.chat.biz.ChatBiz;
import channel.chat.dto.ChatDto;
import channel.common.Util;
import channel.room.biz.RoomBiz;
import channel.room.dto.RoomDto;
import channel.room.dto.RoomMemberDto;

@WebServlet("/ChatController")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChatController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		RoomBiz biz = new RoomBiz();
		ChatBiz chatBiz = new ChatBiz();
		if (command.equals("channeladd")) {
			
			String channelname = request.getParameter("channelname");
			String channelinfo = request.getParameter("channelinfo");
			String member_id = request.getParameter("member_id");
			
			RoomDto dto = new RoomDto();
			dto.setChannel_name(channelname);
			dto.setChannel_information(channelinfo);
			dto.setMember_id(member_id);
			
			RoomMemberDto roomDto = new RoomMemberDto();
			roomDto.setChannel_name(channelname);
			roomDto.setMember_id(member_id);
			
			int res = biz.createRoom(dto);
			int resAdd = biz.roomMemberAdd(roomDto);
			
			if (res > 0 && resAdd > 0) {	
				System.out.println("채널 생성 완료");
				dispatch("ChatController?command=channelList", request, response);
			} else {
				System.out.println("채널 생성 실패");
				dispatch("history.back();", request, response);
			}
			
		} else if (command.equals("channelAdminList")){
			
			List<RoomDto> list = biz.channelAdminList();
			
			if (list != null) {
				request.setAttribute("channelAdminlist", list);
				dispatch("admin.jsp", request, response);
			}
		} else if (command.equals("channelList")) {
			
			String member_id = request.getParameter("member_id");
			
			List<RoomDto> list = biz.channelList(member_id);
			
			if (list != null) {
				request.setAttribute("channellist", list);
				dispatch("main.jsp", request, response);
			}
		} else if (command.equals("channelupdateform")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			
				RoomDto dto = biz.channelSelect(channel_num);
				
				request.setAttribute("roomDto", dto);
				
				dispatch("channel_update.jsp", request, response);
			
		} else if (command.equals("channelUpdate")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			String channel_name = request.getParameter("channel_name");
			String channel_info = request.getParameter("channel_info");
			
				RoomDto dto = new RoomDto();
				dto.setChannel_num(channel_num);
				dto.setChannel_name(channel_name);
				dto.setChannel_information(channel_info);
				
				int res = biz.channelUpdate(dto);
				
				if (res > 0) {
					System.out.println("채널 수정 완료");
					dispatch("ChatController?command=channelAdminList", request, response);
				} else {
					System.out.println("채널 수정 실패");
					dispatch("ChatController?command=channelupdateform&channel_num="+channel_num, request, response);
				}
			
			
			
		} else if (command.equals("callChatList")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			
			List<ChatDto> list = chatBiz.callChatList(channel_num);
			JsonArray resultArray = new JsonArray();
			
			Gson gson = new Gson();
			
			String jsonString = gson.toJson(list);
			resultArray.add(JsonParser.parseString(jsonString));
			
			JsonObject result = new JsonObject();
			result.add("result", resultArray);
			
			response.getWriter().append(result+"");
			System.out.println(resultArray);
			
		} else if (command.equals("channelInfo")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			
			RoomDto dto = biz.channelSelect(channel_num);
			
			Util util = new Util();
			
			String result = "";
			result += dto.getChannel_num() + "|\\|";
			result += dto.getChannel_name() + "|\\|";
			result += dto.getChannel_information() + "|\\|";
			result += dto.getChannel_enabled() + "|\\|";
			result += util.getTostrings(dto.getChannel_regdate());

			System.out.println(result);
			
			response.getWriter().append(result);
			
		} else if (command.equals("chatinsert")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			String member_name = request.getParameter("member_name");
			String member_id = request.getParameter("member_id");
			String chat_content = request.getParameter("chat_content");
			
			ChatDto dto = new ChatDto();
			dto.setChannel_num(channel_num);
			dto.setMember_name(member_name);
			dto.setMember_id(member_id);
			dto.setChat_content(chat_content);
			
			int res = chatBiz.insertChat(dto);
			
			if (res > 0) {
				System.out.println("메세지 저장 성공");
			} else {
				System.out.println("메세지 저장 실패");
			}
			
			
		}
		
	}
	
	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
