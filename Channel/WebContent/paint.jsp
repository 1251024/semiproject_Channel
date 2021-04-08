<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">


</style>
<title>Insert title here</title>
</head>


<link rel="stylesheet" href="resources/css/paint_style.css">
<link rel="stylesheet" href="resources/css/paint_reset.css">

<body>

<%@ include file="common.jsp" %>


	<canvas id="jsCanvas" class="canvas"></canvas>
	<div class="controls" >
		
		<div class="controls_colors" id="jsColors">
			<div class="controls_color jsColor" style="background-color: #2c2c2c"></div>
			<div class="controls_color jsColor" style="background-color: #969696"></div>
			<div class="controls_color jsColor" style="background-color: white"></div>
			<div class="controls_color jsColor" style="background-color: #FF0000"></div>
			<div class="controls_color jsColor" style="background-color: #FF5050"></div>
			<div class="controls_color jsColor" style="background-color: #FA8282"></div>
			<div class="controls_color jsColor" style="background-color: #FFB914"></div>
			<div class="controls_color jsColor" style="background-color: #FFEB46"></div>
			<div class="controls_color jsColor" style="background-color: #FFFF96"></div>
			<div class="controls_color jsColor" style="background-color: #FFEFD5"></div>
		</div>
		<br/>
		<div class="controls_colors" id="jsColors">	
			<div class="controls_color jsColor" style="background-color: #006400"></div>
			<div class="controls_color jsColor" style="background-color: #54BD54"></div>
			<div class="controls_color jsColor" style="background-color: #82F9B7"></div>
			<div class="controls_color jsColor" style="background-color: #0000CD"></div>
			<div class="controls_color jsColor" style="background-color: #5ABEF5"></div>
			<div class="controls_color jsColor" style="background-color: #00FFFF"></div>
			<div class="controls_color jsColor" style="background-color: #9400D3"></div>
			<div class="controls_color jsColor" style="background-color: #c83cc8"></div>
			<div class="controls_color jsColor" style="background-color: #FF9DFF"></div>
			<div class="controls_color jsColor" style="background-color: #FFAAAF"></div>
			
		</div>
		
		<div class="controls_range">
			<input type="range" id="jsRange" min="0.1" max="10.0" value="5.0" step="0.1">
		</div>
		<div class="controls_btns">
			<button id="jsMode">그리기</button><button id="jsSave">저장</button>
		</div>
	</div>


<script src="resources/js/paint_app.js"></script>

</body>

<link rel="stylesheet" href="resources/css/paint_style.css">

</html>