<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="icon" type="image/png" sizes="16x16"  href="resources/image/channel_favicon.png">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CHANNEL / Weather / ${loginDto.member_name }</title>

</head>
<body>
<%@include file="common.jsp" %>
	<div class="btn-group">
  		<button type="button" class="btn btn-default btn-lg dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    		도시선택 <span class="caret"></span>
  		</button>
 		 <ul class="dropdown-menu" role="menu">
   		 	<li><a href="javascript:void(0);" onclick="callWeather('Seoul')">서울</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Incheon')">인천</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Chuncheon')">춘천</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Chungju')">광주</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Gangneung')">강릉</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Tonghae')">동해</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Andong')">안동</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Suwon')">수원</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Daejeon')">대전</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Jeonju')">전주</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Daegu')">대구</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Ulsan')">울산</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Gwangju')">광주</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Mokpo')">목포</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Reisui')">여수</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Busan')">부산</a></li>
    		<li><a href="javascript:void(0);" onclick="callWeather('Jeju-do')">제주</a></li>
  		</ul>
	</div>
		
        <div> 
			<div class="page-header">
  					<h1 id="current_temp">현재 날씨 정보   </h1>
			</div>
			<table class="table table-hover" id="current_weather">
 				<tr>
 					<th><span class="glyphicon glyphicon-certificate" aria-hidden="true"></span>&nbsp;&nbsp;온도</th>
 					<th><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;체감온도</th>
 					<th><span class="glyphicon glyphicon-tint" aria-hidden="true"></span>&nbsp;&nbsp;습도</th>
 					<th><span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>&nbsp;&nbsp;기압</th>
 					<th><span class="glyphicon glyphicon-cloud" aria-hidden="true"></span>&nbsp;&nbsp;구름</th>
 					<th><span class="glyphicon glyphicon-flag" aria-hidden="true"></span>&nbsp;&nbsp;풍속</th>
 				</tr>
 				
			</table>
        </div>
			<br>
        <div id="chart_temp"> 
        	<div class="page-header">
  					<h1>시간대별 날씨 정보</h1>
			</div>
            <div id="tempchart" style="width: 100em; height: 40em"></div>
            <div id="humidchart" style="width: 100em; height: 40em"></div>
            <div id="presschart" style="width: 100em; height: 40em"></div>
            <div id="cloudchart" style="width: 100em; height: 40em"></div>
            <div id="windchart" style="width: 100m,; height: 40em"></div>
        </div>


</body>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="resources/js/weather.js"></script>

</html>