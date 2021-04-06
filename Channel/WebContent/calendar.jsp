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
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CHANNEL / Calendar / ${loginDto.member_name }</title>
<link href='resources/fullcalendar/main.css' rel='stylesheet' />
<script src='resources/fullcalendar/main.js'></script>
<script src='resources/js/calendar.js'></script>
</head>
<body>
	<%@include file="common.jsp"%>

	<div id='calendar'></div>

	<div class="modal fade" id="addCalendarForm" tabindex="-1"
		role="dialog" aria-labelledby="addCalendarLable" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="addCalendarLable">새 일정 추가</h3>
				</div>
				<div class="modal-body">
					<div>
						<label for="recipient-name" class="control-label">일정 제목 입력</label>
						<input type="text" class="form-control" id="add_calendar_title"
							placeholder="title">
					</div>
					<div>
						<label for="recipient-name" class="control-label">일정 내용 입력</label>
						<textarea class="form-control" id="add_calendar_content" placeholder="content"></textarea>
					<div class="recipient-name">
						<label for="recipient-name" class="control-label">일정 기간 입력</label>
					</div>
					<div class="input-group">
					<div class="input-group input-daterange">
						<input class="form-control" type="text" id="fromDate" placeholder="시작일">
						<select class="form-control" id="fromTime">
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
						<input class="form-control" type="text" id="toDate" class="form-control" placeholder="종료일">
						<select class="form-control" id="toTime">
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
					</div>
				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" onclick="addCalendar();">생성하기</button>
				</div>
			</div>
		</div>
	</div>
</body>
<link rel="stylesheet" href="resources/datetimepicker/css/bootstrap-datepicker.css">
<script src="resources/datetimepicker/js/bootstrap-datepicker.js"></script>
<script src="resources/datetimepicker/bootstrap-datepicker.ko.min.js"></script>
<script src="resources/js/datepickeropener.js"></script>
</html>