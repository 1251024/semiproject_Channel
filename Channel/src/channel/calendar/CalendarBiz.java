package channel.calendar;

import java.util.List;

public interface CalendarBiz {
	
	// 일정 인서트
	public int insertEvent(CalendarDto dto);
	// 해당 맴버의 전체 이벤트 셀렉트
	public List<CalendarDto> selectListEvent(int member_num); 
	// 해당 맴버의 1개 이벤트 셀렉트
	public CalendarDto selectoneEvent(int calendar_num);
	// 해당 일정 이벤트 수정
	public int updateEvent(CalendarDto dto);
	// 해당 일정 이벤트 삭제
	public int deleteEvent(CalendarDto dto);
	
}
