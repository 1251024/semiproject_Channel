<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="MemberController" method="post" class="col-md-12 text-center" >
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