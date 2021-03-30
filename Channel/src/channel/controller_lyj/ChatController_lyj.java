package channel.controller_lyj;

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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import channel.lyj_chat.ChatBiz;
import channel.lyj_chat.ChatBizImpl;
import channel.lyj_chat.ChatDto;

@WebServlet("/ChatControllerlyj")
public class ChatController_lyj extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ChatController_lyj() {

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
		if (command.equals("callChatList")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));

			List<ChatDto> list = chatBiz.callChatList(channel_num);
			JsonArray resultArray = new JsonArray();

			Gson gson = new Gson();

			String jsonString = gson.toJson(list);
			resultArray.add(JsonParser.parseString(jsonString));

			JsonObject result = new JsonObject();
			result.add("result", resultArray);

			response.getWriter().append(result + "");
			System.out.println(resultArray);
		
		// 채팅 DB로 저장
		} else if (command.equals("chatInsert")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			String member_name = request.getParameter("member_name");
			String member_id = request.getParameter("member_id");
			String chat_content = request.getParameter("chat_content");

			ChatDto dto = new ChatDto();
			dto.setChannel_num(channel_num);
			dto.setMember_name(member_name);
			dto.setMember_id(member_id);
			dto.setChat_content(chat_content);

			int res = chatBiz.chatInsert(dto);

			if (res > 0) {
				System.out.println("메세지 저장 성공");
			} else {
				System.out.println("메세지 저장 실패");
			}

			
		}
		
	}
	
	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
