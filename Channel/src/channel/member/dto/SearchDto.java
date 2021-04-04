package channel.member.dto;

import java.util.Date;

public class SearchDto {
	private int chat_num;
	private int channel_num;
	private int member_num;
	private String member_id;
	private String member_name;
	private String chat_content;
	private Date chat_regdate;
	
	public SearchDto() {
		
	}
	
	public SearchDto(int chat_num, int channel_num, int member_num, String member_id, String member_name,
			String chat_content, Date chat_regdate) {
		this.chat_num = chat_num;
		this.channel_num = channel_num;
		this.member_num = member_num;
		this.member_id = member_id;
		this.member_name = member_name;
		this.chat_content = chat_content;
		this.chat_regdate = chat_regdate;
	}
	public int getChat_num() {
		return chat_num;
	}
	public void setChat_num(int chat_num) {
		this.chat_num = chat_num;
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
