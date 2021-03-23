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

<title>회원가입</title>

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
#pw_check{
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


.postcodify_postcode5 {
	
	border: 0px solid;
	width: 120px;
	margin-top: 1rem;
	margin-left: 5rem;
	
}

.postcodify_address {
	border: 0px solid;
	width: 80%;
	margin-top: 1rem;
}

.postcodify_detail {
	border: 0px solid;
	width: 80%;
	margin-top: 1rem;

}
#postcodify_search_button{
	float: right;
	margin-top: 0.5rem;
	border-width : 0px;
	background-color:rgb(FF,FF,FF);
	border-radius: 10px;
	color:rgb(130,130,130);
	font-size: 1.5rem;
}

</style>

<script src="../resources/js/jquery-3.6.0.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

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
		
		$(function() { $('#postcodify_search_button').postcodifyPopUp(); });

	});
	</script>

</head>
<body>
	<form action="../MemberController" method="post" class="col-md-12 text-center" >
		<input type="hidden" name="command" value="member_insert" >
		<h4 class="col-md-12 text-center" style="color:rgb(100,100,100);">회원가입</h4>
		<br>
		<label>
			<p class="label-txt">ID (*) </p> 
			<input type="text" class="input" name="id">	
			<input type="button" value="중복체크" id="id_check" onclick="">					
			<div class="line-box">
				<div class="line"></div>
			</div>			
		</label>
		<br><br>
		<label>
			<p class="label-txt">Password (*)</p> 
			<input type="password" class="input" name="pw">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label> 
		<br><br>
		<label>
			<p class="label-txt">Password 재입력 (*)</p>
			<input type="password" class="input" name="re_pw">
			<input type="button" value="재확인" id="pw_check" onclick="re();" >
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label> 
		<br><br>
		<label>
			<p class="label-txt">Name (*)</p> 
			<input type="text" class="input" name="name">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label>
		<br><br>
		<label>
			<p class="label-txt">Email (*) </p> 
			<input type="text" class="input" name="email">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label> 
		<br><br>
		<label>
			<p class="label-txt">Phone</p> 
			<input type="text" class="input" name="phone">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label> 
		<br><br>
		<label>
			<p class="label-txt">도로명주소</p>
			<div class="col-xs-4" >
				<input type="text" name="pscode" class="postcodify_postcode5" value="" readonly="readonly" />				
			</div>
			<input type="button" id="postcodify_search_button" value="우편번호 검색">	
			<br />
			<div class="col-xs-12">
				<input type="text" name="addr" class="postcodify_address" value="" readonly="readonly" />
			</div>
		</label>
		<label>
			<p class="label-txt">상세주소</p> 
			<input type="text" name="addrdt" class="postcodify_detail" value="" />
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label>
		<br><br><br>
		<button type="submit">submit</button>
	</form>
	

</body>
</html>