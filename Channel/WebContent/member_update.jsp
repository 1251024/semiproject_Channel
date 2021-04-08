<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" sizes="16x16"  href="resources/image/channel_favicon.png">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>CHANNEL / 회원 정보 수정 / ${loginDto.member_name }</title>

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
	border: 1px solid;
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

.lineup {
	margin-top: -3rem;
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
	width: 50%;
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
	background: transparent;
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

#postcodify_search_button {
	float: right;
	margin-top: 0.5rem;
	border-width: 0px;
	background-color: rgb(FF, FF, FF);
	border-radius: 10px;
	color: rgb(130, 130, 130);
	font-size: 1.5rem;
}


    </style>

</head>
<body>
<%@ include file="common.jsp" %>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

				<div class="col-md-1"></div>
				<div class="col-md-10">
				<form action="MemberController" method="post"
					class="col-md-12 text-center">
					<input type="hidden" name="command" value="member_update">
					<input type="hidden" name="member_num" value="${loginDto.member_num }" />
					<input type="hidden" name="member_id" value="${loginDto.member_id }" />
					<h4 class="col-md-12 text-center"
						style="color: rgb(100, 100, 100);">회원정보 수정</h4>
					<br> <label>
						<p class="label-txt">Name</p> 
						<input type="text" class="input" name="name" value="${loginDto.member_name }" required="required">
						<div class="line-box">
							<div class="line"></div>
						</div>
					</label> <br>
					<br> <label>
						<p class="label-txt">Email</p> 
						<input type="text" class="input" name="email" value="${loginDto.member_email }" required="required">
						<div class="line-box">
							<div class="line"></div>
						</div>
					</label> <br>
					<br> <label>
						<p class="label-txt">Phone</p> 
						<input type="text" class="input" name="phone" value="${loginDto.member_phone }" required="required">
						<div class="line-box">
							<div class="line"></div>
						</div>
					</label> <br>
					<br> <label>
						<p class="label-txt">도로명주소</p>
						<div class="col-xs-4">
							<input type="text" name="pscode" class="postcodify_postcode5" value="${loginDto.member_pscode }" required="required" readonly="readonly" title="n" />
						</div> <input type="button" id="postcodify_search_button" value="우편번호 검색"> <br />
						<div class="col-xs-12">
							<input type="text" name="addr" class="postcodify_address" value="${loginDto.member_addr}" required="required" readonly="readonly" />
						</div>
					</label> <label>
						<p class="label-txt">상세주소</p> <input type="text" name="addrdt" value="${loginDto.member_addrdt }" class="postcodify_detail" required="required" />
						<div class="line-box">
							<div class="line"></div>
						</div>
					</label> <br>
					<br>
					<br>
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<input type="submit" class="btn btn-lg btn-block" value="회원 정보 수정" />
					</div>
					<div class="col-md-3"></div>
					<br>
					<br>
					<br>
				</form>
				</div>				
				<br><br>
				<div class="col-md-12">
				<div class="col-md-4"></div>
				<div class="col-md-4 text-center">
					<input type="button" class="btn btn-lg btn-primary btn-block"
						value="비밀번호 수정"
						onclick="location.href='MemberController?command=member_update_pw'">
				</div>
				
				<div class="col-md-4"></div>
				
				<br><br><br><br><br>
				<div class="col-md-12 text-center">&copy;copyright by Coding Four lang</div>
				
				</div>
	


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
		
		
		$("form").submit(function(){
			var pscode = document.getElementsByName("pscode")[0];
			if(pscode.title == 'n'){
				if(pscode.value != null && pscode.value.trim() != ''){
				} else {
					alert("우편번호를 확인해 주세요");
					return false;
				}
			}
		});
	});
	
</script>
	

</body>
		
</html>