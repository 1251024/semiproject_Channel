package channel.calendar;

import java.util.Date;

public class CalendarDto {

	private int calendar_num;
	private int member_num;
	private String title;
	private String content;
	private String start_day;
	private String end_day;
	private String address;
	private Date calendar_regdate;
	
	public CalendarDto() {
		
	}

	public CalendarDto(int calendar_num, int member_num, String title, String content, String start_day, String end_day,
			String address, Date calendar_regdate) {
		this.calendar_num = calendar_num;
		this.member_num = member_num;
		this.title = title;
		this.content = content;
		this.start_day = start_day;
		this.end_day = end_day;
		this.address = address;
		this.calendar_regdate = calendar_regdate;
	}

	public int getCalendar_num() {
		return calendar_num;
	}

	public void setCalendar_num(int calendar_num) {
		this.calendar_num = calendar_num;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStart_day() {
		return start_day;
	}

	public void setStart_day(String start_day) {
		this.start_day = start_day;
	}

	public String getEnd_day() {
		return end_day;
	}

	public void setEnd_day(String end_day) {
		this.end_day = end_day;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCalendar_regdate() {
		return calendar_regdate;
	}

	public void setCalendar_regdate(Date calendar_regdate) {
		this.calendar_regdate = calendar_regdate;
	}

}
