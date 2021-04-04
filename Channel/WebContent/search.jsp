<%@page import="channel.member.biz.MemberBiz"%>
<%@page import="channel.member.dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="channel.member.dto.SearchDto"%>
<%@page import="java.util.List"%>
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
	String search = request.getParameter("search");
	MemberDto memdto = (MemberDto) session.getAttribute("loginDto");
			
	//일단 챗 리스트 가져오기.
	MemberBiz biz = new MemberBiz();
	// 내 번호가 있는 채널의 채팅내역만 가져오기.
	List<SearchDto> searchList = biz.allChatList(memdto.getMember_num());
	
	List<SearchDto> resultList = new ArrayList<SearchDto>();
	for(SearchDto sdto : searchList){
		if(sdto.getChat_content().contains(search)){
			resultList.add(sdto);
		}	
	}	
	
	if(resultList.size() == 0){
%>
	<h2>-----검색된 내용이 없습니다-----</h2>
<%
	} else {
		for(SearchDto rdto : resultList){	
%>
	<a href=""><%=rdto.getChat_content() %></a>
<%	
		}
	}
%>





</body>
</html>