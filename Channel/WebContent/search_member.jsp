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
<style>

	#member_title{
		text-align: center;
		
	}
	.online {		
		background-color: skyblue;
	}
	.offline {		
		background-color: white;
	}
	#member_onoff_box {
		text-align: center;
		overflow:auto;
	}
	.member_onoff_name {
		font-size: 1.8rem;
		font-family: 고딕;
		color: #555;
	}
	.member_online{
		color: red;
		font-size : 1.2rem;
	}
	.member_offline{
		color: gray;
		font-size: 1.1rem;
	}

</style>

</head>
<body>
<%@ include file="common.jsp" %>

<br>
<div class ="col-md-12" id="member_title">
	<h2>On/Off Line</h2>
</div>
<br>
<div class="col-md-12"></div>
<br><br>
<div class="col-xs-3"></div>
<div class="col-xs-6" id="member_onoff_box">
<%
	MemberDto memberdto = (MemberDto) session.getAttribute("loginDto");

	int member_num = memberdto.getMember_num();
	MemberBiz biz = new MemberBiz();	
	
	List<SearchMemberDto> workspacelist = biz.searchMemberList(member_num);
	List<MemberDto> memberlist = biz.selectedMemberList(workspacelist, member_num);
	
	for(MemberDto memdto : memberlist){
%>
		<div <%=(memdto.getMember_statement().equals("1"))?"class='online'":"class='offline'" %>>
		<span class="member_onoff_name"><%=memdto.getMember_name() %></span> &nbsp;
		 <%=(memdto.getMember_statement().equals("1"))?"<span class='member_online'>(온라인)</span>":"<span class='member_offline'>(오프라인)</span>" %>
		</div>
<%						
	}
%>	
</div>
<div class="col-xs-3"></div>
	


</body>
</html>