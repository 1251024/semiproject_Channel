package channel.lyj_room;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import channel.db_lyj.SqlMapConfig;
import channel.lyj_member.MemberDto;

public class RoomDaoImpl extends SqlMapConfig implements RoomDao {

	@Override
	public int createRoom(RoomDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-room.createRoom", dto);
		
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
	public int channelUpdate(RoomDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update("channelmapper-room.channelUpdate", dto);
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
	public int channelDelete(int channel_num) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete("channelmapper-room.channelDelete", channel_num);
		
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
	public List<RoomDto> channelAdminList() {
		SqlSession session = null;
		List<RoomDto> list = new ArrayList<RoomDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-room.channelAdminList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public List<RoomDto> channelList(String member_id) {
		SqlSession session = null;
		List<RoomDto> list = new ArrayList<RoomDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-room.channelList", member_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	@Override
	public RoomDto channelSelect(int channel_num) {
		SqlSession session = null;
		RoomDto dto = new RoomDto();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne("channelmapper-room.channelSelect", channel_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}

	@Override
	public int roomMemberAdd(RoomMemberDto roomDto) {
		SqlSession session = null;
		int res = 0;
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-room.roomMemberAdd", roomDto);
		
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
	public List<MemberDto> channelMemeberList(String channel_name) {
		return null;
	}

	@Override
	public List<String> allMemeberList() {
		return null;
	}

	@Override
	public List<RoomMemberDto> otherMemeberList(String channel_name) {
		return null;
	}

	@Override
	public int delChannelMember(RoomMemberDto dto) {
		return 0;
	}
	
	@Override
	// 11. 채널 수정or삭제 권한 유효성 검사
	public String adminCheck(int channel_num) {
		SqlSession session = null;
		String res = "";
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne("channelmapper-room.adminCheck", channel_num);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

}
