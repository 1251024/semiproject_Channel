<%@page import="channel.calendar.CalendarDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="icon" type="image/png" sizes="16x16"  href="resources/image/channel_favicon.png">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CHANNEL / Calendar / ${loginDto.member_name }</title>
<%
	
	CalendarDto dto = (CalendarDto) request.getAttribute("dto");

	String fromDate = dto.getStart_day().substring(0, 10);
	String toDate = dto.getEnd_day().substring(0, 10);
	
	String fromTime = dto.getStart_day().substring(10, 19);
	String toTime = dto.getStart_day().substring(10, 19);
	
%>
</head>
<body>
	<%@include file="common.jsp"%>
<script type="text/javascript">
$(function(){
		
		var fromTime = '<%=fromTime %>';
		var toTime = '<%=toTime %>';

		var fromSelect = document.getElementById("fromTime");
		var toSelect = document.getElementById("toTime");
		
		for (var i = 0; i < fromSelect.length; i++) {
			if(fromSelect[i].value == fromTime) {
				fromSelect[i].selected = true;
			}
			
			if(toSelect[i].value == toTime) {
				toSelect[i].selected = true;
			}
		}
		
})

function deleteEvent(calnum, memnum) {

	var con = confirm("정말로 일정을 삭제하시겠습니까?")
	
	if (con) {	
		location.href="CalendarController?command=deleteEvent&calendar_num="+calnum+"&member_num="+memnum;	
	}
	
}

</script>
	<div>
		<h3>일정 세부 내용</h3>
		<h4><%=fromDate %> ~ <%=toDate %></h4>
		<form action="CalendarController">
			<input type="hidden" name="command" value="updateEvent">
			<input type="hidden" name="calendar_num" value="<%=dto.getCalendar_num()%>">
			<input type="hidden" name="member_num" value="${loginDto.member_num }">
			<div class="form-group">
				<label for="exampleInputEmail1">일정 제목</label> 
				<input type="text" class="form-control" name="title" value="<%= dto.getTitle()%>">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">일정 내용</label> 
				<textarea class="form-control" name="content"><%= dto.getContent() %></textarea>
			</div>
			<div class="input-group">
					<div class="input-group input-daterange">
						<input class="form-control" type="text" id="fromDate" name="fromDate" placeholder="시작일" value="<%=fromDate %>">
						<select class="form-control" id="fromTime" name="fromTime">
							<option value="T00:00:00">00:00</option>
							<option value="T00:15:00">00:15</option>
							<option value="T00:30:00">00:30</option>
							<option value="T00:45:00">00:45</option>
							
							<option value="T01:00:00">01:00</option>
							<option value="T01:15:00">01:15</option>
							<option value="T01:30:00">01:30</option>
							<option value="T01:45:00">01:45</option>
							
							<option value="T02:00:00">02:00</option>
							<option value="T02:15:00">02:15</option>
							<option value="T02:30:00">02:30</option>
							<option value="T02:45:00">02:45</option>
							
							<option value="T03:00:00">03:00</option>
							<option value="T03:15:00">03:15</option>
							<option value="T03:30:00">03:30</option>
							<option value="T03:45:00">03:45</option>
							
							<option value="T04:00:00">04:00</option>
							<option value="T04:15:00">04:15</option>
							<option value="T04:30:00">04:30</option>
							<option value="T04:45:00">04:45</option>
							
							<option value="T05:00:00">05:00</option>
							<option value="T05:15:00">05:15</option>
							<option value="T05:30:00">05:30</option>
							<option value="T05:45:00">05:45</option>
							
							<option value="T06:00:00">06:00</option>
							<option value="T06:15:00">06:15</option>
							<option value="T06:30:00">06:30</option>
							<option value="T06:45:00">06:45</option>
							
							<option value="T07:00:00">07:00</option>
							<option value="T07:15:00">07:15</option>
							<option value="T07:30:00">07:30</option>
							<option value="T07:45:00">07:45</option>
							
							<option value="T08:00:00">08:00</option>
							<option value="T08:15:00">08:15</option>
							<option value="T08:30:00">08:30</option>
							<option value="T08:45:00">08:45</option>
							
							<option value="T09:00:00">09:00</option>
							<option value="T09:15:00">09:15</option>
							<option value="T09:30:00">09:30</option>
							<option value="T09:45:00">09:45</option>
							
							<option value="T10:00:00">10:00</option>
							<option value="T10:15:00">10:15</option>
							<option value="T10:30:00">10:30</option>
							<option value="T10:45:00">10:45</option>
							
							<option value="T11:00:00">11:00</option>
							<option value="T11:15:00">11:15</option>
							<option value="T11:30:00">11:30</option>
							<option value="T11:45:00">11:45</option>
							
							<option value="T12:00:00">12:00</option>
							<option value="T12:15:00">12:15</option>
							<option value="T12:30:00">12:30</option>
							<option value="T12:45:00">12:45</option>
							
							<option value="T13:00:00">13:00</option>
							<option value="T13:15:00">13:15</option>
							<option value="T13:30:00">13:30</option>
							<option value="T13:45:00">13:45</option>
							
							<option value="T14:00:00">14:00</option>
							<option value="T14:15:00">14:15</option>
							<option value="T14:30:00">14:30</option>
							<option value="T14:45:00">14:45</option>
							
							<option value="T15:00:00">15:00</option>
							<option value="T15:15:00">15:15</option>
							<option value="T15:30:00">15:30</option>
							<option value="T15:45:00">15:45</option>
							
							<option value="T16:00:00">16:00</option>
							<option value="T16:15:00">16:15</option>
							<option value="T16:30:00">16:30</option>
							<option value="T16:45:00">16:45</option>
							
							<option value="T17:00:00">17:00</option>
							<option value="T17:15:00">17:15</option>
							<option value="T17:30:00">17:30</option>
							<option value="T17:45:00">17:45</option>
							
							<option value="T18:00:00">18:00</option>
							<option value="T18:15:00">18:15</option>
							<option value="T18:30:00">18:30</option>
							<option value="T18:45:00">18:45</option>
							
							<option value="T19:00:00">19:00</option>
							<option value="T19:15:00">19:15</option>
							<option value="T19:30:00">19:30</option>
							<option value="T19:45:00">19:45</option>
							
							<option value="T20:00:00">20:00</option>
							<option value="T20:15:00">20:15</option>
							<option value="T20:30:00">20:30</option>
							<option value="T20:45:00">20:45</option>
							
							<option value="T21:00:00">21:00</option>
							<option value="T21:15:00">21:15</option>
							<option value="T21:30:00">21:30</option>
							<option value="T21:45:00">21:45</option>
							
							<option value="T22:00:00">22:00</option>
							<option value="T22:15:00">22:15</option>
							<option value="T22:30:00">22:30</option>
							<option value="T22:45:00">22:45</option>
							
							<option value="T23:00:00">23:00</option>
							<option value="T23:15:00">23:15</option>
							<option value="T23:30:00">23:30</option>
							<option value="T23:45:00">23:45</option>					
						</select>
						<div class="input-group-addon">TO</div>
							<input class="form-control" type="text" id="toDate" name="toDate" class="form-control" placeholder="종료일" value="<%=toDate %>">
						<select class="form-control" id="toTime" name="toTime">
							<option value="T00:00:00">00:00</option>
							<option value="T00:15:00">00:15</option>
							<option value="T00:30:00">00:30</option>
							<option value="T00:45:00">00:45</option>
							
							<option value="T01:00:00">01:00</option>
							<option value="T01:15:00">01:15</option>
							<option value="T01:30:00">01:30</option>
							<option value="T01:45:00">01:45</option>
							
							<option value="T02:00:00">02:00</option>
							<option value="T02:15:00">02:15</option>
							<option value="T02:30:00">02:30</option>
							<option value="T02:45:00">02:45</option>
							
							<option value="T03:00:00">03:00</option>
							<option value="T03:15:00">03:15</option>
							<option value="T03:30:00">03:30</option>
							<option value="T03:45:00">03:45</option>
							
							<option value="T04:00:00">04:00</option>
							<option value="T04:15:00">04:15</option>
							<option value="T04:30:00">04:30</option>
							<option value="T04:45:00">04:45</option>
							
							<option value="T05:00:00">05:00</option>
							<option value="T05:15:00">05:15</option>
							<option value="T05:30:00">05:30</option>
							<option value="T05:45:00">05:45</option>
							
							<option value="T06:00:00">06:00</option>
							<option value="T06:15:00">06:15</option>
							<option value="T06:30:00">06:30</option>
							<option value="T06:45:00">06:45</option>
							
							<option value="T07:00:00">07:00</option>
							<option value="T07:15:00">07:15</option>
							<option value="T07:30:00">07:30</option>
							<option value="T07:45:00">07:45</option>
							
							<option value="T08:00:00">08:00</option>
							<option value="T08:15:00">08:15</option>
							<option value="T08:30:00">08:30</option>
							<option value="T08:45:00">08:45</option>
							
							<option value="T09:00:00">09:00</option>
							<option value="T09:15:00">09:15</option>
							<option value="T09:30:00">09:30</option>
							<option value="T09:45:00">09:45</option>
							
							<option value="T10:00:00">10:00</option>
							<option value="T10:15:00">10:15</option>
							<option value="T10:30:00">10:30</option>
							<option value="T10:45:00">10:45</option>
							
							<option value="T11:00:00">11:00</option>
							<option value="T11:15:00">11:15</option>
							<option value="T11:30:00">11:30</option>
							<option value="T11:45:00">11:45</option>
							
							<option value="T12:00:00">12:00</option>
							<option value="T12:15:00">12:15</option>
							<option value="T12:30:00">12:30</option>
							<option value="T12:45:00">12:45</option>
							
							<option value="T13:00:00">13:00</option>
							<option value="T13:15:00">13:15</option>
							<option value="T13:30:00">13:30</option>
							<option value="T13:45:00">13:45</option>
							
							<option value="T14:00:00">14:00</option>
							<option value="T14:15:00">14:15</option>
							<option value="T14:30:00">14:30</option>
							<option value="T14:45:00">14:45</option>
							
							<option value="T15:00:00">15:00</option>
							<option value="T15:15:00">15:15</option>
							<option value="T15:30:00">15:30</option>
							<option value="T15:45:00">15:45</option>
							
							<option value="T16:00:00">16:00</option>
							<option value="T16:15:00">16:15</option>
							<option value="T16:30:00">16:30</option>
							<option value="T16:45:00">16:45</option>
							
							<option value="T17:00:00">17:00</option>
							<option value="T17:15:00">17:15</option>
							<option value="T17:30:00">17:30</option>
							<option value="T17:45:00">17:45</option>
							
							<option value="T18:00:00">18:00</option>
							<option value="T18:15:00">18:15</option>
							<option value="T18:30:00">18:30</option>
							<option value="T18:45:00">18:45</option>
							
							<option value="T19:00:00">19:00</option>
							<option value="T19:15:00">19:15</option>
							<option value="T19:30:00">19:30</option>
							<option value="T19:45:00">19:45</option>
							
							<option value="T20:00:00">20:00</option>
							<option value="T20:15:00">20:15</option>
							<option value="T20:30:00">20:30</option>
							<option value="T20:45:00">20:45</option>
							
							<option value="T21:00:00">21:00</option>
							<option value="T21:15:00">21:15</option>
							<option value="T21:30:00">21:30</option>
							<option value="T21:45:00">21:45</option>
							
							<option value="T22:00:00">22:00</option>
							<option value="T22:15:00">22:15</option>
							<option value="T22:30:00">22:30</option>
							<option value="T22:45:00">22:45</option>
							
							<option value="T23:00:00">23:00</option>
							<option value="T23:15:00">23:15</option>
							<option value="T23:30:00">23:30</option>
							<option value="T23:45:00">23:45</option>					
						</select>
					</div>
					<div class="recipient-name">
						<label for="recipient-name" class="control-label">일정 장소</label>
					</div>
			</div>
			<div class="input-group">
      			<input class="form-control" type="text" id="getAddress" name="address" placeholder="주소" value="<%=dto.getAddress()%>" onclick="getPostcode()">
     				 <span class="input-group-btn">
      	  				<button class="btn btn-success" type="button" onclick="getPostcode()">장소 수정</button>
     				 </span>
    				</div>
					<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="location.href='calendar.jsp'">돌아가기</button>
					<button type="button" class="btn btn-danger" onclick="deleteEvent(<%=dto.getCalendar_num()%>, ${loginDto.member_num });">삭제</button>
					<button type="submit" class="btn btn-primary">수정</button>
				</div>	
		</form>
	</div>

</body>
<link rel="stylesheet" href="resources/datetimepicker/css/bootstrap-datepicker.css">
<script src="resources/datetimepicker/js/bootstrap-datepicker.js"></script>
<script src="resources/datetimepicker/bootstrap-datepicker.ko.min.js"></script>
<script src="resources/js/datepickeropener.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8b23fbdf7184ddeccfecb57797fda53f&libraries=services"></script>
<script src="resources/js/map.js"></script>
</html>