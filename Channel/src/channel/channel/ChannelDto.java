package channel.channel;

import java.util.Date;

// 채널의 정보를 전달해주는 DTO

public class ChannelDto {

	private int channel_num;
	private int workspace_num;
	private int member_num;
	private String channel_name;
	private String channel_information;
	private Date channel_regdate;
	
	public ChannelDto() {
		
	}

	public ChannelDto(int channel_num, int workspace_num, int member_num, String channel_name,
			String channel_information, Date channel_regdate) {
		super();
		this.channel_num = channel_num;
		this.workspace_num = workspace_num;
		this.member_num = member_num;
		this.channel_name = channel_name;
		this.channel_information = channel_information;
		this.channel_regdate = channel_regdate;
	}

	public int getChannel_num() {
		return channel_num;
	}

	public void setChannel_num(int channel_num) {
		this.channel_num = channel_num;
	}

	public int getWorkspace_num() {
		return workspace_num;
	}

	public void setWorkspace_num(int workspace_num) {
		this.workspace_num = workspace_num;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getChannel_information() {
		return channel_information;
	}

	public void setChannel_information(String channel_information) {
		this.channel_information = channel_information;
	}

	public Date getChannel_regdate() {
		return channel_regdate;
	}

	public void setChannel_regdate(Date channel_regdate) {
		this.channel_regdate = channel_regdate;
	}
	
	
	
}
