<%@page import="channel.member.biz.MemberBiz"%>
<%@page import="channel.member.dto.SearchMemberDto"%>
<%@page import="java.util.List"%>
<%@page import="channel.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
</head>
<body>
<%@ include file="common.jsp" %>


<%
	MemberDto memberdto = (MemberDto) session.getAttribute("loginDto");

	int member_num = memberdto.getMember_num();
	MemberBiz biz = new MemberBiz();	
	
	List<SearchMemberDto> workspacelist = biz.searchMemberList(member_num);
	List<MemberDto> memberlist = biz.selectedMemberList(workspacelist, member_num);
	
	for(MemberDto memdto : memberlist){
%>
		이름 : <%=memdto.getMember_name() %> (<%=(memdto.getMember_statement().equals("1"))?"온라인":"오프라인" %>)<br>
<%						
	}
%>	
	
	
	


</body>
</html>