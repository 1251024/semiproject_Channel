<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %> 

<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/member_statement.js"></script>

<!-- include summernote css/js -->
<link href="resources/summernote/summernote.css" rel="stylesheet">
<script src="resources/summernote/summernote.js"></script>

<script>

	$(document).ready(function() {
		$.ajax({
			type: "get",
			url: "AlarmController?command=alarmNum",
			async: true,	
			
			success: function(result){
				if(result>=1){
					showUnread(result);
				} else {
					showUnread('');
				}
			}
		});
	});
	
	function showUnread(result){
		$('#unread').html(result);
	}
	
	

	
	
	
	$(document).ready(function() {
		$("#alarm").mouseover(function(){
			$.ajax({
				type: "get",
				url: "AlarmController?command=chatAlarmList",
				async: true,		
				
				success: function(data){
					var jsonObj = JSON.parse(data);
					
					var $div = $("<div>");
					//$div.append("<br/>")
					
					$div.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#128233;")
					$div.append(jsonObj[0].member_name);
					$div.append(" : ");
					$div.append(jsonObj[0].chat_content);
					//$div.append("<br/>")
					$div.append(jsonObj[1].member_name);
					$div.append(" : ");
					$div.append(jsonObj[1].chat_content);
					//$div.append("<br/>")
					$div.append(jsonObj[2].member_name);
					$div.append(" : ");
					$div.append(jsonObj[2].chat_content);
					/*
					$div.append(jsonObj[3].member_id);
					$div.append(" : ");
					$div.append(jsonObj[3].chat_content);
					//$div.append(jsonObj[4].member_id);
					//$div.append(" : ");
					//$div.append(jsonObj[4].chat_content);
					
					$div.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#128233;")
					$div.append("<br/>")
					for(i = 0; i<3; i++){
						$div.append(jsonObj[i].member_id);
						$div.append(" : ");
						$div.append(jsonObj[i].chat_content);
						//$div.append("<br/>")
					}
					*/
					$div.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...")
					$div.css("color", "black");
					$div.css("backgroundcolor", "gray");
					$div.css("position", "fixed");
					$div.css("width", "250px");
					$div.css("top", "50px");
					$div.css("right", "0px");
					$div.addClass("alarmclass");
					$("#alarm").after($div);
				}
			});
		});
	
	
		$("#alarm").mouseleave(function(){
			$(".alarmclass").remove();
		});
		
	});			
	






	$(document).ready(function(){
		$("#memberlist").mouseover(function(){	
			$.ajax({
				url : "SearchController?command=memberlist",
				type : "get",
				success : function(data){
					var jsonObj = JSON.parse(data);
					
					var $div = $("<div>");
					$div.append("<br/>")
					
					$div.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#128100;")
					$div.append("<br/>")
					
					for(i = 0; i<3; i++){
						$div.append(" 이름 : ");
						$div.append(jsonObj[i].member_name);
						$div.append(" (");
						$div.append((jsonObj[i].member_statement == '1')?"온라인":"오프라인");
						$div.append(")");
						$div.append("<br/>")
					}
					$div.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...")
					$div.css("color", "black");
					$div.css("backgroundcolor", "gray");
					$div.css("position", "fixed");
					$div.css("width", "250px");
					$div.css("top", "50px");
					$div.css("right", "0px");
					$div.addClass("memberlistclass");
					$("#memberlist").after($div);
				},
				error : function(){
					$(".memberlistclass").remove();
				}				
			});			
		});
		$("#memberlist").mouseleave(function(){
			$(".memberlistclass").remove();
		});
		

		$("#searchTag").click(function(){
			var search = $("#searchBox").val();
			if(search == null || search.trim()==''){
				alert("검색어를 입력해 주세요");
			} else {
				location.href="search.jsp?search="+search;
			}			
		});
		
		
		
	});

</script>
<style>
	.memberlistclass{
			background-color: #f6f9fc;	
			font-family: sans-serif;
			font-weight: bold;
			padding: 0px 0px 28px 15px; 
			box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08);
		}

		.alarmclass{
			background-color: #f6f9fc;	
			font-family: sans-serif;
			font-weight: bold;
			padding: 15px 10px 10px 15px; 
			box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08);
		}
		
        body {
            padding-top: 50px;
        }
        .sub-header {
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }
        .navbar-fixed-top {
            border: 0;
        }
        .sidebar {
            display: none;
        }
        @media (min-width: 768px) {
            .sidebar {
                position: fixed;
                top: 51px;
                bottom: 0;
                left: 0;
                z-index: 1000;
                display: block;
                padding: 20px;
                overflow-x: hidden;
                overflow-y: auto;
                background-color: #f5f5f5;
                border-right: 1px solid #eee;
            }
        }
        .nav-sidebar {
            margin-right: -21px;
            margin-bottom: 20px;
            margin-left: -20px;
        }
        .nav-sidebar>li>a {
            padding-right: 20px;
            padding-left: 20px;
        }
        .nav-sidebar>.active>a,
        .nav-sidebar>.active>a:hover,
        .nav-sidebar>.active>a:focus {
            color: #fff;
            background-color: #428bca;
        }
        .main {
            padding: 20px;
        }
        @media (min-width: 768px) {
            .main {
                padding-right: 40px;
                padding-left: 40px;
            }
        }
        .main .page-header {
            margin-top: 0;
        }
        .placeholders {
            margin-bottom: 30px;
            text-align: center;
        }
        .placeholders h4 {
            margin-bottom: 0;
        }
        .placeholder {
            margin-bottom: 20px;
        }
        .placeholder img {
            display: inline-block;
            border-radius: 50%;
        }

        #side-navbar a {
            color:black;
            font-size: 1.5rem;
        }
        #searchFrame{
        	style="background-color:#222;" 
        }
        #searchTag {
        	width: 45px;
        	height: 28px;
        	background-color: #555;
        	color: white;
        	border: 0px;
        	font-size: 1.4rem;
        	border-radius: 4px;
        }
        

</style>

	<input type="hidden" id="member_num" value="${loginDto.member_num }">
	<input type="hidden" id="member_id" value="${loginDto.member_id }">
	<input type="hidden" id="member_name" value="${loginDto.member_name }">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Top Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <button style="float:left;" type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#side-navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Side Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Channel</a>
                
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a id="alarm" style="font-size: 1.5rem;" href="">&#128365; alarm</a></li>
                    <li><span id="unread" class="label label-info"></span></li>
                    <li><a style="font-size: 1.5rem;" id="memberlist" href="search_member.jsp">&#128100; my</a></li> 
                </ul>


               <form style="background-color:#222;" id="searchFrame" class="navbar-form navbar-right">
                    <input type="text" id="searchBox" class="form-control" placeholder="Search...">
                    <input type="button" id="searchTag" value="검색" />
               </form>

            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <div id="side-navbar" class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li>
                    <div class="panel-heading" role="tab" id="collapseListWorkspace">
							<h4 class="panel-title" id="-collapsible-list-group-">
							<span class="glyphicon glyphicon-folder-close" aria-hidden="true" id="workspaceglyphicon"></span>
								<a class="collapsed" data-toggle="collapse" href="#workspacelist"
									aria-expanded="false" aria-controls="workspacelist">&nbsp;&nbsp;Workspace </a>
							</h4>
					</div>
                    <div id="workspacelist" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="collapseListWorkspace"
							aria-expanded="false" style="height: 0px;">
							<ul class="list-group">
								<li class="list-group-item">
									<button type="button" class="btn btn-default btn-lg btn-block"
										onclick="location.href='workspace.jsp'">Change Workspace</button>
								</li>
							</ul>
					</div>
                    </li>
                    <li>
                    <div class="panel-heading" role="tab" id="collapseListChannel">
						<h4 class="panel-title" id="-collapsible-list-group-">
						<span class="glyphicon glyphicon-comment" aria-hidden="true"></span>
						<a class="collapsed" data-toggle="collapse" href="#channellist"
						aria-expanded="false" aria-controls="channellist">&nbsp;&nbsp;Channel </a>
						</h4>
					</div>
					<div id="channellist" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="collapseListChannel"
							aria-expanded="false" style="height: 0px;">
						<ul class="list-group" id="ChannelArea">

						</ul>
					</div>	
                    </li>
                    <li>
					<div class="panel-heading" role="tab" id="collapseListChannel">
							<h4 class="panel-title" id="-collapsible-list-group-">
							<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
								<a class="collapsed" data-toggle="collapse" href="#messagelist"
									aria-expanded="false" aria-controls="messagelist">&nbsp;&nbsp;Message </a>
							</h4>
						</div>
						<div id="messagelist" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="collapseListChannel"
							aria-expanded="false" style="height: 0px;">
						<ul class="list-group" id="MessageArea">

						</ul>
							
						</div>
					</li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="calendar.jsp"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>&nbsp;&nbsp;Calendar</a></li>
                    <li><a href="weather.jsp"><span class="glyphicon glyphicon-certificate" aria-hidden="true"></span>&nbsp;&nbsp;날씨</a></li>

                    <li><a href="covid.jsp"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span>&nbsp;&nbsp;covid</a></li>
                    <li><a href="paint.jsp"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span>&nbsp;&nbsp;그림판</a></li>
                    <li><a href="trans.jsp"><span class="glyphicon glyphicon-text-background" aria-hidden="true"></span>&nbsp;&nbsp;번역</a></li>


                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="member_update.jsp"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;회원정보 수정</a></li>
                    <li><a href="MemberController?command=logout"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;&nbsp;LogOut</a></li>
                </ul>
            </div>
            
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                
                
                

