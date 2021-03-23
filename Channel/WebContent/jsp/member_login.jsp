<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%	request.setCharacterEncoding("UTF-8"); %>
<%	response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="../resources/imgages/favicon.ico">

<title>Login</title>

<script src="../resources/js/jquery-3.6.0.min.js"></script>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">

<style type="text/css">
	body {
	  padding-top: 40px;
	  padding-bottom: 40px;
	  background-color: white;
	}
	
	.form-signin {
	  max-width: 330px;
	  padding: 15px;
	  margin: 0 auto;
	  text-align: center;
	}
	.form-signin .form-signin-heading,
	.form-signin .checkbox {
	  margin-bottom: 10px;
	}
	.form-signin .checkbox {
	  font-weight: normal;
	}
	.form-signin .form-control {
	  position: relative;
	  height: auto;
	  box-sizing: border-box;
	  padding: 10px;
	  font-size: 16px;
	}
	.form-signin .form-control:focus {
	  z-index: 2;
	}
	.form-signin input[type="email"] {
	  margin-bottom: -1px;
	  border-bottom-right-radius: 0;
	  border-bottom-left-radius: 0;
	}
	.form-signin input[type="password"] {
	  margin-bottom: 10px;
	  border-top-left-radius: 0;
	  border-top-right-radius: 0;
	}
	
	#insert_btn{
		background-color: white;
		color: blue;
	}
	img {
		width: 82%;
		height: auto;
		box-shadow: 0.5px 0.5px;
		border-radius:10px;
		
	}
	
	span {
		color: rgb(30, 30, 30);
	}
	
	h4 {
		color: rgb(120, 120, 120);
	}
	
	.col-md-12 text-center {
	
	}
	
	
	
</style>

</head>

<body>

	<div class="container">
		<form class="form-signin" action="../MemberController" method="post">
			<input type="hidden" name="command" value="member_login">
			<h3 class="form-signin-heading">환영합니다!</h3>
			<br>
			<label for="inputID" class="sr-only">ID</label> 
				<input type="text" id="inputID" class="form-control" placeholder="ID" name="id" required autofocus>
			<label for="inputPassword" class="sr-only">Password</label>
				 <input type="password" id="inputPassword" class="form-control"	placeholder="Password" name="pw" required>
			<br>
			<button class="btn btn-lg btn-block" type="submit">Login</button>			
			
		</form>
		
		<form class="form-signin" action="../MemberController" method="post">
			<input type="hidden" name="command" value="member_insert_page">
			<hr>
			<span>아직 가입하지 않으셨나요?</span> 
			<button class="btn" id="insert_btn" type="submit">회원가입</button>
			<br><hr>
			<h4>SNS ID로 계속해 보세요!</h4>
			<br>
			<div class="col-xs-4">
				<a href="../MemberController?command=member_google_login"><img src="../resources/image/login_google_icon.png"></a>
			</div>
			<div class="col-xs-4"> 
				<a href="../MemberController?command=member_naver_login"><img src="../resources/image/login_naver_icon.png"></a>
			</div>
			<div class="col-xs-4">
				<a href="../MemberController?command=member_kakao_login"><img src="../resources/image/login_kakao_icon.png"></a>
			</div>
		</form>
	</div>
	
		<div class="col-md-12 text-center" style="margin-top: 15%; color:rgb(180,180,180);">
			&copy; copyright by <i>Coding_Four_Lang</i>.
		</div>
	
	
	
	
	
</body>
</html>
