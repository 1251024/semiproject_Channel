package channel.workspace;

import java.util.Date;

public class WorkSpaceMemberDto {

	private int workspacemember_num;
	private int workspace_num;
	private String workspace_name;
	private int member_num;
	private String member_id;
	private String member_name;
	private Date workspacemember_regdate;

	public WorkSpaceMemberDto() {

	}

	public WorkSpaceMemberDto(int workspacemember_num, int workspace_num, String workspace_name, int member_num,
			String member_id, String member_name, Date workspacemember_regdate) {
		this.workspacemember_num = workspacemember_num;
		this.workspace_num = workspace_num;
		this.workspace_name = workspace_name;
		this.member_num = member_num;
		this.member_id = member_id;
		this.member_name = member_name;
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

	public Date getWorkspacemember_regdate() {
		return workspacemember_regdate;
	}

	public void setWorkspacemember_regdate(Date workspacemember_regdate) {
		this.workspacemember_regdate = workspacemember_regdate;
	}

}
