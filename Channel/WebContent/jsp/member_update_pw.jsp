<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%	request.setCharacterEncoding("UTF-8"); %>
<% 	response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>회원 비밀번호 수정</title>

<style>
body {
	background: #efefef;
}

form {
	width: 70%;
	margin: 5% auto;
	margin-top: 50px;
	background: white;
	padding: 5% 10% 10% 10%;
	text-align: center;
	border : 1px solid;
}

label {
	display: block;
	position: relative;
	margin: 20px 0px;
	width: 100%;
}

.label-txt {
	position: absolute;
	top: -1.6em;
	padding: 10px;
	font-family: sans-serif;
	font-size: .9em;
	letter-spacing: 1px;
	color: rgb(120, 120, 120);
	transition: ease .3s;
}

#id_check{
	float: right;
	margin-top: -3rem;
	border-width : 0px;
	background-color:rgb(FF,FF,FF);
	border-radius: 10px;
	color:rgb(130,130,130);
	font-size: 1.3rem;
}

.input {
	width: 100%;
	padding: 10px;
	background: transparent;
	border: none;
	outline: none;
}

.line-box {
	position: relative;
	width: 100%;
	height: 2px;
	background: #BCBCBC;
}

.line {
	position: absolute;
	width: 0%;
	height: 2px;
	top: 0px;
	left: 50%;
	transform: translateX(-50%);
	background: #8BC34A;
	transition: ease .6s;
}

.input:focus+.line-box .line {
	width: 100%;
}

.label-active {
	top: -3em;
}

button {
	display: inline-block;
	padding: 12px 24px;
	background: rgb(220, 220, 220);
	font-weight: bold;
	color: rgb(120, 120, 120);
	border: none;
	outline: none;
	border-radius: 3px;
	cursor: pointer;
	transition: ease .3s;
}

button:hover {
	background: skyblue;
	color: #ffffff;
}

#pw_check{
	float: right;
	margin-top: -3rem;
	border-width : 0px;
	background-color:rgb(FF,FF,FF);
	border-radius: 10px;
	color:rgb(130,130,130);
	font-size: 1.3rem;
}



</style>

<script src="../resources/js/jquery-3.6.0.min.js"></script>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<script>
	$(document).ready(function() {
		$('.input').focus(function() {
			$(this).parent().find(".label-txt").addClass('label-active');
		});
		$(".input").focusout(function() {
			if ($(this).val() == '') {
				$(this).parent().find(".label-txt").removeClass('label-active');
			}
		});
	});
	</script>

</head>
<body>
	<form action="../MemberController" method="post" class="col-md-12 text-center" >
		<input type="hidden" name="command" value="member_update_pw" >
		<h4 class="col-md-12 text-center" style="color:rgb(100,100,100);">비밀번호 수정</h4>
		<br>
		<label>
			<p class="label-txt">예전 비밀번호</p> 
			<input type="password" class="input" name="pw_old">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label> 
		<br><br>
		<label>
			<p class="label-txt">새 비밀번호</p> 
			<input type="password" class="input" name="pw_new">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label> 
		<br><br>
		<label>
			<p class="label-txt">새 비밀번호 다시입력</p>
			<input type="password" class="input" name="pw_new2">
			<input type="button" value="재확인" id="pw_check" onclick="re();" >
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label>
		<br><br><br>
		<button type="submit">비밀번호 수정</button>
	</form>
	

</body>
</html>