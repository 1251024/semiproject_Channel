<%@page import="channel.member.dto.MemberDto"%>
<%@page import="payment.dto.PaymentDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<!-- include 영역 -->
	<%@ include file="common.jsp"%>

	<%
	MemberDto memberdto = (MemberDto) session.getAttribute("loginDto");//(형변환)오브젝트상태로가져오는것-세션가져오기
	PaymentDto paydto = (PaymentDto) request.getAttribute("paydto");
	System.out.println("paydtores:"+paydto.getPay_name());

	
	//<input type="hidden" id="member_num" value="${loginDto.member_num }">
	//<input type="hidden" id="member_id" value="${loginDto.member_id }">
	//<input type="hidden" id="member_pw" value="${loginDto.member_pw }">
	//<input type="hidden" id="member_name" value="${loginDto.member_name }">
	%>


	<h1>결제확인</h1>

	<table border="1">
		<tr>
			<th>결제자이름</th>
			<th>결제이메일</th>
			<th>결제연락처</th>
			<th>결제금액</th>
			<th>결제일</th>
		</tr>
		<tr>
			<td><%=paydto.getPay_name()%></td>
			<td><%=paydto.getPay_email()%></td>
			<td><%=paydto.getPay_phone()%></td>
			<td><%=paydto.getPay_price()%></td>
			<td><%=paydto.getPay_date()%></td>
		</tr>


		<tr>
			<td colspan="5" align="right">
			<input type="button" value="메인으로" onclick="workspace.jsp" />
			</td>
		</tr>

	</table>


</body>
</html>