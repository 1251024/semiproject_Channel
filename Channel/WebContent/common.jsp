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
                    <li><a style="font-size: 1.5rem;" href="">&#128100; my</a></li> 
                </ul>
                <form class="navbar-form navbar-right">
                    <input type="text" class="form-control" placeholder="Search...">
                </form>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <div id="side-navbar" class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a href="">Main</a></li>
                    <li>
                    <div class="panel-heading" role="tab" id="collapseListWorkspace">
							<h4 class="panel-title" id="-collapsible-list-group-">
								<a class="collapsed" data-toggle="collapse" href="#workspacelist"
									aria-expanded="false" aria-controls="workspacelist">Workspace </a>
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
						<a class="collapsed" data-toggle="collapse" href="#channellist"
						aria-expanded="false" aria-controls="channellist">Channel </a>
						</h4>
					</div>
					<div id="channellist" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="collapseListChannel"
							aria-expanded="false" style="height: 0px;">
						<ul class="list-group" id="ChannelArea">
							<li class="list-group-item">
								<button type="button" class="btn btn-default btn-lg btn-block"
						data-toggle="modal" data-target="#addChannelForm">새 채널 생성</button>
							</li>
						</ul>
					</div>	
                    </li>
                    <li>
					<div class="panel-heading" role="tab" id="collapseListChannel">
							<h4 class="panel-title" id="-collapsible-list-group-">
								<a class="collapsed" data-toggle="collapse" href="#messagelist"
									aria-expanded="false" aria-controls="messagelist">Message </a>
							</h4>
						</div>
						<div id="messagelist" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="collapseListChannel"
							aria-expanded="false" style="height: 0px;">
						<ul class="list-group" id="MessageArea">
							<li class="list-group-item">
								<button type="button" class="btn btn-default btn-lg btn-block"
								data-toggle="modal" data-target="#addMessageForm" onclick="selectInviteMessageMemberList();">새 메세지</button>
							</li>
						</ul>
							
						</div>
					</li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="">지도</a></li>
                    <li><a href="">날씨</a></li>
                    <li><a href="">covid</a></li>
                    <li><a href="">그림판</a></li>
                    <li><a href="">번역</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="">화상통화</a></li>
                    <li><a href="">회원정보 수정</a></li>
                    <li><a href="">LogOut</a></li>
                </ul>
            </div>
            
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                
                
                

