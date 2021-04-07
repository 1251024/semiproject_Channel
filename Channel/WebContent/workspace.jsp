<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CHANNEL / Workspace / ${loginDto.member_name }</title>
</head>
<body>
<%@include file="common.jsp" %>
<div class="jumbotron">
  <h1>Workspace</h1>
  <p>환영합니다. ${loginDto.member_name }님, <br>
     워크스페이스를 생성하여 동료들과 업무를 시작해주세요.     
     </p>
  <p><button type="button" class="btn btn-default btn-lg"
	data-toggle="modal" data-target="#addWorkSpaceForm" >새 워크스페이스 생성 <span class="label label-primary">New</span></button></p>
</div>
<div id="workspaceArea">
	<div class="well well-sm"><h3>Workspace List</h3></div>
	<div class="modal fade" id="addWorkSpaceForm" tabindex="-1" role="dialog" aria-labelledby="addWorkSpaceLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="addWorkSpaceLable">새 워크스페이스 추가</h3>
				</div>
				<div class="modal-body">
					<form action="WorkSpaceController" method="post" id="workspaceAddSubmit">
						<div id="workspaceAddCommand">
							<input type="hidden" name="command" value="addWorkSpace">
						</div>
						
						<div class="form-group">
							<label for="recipient-name" class="control-label">회사명을 입력해주세요.</label>
							<input type="text" class="form-control" name="workspace_name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">무슨 일을 하는 회사인가요? 회사정보입력</label>
							<textarea class="form-control" name="workspace_information"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-primary" form="workspaceAddSubmit">생성하기</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="workspaceAdminForm" tabindex="-1" role="dialog"	aria-labelledby="adminWorkspaceLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="adminWorkspaceLable">워크스페이스 관리</h3>
				</div>
				<div class="modal-body">
					<form action="WorkSpaceController" method="post" id="workspaceUpdateSubmit">
						<input type="hidden" name="command" value="updateWorkSpace">
							<div id="workspaceAdminCommand">
								
							</div>			
						<div class="form-group">
							<label for="recipient-name" class="control-label">회사명을 입력해주세요.</label>
							<input type="text" class="form-control" name="workspace_name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">무슨 일을 하는 회사인가요? 회사정보입력</label>
							<textarea class="form-control" name="workspace_information"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" form="workspaceUpdateSubmit">정보수정</button>
				</div>
				<div class="modal-body">	
				<label for="recipient-name" class="control-label"><input class="btn btn-default" type="button" value="맴버 목록"></label>
						<div id="workspaceMemberList">
							
						</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" onclick="banishWorkspace();">추방하기</button>
				</div>
				
				<div class="modal-body">
					
					<label for="recipient-name" class="control-label"><input class="btn btn-default" type="button" value="맴버 초대" onclick="selectWorkspaceInviteList();"></label>	
						<div id="workspaceInviteList">
							
						</div>
						
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="adminWorkspaceCancel">취소</button>
					<button type="submit" class="btn btn-primary" onclick="inviteWorkspace()">초대하기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="resources/js/workspace.js"></script>
</body>
</html>