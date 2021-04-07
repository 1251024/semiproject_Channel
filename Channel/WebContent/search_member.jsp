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
		color:black;
		text-shadow: none;	
		transition : all 3s linear;	
	}
	#member_title:hover{
		color:white;
		text-shadow: 1px 1px black;		
	}	
	
	#member_onoff_box {
		overflow:auto;
	}
	
	#table_head {
		font-size: 1.5rem;
		font-weight: bold;
		height: 50px;
		margin-top: 50px;
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
<div class ="col-md-12 text-center" id="member_title">
	<h2>On/Off Line</h2>
</div>
<br><br>
<div class="col-xs-2"></div>
<div class="col-xs-8 text-center" id="member_onoff_box">
<table>
<%
	MemberDto memberdto = (MemberDto) session.getAttribute("loginDto");

	int member_num = memberdto.getMember_num();
	MemberBiz biz = new MemberBiz();	
	
	List<SearchMemberDto> workspacelist = biz.searchMemberList(member_num);
	List<MemberDto> memberlist = biz.selectedMemberList(workspacelist, member_num);
	
	if(memberlist != null){
%>
		<col width="150px">
		<col width="300px">
		<col width="300px">
		<tr id="table_head">
			<td>접속</td>
			<td>이름</td>
			<td>on/off Line</td>
		</tr>
<%		
		for(MemberDto memdto : memberlist){
			if(memdto.getMember_statement().equals("1")){
				//온라인
%>
				<tr>
					<td class="col-xs-1 text-center"><div class="glyphicon glyphicon-user"></div></td>
					<td class ="member_onoff_name"><%=memdto.getMember_name() %></td>
					<td class ="member_online">온라인</td>			
				</tr>		
<%			
			} else {
				//오프라인
%>
				<tr>
					<td>--</td>
					<td class="member_onoff_name"><%=memdto.getMember_name() %></td>
					<td class="member_offline">오프라인</td>			
				</tr>		
<%
			}								
		}
	}
%>	
</table>
</div>
<div class="col-xs-2"></div>
	


</body>
</html>