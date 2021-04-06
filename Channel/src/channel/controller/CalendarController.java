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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import channel.calendar.CalendarBiz;
import channel.calendar.CalendarBizImpl;
import channel.calendar.CalendarDto;

@WebServlet("/CalendarController")
public class CalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalendarController() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		CalendarBiz biz = new CalendarBizImpl();
		
		
		if (command.equals("insertEvent")) {
			// 일정 인서트
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String start_day = request.getParameter("start_day");
			String end_day = request.getParameter("end_day");
			String address = request.getParameter("address");
			
			CalendarDto dto = new CalendarDto();
			dto.setMember_num(member_num);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setStart_day(start_day);
			dto.setEnd_day(end_day);
			dto.setAddress(address);
			
			int res = biz.insertEvent(dto);
			
			if (res > 0) {
				response.getWriter().append("일정 생성 성공");
			} else {
				response.getWriter().append("일정 생성 실패");
			}
			
		} else if (command.equals("selectListEvent")) {
			// 해당 맴버의 모든 일정 가져오기
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			
			List<CalendarDto> list = biz.selectListEvent(member_num);
			
			if (list != null) {
				Gson gson = new Gson();
				String jsonString = gson.toJson(list);

				response.getWriter().append(jsonString);
				System.out.println(jsonString);
			} else {
				response.getWriter().append("일정이 없습니다.");
			}
			
		} else if (command.equals("selectOneEvent")) {
			// 해당 맴버의 1개의 일정 가져오기
			int calendar_num = Integer.parseInt(request.getParameter("calendar_num"));
			
			CalendarDto dto = biz.selectoneEvent(calendar_num);
			request.setAttribute("dto", dto);
			dispatch("calendar_selectone.jsp", request, response);
			
		} else if (command.equals("updateEvent")) {
			// 해당 일정 정보 수정하기
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			int calendar_num = Integer.parseInt(request.getParameter("calendar_num"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String start_day = request.getParameter("fromDate") + request.getParameter("fromTime");
			String end_day = request.getParameter("toDate") + request.getParameter("toTime");
			
			CalendarDto dto = new CalendarDto();
			dto.setMember_num(member_num);
			dto.setCalendar_num(calendar_num);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setStart_day(start_day);
			dto.setEnd_day(end_day);
			
			int res = biz.updateEvent(dto);
			
			if (res > 0) {
				System.out.println("일정 수정 성공");
				dispatch("calendar.jsp", request, response);
			} else {
				System.out.println("일정 수정 실패");
				dispatch("calendar.jsp", request, response);
			}
			
			
		} else if (command.equals("deleteEvent")) {
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			int calendar_num = Integer.parseInt(request.getParameter("calendar_num"));
			
			CalendarDto dto = new CalendarDto();
			dto.setCalendar_num(calendar_num);
			dto.setMember_num(member_num);
			
			int res = biz.deleteEvent(dto);
			
			if (res > 0) {
				System.out.println("일정 삭제 성공");
				dispatch("calendar.jsp", request, response);
			} else {
				System.out.println("일정 삭제 실패");
				dispatch("calendar.jsp", request, response);
			}
			
			
		}
	}
	
	protected void dispatch(String path, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

}
