package channel.channel;

import java.util.List;

import channel.member.dto.MemberDto;
import channel.workspace.WorkSpaceDto;
import channel.workspace.WorkSpaceMemberDto;

public interface ChannelBiz {

	
	
	// Channel CRUD
	// 1. 채널 추가
	public int addChannel(ChannelDto dto);
	// 2. 채널 정보 수정
	public int updateChannel(ChannelDto dto);
	// 3. 채널 삭제
	public int deleteChannel(ChannelDto dto);
	// 4-1. 아이디가 관리자일 경우 전체 채널 출력
	public List<ChannelDto> channelAdminList();
	// 4-2. 해당 아이디가 가지고 있는 채널 출력
	public List<ChannelDto> selectMemberChannel(ChannelDto chDto);
	// 5. 1개의 채널 정보만 출력
	public ChannelDto selectOneChannel(int channel_num);
	// 6. 채널참여자 리스트에 인서트
	public int addChannelMember(ChannelMemberDto chmemDto);
	// 7. 해당 채널의 참여자 출력
	public List<MemberDto> channelMemeberList(String channel_name);
	// 8. 전체 회원 조회
	public List<String> allMemeberList();
	// 9. 해당 채널에 없는 회원 조회
	public List<ChannelMemberDto> otherMemeberList(String channel_name);
	// 10. 해당 채널의 참여 회원 삭제
	public int delChannelMember(ChannelMemberDto dto);
	// 11. 채널 수정or삭제 권한 유효성 검사
	public String delIdCheck(int channel_num);
	// 12. 가장 최근에 생성된 채널의 번호
	public int getLastChannelSeq();
	// 13. 해당 채널의 맴버 리스트 호출
	public List<ChannelMemberDto> selectChannelMemberList(ChannelMemberDto dto); 
	// 14. 해당 채널의 초대 가능한 맴버 리스트 호출
	public List<WorkSpaceMemberDto> selectChannelInviteList(ChannelDto dto);
	
	// Message CRUD
	
	// 1. 메세지 초대 리스트 불러오기
	public List<WorkSpaceMemberDto> callInviteMessageMemberList(WorkSpaceMemberDto wsmemDto);
	
	
}
