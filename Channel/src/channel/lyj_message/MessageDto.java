package channel.lyj_message;

import java.util.Date;

public class MessageDto {

	private int message_seq;
	private int msgroom_num;
	private String to_id;
	private String to_name;
	private String from_id;
	private String from_name;
	private String content;
	private Date message_regdate;
	
	public MessageDto() {
		
	}

	public MessageDto(int message_seq, int msgroom_num, String to_id, String to_name, String from_id, String from_name,
			String content, Date message_regdate) {
		this.message_seq = message_seq;
		this.msgroom_num = msgroom_num;
		this.to_id = to_id;
		this.to_name = to_name;
		this.from_id = from_id;
		this.from_name = from_name;
		this.content = content;
		this.message_regdate = message_regdate;
	}

	
	public String getTo_name() {
		return to_name;
	}

	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}

	public String getFrom_name() {
		return from_name;
	}

	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}

	public int getMessage_seq() {
		return message_seq;
	}

	public void setMessage_seq(int message_seq) {
		this.message_seq = message_seq;
	}

	public int getMsgroom_num() {
		return msgroom_num;
	}

	public void setMsgroom_num(int msgroom_num) {
		this.msgroom_num = msgroom_num;
	}

	public String getTo_id() {
		return to_id;
	}

	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}

	public String getFrom_id() {
		return from_id;
	}

	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getMessage_regdate() {
		return message_regdate;
	}

	public void setMessage_regdate(Date message_regdate) {
		this.message_regdate = message_regdate;
	}
	
	
	
}
