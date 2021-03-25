package channel.member.dto;

import java.util.Date;

public class MemberDto {
	
	private int member_num;
	private String member_id;
	private String member_pw;
	private String member_name;
	private String member_email;
	private String member_phone;
	private String member_pscode;
	private String member_addr;
	private String member_addrdt;
	private String member_type;
	private String member_auth;
	private Date member_date;
	private String member_enabled;
	private String member_statement;
	
	public MemberDto() {
	}
	public MemberDto(int member_num, String member_id, String member_pw, String member_name, String member_email,
			String member_phone, String member_pscode, String member_addr, String member_addrdt, String member_type,
			String member_auth, Date member_date, String member_enabled, String member_statement) {
		this.member_num = member_num;
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_name = member_name;
		this.member_email = member_email;
		this.member_phone = member_phone;
		this.member_pscode = member_pscode;
		this.member_addr = member_addr;
		this.member_addrdt = member_addrdt;
		this.member_type = member_type;
		this.member_auth = member_auth;
		this.member_date = member_date;
		this.member_enabled = member_enabled;
		this.member_statement = member_statement;
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
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_pscode() {
		return member_pscode;
	}
	public void setMember_pscode(String member_pscode) {
		this.member_pscode = member_pscode;
	}
	public String getMember_addr() {
		return member_addr;
	}
	public void setMember_addr(String member_addr) {
		this.member_addr = member_addr;
	}
	public String getMemeber_addrdt() {
		return member_addrdt;
	}
	public void setMemeber_addrdt(String memeber_addrdt) {
		this.member_addrdt = memeber_addrdt;
	}
	public String getMember_type() {
		return member_type;
	}
	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	public String getMember_auth() {
		return member_auth;
	}
	public void setMember_auth(String member_auth) {
		this.member_auth = member_auth;
	}
	public Date getMember_date() {
		return member_date;
	}
	public void setMember_date(Date member_date) {
		this.member_date = member_date;
	}
	public String getMember_enabled() {
		return member_enabled;
	}
	public void setMember_enabled(String member_enabled) {
		this.member_enabled = member_enabled;
	}
	public String getMember_statement() {
		return member_statement;
	}
	public void setMember_statement(String member_statement) {
		this.member_statement = member_statement;
	}
	
	

}