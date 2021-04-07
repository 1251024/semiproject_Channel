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
<title>CHANNEL / Channel / ${loginDto.member_name }</title>

</head>
<%
	int member_num = Integer.parseInt(request.getParameter("member_num"));
	int workspace_num = Integer.parseInt(request.getParameter("workspace_num"));	
%>
<body>
<%@include file="common.jsp" %>
	<input type="hidden" id="workspace_num" value="<%=workspace_num %>">
		<div id="content_container">
			<div class="jumbotron" id="channeljumbotron">
  					<h1>Channel</h1>
  				<p>${loginDto.member_name }님!<br> 
  					채널을 생성하여 각 부서별로 채팅을 할 수 있습니다.<br> 
    				새 채널 생성을 통해 각 부서원을 초대하고 협업을 시작해주세요!     
     			</p>
  				<p><button type="button" class="btn btn-default btn-lg"
					data-toggle="modal" data-target="#addChannelForm" >새 채널 생성 <span class="label label-primary">New</span></button></p>
			
					<h1>Message</h1>
				<p>	해당 워크스페이스의 맴버들과 1:1채팅을 해보세요!   			</p>
  				<p><button type="button" class="btn btn-default btn-lg"
					data-toggle="modal" data-target="#addMessageForm" onclick="selectInviteMessageMemberList();">새 메세지 <span class="label label-primary">New</span></button></p>
				<p><br>이미 생성하신 채널 or 메세지가 있으면 왼쪽 네비게이션을 통해 채널로 입장이 가능합니다.<br> 
  				   <b>the best way to coworking!</b>
     			</p>
			</div>
			<div id="channelcontentarea">
				<div id="roominfo" class="form-control">
				
				</div>
				<div id="chatarea" class="form-control">
				
				</div>
				<div id="textarea" class="form-control">
					<textarea id="summernote"></textarea>
				</div>
			</div>
			
		</div>
	<div class="modal fade" id="addChannelForm" tabindex="-1" role="dialog" aria-labelledby="addChannelLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="addChannelLable">새 채널 추가</h3>
				</div>
				<div class="modal-body">
					<form action="ChannelController" method="post" id="channelAddSubmit">
						<div id="channelCommand">
							<input type="hidden" name="command" value="addChannel">
								<input type="hidden" name="member_id" value="${loginDto.member_id }">
								<input type="hidden" name="member_name" value="${loginDto.member_name }">
						</div>
						
						<div class="form-group">
							<label for="recipient-name" class="control-label">채널명을 입력해주세요.</label>
							<input type="text" class="form-control" name="channel_name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">무슨 일을 하는 채널인가요? 채널정보입력</label>
							<textarea class="form-control" name="channel_information"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-primary" form="channelAddSubmit">생성하기</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="adminChannelForm" tabindex="-1" role="dialog" aria-labelledby="adminChannelLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="adminChannelLable">채널 수정</h3>
				</div>
				<div class="modal-body">
					<form action="ChannelController" method="post" id="channelUpdateSubmit">
						<input type="hidden" name="command" value="updateChannel"> 
						<input type="hidden" name="workspace_num" value="<%= workspace_num%>"> 
						<input type="hidden" name="channel_num" id="update_channel_num">
						<input type="hidden" name="member_num" id="update_member_num">
						<div class="form-group">
							<label for="recipient-name" class="control-label">채널명</label>
							<input type="text" class="form-control" name="channel_name" id="update_channel_name">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">채널정보</label>
							<textarea class="form-control" name="channel_information" id="update_channel_information"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" form="channelUpdateSubmit">수정하기</button>
				</div>
				<div class="modal-body">
					<div id="channelAdminCommand">
						
					</div>
					<label for="recipient-name" class="control-label"><input class="btn btn-default" type="button" value="맴버 목록"></label>
						<div id="channelMemberList">
							
						</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" onclick="banishChannel();">추방하기</button>
				</div>
				
				<div class="modal-body">
					
					<label for="recipient-name" class="control-label"><input class="btn btn-default" type="button" value="맴버 초대" onclick="selectChannelInviteList();"></label>	
						<div id="channelInviteList">
							
						</div>
						
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="adminChannelCancel">취소</button>
					<button type="submit" class="btn btn-primary" onclick="inviteChannel()">초대하기</button>
				</div>
			</div>
		</div>
	</div>	
	<div class="modal fade" id="addMessageForm" tabindex="-1" role="dialog"	aria-labelledby="addMessageLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="addMessageLable">새 메세지 생성</h3>
				</div>
				<div class="modal-body">
					<label for="recipient-name" class="control-label">메세지 가능 리스트</label>
						<div id="messageInviteMemberList">
							
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="addMessageCancel">취소</button>
					<button type="submit" class="btn btn-primary" onclick="createMessageRoom();" >메세지 보내기</button>
				</div>
			</div>
		</div>
	</div>
<input type="hidden" id="search_workspace_num" value="${param.workspace_num }" >	
<input type="hidden" id="search_channel_num" value="${param.channel_num }">
<input type="hidden" id="search_chat_num" value="${param.chat_num }">
<input type="hidden" id="search_member_num" value="${param.member_num }">
<input type="hidden" id="search_value" value="${param.search }" >

<script type="text/javascript" src="resources/js/channel.js"></script>
<script type="text/javascript" src="resources/js/message.js"></script>
<script type="text/javascript" src="resources/js/websocket.js"></script>
<script type="text/javascript" src="resources/js/callsummernote.js"></script>
<script type="text/javascript" src="resources/js/search.js"></script>
<style type="text/css">
/* 이용준 main css 수정 부분 */

#roominfo {
	height: 3em;

}

#chatarea {
	color: black;
	height: 45em;
	overflow: auto;
}

#textarea {
	height: 12em;
}

#chatarea::-webkit-scrollbar {
	width: 7px; /*스크롤바의 너비*/
}

#chatarea::-webkit-scrollbar-thumb {
	background-color: gray; /*스크롤바의 색상*/
	border-radius: 5px;
}

#chatarea::-webkit-scrollbar-track {
	background-color: white; /*스크롤바 트랙 색상*/
}

.note-btn-group btn-group note-mybutton {
	float: right;
	
}

</style>
</body>
</html>