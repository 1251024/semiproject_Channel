package channel.member.dto;

import java.util.Date;

public class SearchMemberDto {
	
	private int workspacemember_num;
	private int workspace_num;
	private String workspace_name;
	private int member_num;
	private Date workspacemember_regdate;
	
	public SearchMemberDto() {

	}
	public SearchMemberDto(int workspacemember_num, int workspace_num, String workspace_name, int member_num,
			Date workspacemember_regdate) {

		this.workspacemember_num = workspacemember_num;
		this.workspace_num = workspace_num;
		this.workspace_name = workspace_name;
		this.member_num = member_num;
		this.workspacemember_regdate = workspacemember_regdate;
	}
	
	public int getWorkspacemember_num() {
		return workspacemember_num;
	}
	public void setWorkspacemember_num(int workspacemember_num) {
		this.workspacemember_num = workspacemember_num;
	}
	public int getWorkspace_num() {
		return workspace_num;
	}
	public void setWorkspace_num(int workspace_num) {
		this.workspace_num = workspace_num;
	}
	public String getWorkspace_name() {
		return workspace_name;
	}
	public void setWorkspace_name(String workspace_name) {
		this.workspace_name = workspace_name;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public Date getWorkspacemember_regdate() {
		return workspacemember_regdate;
	}
	public void setWorkspacemember_regdate(Date workspacemember_regdate) {
		this.workspacemember_regdate = workspacemember_regdate;
	}
	@Override
	public String toString() {
		return "SearchMemberDto [workspacemember_num=" + workspacemember_num + ", workspace_num=" + workspace_num
				+ ", workspace_name=" + workspace_name + ", member_num=" + member_num + ", workspacemember_regdate="
				+ workspacemember_regdate + "]";
	}
	
	
	
	
	
	

}
