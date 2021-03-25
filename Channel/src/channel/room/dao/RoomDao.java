package channel.room.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import channel.db.SqlMapConfig;
import channel.room.dto.RoomDto;
import channel.room.dto.RoomMemberDto;

public class RoomDao extends SqlMapConfig{
    // 1. 채널 추가
	public int createRoom(RoomDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper.createRoom", dto);
		
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
	
	// 2. 채널 추가시 채널 추가한 계정을 채널참여자 리스트에 인서트
		public int roomMemberAdd(RoomMemberDto roomDto) {
			SqlSession session = null;
			int res = 0;
			try {
				session = getSqlSessionFactory().openSession(false);
				res = session.insert("channelmapper.roomMemberAdd", roomDto);
			
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
	
	// 3-1.  아이디가 관리자일 경우 전체 채널 출력
	public List<RoomDto> channelAdminList() {
		SqlSession session = null;
		List<RoomDto> list = new ArrayList<RoomDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper.channelAdminList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
		
		
	// 3-1.  해당 아이디가 가지고 있는 채널 출력
	public List<RoomDto> channelList(String member_id) {
		SqlSession session = null;
		List<RoomDto> list = new ArrayList<RoomDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper.channelList", member_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	
	// 4. 1개의 채널 정보만 출력
	public RoomDto channelSelect(int channel_num) {
		SqlSession session = null;
		RoomDto dto = new RoomDto();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne("channelmapper.channelSelect", channel_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}
	
	// 5. 채널 정보 수정
	public int channelUpdate(RoomDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update("channelmapper.channelUpdate", dto);
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
	
	// 6. 채널 수정 및 삭제시 권한이 있는지 없는지 판별
	public int permissionCheck(String member_id) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne("channelmapper.permissionCheck", member_id);
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
	
	// 7. 채널 삭제
	public int channelDelete() {
		return 0;
	}
}
