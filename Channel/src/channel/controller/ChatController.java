package channel.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import channel.channel.MessageRoomDto;
import channel.chat.ChatBiz;
import channel.chat.ChatBizImpl;
import channel.chat.ChatDto;
import channel.chat.MessageDto;
import channel.common.Util;

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

		// 채팅 & 메세지 관련 컨트롤러
		ChatBiz chatBiz = new ChatBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		// 채팅 DB에서 불러오기
		if (command.equals("selectChatList")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));

			List<ChatDto> list = chatBiz.selectChatList(channel_num);
			JsonArray resultArray = new JsonArray();
			Gson gson = new Gson();

			String jsonString = gson.toJson(list);
			resultArray.add(JsonParser.parseString(jsonString));

			JsonObject result = new JsonObject();
			result.add("result", resultArray);

			response.getWriter().append(result + "");
			System.out.println(resultArray);
		
		// 채팅 DB로 저장
		} else if (command.equals("insertChat")) {
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			String member_id = request.getParameter("member_id");
			String member_name = request.getParameter("member_name");	
			String chat_content = request.getParameter("chat_content");

			ChatDto dto = new ChatDto();
			
			Util util = new Util();
			
			dto.setChannel_num(channel_num);
			dto.setMember_num(member_num);
			dto.setMember_id(member_id);
			dto.setMember_name(member_name);
			dto.setChat_content(chat_content);

			int res = chatBiz.insertChat(dto);

			if (res > 0) {
				System.out.println("메세지 저장 성공");
			} else {
				System.out.println("메세지 저장 실패");
			}

			
		} else if (command.equals("callMessageList")) {
		  // 메세지 DB 불러오기
		
			int messageroom_num = Integer.parseInt(request.getParameter("messageroom_num"));

			List<MessageDto> list = chatBiz.callMessageList(messageroom_num);
			
			if (list != null) {
				JsonArray resultArray = new JsonArray();
				Gson gson = new Gson();

				String jsonString = gson.toJson(list);
				resultArray.add(JsonParser.parseString(jsonString));

				JsonObject result = new JsonObject();
				result.add("result", resultArray);

				response.getWriter().append(result + "");
				System.out.println(resultArray);
		
			} else {
				response.getWriter().append("메세지가 없습니다.");
			}
	
		} else if (command.equals("msgRoomSelect")) {
			
			int messageroom_num = Integer.parseInt(request.getParameter("messageroom_num"));
			System.out.println(messageroom_num);
			MessageRoomDto dto = chatBiz.msgRoomSelect(messageroom_num);
			
			Util util = new Util();
			
			String result = "";
			result += dto.getMessageroom_num() + "|\\|";
			result += dto.getWorkspace_num() + "|\\|";
			result += dto.getMember_num() + "|\\|";
			result += dto.getMember_id() + "|\\|";
			result += dto.getMember_name() + "|\\|";
			result += dto.getMember2_num() + "|\\|";
			result += dto.getMember2_id() + "|\\|";
			result += dto.getMember2_name() + "|\\|";
			result += util.getTostrings(dto.getMessageroom_regdate());

			System.out.println(result);

			response.getWriter().append(result);
			
		} else if (command.equals("messageInsert")) {
			
			int messageroom_num = Integer.parseInt(request.getParameter("messageroom_num"));
			int to_num = Integer.parseInt(request.getParameter("to_num"));
			String to_id = request.getParameter("to_id");
			String to_name = request.getParameter("to_name");
			int from_num = Integer.parseInt(request.getParameter("from_num"));
			String from_id = request.getParameter("from_id");
			String from_name = request.getParameter("from_name");
			String message_content = request.getParameter("message_content");
			System.out.println(message_content);

			MessageDto dto = new MessageDto();
			dto.setMessageroom_num(messageroom_num);
			dto.setTo_num(to_num);
			dto.setTo_id(to_id);
			dto.setTo_name(to_name);
			dto.setFrom_num(from_num);
			dto.setFrom_id(from_id);
			dto.setFrom_name(from_name);
			dto.setMessage_content(message_content);
			
			int res = chatBiz.messageInsert(dto);
			
			if (res > 0) {
				System.out.println("메세지 저장 성공");
			} else {
				System.out.println("메세지 저장 실패");
			}
			
		} else if (command.equals("createMessageRoom")) {
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			String member_id = request.getParameter("member_id");
			String member_name = request.getParameter("member_name");
			int member2_num = Integer.parseInt(request.getParameter("member2_num"));
			String member2_id = request.getParameter("member2_id");
			String member2_name = request.getParameter("member2_name");
			
			MessageRoomDto dto = new MessageRoomDto();
			dto.setWorkspace_num(workspace_num);
			dto.setMember_num(member_num);
			dto.setMember_id(member_id);
			dto.setMember_name(member_name);
			dto.setMember2_num(member2_num);
			dto.setMember2_id(member2_id);
			dto.setMember2_name(member2_name);
			
			int res = chatBiz.createMessageRoom(dto);
			
			if (res > 0) {
				System.out.println("메세지룸 생성 성공");
				response.getWriter().append("메세지룸 생성 성공");
			} else {
				System.out.println("메세지룸 생성 실패");
				response.getWriter().append("메세지룸 생성 실패");
			}
			
		} else if (command.equals("selectMessageRoomList")) {
			
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			
			MessageRoomDto dto = new MessageRoomDto();
			dto.setWorkspace_num(workspace_num);
			dto.setMember_num(member_num);
			
			List<MessageRoomDto> list = chatBiz.messageRoomList(dto);
			
			if (list != null) {
				JsonArray resultArray = new JsonArray();
				Gson gson = new Gson();
				String jsonString = gson.toJson(list);
				resultArray.add(JsonParser.parseString(jsonString));
				JsonObject result = new JsonObject();
				result.add("result", resultArray);
				
				response.getWriter().append(result + "");
				System.out.println(resultArray);
			} else {
				response.getWriter().append("메세지가 없습니다.");
			}	
		} else if (command.equals("deleteMessageRoom")) {
			
			int messageroom_num = Integer.parseInt(request.getParameter("messageroom_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			
			MessageRoomDto dto = new MessageRoomDto();
			dto.setMessageroom_num(messageroom_num);
			dto.setMember_num(member_num);
	
			int res = chatBiz.deleteMessageRoom(dto);
			
			if (res > 0) {
				System.out.println("메세지룸 삭제 성공");
				response.getWriter().append("메세지룸 삭제 성공");
			} else {
				System.out.println("메세지룸 삭제 실패");
				response.getWriter().append("메세지룸 삭제 실패");
			}
			
		}
	} 
	
	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
