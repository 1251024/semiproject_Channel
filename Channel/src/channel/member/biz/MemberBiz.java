package channel.member.biz;

import channel.member.dao.MemberDao;
import channel.member.dto.MemberDto;

public class MemberBiz {
	
	MemberDao dao;
	
	public MemberBiz() {
		dao = new MemberDao();
	}
	
	// 1. 로그인
	public MemberDto login(String member_id, String member_pw) {
		MemberDto dto = new MemberDto();
		dto.setMember_id(member_id);
		dto.setMember_pw(member_pw);
		
		return dao.login(dto);
	}
	// 2. 중복체크
	public MemberDto idCheck(String member_id) {
		return dao.idCheck(member_id);
	}
	// 3. 회원가입
	public int insertUser(MemberDto dto) {
		return dao.insertUser(dto);
	}
	// 4. 정보 조회
	public MemberDto selectUser(int member_num) {
		return dao.selectUser(member_num);
	}
	// 5. 정보 수정
	public int updateUser(MemberDto dto) {
		return dao.updateUser(dto);
	}
	// 6. 비밀번호 수정
	public int updatePw(MemberDto dto) {
		return dao.updatePw(dto);
	}
	// 7. 회원 탈퇴 (update)
	public int deleteUser(int member_num) {
		return dao.deleteUser(member_num);
	}
	// 8. 상태 변경 (update)
	public int updateStatement(MemberDto dto) {
		return dao.updateStatement(dto);
	}

}