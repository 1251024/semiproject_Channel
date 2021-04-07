// 워크스페이스 리스트 호출해주는 함수 실행
$(function(){

	var member_num = $("#member_num").val();
	var member_id = $("#member_id").val();
	var member_name = $("#member_name").val();
	
	// 워크스페이스 생성 모달 안에 맴버넘버 히든 인풋으로 넣어주기
	var res = document.querySelector('#workspaceAddCommand');
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","member_num");
	input.value = member_num;
	res.appendChild(input);
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","member_id");
	input.value = member_id;
	res.appendChild(input);
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","member_name");
	input.value = member_name;
	res.appendChild(input);
	
	if (member_num != null) {
		function  getParameterValues() {
			return "?command=selectMemberWorkSpace&member_num=" + member_num;
		}
		
		$.ajax({
			url:"WorkSpaceController"+getParameterValues(),
			dataType: "json",
			method: "post",
			success:function(data){
				
				var list = data.result;
				if (list != null) {
					for (var i = 0; i < list[0].length; i++) {
						
						var div = document.createElement('div');
						div.setAttribute("class","btn-group btn-block");
						
						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default dropdown-toggle btn-lg");
						button.setAttribute("data-toggle","dropdown");
						button.setAttribute("aria-expanded","false");
						button.innerHTML = list[0][i].workspace_name+"  ";
						
						div.appendChild(button);
						
						var span = document.createElement('span');
						span.setAttribute("class","caret");
						button.appendChild(span);
						
						var span = document.createElement('span');
						span.setAttribute("class","sr-only");
						span.innerHTML = 'Toggle Dropdown';
						button.appendChild(span);
						div.appendChild(button);
						
						var ul = document.createElement('ul');
						ul.setAttribute("class","dropdown-menu");
						ul.setAttribute("role","menu");
						
						var workspacein = document.createElement('li');
						
						var a = document.createElement('a');
						a.setAttribute("href","ChannelController?command=channelIn&member_num="+list[0][i].member_num+"&workspace_num="+list[0][i].workspace_num);
						a.innerHTML = "워크스페이스 들어가기";
						workspacein.appendChild(a);
						ul.appendChild(workspacein);
						
						var divider = document.createElement('li');
						divider.setAttribute("class","divider");
						ul.appendChild(divider);
						
						var update = document.createElement('li');
						var a = document.createElement('a');
						a.setAttribute("href","javascript:void(0);");
						a.setAttribute("data-toggle","modal");
						a.setAttribute("data-target","#workspaceAdminForm");
						a.setAttribute("onclick","selectWorkspaceMemberList("+list[0][i].workspace_num+","+"'"+list[0][i].workspace_name+"'"+")");
						a.innerHTML = "관리";
						update.appendChild(a);
						ul.appendChild(update);

						var del = document.createElement('li');
						var a = document.createElement('a');
						a.setAttribute("href","javascript:void(0);");
						a.setAttribute("onclick","workspaceDelcon("+list[0][i].workspace_num+")");
						a.innerHTML = "삭제";
						del.appendChild(a);
						ul.appendChild(del);
						div.appendChild(ul);

						$("#workspaceArea").append(div);				
					}
				} else {
					
					var p = document.createElement('p');
					p.innerHTML = "워크스페이스가 없습니다. 새로 워크스페이스를 생성해주세요.";
					$("#workspaceArea").append(p);	
					
				}
					
			},
			error: function(){
				alert("워크스페이스 불러오기 실패")
			}		
		})		
	} else {
		alert("로그인 세션이 만료되었습니다. 다시 로그인해주세요.")
		location.href="member_login.jsp";
	}

})
//워크스페이스 삭제 확인 함수
function workspaceDelcon(wsnum) {
	
	var con = confirm("정말로 채널을 삭제하시겠습니까?");
	var workspace_num = wsnum;
	
	if (con) {
		location.href='WorkSpaceController?command=delWorkSpace&workspace_num='+workspace_num;
	}
}
//워크스페이스 관리 클릭시 해당 워크스페이스의 맴버 목록 호출
function selectWorkspaceMemberList(wsnum, wsname) {

	var workspace_num = wsnum;
	var workspace_name = wsname;
	var member_num = $("#member_num").val();
	
	// 워크스페이스 수정 모달 안에 히든 인풋으로 넣어주기
	
	$("#workspaceAdminCommand").children().remove();
	
	var res = document.querySelector('#workspaceAdminCommand');
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","update_workspace_num");
	input.value = workspace_num;
	res.appendChild(input);
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","update_workspace_name");
	input.value = workspace_name;
	res.appendChild(input);
	
	function getParameterValues() {

		return "?command=selectWorkspaceMemberList&workspace_num="
				+ workspace_num + "&member_num=" + member_num;
	}

	$.ajax({
		url : "WorkSpaceController" + getParameterValues(),
		dataType : "json",
		method : "post",
		success : function(data) {
			var list = data.result;
			console.log(list);
			$("#workspaceMemberList").children().remove();
			$("#workspaceInviteList").children().remove();

			var ul = document.createElement('ul');
			ul.setAttribute("class", "list-group");
			document.getElementById('workspaceMemberList').appendChild(ul);

			for (var i = 0; i < list[0].length; i++) {
				var member_num = list[0][i].member_num;
				var member_id = list[0][i].member_id;
				var member_name = list[0][i].member_name;

				var input = document.createElement('input');
				input.id = "member_num" + (i + 1);
				input.type = "hidden";
				input.value = member_num;
				document.getElementById('workspaceMemberList').appendChild(
						input);

				var li = document.createElement('li');
				li.setAttribute("class", "list-group-item");

				var div = document.createElement('div');
				div.setAttribute("class", "input-group");

				var span = document.createElement('span');
				span.setAttribute("class", "input-group-addon");

				var input = document.createElement('input');
				input.id = "member_id" + (i + 1);
				input.type = "checkbox";
				input.name = "banishWorkspaceChk";
				input.value = member_id;
				span.appendChild(input);

				var input = document.createElement('input');
				input.id = "member_name" + (i + 1);
				input.type = "hidden";
				input.value = member_name;
				span.appendChild(input);

				div.appendChild(span);

				var input = document.createElement('input');

				input.type = "text";
				input.value = "이름(ID) : " + member_name + " ( " + member_id
						+ " ) ";
				input.setAttribute("readonly", "readonly");
				input.setAttribute("class", "form-control");
				input.style["background-color"] = "white";
				div.appendChild(input);
				li.appendChild(div);

				document.getElementById('workspaceMemberList').appendChild(li);
			}
		},
		error : function() {
			alert("통신 실패")
		}

	})

}
//워크스페이스 관리에서 초대하기 누르면 밑에 초대맴버 목록 노출
function selectWorkspaceInviteList() {
	
	var workspace_num = $("input[name=update_workspace_num").val();

	function getParameterValues() {

		return "?command=selectWorkspaceInviteList&workspace_num=" + workspace_num;
	}

	$.ajax({
		url : "WorkSpaceController" + getParameterValues(),
		dataType : "json",
		method : "post",
		success : function(data) {
			var list = data.result;
			console.log(data);
			$("#workspaceInviteList").children().remove();
			var ul = document.createElement('ul');
			ul.setAttribute("class", "list-group");
			document.getElementById('workspaceInviteList').appendChild(ul);

			for (var i = 0; i < list[0].length; i++) {
				var member_num = list[0][i].member_num;
				var member_id = list[0][i].member_id;
				var member_name = list[0][i].member_name;

				var input = document.createElement('input');
				input.id = "member_num" + (i + 1);
				input.type = "hidden";
				input.value = member_num;
				document.getElementById('workspaceInviteList').appendChild(
						input);

				var li = document.createElement('li');
				li.setAttribute("class", "list-group-item");

				var div = document.createElement('div');
				div.setAttribute("class", "input-group");

				var span = document.createElement('span');
				span.setAttribute("class", "input-group-addon");

				var input = document.createElement('input');
				input.id = "member_id" + (i + 1);
				input.type = "checkbox";
				input.name = "inviteWorkspaceChk";
				input.value = member_id;
				span.appendChild(input);

				var input = document.createElement('input');
				input.id = "member_name" + (i + 1);
				input.type = "hidden";
				input.value = member_name;
				span.appendChild(input);

				div.appendChild(span);

				var input = document.createElement('input');

				input.type = "text";
				input.value = "이름(ID) : " + member_name + " ( " + member_id
						+ " ) ";
				input.setAttribute("readonly", "readonly");
				input.setAttribute("class", "form-control");
				input.style["background-color"] = "white";
				div.appendChild(input);
				li.appendChild(div);

				document.getElementById('workspaceInviteList').appendChild(li);
			}
		},
		error : function() {
			alert("통신 실패")
		}

	})

}
//체크한 사람들 초대하는 함수
function inviteWorkspace() {
	$("#workspaceMemberList").children().remove();

	var chks = document.getElementsByName("inviteWorkspaceChk");
	var workspace_num = $("input[name=update_workspace_num").val();
	var workspace_name = $("input[name=update_workspace_name").val();

	var param = new Array();

	var count = 0;
	for (var i = 0; i < chks.length; i++) {
		if (chks[i].checked) {
			param[count] = new Array();
			param[count][0] = workspace_num;
			param[count][1] = workspace_name;
			param[count][2] = $("#member_num" + (i + 1)).val();
			param[count][3] = $("#member_id" + (i + 1)).val();
			param[count][4] = $("#member_name" + (i + 1)).val();
			count++;
		}
	}
	console.log(param);
	$.ajax({
		url : "WorkSpaceController?command=inviteWorkspace",
		data : {
			"inviteMember" : param,
			"count" : count
		},
		method : "post",
		traditional : true,
		dataType : "text",
		success : function(msg) {
			alert(msg);
			$("#adminWorkspaceCancel").bind("click");
			$("#adminWorkspaceCancel").trigger("click");
			$("#workspaceMemberList").children().remove();
			$("#workspaceInviteList").children().remove();
			$("#workspaceAdminCommand").children().remove();
			
			

		},
		error : function() {
			alert("통신 실패");
		}
	})

}
//체크한 사람들 삭제하는 함수
function banishWorkspace() {

	var con = confirm("정말로 추방하시겠습니까??");

	if (con) {
		$("#workspaceInviteList").children().remove();
		var chks = document.getElementsByName("banishWorkspaceChk");
		var workspace_num = $("input[name=update_workspace_num").val();

		var param = new Array();

		var count = 0;
		for (var i = 0; i < chks.length; i++) {
			if (chks[i].checked) {
				param[count] = new Array();
				param[count][0] = workspace_num;
				param[count][1] = $("#member_num" + (i + 1)).val();
				count++;
			}
		}
		console.log(param);
		$.ajax({
			url : "WorkSpaceController?command=banishWorkspace",
			data : {
				"banishMember" : param,
				"count" : count
			},
			method : "post",
			traditional : true,
			dataType : "text",
			success : function(msg) {
				alert(msg);
				$("#adminWorkspaceCancel").bind("click");
				$("#adminWorkspaceCancel").trigger("click");

			},
			error : function() {
				alert("통신 실패");
			}
		})
	}
}