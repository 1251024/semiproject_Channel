package channel.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import channel.member.db.SqlMapConfig;
import channel.member.dto.MemberDto;
import channel.member.dto.SearchMemberDto;

public class MemberDao extends SqlMapConfig {
	
	// 1. 로그인 // 2. 중복체크
	public List<MemberDto> allCheck() {
		SqlSession session = null;
			
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		try {
			session = getSqlSessionFactory().openSession(true);
			list = session.selectList("kspmapper.allCheck");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
			
		return list;
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
	
	// 10. 네이버 로그인 인서트
	public int naverLoginInsert(MemberDto dto) {
		SqlSession session = null;
		int res = 0;
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("kspmapper.naverLoginInsert", dto);
			if(res > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		
			
		return res;
	}
	
	// 11. 구글 로그인 인서트
		public int googleLoginInsert(MemberDto dto) {
			SqlSession session = null;
			int res = 0;
			
			try {
				session = getSqlSessionFactory().openSession(false);
				res = session.insert("kspmapper.googleLoginInsert", dto);
				if(res > 0) {
					session.commit();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}		
				
			return res;
		}	
		
	// 12. 워크스페이스 리스트 전부 가져오기.
		public List<SearchMemberDto> searchMemberList(){
			
			SqlSession session = null;
			
			List<SearchMemberDto> list = new ArrayList<SearchMemberDto>();
			
			try {
				session = getSqlSessionFactory().openSession(true);
				list = session.selectList("kspmapper.searchMemberList");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
			
			return list;
		}
		
	// 13. member_num에 따른 statement 가져오기.
	public MemberDto memberStatement(int member_num) {
			SqlSession session = null;
			MemberDto dto = null;
			
			try {
				session = getSqlSessionFactory().openSession(true);
				dto = session.selectOne("kspmapper.memberStatement", member_num);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}			
			
		return dto;
	}
	
	
	

}
