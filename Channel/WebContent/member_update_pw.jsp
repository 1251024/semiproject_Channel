<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%	request.setCharacterEncoding("UTF-8"); %>
<% 	response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" sizes="16x16"  href="resources/image/channel_favicon.png">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>CHANNEL / 회원 비밀번호 수정 / ${loginDto.member_name }</title>

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
	margin-top: -1rem;
	margin-bottom: -1rem;
	border-width : 0px;
	background-color:rgb(FF,FF,FF);
	border-radius: 10px;
	color:rgb(130,130,130);
	font-size: 1.3rem;
}

    </style>
    
</head>

<body>
<%@ include file="common.jsp" %>

                <div class="col-md-1"></div>
				<div class="col-md-10">		
				<form action="MemberController" method="post" class="col-md-12 text-center" >
					<input type="hidden" name="command" value="member_update_pw" >
					<input type="hidden" name="member_num" value="${loginDto.member_num }" >
					<input type="hidden" name="member_id" value="${loginDto.member_id }" >
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
						<input type="password" class="input" name="pw_new" title="n">
						<div class="line-box">
							<div class="line"></div>
						</div>
					</label> 
					<br><br>
					<input type="button" value="재확인" id="pw_check">
					<label>		
						<p class="label-txt">새 비밀번호 다시입력</p>
						<input type="password" class="input" name="pw_new2">			
						<div class="line-box">
							<div class="line"></div>
						</div>
					</label>
					<br>
					<button type="submit">비밀번호 수정</button>
				</form>
				</div>
				<div class="col-md-1"></div>
				<br><br>
				<div class="col-md-2"></div>
				<div class="col-xs-2"></div>				
				<div class="col-xs-3" id="google_recaptha">
				<script src='https://www.google.com/recaptcha/api.js'></script>
				<div class="g-recaptcha" data-sitekey="6LeiGpgaAAAAAKkTjqI0wPyJ3hLrTDXjX-4KJHLo"></div>
				</div>
				<div class="col-xs-4"></div>
				<br><br><br><br><br>		
				<div class="col-md-12 text-center">&copy;copyright by Coding Four lang</div>

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
		
		$("#pw_check").click(function(){
			pw_new = document.getElementsByName("pw_new")[0].value;
			pw_new2 = document.getElementsByName("pw_new2")[0].value;
			
			if(pw_new !== null && pw_new.trim() !== ''){
				if(pw_new == pw_new2){
					alert("비밀번호가 같습니다.");
					document.getElementsByName("pw_new")[0].setAttribute("title", "y");
				} else {
					alert("비밀번호가 다릅니다.");
				}
			} else {
				alert("새 비밀번호를 입력해 주세요");
			}			
		});
		
		$("input[name=pw_new]").change(function(){
			document.getElementsByName("pw_new")[0].setAttribute("title", "n");
		});
		
		$("input[name=pw_new2]").change(function(){
			document.getElementsByName("pw_new2")[0].setAttribute("title", "n");
		});
				
		$("form").submit(function(){
			equals_pw = document.getElementsByName("pw_new")[0].title;
			if(equals_pw !== 'y'){
				alert("비밀번호를 확인해 주세요");
				return false;
			}
		});		
	});
	</script>    			
				

</body>
</html>