package channel.lyj_member;

public interface MemberDao {
		// 1. 로그인
		public MemberDto login(MemberDto dto);
		// 2. 중복체크
		public MemberDto idCheck(String member_id);
		// 3. 회원가입
		public int insertUser(MemberDto dto);
		// 4. 정보 조회
		public MemberDto selectUser(int member_num);
		// 5. 정보 수정
		public int updateUser(MemberDto dto);
		// 6. 비밀번호 수정
		public int updatePw(MemberDto dto);
		// 7. 회원 탈퇴 (update)
		public int deleteUser(int member_num);
		// 8. 상태 변경 (update)
		public int updateStatement(MemberDto dto);
}
