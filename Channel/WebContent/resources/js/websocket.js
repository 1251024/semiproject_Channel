//채팅영역 Websocket 접속 함수들
var webSocket = new WebSocket('ws://localhost:8787/Channel/websocket');
var re_send = "";
webSocket.onerror = function(event) {
	onError(event)
};
webSocket.opopen = function() {
	onOpen()
};
webSocket.onmessage = function(event) {
	onMessage(event)
};
webSocket.onclose = function() {
	var member_name = $('#member_name').val();

	var query = document.querySelector('#chatarea');
	
	var div = document.createElement('div');
	div.style["float"] = "left";

	div.innerHTML = member_name + "님 이 채팅방에서 퇴장하였습니다.";
	query.appendChild(div);

	var clear = document.createElement('div');
	clear.style["clear"] = "both";
	query.appendChild(clear);

};
// 웹소캣으로 send한 메세지를 출력시켜주는 함수
function onMessage(event) {

	var message = event.data.split("|\|");
	
	if (message[0] != re_send) {
		var who = document.createElement('div');
		// who.style["float"]="right";
		who.style["display"] = "inline-block";
		who.style["font-weight"] = "bold";
		who.style["font-size"] = "14px";
		who.style["padding-top"] = "5px;"
		who.style["padding-bottom"] = "2px;"
		who.style["padding-left"] = "10px;"
		who.innerHTML = message[0];

		var icon = document.createElement('span');
		// icon.style["float"]="right";
		icon.setAttribute("class", "glyphicon glyphicon-user");
		icon.setAttribute("aria-hidden", "true");
		icon.style["color"] = "gray";
		icon.style["width"] = "15px;";
		icon.style["height"] = "15px;";

		document.getElementById('chatarea').appendChild(icon);
		document.getElementById('chatarea').appendChild(who);

		var clear = document.createElement('div');
		clear.style["clear"] = "both";
		document.getElementById('chatarea').appendChild(clear);

		re_send = message[0];

	}

	var div = document.createElement('div');
	// div.style["float"]="right";
	div.style["display"] = "block";
	// div.style["font-weight"]="bold";
	div.style["color"] = "#1D1C1D";
	div.style["padding-left"] = "10px";
	div.style["padding-top"] = "3px;"
	div.style["padding-bottom"] = "3px;"

	div.innerHTML = message[1];
	document.getElementById('chatarea').appendChild(div);

	var clear = document.createElement('div');
	clear.style["clear"] = "both";
	document.getElementById('chatarea').appendChild(clear);

	chatarea.scrollTop = chatarea.scrollHeight;

}
// 웹소켓에 접속시 실행되는 함수
function onOpen() {
	var member_name = $('#member_name').val();

	var query = document.querySelector('#chatarea');
	
	var div = document.createElement('div');
	div.style["float"] = "left";

	div.innerHTML = member_name + "님 이 채팅방에 입장하였습니다.";
	query.appendChild(div);

	var clear = document.createElement('div');
	clear.style["clear"] = "both";
	query.appendChild(clear);

	webSocket.send(member_name + "님이 채팅방에 입장하였습니다.|\|메세지를 보내주세요.");

}
// 웹소켓 에러시 실행되는 함수
function onError(event) {

	console.log("서버 연결 에러" + event.data);
}
function send(msg) {
	// 채팅 메세지가 널이 아니면~
	var content = msg;

	if (content.value != "") {
		// 맴버 2아이디가 널이 아니면~ 메세지인서트
		var member2_id = $("#messageInfo_member2_id").val();
		console.log(member2_id);
		var member_id = $("#member_id").val();
		if (member2_id != null) {
			 
			var messageroom_num = $("#messageroom_num").val();
			var to_num = $("#messageInfo_member_num").val();
			var to_id = $("#messageInfo_member_id").val();
			var to_name = $("#messageInfo_member_name").val();
			var from_num = $("#messageInfo_member2_num").val();
			var from_id = $("#messageInfo_member2_id").val();
			var from_name = $("#messageInfo_member2_name").val();
			var message_content = msg;

			function getParameterValues() {

				return "?command=messageInsert&messageroom_num="+ messageroom_num
				+ "&to_num=" + to_num + "&to_id=" + to_id + "&to_name="+ to_name
				+ "&from_num=" + from_num + "&from_id=" + from_id + "&from_name="+ from_name
				+ "&message_content=" + message_content;
			}

			$.ajax({
				url : "ChatController" + getParameterValues(),
				dataType : "text",
				method : "post",
				success : function() {
					console.log("메세지 저장 완료")
				},
				error : function() {
					alert("통신 실패")
				}
			})

			webSocket.send(to_name + "|\|" + message_content);
			
			if (to_name != re_send) {
				var who = document.createElement('div');
				// who.style["float"]="left";
				who.style["display"] = "inline-block";
				who.style["font-weight"] = "bold";
				who.style["font-size"] = "14px";
				who.style["padding-top"] = "5px;"
				who.style["padding-bottom"] = "2px;"
				who.style["padding-left"] = "10px;"
				who.innerHTML = to_name;

				var icon = document.createElement('span');
				icon.style["color"] = "gold";
				icon.style["width"] = "15px;";
				icon.style["height"] = "15px;";
				icon.setAttribute("class", "glyphicon glyphicon-user");
				icon.setAttribute("aria-hidden", "true");

				document.getElementById('chatarea').appendChild(icon);
				document.getElementById('chatarea').appendChild(who);
				var clear = document.createElement('div');
				clear.style["clear"] = "both";
				document.getElementById('chatarea').appendChild(clear);

				re_send = to_name;


			}

			var div = document.createElement('div');
			// div.style["float"]="left";
			div.style["display"] = "block";
			// div.style["font-weight"]="bold";
			div.style["color"] = "#1D1C1D";
			div.style["padding-left"] = "10px";
			div.style["padding-top"] = "3px;"
			div.style["padding-bottom"] = "3px;"

			div.innerHTML = message_content;
			document.getElementById('chatarea').appendChild(div);

			var clear = document.createElement('div');
			clear.style["clear"] = "both";
			document.getElementById('chatarea').appendChild(clear);

			message_content.value = '';

			chatarea.scrollTop = chatarea.scrollHeight;

			re_send = to_name;


			// 그게 아니면 채팅 인서트
		} else {

			var channel_num = $("#channel_num").val();
			var member_num = $("#member_num").val();
			var member_id = $("#member_id").val();
			var member_name = $("#member_name").val();
			var chat_content = msg;
			console.log(chat_content);

			function getParameterValues() {

				return "?command=insertChat&channel_num=" + channel_num
						+ "&member_num=" + member_num + "&member_id=" + member_id + "&member_name="+ member_name + "&chat_content=" + chat_content;
			}

			$.ajax({
				url : "ChatController" + getParameterValues(),
				dataType : "text",
				method : "post",
				success : function() {
					console.log("채팅 저장 완료")
				},
				error : function() {
					alert("통신 실패")
				}
			})

			webSocket.send(member_name + "|\|" + chat_content);
			
			if (member_name != re_send) {
				var who = document.createElement('div');
				// who.style["float"]="left";
				who.style["display"] = "inline-block";
				who.style["font-weight"] = "bold";
				who.style["font-size"] = "14px";
				who.style["padding-top"] = "5px;"
				who.style["padding-bottom"] = "2px;"
				who.style["padding-left"] = "10px;"
				who.innerHTML = member_name;

				var icon = document.createElement('span');
				icon.style["color"] = "gold";
				icon.style["width"] = "15px;";
				icon.style["height"] = "15px;";
				icon.setAttribute("class", "glyphicon glyphicon-user");
				icon.setAttribute("aria-hidden", "true");

				document.getElementById('chatarea').appendChild(icon);
				document.getElementById('chatarea').appendChild(who);
				var clear = document.createElement('div');
				clear.style["clear"] = "both";
				document.getElementById('chatarea').appendChild(clear);

				re_send = member_name;

			}

			var div = document.createElement('div');
			// div.style["float"]="left";
			div.style["display"] = "block";
			// div.style["font-weight"]="bold";
			div.style["color"] = "#1D1C1D";
			div.style["padding-left"] = "10px";
			div.style["padding-top"] = "3px;"
			div.style["padding-bottom"] = "3px;"

			div.innerHTML = chat_content;
			document.getElementById('chatarea').appendChild(div);

			var clear = document.createElement('div');
			clear.style["clear"] = "both";
			document.getElementById('chatarea').appendChild(clear);

			chat_content.value = '';

			chatarea.scrollTop = chatarea.scrollHeight;

			re_send = member_name;
		}

	}

}