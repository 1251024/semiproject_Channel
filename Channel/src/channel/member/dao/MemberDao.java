package channel.member.dao;

import org.apache.ibatis.session.SqlSession;

import channel.db.SqlMapConfig;
import channel.member.dto.MemberDto;

public class MemberDao extends SqlMapConfig {
	
	// 1. 로그인
	public MemberDto login(MemberDto dto) {
		SqlSession session = null;
			
		MemberDto resDto = new MemberDto();
		
		try {
			session = getSqlSessionFactory().openSession(true);
			resDto = session.selectOne("kspmapper.login", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
			
		return resDto;
	}
	// 2. 중복체크
	public MemberDto idCheck(String member_id) {
		SqlSession session = null;
			
		MemberDto resDto = new MemberDto();
			
		try {
			session = getSqlSessionFactory().openSession(true);
			resDto = session.selectOne("kspmapper.idCheck", member_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
			
		return resDto;
	}
	// 3. 회원가입
	public int insertUser(MemberDto dto) {
		SqlSession session = null;
		int res = 0;
			
		try {
			session = getSqlSessionFactory().openSession(false);			
			res = session.insert("kspmapper.insertUser", dto);
			
			if(res >0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	// 4. 정보 조회
	public MemberDto selectUser(int member_num) {
		SqlSession session = null;
		MemberDto resDto = new MemberDto();
			
		try {
			session = getSqlSessionFactory().openSession(true);
			resDto = session.selectOne("kspmapper.selectUser", member_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
					
		return resDto;
	}
	// 5. 정보 수정
	public int updateUser(MemberDto dto) {
		SqlSession session = null;
		int res = 0;
			
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update("kspmapper.updateUser", dto);
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
	// 6. 비밀번호 수정
	public int updatePw(MemberDto dto) {
		SqlSession session = null;
		int res = 0;
			
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update("kspmapper.updatePw", dto);
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
	// 7. 회원 탈퇴 (update)
	public int deleteUser(int member_num) {
		SqlSession session = null;
		int res = 0;
			
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update("kspmapper.deleteUser", member_num);
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
	// 8. 상태 변경 (update)
	public int updateStatement(MemberDto dto) {
		SqlSession session = null;
		int res = 0;
			
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update("kspmapper.updateStatement", dto);
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

}
