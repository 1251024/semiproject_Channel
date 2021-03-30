package channel.lyj_member;

import org.apache.ibatis.session.SqlSession;
import channel.db_lyj.SqlMapConfig;

public class MemberDaoImpl extends SqlMapConfig implements MemberDao  {
	
	@Override
	public MemberDto login(MemberDto dto) {
		SqlSession session = null;
		
		MemberDto resDto = new MemberDto();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			resDto = session.selectOne("channelmapper-member.login", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
			
		return resDto;
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
