package channel.workspace;

import java.util.Date;

public class WorkSpaceDto {

	private int workspace_num;
	private String workspace_name;
	private String workspace_information;
	private int member_num;
	private Date workspace_regdate;
	
	public WorkSpaceDto() {
		
	}

	public WorkSpaceDto(int workspace_num, String workspace_name, String workspace_information, int member_num,
			Date workspace_regdate) {
		this.workspace_num = workspace_num;
		this.workspace_name = workspace_name;
		this.workspace_information = workspace_information;
		this.member_num = member_num;
		this.workspace_regdate = workspace_regdate;
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

	public String getWorkspace_information() {
		return workspace_information;
	}

	public void setWorkspace_information(String workspace_information) {
		this.workspace_information = workspace_information;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public Date getWorkspace_regdate() {
		return workspace_regdate;
	}

	public void setWorkspace_regdate(Date workspace_regdate) {
		this.workspace_regdate = workspace_regdate;
	}
	
	

	
	
}

