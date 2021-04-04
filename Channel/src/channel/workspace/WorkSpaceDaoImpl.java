package channel.workspace;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import channel.channel.db.SqlMapConfig;
import channel.member.dto.MemberDto;

public class WorkSpaceDaoImpl extends SqlMapConfig implements WorkSpaceDao {

	// WorkSpace CRUD	
	// 1. 워크스페이스 생성
	@Override
	public int createWorkSpace(WorkSpaceDto wsDto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-workspace.createWorkSpace", wsDto);
		
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

	// 2. 워크스페이스 맴버 추가
	@Override
	public int insertWorkSpaceMember(WorkSpaceMemberDto wsmemDto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-workspace.insertWorkSpaceMember", wsmemDto);
		
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

	// 3. 워크스페이스 맴버 제거
	@Override
	public int deleteWorkSpaceMember(WorkSpaceMemberDto wsmemDto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete("channelmapper-workspace.deleteWorkSpaceMember", wsmemDto);
		
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
	
	// 4. 워크스페이스 정보 수정
	@Override
	public int updateWorkSpace(WorkSpaceDto wsDto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 5. 워크스페이스 삭제
	@Override
	public int deleteWorkSpace(int workspace_num) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete("channelmapper-workspace.deleteWorkSpace", workspace_num);
		
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

	// 6. 전체 워크스페이스 리스트 출력
	@Override
	public List<WorkSpaceDto> selectAllWorkSpace() {
		// TODO Auto-generated method stub
		return null;
	}

	// 7. 해당 회원이 등록되어있는 워크스페이스 리스트 출력
	@Override
	public List<WorkSpaceDto> selectMemberWorkSpace(int member_num) {
		SqlSession session = null;
		List<WorkSpaceDto> list = new ArrayList<WorkSpaceDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-workspace.selectMemberWorkSpace", member_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

	// 8. 1개 워크스페이스 출력
	@Override
	public WorkSpaceDto selectOneWorkSpace(WorkSpaceDto wsDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 9. 가장 최근에 생성된 워크스페이스의 번호
	@Override
	public int getLastWorkSpaceSeq() {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.selectOne("channelmapper-workspace.getLastWorkSpaceSeq");
		
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
	
	// 10. 해당 워크스페이스의 맴버 리스트 호출 (본인 제외)
	@Override
	public List<WorkSpaceMemberDto> selectWorkspaceMemberList(WorkSpaceMemberDto wsmemDto) {
		SqlSession session = null;
		List<WorkSpaceMemberDto> list = new ArrayList<WorkSpaceMemberDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-workspace.selectWorkspaceMemberList", wsmemDto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
			return list;
	}
	
	// 11. 워크스페이스 초대 맴버 리스트
	@Override
	public List<MemberDto> selectWorkspaceInviteList(int workspace_num) {
		SqlSession session = null;
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-workspace.selectWorkspaceInviteList", workspace_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
			return list;
		
	}
	
}
