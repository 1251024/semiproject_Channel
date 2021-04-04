package channel.channel;

import java.util.Date;

// 해당 채널의 맴버들의 정보를 전달해주는 DTO

public class ChannelMemberDto {

	private int channelmember_num;
	private int channel_num;
	private int member_num;
	private String member_id;
	private String member_name;
	private Date regdate;
	
	public ChannelMemberDto() {
		
	}

	public ChannelMemberDto(int channelmember_num, int channel_num, int member_num, String member_id,
			String member_name, Date regdate) {
		this.channelmember_num = channelmember_num;
		this.channel_num = channel_num;
		this.member_num = member_num;
		this.member_id = member_id;
		this.member_name = member_name;
		this.regdate = regdate;
	}

	public int getChannelmember_num() {
		return channelmember_num;
	}

	public void setChannelmember_num(int channelmember_num) {
		this.channelmember_num = channelmember_num;
	}

	public int getChannel_num() {
		return channel_num;
	}

	public void setChannel_num(int channel_num) {
		this.channel_num = channel_num;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
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