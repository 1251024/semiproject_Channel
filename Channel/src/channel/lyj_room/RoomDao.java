package channel.lyj_room;

import java.util.List;

import channel.lyj_member.MemberDto;

public interface RoomDao {

	// 1. 채널 추가
	public int createRoom(RoomDto dto);
	// 2. 채널 정보 수정
	public int channelUpdate(RoomDto dto);
	// 3. 채널 삭제
	public int channelDelete(int channel_num);
	// 4-1. 아이디가 관리자일 경우 전체 채널 출력
	public List<RoomDto> channelAdminList();
	// 4-2. 해당 아이디가 가지고 있는 채널 출력
	public List<RoomDto> channelList(String member_id);
	// 5. 1개의 채널 정보만 출력
	public RoomDto channelSelect(int channel_num);
	// 6. 채널참여자 리스트에 인서트
	public int roomMemberAdd(RoomMemberDto roomDto);
	// 7. 해당 채널의 참여자 출력
	public List<MemberDto> channelMemeberList(String channel_name);
	// 8. 전체 회원 조회
	public List<String> allMemeberList();
	// 9. 해당 채널에 없는 회원 조회
	public List<RoomMemberDto> otherMemeberList(String channel_name);
	// 10. 해당 채널의 참여 회원 삭제
	public int delChannelMember(RoomMemberDto dto);
	// 11. 채널 수정or삭제 권한 유효성 검사
	public String adminCheck(int channel_num);
}
