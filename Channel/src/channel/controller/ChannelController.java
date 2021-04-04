package channel.controller;

import java.io.IOException;
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

import channel.channel.ChannelBiz;
import channel.channel.ChannelBizImpl;
import channel.channel.ChannelDto;
import channel.channel.ChannelMemberDto;
import channel.channel.MessageRoomDto;
import channel.chat.ChatBiz;
import channel.chat.ChatBizImpl;
import channel.common.Util;
import channel.workspace.WorkSpaceMemberDto;

@WebServlet("/ChannelController")
public class ChannelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChannelController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 워크스페이스, 채널채팅방, 메세지창 출력,생성,수정,삭제 관련 서블릿
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ChannelBiz chBiz = new ChannelBizImpl();
		ChatBiz msgBiz = new ChatBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		if (command.equals("channelIn")) {
			// 채널.jsp로 전달해주시는 함수
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
			
			response.sendRedirect("channel.jsp?member_num="+member_num+"&workspace_num="+workspace_num);
			
		} else if (command.equals("selectMemberChannel")) {
			// 채널.jsp 접속시 에이잭스
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
			
			ChannelDto chDto = new ChannelDto();
			chDto.setWorkspace_num(workspace_num);
			chDto.setMember_num(member_num);
			
			List<ChannelDto> list = chBiz.selectMemberChannel(chDto);
			
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
				response.getWriter().append("채널이 없습니다.");
			}		

		} else if (command.equals("addChannel")) {
			// 채널 추가시	
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			String channel_name = request.getParameter("channel_name");
			String channel_information = request.getParameter("channel_information");
			String member_id = request.getParameter("member_id");
			String member_name = request.getParameter("member_name");
			
			ChannelDto chDto = new ChannelDto();
			chDto.setWorkspace_num(workspace_num);
			chDto.setMember_num(member_num);
			chDto.setChannel_name(channel_name);
			chDto.setChannel_information(channel_information);
			
			int res = chBiz.addChannel(chDto);
			
			if (res > 0) {
				System.out.println(workspace_num + "번 워크스페이스의 "+channel_name+"채널 생성 완료");
				
				int channel_num = chBiz.getLastChannelSeq();
				
				ChannelMemberDto chmemDto = new ChannelMemberDto();
				chmemDto.setChannel_num(channel_num);
				chmemDto.setMember_num(member_num);
				chmemDto.setMember_id(member_id);
				chmemDto.setMember_name(member_name);

				int resAdd = chBiz.addChannelMember(chmemDto);
				
				if (resAdd > 0) {
					System.out.println(channel_name+"채널의 맴버로 추가 완료");
					dispatch("channel.jsp?member_num="+member_num+"&workspace_seq="+workspace_num, request, response);
				} else {
					System.out.println("ERROR! 채널의 맴버로 추가되는 데에 실패하였습니다. 관리자에게 문의하세요!");
					response.sendRedirect("channel.jsp?member_num="+member_num+"&workspace_seq="+workspace_num);
				}
			} else {
				System.out.println("ERROR! 채널 추가에 실패하였습니다. 관리자에게 문의하세요!");
				response.sendRedirect("channel.jsp?member_num"+member_num+"&workspace_seq="+workspace_num);
			}		
		} else if (command.equals("deleteChannel")) {
			//채널 삭제시
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			
			ChannelDto dto = new ChannelDto();
			dto.setChannel_num(channel_num);
			dto.setMember_num(member_num);

			String msg = "";
			int res = chBiz.deleteChannel(dto);
					
			if (res > 0) {
				msg = "채널을 삭제하였습니다.";
				response.getWriter().append(msg);
			} else {
				msg = "채널삭제에 실패하였습니다. 권한을 확인해주세요.";
				response.getWriter().append(msg);	
			}
			
		} else if (command.equals("updateChannel")) {
			//채널 수정시
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			String channel_name = request.getParameter("channel_name");
			String channel_information = request.getParameter("channel_information");
				
				ChannelDto dto = new ChannelDto();
				dto.setChannel_num(channel_num);
				dto.setMember_num(member_num);
				dto.setChannel_name(channel_name);
				dto.setChannel_information(channel_information);

				int res = chBiz.updateChannel(dto);
				 
				if (res > 0) {
					System.out.println("채널 정보 수정 성공");
					dispatch("channel.jsp?member_num="+member_num+"&workspace_seq="+workspace_num, request, response);
				} else {
					System.out.println("ERROR! 채널의 정보 수정에 실패하였습니다. 채널 관리자에게 문의하세요!");
					response.sendRedirect("channel.jsp?member_num="+member_num+"&workspace_seq="+workspace_num);
				}
					
			} else if (command.equals("selectOneChannel")) {
			// 1개 채널 SELECT
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			
			ChannelDto dto = chBiz.selectOneChannel(channel_num);
			
			Util util = new Util();
			
			String result = "";
			result += dto.getChannel_num() + "|\\|";
			result += dto.getWorkspace_num() + "|\\|";
			result += dto.getMember_num() + "|\\|";
			result += dto.getChannel_name() + "|\\|";
			result += dto.getChannel_information() + "|\\|";
			result += util.getTostrings(dto.getChannel_regdate());
			System.out.println(result);
			response.getWriter().append(result);	
		
		} else if (command.equals("selectChannelMemberList")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			
			ChannelMemberDto dto = new ChannelMemberDto();
			dto.setChannel_num(channel_num);
			dto.setMember_num(member_num);
			
			List<ChannelMemberDto> list = chBiz.selectChannelMemberList(dto); 
			
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
				response.getWriter().append("해당 채널에 맴버가 없습니다.");
			}

		} else if (command.equals("selectChannelInviteList")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
			
			ChannelDto dto = new ChannelDto();
			dto.setChannel_num(channel_num);
			dto.setWorkspace_num(workspace_num);
			
			List<WorkSpaceMemberDto> list = chBiz.selectChannelInviteList(dto); 
			
			System.out.println(list);
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
				response.getWriter().append("해당 채널에 초대가능한 맴버가 없습니다.");
			}
			
			
		} else if (command.equals("inviteChannel")) {
			
			int count = Integer.parseInt(request.getParameter("count"));
			String [] inviteMember = request.getParameterValues("inviteMember");

			List<ChannelMemberDto> list = new ArrayList<ChannelMemberDto>();
			 
			for (int i = 0; i < count; i++) {
				String [] member = inviteMember[i].split(",");
				ChannelMemberDto dto = new ChannelMemberDto();
				dto.setChannel_num(Integer.parseInt(member[0]));
				dto.setMember_num(Integer.parseInt(member[1]));
				dto.setMember_id(member[2]);
				dto.setMember_name(member[3]);
				
				list.add(dto);
			}
			
			int res = 0;
			
			for (ChannelMemberDto dto : list) {
				res = chBiz.addChannelMember(dto);
			}

			if (res > 0) {
				System.out.println("채널 맴버 추가 성공");
				response.getWriter().append("워크스페이스 맴버 추가 성공");
			} else {
				System.out.println("채널 맴버 추가 실패");
				response.getWriter().append("워크스페이스 맴버 추가 실패");
			}
		} else if (command.equals("banishChannel")) {
			int count = Integer.parseInt(request.getParameter("count"));
			String [] banishMember = request.getParameterValues("banishMember");
			
			List<ChannelMemberDto> list = new ArrayList<ChannelMemberDto>();
			 
			for (int i = 0; i < count; i++) {
				String [] member = banishMember[i].split(",");
				ChannelMemberDto dto = new ChannelMemberDto();
				dto.setChannel_num(Integer.parseInt(member[0]));
				dto.setMember_num(Integer.parseInt(member[1]));
				
				list.add(dto);
			}
			
			int res = 0;
			
			for (ChannelMemberDto dto : list) {
				res = chBiz.delChannelMember(dto);
			}

			if (res > 0) {
				System.out.println("채널 맴버 삭제 성공");
				response.getWriter().append("채널 맴버 삭제 성공");
			} else {
				System.out.println("채널 맴버 삭제 실패");
				response.getWriter().append("채널 맴버 삭제 실패");
			}
		} else if (command.equals("selectInviteMessageMemberList")) {
			
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			
			MessageRoomDto dto = new MessageRoomDto();
			dto.setWorkspace_num(workspace_num);
			dto.setMember_num(member_num);
		
			List<MessageRoomDto> list = msgBiz.selectInviteMessageMemberList(dto);	

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
				response.getWriter().append("초대할 맴버가 없습니다.");
			}
			
		}
		
		else if (command.equals("callInviteMessageMemberList")) {
				int workspace_seq = Integer.parseInt(request.getParameter("workspace_seq"));
				String member_id = request.getParameter("member_id");
				String member_name = request.getParameter("member_name");
				
				WorkSpaceMemberDto wsmemDto = new WorkSpaceMemberDto();
//				wsmemDto.setWorkspace_seq(workspace_seq);
//				wsmemDto.setMember_id(member_id);
				
				List<WorkSpaceMemberDto> list = chBiz.callInviteMessageMemberList(wsmemDto);
				
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
					response.getWriter().append("초대할 맴버가 없습니다.");
				}

			} 
			
		}

	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
