<%@page import="channel.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" sizes="16x16"  href="resources/image/channel_favicon.png">
<meta charset="UTF-8">
<title>CHANNEL / OrderSheet / ${loginDto.member_name }</title>
<style>
span {
	width: 60px;
	display: inline-block;
}

textarea {
	width: 40%;
	height: 280px;
}
</style>



</head>
<body>
<%
MemberDto memberdto =(MemberDto)session.getAttribute("loginDto");//(형변환)오브젝트상태로가져오는것-세션가져오기

int pay_member_no = memberdto.getMember_num();
String pay_member_email = memberdto.getMember_email();
String Pay_member_phone = memberdto.getMember_phone();
%>

	<!-- include 영역 -->
	<%@ include file="common.jsp"%>
	
	
<h2>CHANNEL 이용료 결제</h2>

	<form action="pay_credit_checkout.jsp" method="post">
	<input type="hidden" name="type" value="CREDIT">
	<input type="hidden" name="member_num" value="${loginDto.member_num }">
		<p>
			<span>name:</span> <input type="text" name="name" value="${loginDto.member_name }" readonly="readonly" autofocus="autofocus">


			<!-- 값 가져오기 -->
		</p>
		<p>
			<span>이메일:</span> <input type="text" name="email" value="${loginDto.member_email }"placeholder="이메일을 입력해주세요">
			
		</p>
		
		<p>
			<span>폰넘버:</span><input type="text" name="phone" value="${loginDto.member_phone }" placeholder="연락처를 입력해주세요">
		</p>
		
		<p>
			<span>가격:</span><input name="totalPrice" value="9900" readonly="readonly">
		</p>
		<input type="submit" value="결제하기"> 
		<input type="reset"	value="취소하기">
		<input type="button" value="뒤로가기" onclick="location.href='index.html'">
	</form>


</body>
</html>