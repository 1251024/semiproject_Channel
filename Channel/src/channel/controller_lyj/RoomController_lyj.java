package channel.controller_lyj;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import channel.lyj_common.Util;
import channel.lyj_room.RoomBiz;
import channel.lyj_room.RoomBizImpl;
import channel.lyj_room.RoomDto;
import channel.lyj_room.RoomMemberDto;

@WebServlet("/RoomController")
public class RoomController_lyj extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RoomController_lyj() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 채널 룸 출력,생성,수정,삭제 관련 서블릿
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		RoomBiz roomBiz = new RoomBizImpl();

		String command = request.getParameter("command");
		System.out.println("[" + command + "]");

		// 로그인시 어드민으로 로그인 할 경우
		if (command.equals("channelAdminList")) {

			List<RoomDto> list = roomBiz.channelAdminList();

			if (list != null) {
				request.setAttribute("channelAdminlist", list);
				dispatch("admin.jsp", request, response);
			}

		// 로그인시 일반 유저로 로그인 할 경우
		} else if (command.equals("channelList")) {

			String member_id = request.getParameter("member_id");
			List<RoomDto> list = roomBiz.channelList(member_id);

			if (list != null) {
				request.setAttribute("channelList", list);
				dispatch("main.jsp", request, response);
			}
		
		// 1개 채널 SELECT
		} else if (command.equals("channelSelect")) {
			
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));

			RoomDto dto = roomBiz.channelSelect(channel_num);
			
			Util util = new Util();
			
			String result = "";
			result += dto.getChannel_num() + "|\\|";
			result += dto.getChannel_name() + "|\\|";
			result += dto.getChannel_information() + "|\\|";
			result += dto.getChannel_enabled() + "|\\|";
			result += util.getTostrings(dto.getChannel_regdate());

			System.out.println(result);

			response.getWriter().append(result);
			
		// 채널 추가시
		} else if (command.equals("channelAdd")) {
			
			String channel_name = request.getParameter("channel_name");
			String channel_information = request.getParameter("channel_information");
			String member_id = request.getParameter("member_id");
			String member_name = request.getParameter("member_name");
			String channel_access = request.getParameter("channel_access");
			
			RoomDto dto = new RoomDto();
			dto.setChannel_name(channel_name);
			dto.setChannel_information(channel_information);
			dto.setMember_id(member_id);
			dto.setChannel_access(channel_access);
			System.out.println(channel_access);
			
			RoomMemberDto roomDto = new RoomMemberDto();
			roomDto.setChannel_name(channel_name);
			roomDto.setMember_id(member_id);
			roomDto.setMember_name(member_name);
			
			int res = roomBiz.createRoom(dto);
			int resAdd = roomBiz.roomMemberAdd(roomDto);
			
			if (res > 0 && resAdd > 0) {
				System.out.println("채널 생성 완료");
				dispatch("RoomController?command=channelList&member_id"+member_id, request, response);
			} else {
				System.out.println("채널 생성 실패");
				dispatch("RoomController?command=channelList&member_id"+member_id, request, response);
			}
			
			
		} else if (command.equals("channelDelete")) {
			
			String member_id = request.getParameter("member_id");
			System.out.println(member_id);
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			String msg = "";
			// 유효성 체크
			String adminCheck = roomBiz.adminCheck(channel_num);
			System.out.println(adminCheck);
			if (adminCheck.equals(member_id)) {
				int res = roomBiz.channelDelete(channel_num);
				 
				if (res > 0) {
					msg = "채널을 삭제하였습니다.";
					response.getWriter().append(msg);
				} else {
					msg = "채널을 삭제에 실패하였습니다.";
					response.getWriter().append(msg);
				}
				
			} else {
				msg = "채널을 삭제할 권한이 없습니다.";
				response.getWriter().append(msg);	
			}	
		} else if (command.equals("channelUpdate")) {
			int channel_num = Integer.parseInt(request.getParameter("channel_num"));
			String channel_name = request.getParameter("channel_name");
			String channel_information = request.getParameter("channel_information");
			String channel_access = request.getParameter("channel_access");
			String member_id = request.getParameter("member_id");
			
			String adminCheck = roomBiz.adminCheck(channel_num);
			
			if (adminCheck.equals(member_id)) {
				
				RoomDto dto = new RoomDto();
				dto.setChannel_num(channel_num);
				dto.setChannel_name(channel_name);
				dto.setChannel_information(channel_information);
				dto.setChannel_access(channel_access);
				
				int res = roomBiz.channelUpdate(dto);
				 
				if (res > 0) {
					System.out.println("채널 정보 수정 성공");
					dispatch("RoomControllerlyj?command=channelList&member_id"+member_id, request, response);
				} else {
					System.out.println("채널 정보 수정 실패");
					dispatch("RoomControllerlyj?command=channelList&member_id"+member_id, request, response);
				}
					
			} else {
				
					System.out.println("채널 정보 수정할 권한이 없습니다.");
			}	
			
		}

	}

	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
