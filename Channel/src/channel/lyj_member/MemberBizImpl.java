package channel.lyj_member;

public class MemberBizImpl implements MemberBiz {

	MemberDao dao = new MemberDaoImpl();
	
	@Override
	public MemberDto login(String member_id, String member_pw) {
		MemberDto dto = new MemberDto();
		dto.setMember_id(member_id);
		dto.setMember_pw(member_pw);
		return dao.login(dto);
	}

	@Override
	public MemberDto idCheck(String member_id) {
		return null;
	}

	@Override
	public int insertUser(MemberDto dto) {
		return 0;
	}

	@Override
	public MemberDto selectUser(int member_num) {
		return null;
	}

	@Override
	public int updateUser(MemberDto dto) {
		return 0;
	}

	@Override
	public int updatePw(MemberDto dto) {
		return 0;
	}

	@Override
	public int deleteUser(int member_num) {
		return 0;
	}

	@Override
	public int updateStatement(MemberDto dto) {
		return 0;
	}

}
