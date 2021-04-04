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
<%
List<PaymentDto> list = (List<PaymentDto>)request.getAttribute("list");


%>
<body>
	<input type="hidden" id="member_num" value="${loginDto.member_num }">
	<input type="hidden" id="member_id" value="${loginDto.member_id }">
	<input type="hidden" id="member_pw" value="${loginDto.member_pw }">
	<input type="hidden" id="member_name" value="${loginDto.member_name }">



	<h1>결제확인</h1>

	<table border="1">
		<tr>
			<th>결제번호</th>
			<th>결제이메일</th>
			<th>결제연락처</th>
			<th>결제방법</th>
			<th>결제금액</th>
			<th>결제일</th>
		</tr>


		<%
		for (PaymentDto dto : list) {
		%>

		<tr>
			<td><%=dto.getPay_no()%></td>
			<td><%=dto.getPay_name()%></td>
			<td><%=dto.getPay_email()%></td>
			<td><%=dto.getPay_phone()%></td>
			<td><%=dto.getPay_type()%></td>
			<td><%=dto.getPay_price()%></td>
			<td><%=dto.getPay_date()%></td>
		</tr>

		<%
		}
		%>
		<tr>
			<td colspan="4" align="right">
			<input type="button" value="메인으로" onclick="" /></td>
		</tr>

	</table>

	
</body>
</html>