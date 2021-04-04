package channel.channel;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

import channel.channel.db.SqlMapConfig;
import channel.member.dto.MemberDto;
import channel.workspace.WorkSpaceDto;
import channel.workspace.WorkSpaceMemberDto;

public class ChannelDaoImpl extends SqlMapConfig implements ChannelDao {

	
	
	
	
	// Channel CRUD
	// 1. 채널 추가
	@Override
	public int addChannel(ChannelDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-channel.addChannel", dto);
		
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
	public int updateChannel(ChannelDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update("channelmapper-channel.updateChannel", dto);
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
	// 3. 채널 삭제
	@Override
	public int deleteChannel(ChannelDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete("channelmapper-channel.deleteChannel", dto);
		
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
	// 4-1. 아이디가 관리자일 경우 전체 채널 출력
	@Override
	public List<ChannelDto> channelAdminList() {
		SqlSession session = null;
		List<ChannelDto> list = new ArrayList<ChannelDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.channelAdminList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	// 4-2. 해당 아이디가 가지고 있는 채널 출력
	@Override
	public List<ChannelDto> selectMemberChannel(ChannelDto chDto) {
		SqlSession session = null;
		List<ChannelDto> list = new ArrayList<ChannelDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.selectMemberChannel", chDto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	// 5. 1개의 채널 정보만 출력
	@Override
	public ChannelDto selectOneChannel(int channel_num) {
		SqlSession session = null;
		ChannelDto dto = new ChannelDto();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne("channelmapper-channel.selectOneChannel", channel_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return dto;
	}
	// 6. 채널참여자 리스트에 인서트
	@Override
	public int addChannelMember(ChannelMemberDto chmemDto) {
		SqlSession session = null;
		int res = 0;
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-channel.addChannelMember", chmemDto);
		
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
	public List<ChannelMemberDto> otherMemeberList(String channel_name) {
		return null;
	}

	@Override
	public int delChannelMember(ChannelMemberDto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete("channelmapper-channel.deleteChannelMember", dto);
		
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
	// 11. 채널 수정or삭제 권한 유효성 검사
	public String delIdCheck(int channel_num) {
		SqlSession session = null;
		String res = "";
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne("channelmapper-channel.delIdCheck", channel_num);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	// 12. 가장 최근에 생성된 채널의 번호
	@Override
	public int getLastChannelSeq() {
		SqlSession session = null;
		int res = 0;
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne("channelmapper-channel.getLastChannelSeq");
		
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
	// 13. 해당 채널의 맴버 리스트 호출
	@Override
	public List<ChannelMemberDto> selectChannelMemberList(ChannelMemberDto dto) {
		List<ChannelMemberDto> list = new ArrayList<ChannelMemberDto>();
		
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.selectChannelMemberList", dto);
			
			if (res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list; 
	}
	// 14. 해당 채널의 초대 가능한 맴버 리스트 호출
	@Override
	public List<WorkSpaceMemberDto> selectChannelInviteList(ChannelDto dto) {
		List<WorkSpaceMemberDto> list = new ArrayList<WorkSpaceMemberDto>();
		
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.selectChannelInviteList", dto);
			
			if (res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list; 
	}

	// Message CRUD
	// 1. 메세지 초대 리스트 불러오기
	@Override
	public List<WorkSpaceMemberDto> callInviteMessageMemberList(WorkSpaceMemberDto wsmemDto) {
			List<WorkSpaceMemberDto> list = new ArrayList<WorkSpaceMemberDto>();
		
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-channel.callInviteMessageMemberList", wsmemDto);
			
			if (res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list; 

	}
	
	


}
