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
							button.setAttribute("class","btn btn-default btn-block");
							button.setAttribute("onclick","callMessageList("+list[0][i].messageroom_num+","+member_num+");");
							button.innerHTML = 
							list[0][i].member_name + "/" + list[0][i].member2_name + "의 채팅룸";
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
	
	$("#channelcontentarea").children().show();
	$("#channeljumbotron").hide();
	
	$('#chatarea').children().remove();
	re_send = "";
	var member_num = memnum;
	var messageroom_num = msgRoomNum;

	$.ajax({
		url : "ChatController?command=callMessageList&messageroom_num="+ messageroom_num,
		dataType : "json",
		method : "post",
		success : function(data) {
			var list = data.result;
			var firstname = "";
			for (var i = 0; i < list[0].length; i++) {
				
				if (list[0][i].to_num == member_num) {
					var query = document.querySelector('#chatarea');
						if (list[0][i].to_name != firstname) {
							
							var icon = document.createElement('span');
							icon.setAttribute("class","glyphicon glyphicon-user");
							icon.setAttribute("aria-hidden", "true");
							icon.style["color"] = "gold";
							icon.style["width"] = "15px;";
							icon.style["height"] = "15px;";
							
							var who = document.createElement('button');
							who.setAttribute("class","btn btn-default");
							who.setAttribute("data-toggle", "tooltip");
							who.setAttribute("data-placement", "right");
							who.setAttribute("title", list[0][i].message_regdate);
							who.style["font-weight"] = "bold";
							who.appendChild(icon);
							who.append(list[0][i].to_name);

							query.appendChild(who);

							var clear = document.createElement('div');
							clear.style["clear"] = "both";
							query.appendChild(clear);

							firstname = list[0][i].to_name;

							}

						var div = document.createElement('div');
						div.setAttribute("data-toggle", "tooltip");
						div.setAttribute("data-placement", "right");
						div.setAttribute("title", list[0][i].message_regdate);
						div.style["display"] = "inline-block";
						div.style["padding-left"] = "10px";
						div.style["padding-top"] = "3px;"
						div.style["padding-bottom"] = "3px;"
						div.innerHTML = list[0][i].message_content;
						div.setAttribute("id", list[0][i].chat_num);
						
						query.appendChild(div);

							var clear = document.createElement('div');
							clear.style["clear"] = "both";
							query.appendChild(clear);

							
						} else {
							var query = document.querySelector('#chatarea');

							if (list[0][i].to_name != firstname) {
								
								var icon = document.createElement('span');
								icon.setAttribute("class", "glyphicon glyphicon-user");
								icon.setAttribute("aria-hidden", "true");
								icon.style["color"] = "gray";
								icon.style["width"] = "15px;";
								icon.style["height"] = "15px;";
								
								var who = document.createElement('button');
								who.setAttribute("class","btn btn-default");
								who.setAttribute("data-toggle", "tooltip");
								who.setAttribute("data-placement", "right");
								who.setAttribute("title", list[0][i].message_regdate);
								who.style["font-weight"] = "bold";
								who.appendChild(icon);
								who.append(list[0][i].to_name);
								
								query.appendChild(who);

								var clear = document.createElement('div');
								clear.style["clear"] = "both";
								query.appendChild(clear);

								firstname = list[0][i].to_name;

							}

							var div = document.createElement('div');
							div.setAttribute("data-toggle", "tooltip");
							div.setAttribute("data-placement", "right");
							div.setAttribute("title", list[0][i].message_regdate);
							div.style["display"] = "inline-block";
							div.style["padding-left"] = "10px";
							div.style["padding-top"] = "3px;"
							div.style["padding-bottom"] = "3px;"
							div.innerHTML = list[0][i].message_content;
							query.appendChild(div);

							var clear = document.createElement('div');
							clear.style["clear"] = "both";
							query.appendChild(clear);

						}

					}

			
					var query = document.querySelector('#chatarea');
			
					var last = document.createElement('div');
					last.setAttribute("class", "well");
					last.style["clear"] = "both";
					last.style["font-weight"] = "bold";
					last.style["display"] = "block";
					last.style["text-align"] = "center";
					last.innerHTML = "------------------------------이전 메세지는 여기까지 입니다.------------------------------";

					query.appendChild(last);

					
					const $messageTextBox = $('#chatarea'); 
					$messageTextBox.scrollTop($messageTextBox[0].scrollHeight);
					
					$('[data-toggle="tooltip"]').tooltip()
				},
				error : function() {
					alert("통신 실패")
				}
			})

	$("#roominfo").children().remove();

	messsageInfo(msgRoomNum, member_num);

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

			var query = document.querySelector('#roominfo');
			
			var messageroom_num = res[0];
			var workspace_num = res[1];
			var member_num = res[2];
			var member_id = res[3];
			var member_name = res[4];
			var member2_num = res[5];
			var member2_id = res[6];
			var member2_name = res[7];
			var messsageroom_regdate = res[8];

			var div = document.createElement('div');
			div.setAttribute("class", "pull-left");
			div.setAttribute("data-toggle", "tooltip");
			div.setAttribute("data-placement", "right");
			div.setAttribute("title", messsageroom_regdate);
			
			var h = document.createElement('h4');
			h.style["font-weight"]="bold";
			h.innerHTML = "<span class='glyphicon glyphicon-info-sign' aria-hidden='true'></span>  " 
				+ member_name + "(" + member_id + ")님 과 " + member2_name + "(" + member2_id + ")님 의 채팅룸입니다." ;
			div.appendChild(h);
			
			query.appendChild(div);
			
			var input = document.createElement('input');
			input.id = "messageroom_num";
			input.type = "hidden";
			input.value = messageroom_num;
			query.appendChild(input);

			if (member_num == memnum) {
			
				var input = document.createElement('input');
				input.id = "messageInfo_member_num";
				input.type = "hidden";
				input.value = member_num;
				query.appendChild(input);
				
				var input = document.createElement('input');
				input.id = "messageInfo_member_id";
				input.type = "hidden";
				input.value = member_id;
				query.appendChild(input);

				var input = document.createElement('input');
				input.id = "messageInfo_member_name";
				input.type = "hidden";
				input.value = member_name;
				query.appendChild(input);

				var input = document.createElement('input');
				input.id = "messageInfo_member2_num";
				input.type = "hidden";
				input.value = member2_num;
				query.appendChild(input);
				
				var input = document.createElement('input');
				input.id = "messageInfo_member2_id";
				input.type = "hidden";
				input.value = member2_id;
				query.appendChild(input);

				var input = document.createElement('input');
				input.id = "messageInfo_member2_name";
				input.type = "hidden";
				input.value = member2_name;
				query.appendChild(input);
				
			} else {
				
				var input = document.createElement('input');
				input.id = "messageInfo_member_num";
				input.type = "hidden";
				input.value = member2_num;
				query.appendChild(input);
				
				var input = document.createElement('input');
				input.id = "messageInfo_member_id";
				input.type = "hidden";
				input.value = member2_id;
				query.appendChild(input);

				var input = document.createElement('input');
				input.id = "messageInfo_member_name";
				input.type = "hidden";
				input.value = member2_name;
				query.appendChild(input);

				var input = document.createElement('input');
				input.id = "messageInfo_member2_num";
				input.type = "hidden";
				input.value = member_num;
				query.appendChild(input);
				
				var input = document.createElement('input');
				input.id = "messageInfo_member2_id";
				input.type = "hidden";
				input.value = member_id;
				query.appendChild(input);

				var input = document.createElement('input');
				input.id = "messageInfo_member2_name";
				input.type = "hidden";
				input.value = member_name;
				query.appendChild(input);
			}
			
			var div = document.createElement('div');
			div.setAttribute("class", "pull-right");
			
			var button = document.createElement('button');
			button.setAttribute("class","btn btn-danger btn-sm");
			button.setAttribute("onclick","messagedelcon("+messageroom_num+","+member_num+");");
			button.innerHTML = "삭제";
			div.appendChild(button);
			query.appendChild(div);
			
			$('[data-toggle="tooltip"]').tooltip()

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