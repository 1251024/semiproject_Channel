package channel.calendar;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import channel.channel.db.SqlMapConfig;

public class CalendarDaoImpl extends SqlMapConfig implements CalendarDao {

	@Override
	public int insertEvent(CalendarDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-calendar.insertEvent", dto);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;

	}

	@Override
	public List<CalendarDto> selectListEvent(int member_num) {
		SqlSession session = null;
		List<CalendarDto> list = new ArrayList<CalendarDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-calendar.selectListEvent", member_num);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		

		return list;
	}

	@Override
	public CalendarDto selectoneEvent(int calendar_num) {
		SqlSession session = null;
		CalendarDto dto = new CalendarDto();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne("channelmapper-calendar.selectoneEvent", calendar_num);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		

		return dto;
	}
	
	@Override
	public int updateEvent(CalendarDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update("channelmapper-calendar.updateEvent", dto);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	@Override
	public int deleteEvent(CalendarDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete("channelmapper-calendar.deleteEvent", dto);
		
		if (res > 0) {
			session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

}
