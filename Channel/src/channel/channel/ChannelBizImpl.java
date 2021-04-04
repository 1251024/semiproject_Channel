package channel.channel;

import java.util.List;

import channel.member.dto.MemberDto;
import channel.workspace.WorkSpaceDto;
import channel.workspace.WorkSpaceMemberDto;

public class ChannelBizImpl implements ChannelBiz {

	ChannelDao dao = new ChannelDaoImpl();

	
	
	// Channel CRUD
	// 1. 채널 추가
	@Override
	public int addChannel(ChannelDto dto) {
		return dao.addChannel(dto);
	}
	// 2. 채널 정보 수정
	@Override
	public int updateChannel(ChannelDto dto) {
		return dao.updateChannel(dto);
	}
	// 3. 채널 삭제
	@Override
	public int deleteChannel(ChannelDto dto) {
		return dao.deleteChannel(dto);
	}
	// 4-1. 아이디가 관리자일 경우 전체 채널 출력
	@Override
	public List<ChannelDto> channelAdminList() {
		return dao.channelAdminList();
	}
	// 4-2. 해당 아이디가 가지고 있는 채널 출력
	@Override
	public List<ChannelDto> selectMemberChannel(ChannelDto chDto) {
		return dao.selectMemberChannel(chDto);
	}
	// 5. 1개의 채널 정보만 출력
	@Override
	public ChannelDto selectOneChannel(int channel_num) {
		return dao.selectOneChannel(channel_num);
	}
	// 6. 채널참여자 리스트에 인서트
	@Override
	public int addChannelMember(ChannelMemberDto chmemDto) {
		return dao.addChannelMember(chmemDto);
	}

	@Override
	public List<MemberDto> channelMemeberList(String channel_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> allMemeberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChannelMemberDto> otherMemeberList(String channel_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delChannelMember(ChannelMemberDto dto) {
		return dao.delChannelMember(dto);
	}

	@Override
	public String delIdCheck(int channel_num) {
		return dao.delIdCheck(channel_num);
	}
	
	@Override
	public int getLastChannelSeq() {
		return dao.getLastChannelSeq();
	}
	
	// 13. 해당 채널의 맴버 리스트 호출
	@Override
	public List<ChannelMemberDto> selectChannelMemberList(ChannelMemberDto dto) {
		return dao.selectChannelMemberList(dto);
	}

	// 14. 해당 채널의 초대 가능한 맴버 리스트 호출
	@Override
	public List<WorkSpaceMemberDto> selectChannelInviteList(ChannelDto dto) {
		return dao.selectChannelInviteList(dto);
	}
	
	// Message CRUD
	// 1. 메세지 초대 리스트 불러오기
	@Override
	public List<WorkSpaceMemberDto> callInviteMessageMemberList(WorkSpaceMemberDto wsmemDto) {
		return dao.callInviteMessageMemberList(wsmemDto);
	}
	
	
}
