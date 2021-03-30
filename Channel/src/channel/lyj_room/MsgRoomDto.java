package channel.lyj_room;

import java.util.Date;

public class MsgRoomDto {

	private int msgroom_num;
	private String member_id_one;
	private String member_name_one;
	private String member_id_two;
	private String member_name_two;
	private Date msgroom_regdate;	
	
	public MsgRoomDto() {
		
	}
	
	public MsgRoomDto(int msgroom_num, String member_id_one, String member_name_one, String member_id_two,
			String member_name_two, Date msgroom_regdate) {
		this.msgroom_num = msgroom_num;
		this.member_id_one = member_id_one;
		this.member_name_one = member_name_one;
		this.member_id_two = member_id_two;
		this.member_name_two = member_name_two;
		this.msgroom_regdate = msgroom_regdate;
	}


	
	
	public String getMember_name_one() {
		return member_name_one;
	}

	public void setMember_name_one(String member_name_one) {
		this.member_name_one = member_name_one;
	}

	public String getMember_name_two() {
		return member_name_two;
	}

	public void setMember_name_two(String member_name_two) {
		this.member_name_two = member_name_two;
	}

	public int getMsgroom_num() {
		return msgroom_num;
	}

	public void setMsgroom_num(int msgroom_num) {
		this.msgroom_num = msgroom_num;
	}

	public String getMember_id_one() {
		return member_id_one;
	}

	public void setMember_id_one(String member_id_one) {
		this.member_id_one = member_id_one;
	}

	public String getMember_id_two() {
		return member_id_two;
	}

	public void setMember_id_two(String member_id_two) {
		this.member_id_two = member_id_two;
	}

	public Date getMsgroom_regdate() {
		return msgroom_regdate;
	}

	public void setMsgroom_regdate(Date msgroom_regdate) {
		this.msgroom_regdate = msgroom_regdate;
	}
	
}






