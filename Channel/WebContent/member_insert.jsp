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

<title>CHANNEL / 회원가입</title>

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
	margin-top : 3rem;
	margin-bottom: 0rem;
	border-width : 0px;
	background-color:rgb(FF,FF,FF);
	border-radius: 10px;
	color:rgb(130,130,130);
	font-size: 1.3rem;
	z-index: 1;
}
#pw_check{
	float: right;
	margin-top: 3rem;
	margin-bottom: -1rem;
	border-width : 0px;
	background-color:rgb(FF,FF,FF);
	border-radius: 10px;
	color:rgb(130,130,130);
	font-size: 1.4rem;
	z-index: 10;
}

.lineup{
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
#postcodify_search_button{
	float: right;
	margin-top: 0.5rem;
	border-width : 0px;
	background-color:rgb(FF,FF,FF);
	border-radius: 10px;
	color:rgb(130,130,130);
	font-size: 1.5rem;
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
        
        #insertbox {
        	background-image: url('resources/image/bg2.jpg');
        	background-size: cover;
        
        }

</style>

<script src="resources/js/jquery-3.6.0.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/member_insert.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

</head>
<body>

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
	<div id="insertbox" class="col-md-12">
	<div class="col-xs-3"></div>	
	<div class="col-md-6">
	<form action="MemberController" method="post" class="col-md-12 text-center" >
		<input type="hidden" name="command" value="member_insert" >
		<h4 class="col-md-12 text-center" style="color:rgb(100,100,100);">회원가입</h4>
		<br><br>
		<input type="button" value="중복체크" id="id_check">
		<label class="lineup">
			<p class="label-txt">ID</p> 
			<input type="text" class="input" name="id" required="required" title="n">								
			<div class="line-box">
				<div class="line"></div>
			</div>						
		</label>
		<br><br>
		<label>
			<p class="label-txt">Password</p> 
			<input type="password" class="input" name="pw" required="required" title="n">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label>
		<input type="button" value="재확인" id="pw_check" >	
		<br><br>
		<label>
			<p class="label-txt">Password 재입력</p>
			<input type="password" class="input" name="re_pw" required="required">					
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label> 
		<br><br>
		<label>
			<p class="label-txt">Name</p> 
			<input type="text" class="input" name="name" required="required">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label>
		<br><br>
		<label>
			<p class="label-txt">Email</p> 
			<input type="text" class="input" name="email" required="required">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label> 
		<br><br>
		<label>
			<p class="label-txt">Phone</p> 
			<input type="text" class="input" name="phone" required="required">
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label> 
		<br><br>
		<label>
			<p class="label-txt">도로명주소</p>
			<div class="col-xs-4" >
				<input type="text" name="pscode" class="postcodify_postcode5" value="" required="required" readonly="readonly" title="n"/>				
			</div>
			<input type="button" id="postcodify_search_button" value="우편번호 검색">	
			<br />
			<div class="col-xs-12">
				<input type="text" name="addr" class="postcodify_address" value="" required="required" readonly="readonly" />
			</div>
		</label>
		<label>
			<p class="label-txt">상세주소</p> 
			<input type="text" name="addrdt" class="postcodify_detail" value="" required="required" />
			<div class="line-box">
				<div class="line"></div>
			</div>
		</label>
		<br><br><br>
		<button type="submit">submit</button>
	</form>
	</div>
	<div class="col-xs-3"></div>
	</div>
</body>
</html>