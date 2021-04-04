package channel.member.biz;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import channel.member.dao.MemberDao;
import channel.member.dto.MemberDto;

public class MemberBiz {
	
	MemberDao dao;
	
	public MemberBiz() {
		dao = new MemberDao();
	}
	
	// 1. 로그인
	public MemberDto login(String member_id, String member_pw) {
		List<MemberDto> lists = dao.allCheck();
		MemberDto dto = null;
		for(MemberDto list : lists) {
			if(list.getMember_id().equals(member_id) && list.getMember_pw().equals(member_pw)) {
				dto = new MemberDto(list.getMember_num(),
									list.getMember_id(),
									list.getMember_pw(),
									list.getMember_name(),
									list.getMember_email(),
									list.getMember_phone(),
									list.getMember_pscode(),
									list.getMember_addr(),
									list.getMember_addrdt(),
									list.getMember_type(),
									list.getMember_auth(),
									list.getMember_date(),
									list.getMember_enabled(),
									list.getMember_statement());
			}
		}		
		return dto;
	}
	// 2. 중복체크
	public MemberDto idCheck(String member_id) {
		List<MemberDto> lists = dao.allCheck();
		MemberDto dto = null;
		for(MemberDto list : lists) {
			if(list.getMember_id().equals(member_id)) {
				dto = new MemberDto(list.getMember_num(),
									list.getMember_id(),
									list.getMember_pw(),
									list.getMember_name(),
									list.getMember_email(),
									list.getMember_phone(),
									list.getMember_pscode(),
									list.getMember_addr(),
									list.getMember_addrdt(),
									list.getMember_type(),
									list.getMember_auth(),
									list.getMember_date(),
									list.getMember_enabled(),
									list.getMember_statement());
			}
		}		
		return dto;				
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
	// 9. 비밀번호 암호화 (SHA-256)
	public String getSHA256(String input) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] salt = "Hello! This is Salt.".getBytes();
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(input.getBytes("UTF-8"));
			for(int i = 0; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if(hex.length() == 1) result.append("0");
				result.append(hex);
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		
		return result.toString();
	}
	
	// 10. 네이버 로그인 인서트
	public int naverLoginInsert(String email) {
		MemberDto dto = new MemberDto(0, email, this.getSHA256(email), email.split("@")[0], email,
									"010-0000-0000", "00000", "주소를 입력해주세요", "상세주소를 입력해주세요", "NAVER", "NAVER",
									null, "Y", "0");
		int res = dao.naverLoginInsert(dto);
		return res;
	}
	
	// 11. 구글 로그인 인서트
	public int googleLoginInsert(String email) {
		MemberDto dto = new MemberDto(0, email, this.getSHA256(email), email.split("@")[0], email,
									"010-0000-0000", "00000", "주소를 입력해주세요", "상세주소를 입력해주세요", "GOOGLE", "GOOGLE",
									null, "Y", "0");
		int res = dao.googleLoginInsert(dto);		
		return res;
	}
	
	
	

}
