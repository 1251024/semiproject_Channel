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
import channel.member.dto.MemberDto;
import channel.workspace.WorkSpaceBiz;
import channel.workspace.WorkSpaceBizImpl;
import channel.workspace.WorkSpaceDto;
import channel.workspace.WorkSpaceMemberDto;

@WebServlet("/WorkSpaceController")
public class WorkSpaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WorkSpaceController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 워크스페이스 출력,생성,수정,삭제 관련 서블릿
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		WorkSpaceBiz biz = new WorkSpaceBizImpl();
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");

		if (command.equals("selectMemberWorkSpace")) {
			// 로그인 한 회원의 워크스페이스 리스트 불러오기
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			System.out.println(member_num);
			List<WorkSpaceDto> list = biz.selectMemberWorkSpace(member_num);
			JsonArray resultArray = new JsonArray();
			Gson gson = new Gson();
			
			String jsonString = gson.toJson(list);
			resultArray.add(JsonParser.parseString(jsonString));

			JsonObject result = new JsonObject();
			result.add("result", resultArray);

			response.getWriter().append(result + "");
			System.out.println(resultArray);

		}else if (command.equals("addWorkSpace")) {
			// 워크스페이스 추가시
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			String workspace_name = request.getParameter("workspace_name");
			String workspace_information = request.getParameter("workspace_information");
			String member_id = request.getParameter("member_id");
			String member_name = request.getParameter("member_name");
			
			WorkSpaceDto wsDto = new WorkSpaceDto();
			wsDto.setMember_num(member_num);
			wsDto.setWorkspace_name(workspace_name);
			wsDto.setWorkspace_information(workspace_information);
			
			int wsres = biz.createWorkSpace(wsDto);
			
				if (wsres > 0) {
					System.out.println("워크스페이스 생성 중...");
					//방금 생성한 워크스페이스의 번호 가져오기?..
					int workspace_num = biz.getLastWorkSpaceSeq();
					
					if (workspace_num > 0) {
						System.out.println("워크스페이스 번호 : " + workspace_num);
						// 워크스페이스의 맴버로 추가하기
						WorkSpaceMemberDto wsmemDto = new WorkSpaceMemberDto();
						wsmemDto.setMember_num(member_num);
						wsmemDto.setWorkspace_name(workspace_name);
						wsmemDto.setWorkspace_num(workspace_num);
						wsmemDto.setMember_id(member_id);
						wsmemDto.setMember_name(member_name);
						
						int wsmemRes = biz.insertWorkSpaceMember(wsmemDto);
						
						if (wsmemRes > 0) {
							System.out.println(workspace_num + "번 워크스페이스 맴버로 추가 완료");	
							dispatch("workspace.jsp", request, response);
			
						} else {
							System.out.println("ERROR! 워크스페이스의 맴버로 추가 되지 않았습니다. 관리자에게 문의 바랍니다.");
							response.sendRedirect("workspace.jsp");
						}				
					} else {
						System.out.println("ERROR! 워크스페이스 번호 호출 실패, 관리자에게 문의 바랍니다.");
						response.sendRedirect("workspace.jsp");
					}		
				} else {
					System.out.println("ERROR! 워크스페이스 추가 실패");
					response.sendRedirect("workspace.jsp");
				}
		} else if (command.equals("delWorkSpace")) {
				// 워크스페이스 삭제 요청
				int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
				System.out.println(workspace_num);
				int res = biz.deleteWorkSpace(workspace_num);
		
				if (res > 0) {
					System.out.println("워크스페이스 삭제 성공");
					dispatch("workspace.jsp", request, response);
					
				} else {
					System.out.println("워크스페이스 삭제 실패");
					response.sendRedirect("workspace.jsp");	
				}
			
		} else if (command.equals("selectWorkspaceMemberList")) {
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			
			System.out.println(workspace_num + " : " + member_num);
			
			WorkSpaceMemberDto wsmemDto = new WorkSpaceMemberDto();
			wsmemDto.setWorkspace_num(workspace_num);
			wsmemDto.setMember_num(member_num);
			
			List<WorkSpaceMemberDto> wsmemList = biz.selectWorkspaceMemberList(wsmemDto);
			
			if (wsmemList != null) {
				JsonArray resultArray = new JsonArray();
				Gson gson = new Gson();
				String jsonString = gson.toJson(wsmemList);
				resultArray.add(JsonParser.parseString(jsonString));
				JsonObject result = new JsonObject();
				result.add("result", resultArray);
				
				response.getWriter().append(result + "");
				System.out.println(resultArray);
			} else {
				response.getWriter().append("맴버가 없습니다.");
			}
			
		// 워크스페이스 초대 맴버 리스트	
		} else if (command.equals("selectWorkspaceInviteList")) {
			
			int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));
					
			List<MemberDto> list = biz.selectWorkspaceInviteList(workspace_num);
			
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
		} else if (command.equals("inviteWorkspace")) {
			int count = Integer.parseInt(request.getParameter("count"));
			String [] inviteMember = request.getParameterValues("inviteMember");

			List<WorkSpaceMemberDto> list = new ArrayList<WorkSpaceMemberDto>();
			 
			for (int i = 0; i < count; i++) {
				String [] member = inviteMember[i].split(",");
				WorkSpaceMemberDto dto = new WorkSpaceMemberDto();
				dto.setWorkspace_num(Integer.parseInt(member[0]));
				dto.setWorkspace_name(member[1]);
				dto.setMember_num(Integer.parseInt(member[2]));
				dto.setMember_id(member[3]);
				dto.setMember_name(member[4]);
				
				list.add(dto);
			}
			
			int res = 0;
			
			for (WorkSpaceMemberDto dto : list) {
				res = biz.insertWorkSpaceMember(dto);
			}

			if (res > 0) {
				System.out.println("워크스페이스 맴버 추가 성공");
				response.getWriter().append("워크스페이스 맴버 추가 성공");
			} else {
				System.out.println("워크스페이스 맴버 추가 실패");
				response.getWriter().append("워크스페이스 맴버 추가 실패");
			}
			
		} else if (command.equals("banishWorkspace")){
			int count = Integer.parseInt(request.getParameter("count"));
			String [] banishMember = request.getParameterValues("banishMember");
			
			List<WorkSpaceMemberDto> list = new ArrayList<WorkSpaceMemberDto>();
			 
			for (int i = 0; i < count; i++) {
				String [] member = banishMember[i].split(",");
				WorkSpaceMemberDto dto = new WorkSpaceMemberDto();
				dto.setWorkspace_num(Integer.parseInt(member[0]));
				dto.setMember_num(Integer.parseInt(member[1]));
				
				list.add(dto);
			}
			
			int res = 0;
			
			for (WorkSpaceMemberDto dto : list) {
				res = biz.deleteWorkSpaceMember(dto);
			}

			if (res > 0) {
				System.out.println("워크스페이스 맴버 삭제 성공");
				response.getWriter().append("워크스페이스 맴버 삭제 성공");
			} else {
				System.out.println("워크스페이스 맴버 삭제 실패");
				response.getWriter().append("워크스페이스 맴버 삭제 실패");
			}
			
		}  

	} 

	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}
	
}
