document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			initialView : 'dayGridMonth',
			headerToolbar : {
				start : 'prevYear,prev,next,nextYear addEventButton',
				center : 'title',
				end : 'today dayGridMonth,timeGridWeek,timeGridDay'
			},
			height : 800,
			aspectRatio : 1.35,
			locale : 'ko',
			customButtons : {
				addEventButton : {
					text : '일정추가',
					click : function() {
						$('#addCalendarForm').modal();
					}
				}
			},
			eventClick : function(info) {
				var eventObj = info.event;

				if (eventObj.url) {
					location.href=eventObj.url;
					info.jsEvent.preventDefault();
				} else {
					alert('Clicked ' + eventObj.title);
				}
			}
		});
		calendar.render();
		
		var member_num = $("#member_num").val();
		
		$.ajax ({
			
			url:"CalendarController?command=selectListEvent&member_num="+member_num,
			dataType:"json",
			method:"post",
			success:function(data) {
				
				var event = data;
				
				for (var i = 0; i < event.length; i++) {
					
					calendar.addEvent({
						'title' : event[i].title,
						'start' : event[i].start_day,
						'end' : event[i].end_day,
						'url' : 'CalendarController?command=selectOneEvent&calendar_num='+event[i].calendar_num
					})
				}
			},
			error:function(msg) {
				alert(msg);
			}
		})
	});

function addCalendar() {
	
	var member_num = $("#member_num").val();
	
	var title = $("#add_calendar_title").val();
	var content = $("#add_calendar_content").val();
	
	var fromDate = $("#fromDate").val();
	var fromTime = $("#fromTime option:selected").val();
	
	var toDate = $("#toDate").val();
	var toTime = $("#toTime option:selected").val();
	
	var start_day = fromDate + fromTime;
	var end_day = toDate + toTime;
	
	function  getParameterValues() {
		return "?command=insertEvent&member_num=" + member_num
				+ "&title=" + title
				+ "&content=" + content
				+ "&start_day=" + start_day
				+ "&end_day=" + end_day;
	}
	$.ajax({
		url:"CalendarController"+getParameterValues(),
		dataType:"text",
		method:"post",
		success:function(msg) {
			alert(msg)
			location.href="calendar.jsp";
		},
		error:function(msg) {
			alert(msg)
		}
	
	})

}

function selectOneEvent(calnum) {
	alert(calnum);
}