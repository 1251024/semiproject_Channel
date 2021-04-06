package channel.calendar;

import java.util.List;

public class CalendarBizImpl implements CalendarBiz {

	CalendarDao dao = new CalendarDaoImpl();
	
	@Override
	public int insertEvent(CalendarDto dto) {
		return dao.insertEvent(dto);
	}

	@Override
	public List<CalendarDto> selectListEvent(int member_num) {
		return dao.selectListEvent(member_num);
	}

	@Override
	public CalendarDto selectoneEvent(int calendar_num) {
		return dao.selectoneEvent(calendar_num);
	}
	
	// 해당 일정 이벤트 수정
	@Override
	public int updateEvent(CalendarDto dto) {
		return dao.updateEvent(dto);
	}
	
	// 해당 일정 이벤트 삭제
	@Override
	public int deleteEvent(CalendarDto dto) {
		return dao.deleteEvent(dto);
	}

}
