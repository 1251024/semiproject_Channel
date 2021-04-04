$(function(){
	selectMessageRoom();
})
function selectMessageRoom() {
		
		var member_num = $("#member_num").val();
		var workspace_num = $("#workspace_num").val();
	
		//메세지 리스트 호출해주는 함수 실행
		if (member_num != null) {
			function  getParameterValues() {
				return "?command=selectMessageRoomList&member_num=" + member_num +"&workspace_num=" + workspace_num;
			}
			
			$.ajax({
				url:"ChatController"+getParameterValues(),
				dataType: "json",
				method: "post",
				success:function(data){
					
					var list = data.result;
					console.log(list);
					if (list != null) {
						
						for (var i = 0; i < list[0].length; i++) {
							
							var query = document.querySelector('#MessageArea');
							
							var li = document.createElement('li');
							li.setAttribute("class","list-group-item");
							
							var button = document.createElement('button');
							button.setAttribute("class","btn btn-default");
							button.setAttribute("onclick","callMessageList("+list[0][i].messageroom_num+","+member_num+");");
							button.innerHTML = 
							list[0][i].member_name + "/" + list[0][i].member2_name + "의 채팅룸";
							li.appendChild(button);
							
							var button = document.createElement('button');
							button.setAttribute("class","btn btn-default btn-xs");
							button.setAttribute("onclick","messagedelcon("+list[0][i].messageroom_num+","+member_num+");");
							button.innerHTML = "삭제";
							li.appendChild(button);
							query.append(li);
							
						}
					} else {
						var p = document.createElement('p');
						p.innerHTML = "메세지가 없습니다. 메세지를 보내보세요.";
						$("#MessageArea").append(p);	
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
	}
// 새 메세지 클릭시 맴버 목록 자동 조회 하는 ajax
function selectInviteMessageMemberList() {
	// 해당 워크스페이스의 번호에 속해 있는 맴버들 - 나랑 대화중인 맴버 - 나
	var workspace_num = $("#workspace_num").val();
	var member_num = $("#member_num").val();
	
	function getParameterValues() {
		return "?command=selectInviteMessageMemberList&workspace_num=" + workspace_num
				+ "&member_num=" + member_num;
	}	
	$.ajax({
		url :"ChannelController" + getParameterValues(),
		dataType : "json",
		method : "post",
		success:function(data) {
			var list = data.result;
			console.log(data);

			$("#messageInviteMemberList").children().remove();
			$("#channelMemberList").children().remove();
			$("#channelInviteList").children().remove();

			var query = document.querySelector('#messageInviteMemberList');

			var ul = document.createElement('ul');
			ul.setAttribute("class", "list-group");
			query.appendChild(ul);

			for (var i = 0; i < list[0].length; i++) {
				var member2_num = list[0][i].member_num;
				var member2_id = list[0][i].member_id;
				var member2_name = list[0][i].member_name;

				var li = document.createElement('li');
				li.setAttribute("class", "list-group-item");

				var div = document.createElement('div');
				div.setAttribute("class", "input-group");

				var span = document.createElement('span');
				span.setAttribute("class", "input-group-addon");

				var input = document.createElement('input');
				input.type = "radio";
				input.name = "inviteMessageChk";
				input.value = (i + 1);
				span.appendChild(input);
				
				var input = document.createElement('input');
				input.id = "member2_num" + (i + 1);
				input.type = "hidden";
				input.value = member2_num;
				span.appendChild(input);
				
				var input = document.createElement('input');
				input.id = "member2_id" + (i + 1);
				input.type = "hidden";
				input.value = member2_id;
				span.appendChild(input);

				var input = document.createElement('input');
				input.id = "member2_name" + (i + 1);
				input.type = "hidden";
				input.value = member2_name;
				span.appendChild(input);

				div.appendChild(span);

				var input = document.createElement('input');

				input.type = "text";
				input.value = "이름(ID) : " + member2_name + " ( " + member2_id
						+ " ) ";
				input.setAttribute("readonly", "readonly");
				input.setAttribute("class", "form-control");
				input.style["background-color"] = "white";
				div.appendChild(input);
				li.appendChild(div);

				query.appendChild(li);
			}
		},
		error:function(){
			alert("통신 실패")
		}

	})
}
//선택한 맴버 메세지방 생성
function createMessageRoom() {
	
	var chks = document.getElementsByName('inviteMessageChk');
	
	chks.forEach((node) => {
		    if(node.checked)  {
		    	var num = node.value;
		    	var workspace_num = $("#workspace_num").val();
		    	var member_num = $("#member_num").val();
		    	var member_id = $("#member_id").val();
		    	var member_name = $("#member_name").val();
		    	var member2_num = $("#member2_num"+num).val();
		    	var member2_id = $("#member2_id"+num).val();
		    	var member2_name = $("#member2_name"+num).val();
		    	
		    	function getParameterValues() {    		
		    		return "?command=createMessageRoom&workspace_num=" + workspace_num
		    		+ "&member_num=" + member_num + "&member_id=" + member_id + "&member_name=" + member_name
		    		+ "&member2_num=" + member2_num + "&member2_id=" + member2_id + "&member2_name=" + member2_name;
		    		
		    	}
		    	
		    	$.ajax({
		    		url:"ChatController"+getParameterValues(),
		    		dataType:"text",
		    		method:"post",
		    		success:function(msg) {
		    			alert(msg)
		    			$("#addMessageCancel").bind("click");
						$("#addMessageCancel").trigger("click");
						selectMessageRoom();
		    			
		    		},
		    		error:function(msg){
		    			alert(msg)
		    		}
		    		
		    		
		    	})
		    	
		    	
		    }
	 })
		
}// 메세지방 DB 불러오는 ajax
function callMessageList(msgRoomNum, memnum) {
	$('#chatarea').children().remove();
	re_send = "";
	var member_num = memnum;
	var messageroom_num = msgRoomNum;
	
	$.ajax({url : "ChatController?command=callMessageList&messageroom_num="+ messageroom_num,
				dataType : "json",
				method : "post",
				success : function(data) {
					var list = data.result;
					var firstname = "";
					for (var i = 0; i < list[0].length; i++) {
						if (list[0][i].to_num == member_num) {
							if (list[0][i].to_id != firstname) {
								var who = document.createElement('div');
								// who.style["float"]="left";
								who.style["display"] = "inline-block";
								who.style["font-weight"] = "bold";
								who.style["font-size"] = "14px";
								who.style["padding-top"] = "5px;"
								who.style["padding-bottom"] = "2px;"
								who.style["padding-left"] = "10px;"
								who.append(list[0][i].to_id);

								var icon = document.createElement('span');
								icon.setAttribute("class",
										"glyphicon glyphicon-user");
								icon.setAttribute("aria-hidden", "true");
								icon.style["color"] = "gold";
								icon.style["width"] = "15px;";
								icon.style["height"] = "15px;";

								document.getElementById('chatarea')
										.appendChild(icon);
								document.getElementById('chatarea')
										.appendChild(who);

								var clear = document.createElement('div');
								clear.style["clear"] = "both";
								document.getElementById('chatarea')
										.appendChild(clear);

								firstname = list[0][i].to_id;

							}

							var div = document.createElement('div');
							// div.style["float"]="left";
							div.style["display"] = "block";
							// div.style["font-weight"]="bold";
							div.style["color"] = "#1D1C1D";
							div.style["padding-left"] = "10px";
							div.style["padding-top"] = "3px;"
							div.style["padding-bottom"] = "3px;"
							div.innerHTML = list[0][i].message_content;
							document.getElementById('chatarea')
									.appendChild(div);

							var clear = document.createElement('div');
							clear.style["clear"] = "both";
							document.getElementById('chatarea').appendChild(
									clear);

						} else {

							if (list[0][i].from_id != firstname) {
								var who = document.createElement('div');
								// who.style["float"]="right";
								who.style["display"] = "inline-block";
								who.style["font-weight"] = "bold";
								who.style["font-size"] = "14px";
								who.style["padding-top"] = "5px;"
								who.style["padding-bottom"] = "2px;"
								who.style["padding-left"] = "10px;"
								who.innerHTML = list[0][i].from_id;

								var icon = document.createElement('span');
								// icon.style["float"]="right";
								icon.setAttribute("class",
										"glyphicon glyphicon-user");
								icon.setAttribute("aria-hidden", "true");
								icon.style["color"] = "gray";
								icon.style["width"] = "15px;";
								icon.style["height"] = "15px;";

								document.getElementById('chatarea')
										.appendChild(icon);
								document.getElementById('chatarea')
										.appendChild(who);

								var clear = document.createElement('div');
								clear.style["clear"] = "both";
								document.getElementById('chatarea')
										.appendChild(clear);

								firstname = list[0][i].from_id;

							}

							var div = document.createElement('div');
							// div.style["float"]="right";
							div.style["display"] = "block";
							// div.style["font-weight"]="bold";
							div.style["color"] = "#1D1C1D";
							div.style["padding-left"] = "10px";
							div.style["padding-top"] = "3px;"
							div.style["padding-bottom"] = "3px;"
							div.innerHTML = list[0][i].message_content;
							document.getElementById('chatarea')
									.appendChild(div);

							var clear = document.createElement('div');
							clear.style["clear"] = "both";
							document.getElementById('chatarea').appendChild(
									clear);

						}

					}

					var div = document.createElement('div');
					div.style["clear"] = "both";
					div.style["font-weight"] = "bold";
					div.style["color"] = "#1D1C1D";
					div.style["background"] = "lightgray";
					div.style["display"] = "block";
					div.style["text-align"] = "center";
					div.innerHTML = "------------------------------이전 메세지는 여기까지 입니다.------------------------------";

					document.getElementById('chatarea').appendChild(div);

					chatarea.scrollTop = chatarea.scrollHeight;
				},
				error : function() {
					alert("통신 실패")
				}
			})

	$("#roominfo").children().remove();

	messsageInfo(msgRoomNum);

}
// 메세지방 정보 불러오는 ajax
function messsageInfo(msgRoomNum, memnum) {
	console.log(msgRoomNum)
	$.ajax({
		url : "ChatController?command=msgRoomSelect&messageroom_num="
				+ msgRoomNum,
		dataType : "text",
		method : "post",
		success : function(data) {
			var res = data.split("|\\|");
			console.log(data);
			var messageroom_num = res[0];
			var workspace_num = res[1];
			var member_num = res[2];
			var member_id = res[3];
			var member_name = res[4];
			var member2_num = res[5];
			var member2_id = res[6];
			var member2_name = res[7];
			var messsageroom_regdate = res[8];

			var input = document.createElement('input');
			input.id = "messageroom_num";
			input.type = "hidden";
			input.value = messageroom_num;
			document.getElementById('roominfo').appendChild(input);

			var input = document.createElement('input');
			input.id = "messageInfo_member_num";
			input.type = "hidden";
			input.value = member_num;
			document.getElementById('roominfo').appendChild(input);
			
			var input = document.createElement('input');
			input.id = "messageInfo_member_id";
			input.type = "hidden";
			input.value = member_id;
			document.getElementById('roominfo').appendChild(input);

			var input = document.createElement('input');
			input.id = "messageInfo_member_name";
			input.type = "hidden";
			input.value = member_name;
			document.getElementById('roominfo').appendChild(input);

			var input = document.createElement('input');
			input.id = "messageInfo_member2_num";
			input.type = "hidden";
			input.value = member2_num;
			document.getElementById('roominfo').appendChild(input);
			
			var input = document.createElement('input');
			input.id = "messageInfo_member2_id";
			input.type = "hidden";
			input.value = member2_id;
			document.getElementById('roominfo').appendChild(input);

			var input = document.createElement('input');
			input.id = "messageInfo_member2_name";
			input.type = "hidden";
			input.value = member2_name;
			document.getElementById('roominfo').appendChild(input);

			var span = document.createElement('span');
			span.style["font-size"] = "10px";
			span.style["display"] = "inline-block";

			span.innerHTML = " 워크스페이스 : " + workspace_num + "번의 " + member_name
					+ "(" + member_id + ")와 " + member2_name + "(" + member2_id
					+ ")의 채팅방 <br>" + " 채널생성일 : " + messsageroom_regdate;

			document.getElementById('roominfo').appendChild(span);
		},
		error : function() {
			alert("통신 실패")
		}
	})

}function messagedelcon(msgRoomNum, memnum) {
	var con = confirm("해당 메세지룸을 삭제하시겠습니까?");
	var workspace_num = $("#workspace_num").val();
	if (con) {
		
		var messageroom_num = msgRoomNum;
		var member_num = memnum;

		function getParameterValues() {

			return "?command=deleteMessageRoom&messageroom_num=" + messageroom_num
					+ "&member_num=" + member_num;
		}

		$.ajax({
			url : "ChatController" + getParameterValues(),
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