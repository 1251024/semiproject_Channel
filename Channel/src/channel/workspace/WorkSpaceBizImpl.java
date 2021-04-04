package channel.workspace;

import java.util.List;

import channel.member.dto.MemberDto;

public class WorkSpaceBizImpl implements WorkSpaceBiz {

	WorkSpaceDao dao = new WorkSpaceDaoImpl();
	
	// WorkSpace CRUD
	// 1. 워크스페이스 생성
	@Override
	public int createWorkSpace(WorkSpaceDto wsDto) {
		return dao.createWorkSpace(wsDto);
	}

	// 2. 워크스페이스 맴버 추가
	@Override
	public int insertWorkSpaceMember(WorkSpaceMemberDto wsmemDto) {
		return dao.insertWorkSpaceMember(wsmemDto);
	}

	// 3. 워크스페이스 맴버 제거
	@Override
	public int deleteWorkSpaceMember(WorkSpaceMemberDto wsmemDto) {
		return dao.deleteWorkSpaceMember(wsmemDto);
	}

	// 4. 워크스페이스 정보 수정
	@Override
	public int updateWorkSpace(WorkSpaceDto wsDto) {
		
		return 0;
	}

	// 5. 워크스페이스 삭제
	@Override
	public int deleteWorkSpace(int workspace_seq) {
		int res = dao.deleteWorkSpace(workspace_seq);
		return res;
	}

	// 6. 전체 워크스페이스 리스트 출력
	@Override
	public List<WorkSpaceDto> selectAllWorkSpace() {
		
		return null;
	}

	// 7. 해당 회원이 등록되어있는 워크스페이스 리스트 출력
	@Override
	public List<WorkSpaceDto> selectMemberWorkSpace(int member_num) {
		return dao.selectMemberWorkSpace(member_num);
	}

	// 8. 1개 워크스페이스 출력
	@Override
	public WorkSpaceDto selectOneWorkSpace(WorkSpaceDto wsDto) {
		
		return null;
	}
	
	// 9. 가장 최근에 생성된 워크스페이스 번호
	@Override
	public int getLastWorkSpaceSeq() {
		return dao.getLastWorkSpaceSeq();
	}
	
	// 10. 해당 워크스페이스의 맴버 리스트 호출 (본인 제외)
	@Override
	public List<WorkSpaceMemberDto> selectWorkspaceMemberList(WorkSpaceMemberDto wsmemDto) {
		return dao.selectWorkspaceMemberList(wsmemDto);
	}
	
	// 11. 워크스페이스 초대 맴버 리스트
	@Override
	public List<MemberDto> selectWorkspaceInviteList(int workspace_num) {
		return dao.selectWorkspaceInviteList(workspace_num);
	}
	
}
