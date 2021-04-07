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

	var today = new Date();
	var month = today.getMonth() + 1;  // 월
	var date = today.getDate();  // 날짜
	
	var hours = today.getHours(); // 시
	var minutes = today.getMinutes();  // 분
	var seconds = today.getSeconds();  // 초
	
	var realTime = month + "월 " + date +"일 "+ hours + ":" + minutes + ":" + seconds;
	
	var message = event.data.split("|\|");
	
	var query = document.querySelector('#chatarea');
	
	if (message[0] != re_send) {
		
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
		who.setAttribute("title", realTime);
		who.style["font-weight"] = "bold";
		who.appendChild(icon);
		who.append(message[0]);
		query.appendChild(who);
		
		var clear = document.createElement('div');
		clear.style["clear"] = "both";
		query.appendChild(clear);

		re_send = message[0];

	}

	var div = document.createElement('div');
	div.setAttribute("data-toggle", "tooltip");
	div.setAttribute("data-placement", "right");
	div.setAttribute("title", realTime);
	div.style["display"] = "inline-block";
	div.style["padding-left"] = "10px";
	div.style["padding-top"] = "3px;"
	div.style["padding-bottom"] = "3px;"
	div.innerHTML = message[1];
	
	query.appendChild(div);

	var clear = document.createElement('div');
	clear.style["clear"] = "both";
	query.appendChild(clear);

	const $messageTextBox = $('#chatarea'); 
	$messageTextBox.scrollTop($messageTextBox[0].scrollHeight);
	
	//chatarea.scrollTop = chatarea.scrollHeight;
	
	$('[data-toggle="tooltip"]').tooltip()

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
	
	var today = new Date();
	var month = today.getMonth() + 1;  // 월
	var date = today.getDate();  // 날짜
	
	var hours = today.getHours(); // 시
	var minutes = today.getMinutes();  // 분
	var seconds = today.getSeconds();  // 초
	
	var realTime = month + "월 " + date +"일 "+ hours + ":" + minutes + ":" + seconds;

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
				+ "&message_content=" + encodeURIComponent(message_content);
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
			
			var query = document.querySelector('#chatarea');
			if (to_name != re_send) { 
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
				who.setAttribute("title", realTime);
				who.style["font-weight"] = "bold";
				who.appendChild(icon);
				who.append(to_name);

				query.appendChild(who);
				
				var clear = document.createElement('div');
				clear.style["clear"] = "both";
				query.appendChild(clear);

				re_send = to_name;


			}

			var div = document.createElement('div');
			div.setAttribute("data-toggle", "tooltip");
			div.setAttribute("data-placement", "right");
			div.setAttribute("title", realTime);
			div.style["display"] = "inline-block";
			div.style["padding-left"] = "10px";
			div.style["padding-top"] = "3px;"
			div.style["padding-bottom"] = "3px;"
			div.innerHTML = message_content;
			query.appendChild(div);

			var clear = document.createElement('div');
			clear.style["clear"] = "both";
			query.appendChild(clear);

			message_content.value = '';

			const $messageTextBox = $('#chatarea'); 
			$messageTextBox.scrollTop($messageTextBox[0].scrollHeight);
			
			//chatarea.scrollTop = chatarea.scrollHeight;

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
						+ "&member_num=" + member_num + "&member_id=" + member_id + "&member_name="+ member_name + "&chat_content=" + encodeURIComponent(chat_content);
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
			var query = document.querySelector('#chatarea');
			if (member_name != re_send) {
				
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
				who.setAttribute("title", realTime);
				who.style["font-weight"] = "bold";
				who.appendChild(icon);
				who.append(member_name);
				
				query.appendChild(who);
				
				var clear = document.createElement('div');
				clear.style["clear"] = "both";
				query.appendChild(clear);

				re_send = member_name;

			}

			var div = document.createElement('div');
			div.setAttribute("data-toggle", "tooltip");
			div.setAttribute("data-placement", "right");
			div.setAttribute("title", realTime);
			div.style["display"] = "inline-block";
			div.style["padding-left"] = "10px";
			div.style["padding-top"] = "3px;"
			div.style["padding-bottom"] = "3px;"
			div.innerHTML = chat_content;
			query.appendChild(div);

			var clear = document.createElement('div');
			clear.style["clear"] = "both";
			query.appendChild(clear);

			chat_content.value = '';

			const $messageTextBox = $('#chatarea'); 
			$messageTextBox.scrollTop($messageTextBox[0].scrollHeight);
			//chatarea.scrollTop = chatarea.scrollHeight;

			re_send = member_name;
		}

	}
	
	$('[data-toggle="tooltip"]').tooltip()

}