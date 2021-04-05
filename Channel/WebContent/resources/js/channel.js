$(function(){
	
	var member_num = $("#member_num").val();
	var workspace_num = $("#workspace_num").val();
	var member_id = $("#member_id").val();
	var member_name = $("#member_name").val();
	
	// 채널 생성 모달 안에 맴버넘버, 워크스페이스넘버 히든 인풋으로 넣어주기
	var query = document.querySelector('#channelCommand');
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","member_num");
	input.value = member_num;
	query.appendChild(input);
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","workspace_num");
	input.value = workspace_num;
	query.appendChild(input);
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","member_id");
	input.value = member_id;
	query.appendChild(input);
	
	var input = document.createElement("input");
	input.type = "hidden";
	input.setAttribute("name","member_name");
	input.value = member_name;
	query.appendChild(input);
	
	
	//채널 리스트 호출해주는 함수 실행
	if (member_num != null) {
		function  getParameterValues() {
			return "?command=selectMemberChannel&member_num=" + member_num +"&workspace_num=" + workspace_num;
		}
		
		$.ajax({
			url:"ChannelController"+getParameterValues(),
			dataType: "json",
			method: "post",
			success:function(data){
				
				var list = data.result;
				if (list != null) {
					
					for (var i = 0; i < list[0].length; i++) {
						
						var query = document.querySelector('#ChannelArea');
						
						var li = document.createElement('li');
						li.setAttribute("class","list-group-item");
						
						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default");
						button.setAttribute("onclick","selectChatList("+list[0][i].channel_num+","+member_num+");");
						button.innerHTML = list[0][i].channel_name;
						li.appendChild(button);

						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default btn-xs");
						button.setAttribute("data-toggle","modal");
						button.setAttribute("data-target","#adminChannelForm");
						button.setAttribute("onclick",
						"channelAdmin("
						+list[0][i].channel_num+","
						+member_num+","
						+"'"+list[0][i].channel_name+"'"+","
						+"'"+list[0][i].channel_information+"'"+");");
						button.innerHTML = "관리";
						li.appendChild(button);
						
						var button = document.createElement('button');
						button.setAttribute("class","btn btn-default btn-xs");
						button.setAttribute("onclick","channelDelcon("+list[0][i].channel_num+","+member_num+")");
						button.innerHTML = "삭제";
						li.appendChild(button);

						query.append(li);
						
					}
				} else {
					
					var p = document.createElement('p');
					p.innerHTML = "채널이 없습니다. 새로 채널을 생성해주세요.";
					$("#ChannelArea").append(p);
					
				}
					
			},
			error: function(){
				alert("채널 불러오기 실패")
			}		
		})		
	} else {
		alert("로그인 세션이 만료되었습니다. 다시 로그인해주세요.")
		location.href="member_login.jsp";
	}
})
// 채널 수정 시
function channelAdmin(chnum, memnum, chname, chinfo) {
	
	var channel_num = chnum;
	var member_num = memnum;
	var channel_name = chname;
	var channel_information = chinfo;
	$("#update_channel_num").val(channel_num);
	$("#update_member_num").val(member_num);
	$("#update_channel_name").val(channel_name);
	$("#update_channel_information").val(channel_information);
	
	function getParameterValues() {

		return "?command=selectChannelMemberList&channel_num="
				+ channel_num + "&member_num=" + member_num;
	}
	
	$.ajax({
		url : "ChannelController" + getParameterValues(),
		dataType : "json",
		method : "post",
		success : function(data) {
			var list = data.result;
			console.log(list);
			$("#channelMemberList").children().remove();
			$("#channelInviteList").children().remove();

			var ul = document.createElement('ul');
			ul.setAttribute("class", "list-group");
			document.getElementById('channelMemberList').appendChild(ul);

			for (var i = 0; i < list[0].length; i++) {
				var member_num = list[0][i].member_num;
				var member_id = list[0][i].member_id;
				var member_name = list[0][i].member_name;

				var input = document.createElement('input');
				input.id = "member_num" + (i + 1);
				input.type = "hidden";
				input.value = member_num;
				document.getElementById('channelMemberList').appendChild(
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
				input.name = "banishChannelChk";
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

				document.getElementById('channelMemberList').appendChild(li);
			}
		},
		error : function() {
			alert("통신 실패")
		}
	})
}
// 채널 관리에서 초대하기 누르면 밑에 초대맴버 목록 노출
function selectChannelInviteList() {

	var channel_num = $("#update_channel_num").val();
	var workspace_num = $("#workspace_num").val();

	function getParameterValues() {

		return "?command=selectChannelInviteList&channel_num=" + channel_num
				+ "&workspace_num=" + workspace_num;
	}

	$.ajax({
		url : "ChannelController" + getParameterValues(),
		dataType : "json",
		method : "post",
		success : function(data) {
						
			var list = data.result;

			$("#channelInviteList").children().remove();

			var ul = document.createElement('ul');
			ul.setAttribute("class", "list-group");
			document.getElementById('channelInviteList').appendChild(ul);

			for (var i = 0; i < list[0].length; i++) {
				var member_num = list[0][i].member_num;
				var member_id = list[0][i].member_id;
				var member_name = list[0][i].member_name;

				var li = document.createElement('li');
				li.setAttribute("class", "list-group-item");

				var div = document.createElement('div');
				div.setAttribute("class", "input-group");

				var span = document.createElement('span');
				span.setAttribute("class", "input-group-addon");

				var input = document.createElement('input');
				input.id = "member_num" + (i + 1);
				input.type = "hidden";
				input.value = member_num;
				span.appendChild(input);
				
				var input = document.createElement('input');
				input.id = "member_id" + (i + 1);
				input.type = "checkbox";
				input.name = "inviteChannelChk";
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

				document.getElementById('channelInviteList').appendChild(li);
			}
		},
		error : function() {
			alert("통신 실패")
		}
	})
}
// 체크한 사람들 초대하는 함수
function inviteChannel() {
	$("#channelMemberList").children().remove();
	
	var chks = document.getElementsByName("inviteChannelChk");
	var channel_num = $("#update_channel_num").val();

	var param = new Array();

	var count = 0;
	for (var i = 0; i < chks.length; i++) {
		if (chks[i].checked) {
			param[count] = new Array();
			param[count][0] = channel_num;
			param[count][1] = $("#member_num" + (i + 1)).val();
			param[count][2] = $("#member_id" + (i + 1)).val();
			param[count][3] = $("#member_name" + (i + 1)).val();
			count++;
		}
	}
	console.log(param);
	$.ajax({
		url : "ChannelController?command=inviteChannel",
		data : {
			"inviteMember" : param,
			"count" : count
		},
		method : "post",
		traditional : true,
		dataType : "text",
		success : function(msg) {
			alert(msg);
			$("#adminChannelCancel").bind("click");
			$("#adminChannelCancel").trigger("click");
			$("#channelMemberList").children().remove();
			$("#channelInviteList").children().remove();
			
			
			
		},
		error : function() {
			alert("통신 실패");
		}
	})

}
// 체크한 사람들 삭제하는 함수
function banishChannel() {

	var con = confirm("정말로 추방하시겠습니까??");

	if (con) {
		$("#channelInviteList").children().remove();
		var chks = document.getElementsByName("banishChannelChk");
		var channel_num = $("#update_channel_num").val();

		var param = new Array();

		var count = 0;
		for (var i = 0; i < chks.length; i++) {
			if (chks[i].checked) {
				param[count] = new Array();
				param[count][0] = channel_num;
				param[count][1] = $("#member_num" + (i + 1)).val();
				count++;
			}
		}
		console.log(param);
		$.ajax({
			url : "ChannelController?command=banishChannel",
			data : {
				"banishMember" : param,
				"count" : count
			},
			method : "post",
			traditional : true,
			dataType : "text",
			success : function(msg) {
				alert(msg);
				$("#adminChannelCancel").bind("click");
				$("#adminChannelCancel").trigger("click");

			},
			error : function(msg) {
				alert(msg);
			}
		})
	}
}
// 채널삭제 확인
function channelDelcon(chnum, memnum) {
	var con = confirm("해당 채널을 삭제하시겠습니까?");

	if (con) {
		
		var channel_num = chnum;
		var member_num = memnum;
		var workspace_num = $("#workspace_num").val();

		function getParameterValues() {

			return "?command=deleteChannel&channel_num=" + channel_num
					+ "&member_num=" + member_num;
		}

		$.ajax({
			url : "ChannelController" + getParameterValues(),
			dataType : "text",
			method : "post",
			success : function(msg) {
				alert(msg)
				location.href = 'ChannelController?command=channelIn&member_num='+member_num+"&workspace_num="+workspace_num;
			},
			error : function() {
				alert("통신 실패")
			}

		})
	}

}
// 채팅방 DB 호출 ajax
function selectChatList(chnum, memnum) {
	$('#chatarea').children().remove();
	re_send = "";
	var member_num = memnum;
	var channel_num = chnum;
	
	function  getParameterValues() {
		return "?command=selectChatList&channel_num=" + channel_num;
	}
	
	$.ajax({
		url : "ChatController"+getParameterValues(),
		dataType : "json",
		method : "post",
		success : function(data) {
			var list = data.result;
			var firstname = "";
			for (var i = 0; i < list[0].length; i++) {
				if (list[0][i].member_num == member_num) {
					var query = document.querySelector('#chatarea');
					if (list[0][i].member_name != firstname) {
						var who = document.createElement('div');
						// who.style["float"]="left";
						who.style["display"] = "inline-block";
						who.style["font-weight"] = "bold";
						who.style["font-size"] = "14px";
						who.style["padding-top"] = "5px;"
						who.style["padding-bottom"] = "2px;"
						who.style["padding-left"] = "10px;"
						who.append(list[0][i].member_name);
						
							var icon = document.createElement('span');
								icon.setAttribute("class",
										"glyphicon glyphicon-user");
								icon.setAttribute("aria-hidden", "true");
								icon.style["color"] = "gold";
								icon.style["width"] = "15px;";
								icon.style["height"] = "15px;";

								query.appendChild(icon);
								query.appendChild(who);

								var clear = document.createElement('div');
								clear.style["clear"] = "both";
								query.appendChild(clear);

								firstname = list[0][i].member_name;

							}

							var div = document.createElement('div');
							// div.style["float"]="left";
							div.style["display"] = "block";
							// div.style["font-weight"]="bold";
							div.style["color"] = "#1D1C1D";
							div.style["padding-left"] = "10px";
							div.style["padding-top"] = "3px;"
							div.style["padding-bottom"] = "3px;"
							div.innerHTML = list[0][i].chat_content;
							
							div.setAttribute("id", list[0][i].chat_num);
														
							query.appendChild(div);

							var clear = document.createElement('div');
							clear.style["clear"] = "both";
							query.appendChild(clear);

						} else {
							var query = document.querySelector('#chatarea');
							if (list[0][i].member_name != firstname) {
								var who = document.createElement('div');
								// who.style["float"]="right";
								who.style["display"] = "inline-block";
								who.style["font-weight"] = "bold";
								who.style["font-size"] = "14px";
								who.style["padding-top"] = "5px;"
								who.style["padding-bottom"] = "2px;"
								who.style["padding-left"] = "10px;"
								who.innerHTML = list[0][i].member_name;

								var icon = document.createElement('span');
								// icon.style["float"]="right";
								icon.setAttribute("class",
										"glyphicon glyphicon-user");
								icon.setAttribute("aria-hidden", "true");
								icon.style["color"] = "gray";
								icon.style["width"] = "15px;";
								icon.style["height"] = "15px;";

								query.appendChild(icon);
								query.appendChild(who);


								var clear = document.createElement('div');
								clear.style["clear"] = "both";
								query.appendChild(clear);

								firstname = list[0][i].member_name;

							}

							var div = document.createElement('div');
							// div.style["float"]="right";
							div.style["display"] = "block";
							// div.style["font-weight"]="bold";
							div.style["color"] = "#1D1C1D";
							div.style["padding-left"] = "10px";
							div.style["padding-top"] = "3px;"
							div.style["padding-bottom"] = "3px;"
							div.innerHTML = list[0][i].chat_content;
								
							div.setAttribute("id", list[0][i].chat_num);
							
							query.appendChild(div);

							var clear = document.createElement('div');
							clear.style["clear"] = "both";
							query.appendChild(clear);

						}

					}

					var div = document.createElement('div');
					div.style["clear"] = "both";
					div.style["font-weight"] = "bold";
					div.style["color"] = "#1D1C1D";
					div.style["background"] = "lightgray";
					div.style["display"] = "block";
					div.style["text-align"] = "center";
					div.innerHTML = "------------------------------이전 채팅은 여기까지 입니다.------------------------------";

					query.appendChild(div);

					chatarea.scrollTop = chatarea.scrollHeight;
				},
				error : function() {
					alert("통신 실패")
				}
			})
	$("#roominfo").children().remove();
	channelInfo(chnum);
}
// 채널정보 불러오는 ajax
function channelInfo(chnum) {

	$.ajax({
		url : "ChannelController?command=selectOneChannel&channel_num=" + chnum,
		dataType : "text",
		method : "post",
		success : function(data) {
			var res = data.split("|\\|");
			
			var query = document.querySelector('#roominfo');

			var channel_num = res[0];
			var workspace_num = res[1];
			var member_num = res[2];
			var channel_name = res[3];
			var channel_info = res[4];
			var channel_regdate = res[5];
			
			var input = document.createElement('input');
			input.id = "channel_num";
			input.type = "hidden";
			input.value = channel_num;
			query.appendChild(input);

			var span = document.createElement('span');
			span.style["font-size"] = "10px";
			span.style["display"] = "inline-block";

			span.innerHTML = 
			" 채널번호 : " + channel_num + " 워크스페이스 번호 : " + workspace_num + "<br>" + " 맴버 번호 : " + member_num + "<br>" + 
			" 채널이름 : " + channel_name + " 채널정보 : " + channel_info + "<br>" + 
			" 채널생성일 : " + channel_regdate;

			query.appendChild(span);
		},
		error : function() {
			alert("통신 실패")
		}
	})
}
