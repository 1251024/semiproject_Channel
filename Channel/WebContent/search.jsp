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
<style>

#search_text_box{
	overflow:auto;
}

#search_header{
	height: 50px;
	margin-top:50px;
	font-size:1.5rem;
	font-weight: bold;
}


.search_content{
	height: 30px;
	font-size: 1.6rem;
}
.search_writer{
	font-size: 1.3rem;
}
.search_date{
	font-size: 1.1rem;
	font-color: gray;
}



.search_result_box{
	float: left;
	height: 30px;
	font-size: 1.8rem;
}

.text_writer_id{
	clear: both;
	font-size: 1.1rem;
	font-color: gray;
}

</style>
</head>
<body>
<%@ include file="common.jsp" %>

<div>
	<h2>Search!</h2>
</div>
<br><br>

<div id="search_text_box">
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
	<h3>----- 검색된 내용이 없습니다 -----</h3>
<%
	} else {
%>
	<table>
		<col width="300px">
		<col width="100px">
		<col width="200px">
		<tr id="search_header">
			<td>검색한 내용</td>
			<td>작성자</td>
			<td>작성일</td>		
		</tr>
<%	
		for(SearchDto rdto : resultList){				
%>	
		<tr>
			<td class="search_content"><a href="channel.jsp?command=search&workspace_num=<%=biz.workspace_num(rdto.getChannel_num()) %>&chat_num=<%=rdto.getChat_num()%>&member_num=<%=memdto.getMember_num() %>&search=y&channel_num=<%=rdto.getChannel_num()%>"><%=rdto.getChat_content() %></a></td>
			<td class="search_writer"><%=rdto.getMember_id() %></td>
			<td class="search_date"><%=rdto.getChat_regdate().toLocaleString() %></td>	
		</tr>	
<%	
		}
	}
%>
	</table>
</div>




</body>
</html>