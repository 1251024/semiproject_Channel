<%@page import="channel.lyj_room.RoomDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CHANNEL_ADMIN</title>

<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="./resources/summernote/summernote.css" rel="stylesheet">
<script src="./resources/summernote/summernote.js"></script>
<script src="./resources/js/chat.js"></script>
<style type="text/css">
body {
	font-size: 1em;
	margin: 0 auto;
	width: 100%;
	height: 100%;
	
}

#total_container {
	
	border: 1px solid gray;

}

#header_container {
	background-color: #282828;
	height: 3em;
	color: white;
}

#nav_container {
	background-color: #3c3c3c;
	color: white;
	height: 60em;
}

#roominfo {
	border-right: 1px solid gray;
	border-left: 1px solid gray;
	height: 5em;
}

#chatarea {
	background-color: white;
	color: black;
	height: 43em;
	border: 1px solid gray;
	overflow: auto;
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

#textarea {
	background-color: white;
	color: black;
	height: 120px;
}

#channel_add_insert {
	z-index: 3000; display : none;
	position: absolute;
	margin: 0 auto;
	top: 25%;
	left: 20%;
	background-color: white;
	color: black;
	border: 3px solid gray;
	border-radius: 5px;
	width: 60%;
	display: none;
}

#channel_info_update {
	z-index: 3000; display : none;
	position: absolute;
	margin: 0 auto;
	top: 25%;
	left: 20%;
	background-color: white;
	color: black;
	border: 3px solid gray;
	border-radius: 5px;
	width: 60%;
	display: none;
}

#backLayer {
	display: none;
	background-color: black;
	position: absolute;
	left: 0px;
	top: 0px;
}
</style>
</head>
<body>
				<input type="hidden" id="member_num" value="${loginDto.member_num }">
				<input type="hidden" id="member_id" value="${loginDto.member_id }">
				<input type="hidden" id="member_pw" value="${loginDto.member_pw }">
				<input type="hidden" id="member_name" value="${loginDto.member_name }">
	<div class="container-fluid" id="total_container">
		<div id="header_container">
			<div id="nav_channel_info">
				해더 메뉴 영역
			</div>
		</div>

		<div id="main_container">
			<div class="col-xs-3" id="nav_container">
				메뉴 영역
				<div id="nav_menu_list">
					메뉴 리스트
					<div id="thread_list">
						스레드(알림)
					</div>
					<div id="bookmark_list">
						즐겨찾기</div>
					<div id="nav_channel_list">

						<%
						List<RoomDto> list = (List<RoomDto>) request.getAttribute("channelList");
					
						if (list.size() == 0) {
						%>
						<div>
							<span>채널이 없습니다. 채널을 추가해보세요.</span>
						</div>
						<%
						} else {
						%>
						<div>
							<div class="col-md-12">
								<a href="javascript:void(0)"
									onclick="callChatList(<%=list.get(0).getChannel_num()%>);"><%=list.get(0).getChannel_name()%></a>
							</div>
						</div>
						
						<%
						for (int i = 1; i < list.size(); i++) {
						%>
						<div>
							<div class="col-md-6">
								<a href="javascript:void(0)"
									onclick="callChatList(<%=list.get(i).getChannel_num()%>);"><%=list.get(i).getChannel_name()%></a>
							</div>
							<div class="col-md-3">
								<a href="javascript:void(0)"
									onclick="channelAdmin(<%=list.get(i).getChannel_num()%>, '<%=list.get(i).getChannel_name()%>', '<%=list.get(i).getChannel_information()%>', '<%=list.get(i).getChannel_access()%>');">관리</a>
							</div>
							<div class="col-md-3">
								<a href="javascript:void(0)"
									onclick="channeldelcon(<%=list.get(i).getChannel_num()%>);">삭제</a>
							</div>
						</div>

						<%
							}

						}
						%>

						<div id="nav_add_channel" class="col-md-12">
							<a href="javascript:void(0)" onclick="addChannel();">채널추가</a>
						</div>
					</div>
					<div id="nav_massage_list">
						메세지
						<div></div>
						<div>
							<a href="javascript:void(0)" onclick="addMessage();" class="col-md-12">메세지추가</a>
						</div>
					</div>
					<div id="nav_calendar_list">일정</div>
					<div id="nav_cloud_list">클라우드</div>
					<div id="nav_extra_list">더보기</div>
				</div>
			</div>
			<div class="col-xs-9" id="content_container">
				<div class="col-md-12" id="roominfo">
					
				</div>
				<div class="col-md-12" id="chatarea"></div>
				<div class="col-md-12" id="textarea">
					<textarea id="summernote"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div id="channel_add_insert">
		<form action="RoomControllerlyj" method="post" class="form-horizontal">
			<input type="hidden" name="command" value="channelAdd"> 
			<input type="hidden" name="member_id" value="${loginDto.member_id }">
			<input type="hidden" name="member_name" value="${loginDto.member_name }">
			<div class="form-group">
				<label for="" class="col-sm-12"><h1>채널 생성</h1></label>
			</div>
			<div class="form-group">
				<label for="" class="col-sm-2 control-label">채널명</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" placeholder="채널 이름을 입력해주세요." name="channel_name">
				</div>
			</div>
			<div class="form-group">
				<label for="" class="col-sm-2 control-label">채널정보</label>
				<div class="col-sm-10">
					<input type="text" class="form-control"	placeholder="무슨 채널인지 설명해주세요." name="channel_information">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox" >
						<label>
							<select name="channel_access">
								<option value="PUBLIC">공개</option>
								<option value="PRIVATE">비공개</option>
							</select>
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">채널생성</button>
					<input type="button" value="취소" class="btn btn-default"
						onclick="addCancel();" />
				</div>
			</div>
		</form>
	</div>
	<div id="channel_info_update">
		<form action="RoomControllerlyj" method="post" class="form-horizontal">
			<input type="hidden" name="command" value="channelUpdate">
			<input type="hidden" name="member_id" value="${loginDto.member_id }">
			<input type="hidden" name="member_name" value="${loginDto.member_name }">
			<input type="hidden" name="channel_num" id="update_channel_num">
			<div class="form-group">
				<label for="" class="col-sm-12"><h1>채널 정보 수정</h1></label>
			</div>
			<div class="form-group">
				<label for="" class="col-sm-2 control-label" >채널명</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" placeholder="채널 이름을 변경하시려면 수정해주세요." name="channel_name" id="update_channel_name">
				</div>
			</div>
			<div class="form-group">
				<label for="" class="col-sm-2 control-label">채널정보</label>
				<div class="col-sm-10">
					<input type="text" class="form-control"	placeholder="채널 정보를 변경하시려면 수정해주세요." name="channel_information" id="update_channel_information">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
							<select name="channel_access" id="update_channel_access">
								<option value="PUBLIC">공개</option>
								<option value="PRIVATE">비공개</option>
							</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">수정하기</button>
					<input type="button" value="취소" class="btn btn-default"
						onclick="addCancel();" />
				</div>
			</div>
		</form>
	</div>
	<div id="backLayer"></div>
<footer>
	<div class="container">
	<div id="map" style="width:500px; height:400px;"></div>
	</div>
</footer>	
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8b23fbdf7184ddeccfecb57797fda53f"></script>
<script type="text/javascript">
	
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};

	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

</script>
</body>





</html>