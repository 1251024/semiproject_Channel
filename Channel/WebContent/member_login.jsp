<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%	request.setCharacterEncoding("UTF-8"); %>
<%	response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" sizes="16x16"  href="resources/image/channel_favicon.png">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="resources/imgage/favicon.ico">

<title>CHANNEL / Login</title>

<script src="resources/js/jquery-3.6.0.min.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script src="resources/js/bootstrap.min.js"></script>

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
	
	
        a,
        a:focus,
        a:hover {
            color: white;
        }

        .btn-default,
        .btn-default:hover,
        .btn-default:focus {
            color: #333;
            text-shadow: none;
        }

        html,
        body {
            height: 98%;
            background-color: #333;
            margin-top: 25px;

        }

        body {
            color: #fff;
            text-align: center;
            padding-top: 0px;
        }

        #navbox {
            background-color: black;
            color: white;
            font-weight: bold;

        }
                
        #loginbox{
        	background-image: url('resources/image/bg1.jpg');
        	background-size: cover;
        	background-color:transparent;
        }
	
	
	
</style>

</head>

<body>
  
<%

    String NclientId = "BAEN9bUjlj2M_oHlFbAi";//애플리케이션 클라이언트 아이디값";
    String NredirectURI = URLEncoder.encode("http://localhost:8787/Channel/MemberController?command=naverlogin", "UTF-8");
    SecureRandom Nrandom = new SecureRandom();
    String Nstate = new BigInteger(130, Nrandom).toString();
    String NapiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    NapiURL += "&client_id=" + NclientId;
    NapiURL += "&redirect_uri=" + NredirectURI;
    NapiURL += "&state=" + Nstate;
    session.setAttribute("Nstate", Nstate);
    	
	String GclientId = "533483186463-e0gd75qd2j8d8pko7p48mvee4md6p0d5.apps.googleusercontent.com";
	String GredirectURI = URLEncoder.encode("http://localhost:8787/Channel/MemberController?command=googlelogin", "UTF-8");
	String GapiUrl = "https://accounts.google.com/o/oauth2/auth?client_id="+ GclientId
	 				+ "&redirect_uri="+ GredirectURI 
	 				+ "&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email&approval_prompt=force&access_type=offline";

%>

	<nav class="navbar navbar-default navbar-fixed-top" id="navbox">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">Channel</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="index.html">시작하기</a></li>
                    <li><a href="instructional.html">Channel이란?</a></li>
                     <li class="dropdown">
              <a id="drop1" href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" role="button" aria-expanded="false">
                Developer
                <span class="caret"></span>
              </a>
              <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
                <li role="presentation"><a role="menuitem" tabindex="-1" href="https://github.com/ftrspt">강성필</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="https://github.com/jmjnssss">박은희</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="https://github.com/RUCKUSJERRY">이용준</a></li>
                <li role="presentation" class="divider"></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="https://github.com/1251024">장보옥</a></li>
              </ul>
            </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="MemberController?command=member_insert_page">회원가입</a></li>
                    <li><a href="MemberController?command=member_login_page">Login </a></li>
                </ul>
            </div>
        </div>
    </nav>

	<div class="col-xs-12" id="loginbox">
	<div class="container">
		<form class="form-signin" action="MemberController" method="post">
			<input type="hidden" name="command" value="member_login">
			<h3 class="form-signin-heading">환영합니다!</h3>
			<br>
			<label for="inputID" class="sr-only">ID</label> 
				<input type="text" id="inputID" class="form-control" placeholder="ID" name="id" required autofocus>
			<label for="inputPassword" class="sr-only">Password</label>
				 <input type="password" id="inputPassword" class="form-control"	placeholder="Password" name="pw" required>
			<br>
			<button class="btn btn-lg btn-block btn-info" type="submit">Login</button>			
			
		</form>
		
		<form class="form-signin" action="MemberController" method="post">
			<input type="hidden" name="command" value="member_insert_page">
			<hr>
			<span>아직 가입하지 않으셨나요?</span> 
			<button class="btn" id="insert_btn" type="submit">회원가입</button>
			<br><hr>
			<h4>SNS ID로 계속해 보세요!</h4>
			<br>
			<div class="col-xs-2"></div>
			<div class="col-xs-4">
				<a href=<%=GapiUrl %>><img src="resources/image/login_google_icon.png"></a>
			</div>
			<div class="col-xs-4"> 
				<a href="<%=NapiURL%>"><img src="resources/image/login_naver_icon.png"></a>
			</div>
			<div class="col-xs-2"></div>
		</form>
	</div>
	
	
		<div class="col-md-12 text-center" style="margin-top: 15%; color:rgb(180,180,180);">
			&copy; copyright by <i>Coding_Four_Lang</i>.
		</div>
	
	</div>
	
	
	
</body>
</html>
