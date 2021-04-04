package channel.chat;

import java.util.Date;

public class MessageDto {

	private int message_num;
	private int messageroom_num;
	private int to_num;
	private String to_id;
	private String to_name;
	private int from_num;
	private String from_id;
	private String from_name;
	private String message_content;
	private Date message_regdate;
	
	public MessageDto() {
		
	}

	public MessageDto(int message_num, int messageroom_num, int to_num, String to_id, String to_name, int from_num,
			String from_id, String from_name, String message_content, Date message_regdate) {
		this.message_num = message_num;
		this.messageroom_num = messageroom_num;
		this.to_num = to_num;
		this.to_id = to_id;
		this.to_name = to_name;
		this.from_num = from_num;
		this.from_id = from_id;
		this.from_name = from_name;
		this.message_content = message_content;
		this.message_regdate = message_regdate;
	}

	public int getMessage_num() {
		return message_num;
	}

	public void setMessage_num(int message_num) {
		this.message_num = message_num;
	}

	public int getMessageroom_num() {
		return messageroom_num;
	}

	public void setMessageroom_num(int messageroom_num) {
		this.messageroom_num = messageroom_num;
	}

	public int getTo_num() {
		return to_num;
	}

	public void setTo_num(int to_num) {
		this.to_num = to_num;
	}

	public String getTo_id() {
		return to_id;
	}

	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}

	public String getTo_name() {
		return to_name;
	}

	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}

	public int getFrom_num() {
		return from_num;
	}

	public void setFrom_num(int from_num) {
		this.from_num = from_num;
	}

	public String getFrom_id() {
		return from_id;
	}

	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}

	public String getFrom_name() {
		return from_name;
	}

	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public Date getMessage_regdate() {
		return message_regdate;
	}

	public void setMessage_regdate(Date message_regdate) {
		this.message_regdate = message_regdate;
	}

}
