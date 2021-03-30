package channel.lyj_room;

import java.util.List;
import channel.lyj_member.MemberDto;

public class RoomBizImpl implements RoomBiz {

	RoomDao dao = new RoomDaoImpl();
	
	@Override
	public int createRoom(RoomDto dto) {
		return dao.createRoom(dto);
	}

	@Override
	public int channelUpdate(RoomDto dto) {
		return dao.channelUpdate(dto);
	}

	@Override
	public int channelDelete(int channel_num) {
		return dao.channelDelete(channel_num);
	}

	@Override
	public List<RoomDto> channelAdminList() {
		return dao.channelAdminList();
	}

	@Override
	public List<RoomDto> channelList(String member_id) {
		return dao.channelList(member_id);
	}
	
	@Override
	public RoomDto channelSelect(int channel_num) {
		return dao.channelSelect(channel_num);
	}

	@Override
	public int roomMemberAdd(RoomMemberDto roomDto) {
		return dao.roomMemberAdd(roomDto);
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
	public List<RoomMemberDto> otherMemeberList(String channel_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delChannelMember(RoomMemberDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String adminCheck(int channel_num) {
		return dao.adminCheck(channel_num);
	}

}
