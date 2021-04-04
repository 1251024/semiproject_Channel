<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %> 

<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/member_statement.js"></script>
<script>
	$(document).ready(function(){
		$("#memberlist").mouseover(function(){	
			$.ajax({
				url : "SearchController?command=memberlist",
				type : "get",
				success : function(data){
					var jsonObj = JSON.parse(data);
					
					var $div = $("<div>");
					$div.append("<br/>")
					for(i = 0; i<3; i++){
						$div.append(" 이름 : ");
						$div.append(jsonObj[i].member_name);
						$div.append(" (");
						$div.append((jsonObj[i].member_statement == '1')?"온라인":"오프라인");
						$div.append(")");
						$div.append("<br/>")
					}
					$div.append(".....")
					$div.css("color", "black");
					$div.css("backgroundcolor", "gray");
					$div.css("position", "fixed");
					$div.css("width", "200px");
					$div.css("top", "30px");
					$div.css("right", "10px");
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

    </style>

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
                <a class="navbar-brand">Channel</a>
                
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a style="font-size: 1.5rem;" href="">&#128365; alarm</a></li>
                    <li><a style="font-size: 1.5rem;" id="memberlist" href="search_member.jsp">&#128100; my</a></li> 
                </ul>

               <form id="searchFrame" class="navbar-form navbar-right">
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
                    <li><a href="">Main</a></li>
                    <li><a href="">People</a></li>
                    <li><a href="">Channel</a></li>
                    <li><a href="">Chat</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="">지도</a></li>
                    <li><a href="">날씨</a></li>
                    <li><a href="covid.jsp">covid</a></li>
                    <li><a href="paint.jsp">그림판</a></li>
                    <li><a href="trans.jsp">번역</a></li>

                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="member_update.jsp">회원정보 수정</a></li>
                    <li><a href="MemberController?command=logout">LogOut</a></li>
                </ul>
            </div>
            
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                
                
                

