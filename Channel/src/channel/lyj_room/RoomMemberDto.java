package channel.lyj_room;

import java.util.Date;

// 해당 채널의 맴버들의 정보를 전달해주는 DTO

public class RoomMemberDto {

	private int roommember_seq;
	private String channel_name;
	private String member_id;
	private String member_name;
	private Date regdate;
	
	public RoomMemberDto() {
		
	}

	public RoomMemberDto(int roommemberseq, String channel_name, String member_id, String member_name, Date regdate) {
		this.roommember_seq = roommemberseq;
		this.channel_name = channel_name;
		this.member_id = member_id;
		this.member_name = member_name;
		this.regdate = regdate;
	}

	public int getRoommember_Seq() {
		return roommember_seq;
	}

	public void setRoommember_Seq(int roommemberseq) {
		this.roommember_seq = roommemberseq;
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
	

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
	
	
}
