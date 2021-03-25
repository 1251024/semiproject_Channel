package channel.room.biz;

import java.util.List;

import channel.room.dao.RoomDao;
import channel.room.dto.RoomDto;
import channel.room.dto.RoomMemberDto;

public class RoomBiz {

	RoomDao dao = new RoomDao();
	
	public int createRoom(RoomDto dto) {
		return dao.createRoom(dto);
	}
	
	public int roomMemberAdd(RoomMemberDto dto) {
		return dao.roomMemberAdd(dto);
	}
	
	public List<RoomDto> channelAdminList() {
		return dao.channelAdminList();
	}
	
	public List<RoomDto> channelList(String member_id) {
		return dao.channelList(member_id);
	}
	
	public RoomDto channelSelect(int channel_num) {
		return dao.channelSelect(channel_num);
	}
	
	public int channelUpdate(RoomDto dto) {
		return dao.channelUpdate(dto);
	}
	
	public int permissionCheck(String member_id) {
		return dao.permissionCheck(member_id);
	}
}
