package channel.member.biz;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
		List<MemberDto> list = dao.allCheck();
		MemberDto dto = null;
		for(MemberDto lists : list) {
			if(lists.getMember_id().equals(member_id) && lists.getMember_pw().equals(member_pw)) {
				dto = new MemberDto(lists.getMember_num(),
									lists.getMember_id(),
									lists.getMember_pw(),
									lists.getMember_name(),
									lists.getMember_email(),
									lists.getMember_phone(),
									lists.getMember_pscode(),
									lists.getMember_addr(),
									lists.getMember_addrdt(),
									lists.getMember_type(),
									lists.getMember_auth(),
									lists.getMember_date(),
									lists.getMember_enabled(),
									lists.getMember_statement());
			}
		}		
		return dto;
	}
	// 2. 중복체크
	public MemberDto idCheck(String member_id) {
		List<MemberDto> list = dao.allCheck();
		MemberDto dto = null;
		for(MemberDto lists : list) {
			if(lists.getMember_id().equals(member_id)) {
				dto = new MemberDto(lists.getMember_num(),
									lists.getMember_id(),
									lists.getMember_pw(),
									lists.getMember_name(),
									lists.getMember_email(),
									lists.getMember_phone(),
									lists.getMember_pscode(),
									lists.getMember_addr(),
									lists.getMember_addrdt(),
									lists.getMember_type(),
									lists.getMember_auth(),
									lists.getMember_date(),
									lists.getMember_enabled(),
									lists.getMember_statement());
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
	
	
	

}
