package channel.alarm;

import java.util.Date;

public class AlarmDto {

	private int ala_seq;
	private int member_num;
	private int lastchat_num;
	
	
	public AlarmDto() {
		
	}


	public AlarmDto(int ala_seq, int member_num, int lastchat_num) {
		this.ala_seq = ala_seq;
		this.member_num = member_num;
		this.lastchat_num = lastchat_num;
	}


	public int getAla_seq() {
		return ala_seq;
	}


	public void setAla_seq(int ala_seq) {
		this.ala_seq = ala_seq;
	}


	public int getMember_num() {
		return member_num;
	}


	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}


	public int getLastchat_num() {
		return lastchat_num;
	}


	public void setLastchat_num(int lastchat_num) {
		this.lastchat_num = lastchat_num;
	}
	
	

	
}
