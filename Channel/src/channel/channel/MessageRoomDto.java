package channel.channel;

import java.util.Date;

public class MessageRoomDto {

	private int messageroom_num;
	private int workspace_num;
	private int member_num;
	private String member_id;
	private String member_name;
	private int member2_num;
	private String member2_id;
	private String member2_name;
	private Date messageroom_regdate;	
	
	public MessageRoomDto() {
		
	}

	public MessageRoomDto(int messageroom_num, int workspace_num, int member_num, String member_id, String member_name,
			int member2_num, String member2_id, String member2_name, Date messageroom_regdate) {
		this.messageroom_num = messageroom_num;
		this.workspace_num = workspace_num;
		this.member_num = member_num;
		this.member_id = member_id;
		this.member_name = member_name;
		this.member2_num = member2_num;
		this.member2_id = member2_id;
		this.member2_name = member2_name;
		this.messageroom_regdate = messageroom_regdate;
	}

	public int getMessageroom_num() {
		return messageroom_num;
	}

	public void setMessageroom_num(int messageroom_num) {
		this.messageroom_num = messageroom_num;
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

	public int getMember2_num() {
		return member2_num;
	}

	public void setMember2_num(int member2_num) {
		this.member2_num = member2_num;
	}

	public String getMember2_id() {
		return member2_id;
	}

	public void setMember2_id(String member2_id) {
		this.member2_id = member2_id;
	}

	public String getMember2_name() {
		return member2_name;
	}

	public void setMember2_name(String member2_name) {
		this.member2_name = member2_name;
	}

	public Date getMessageroom_regdate() {
		return messageroom_regdate;
	}

	public void setMessageroom_regdate(Date messageroom_regdate) {
		this.messageroom_regdate = messageroom_regdate;
	}

	
	
}






