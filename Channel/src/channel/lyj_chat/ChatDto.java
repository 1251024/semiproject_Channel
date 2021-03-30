package channel.lyj_chat;

import java.util.Date;

public class ChatDto {

	private int chat_seq;
	private int channel_num;
	private String member_id;
	private String member_name;
	private String chat_content;
	private Date chat_regdate;
	
	public ChatDto () {
		
	}

	public ChatDto(int chat_seq, int channel_num, String member_id, String member_name, String chat_content,
			Date chat_regdate) {
		this.chat_seq = chat_seq;
		this.channel_num = channel_num;
		this.member_id = member_id;
		this.member_name = member_name;
		this.chat_content = chat_content;
		this.chat_regdate = chat_regdate;
	}
	
	public int getChat_seq() {
		return chat_seq;
	}

	public void setChat_seq(int chat_seq) {
		this.chat_seq = chat_seq;
	}

	public int getChannel_num() {
		return channel_num;
	}

	public void setChannel_num(int channel_num) {
		this.channel_num = channel_num;
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

	public String getChat_content() {
		return chat_content;
	}

	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}

	public Date getChat_regdate() {
		return chat_regdate;
	}

	public void setChat_regdate(Date chat_regdate) {
		this.chat_regdate = chat_regdate;
	}
	
}
