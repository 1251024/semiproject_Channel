<%@page import="channel.room.dto.RoomDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<% 

	RoomDto dto = (RoomDto) request.getAttribute("roomDto");

%>

</head>
<body>

	<div id="channel_info_update">
		<form action="ChatController" method="get">
			<input type="hidden" name="command" value="channelUpdate">
			<input type="hidden" name="channel_num" value="<%=dto.getChannel_num() %>">
			<table border="1">
				<col width="200px;">
				<col width="500px;">
				<tr>
					<th>채널명</th>
					<td><input type="text" name="channel_name" size="50" value="<%=dto.getChannel_name() %>"/></td>
				</tr>
				<tr>
					<th>채널정보</th>
					<td><input type="text" name="channel_info" size="50" value="<%=dto.getChannel_information() %>"/></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="채널정보수정"/>
						<input type="button" value="취소" onclick="history.back();"/>
					</td>
				</tr>
			</table>			
		</form>
	</div>

</body>
</html>