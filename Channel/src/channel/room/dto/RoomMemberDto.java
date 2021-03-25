package channel.room.dto;

import java.util.Date;

// 해당 채널의 맴버들의 정보를 전달해주는 DTO

public class RoomMemberDto {

	private int roommemberseq;
	private String channel_name;
	private String member_id;
	private Date regdate;
	
	public RoomMemberDto() {
		
	}

	public RoomMemberDto(int roommemberseq, String channel_name, String member_id, Date regdate) {
		this.roommemberseq = roommemberseq;
		this.channel_name = channel_name;
		this.member_id = member_id;
		this.regdate = regdate;
	}

	public int getRoommemberseq() {
		return roommemberseq;
	}

	public void setRoommemberseq(int roommemberseq) {
		this.roommemberseq = roommemberseq;
	}
	
	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
	
	
}
