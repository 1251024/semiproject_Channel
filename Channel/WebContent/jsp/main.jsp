<%@page import="channel.member.dto.MemberDto"%>
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

	<h1>Main Page</h1>
		
	<h2>${loginDto.member_num }</h2>
	<h2>${loginDto.member_id }</h2>
	<h2>${loginDto.member_pw }</h2>
	<h2>${loginDto.member_name }</h2>
	


</body>
</html>