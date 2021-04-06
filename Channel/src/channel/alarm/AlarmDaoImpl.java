package channel.alarm;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import channel.chat.ChatDto;



public class AlarmDaoImpl extends channel.db.peh.SqlMapConfig implements AlarmDao{



	
	
	public int selectAlarm(int num) {
		
		SqlSession session = null;
		int res = 0;
		
		List<AlarmDto> list = new ArrayList<AlarmDto>(); 
		
		try {
			session = getSqlSessionFactory().openSession(true);
			list = session.selectList("pehmapper.alarmlist");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		int lastchat_num = 0;
		
		// alarm 테이블에서 전체 list 중에 지금 로그인해있는 것들중 num이 같은
		// lastchat_num 만 가져가자.
		for(AlarmDto dto : list) {
			if(dto.getMember_num() == num) {
				lastchat_num = dto.getLastchat_num();
			}
		}
		
		return lastchat_num;
		
	}

	@Override
	public List<ChatDto> chatAlarm(int member_num) {
		System.out.println("진입함");
		SqlSession session = null;
		List<ChatDto> list = null;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			list = session.selectList("pehmapper.alarmLastnum", member_num);
				
			System.out.println("실행됐음");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		
		
		return list;
		
	}

	@Override
	public List<ChatDto> chatAlarmList(int member_num) {
		System.out.println("진입함");
		SqlSession session = null;
		List<ChatDto> list = null;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			list = session.selectList("pehmapper.alarmList", member_num);
				
			System.out.println("실행됐음");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
		
	}
	
	
	
	
	

}
