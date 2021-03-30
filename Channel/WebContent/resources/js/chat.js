// 함수 실행을 위한 정보 변수 선언
var channel_num = $("#channel_num").val();
var member_id = $("#member_id").val();
var member_name = $("#member_name").val();

//채널추가 시
function addChannel() {		

	//화면을 가리는 레이어의 사이즈 조정
	var width = $(window).width();
	var height = $(window).height();
	$("#backLayer").width(width);
	$("#backLayer").height(height);
	
	//화면을 가리는 레이어를 보여준다 (0.5초동안 30%의 농도의 투명도) 
	$("#backLayer").fadeTo(300, 0.3);
	
	//채널추가란 보여주기
	$('#channel_add_insert').fadeIn(300);
}

//채널추가 취소
function addCancel() {
	$("#backLayer").fadeOut(1000);
	$('#channel_add_insert').fadeOut(300);	
}
//채널삭제 확인
function channeldelcon(chnum) {	
	var con = confirm("해당 채널을 삭제하시겠습니까?");
	
	var member_id = $("#member_id").val();
	var channel_num = chnum;
	function  getParameterValues() {
		
		return "?command=channelDelete&channel_num=" + channel_num + "&member_id=" + member_id;
	}
	
	if (con) {
		
		$.ajax({
			url:"RoomController"+getParameterValues(),
			dataType: "text",
			method: "post",
			success:function(msg){
				alert(msg)
				location.reload();	
			},
			error: function(){
				alert("통신 실패")
			}	

		}
	)}
	
}
//채널 수정 시
function channelAdmin(chnum, chname, chinfo, chac) {
	var channel_num = chnum;
	var channel_name = chname;
	var channel_information = chinfo;
	var channel_access = chac;	
	$("#update_channel_num").val(channel_num);
	$("#update_channel_name").val(channel_name);
	$("#update_channel_information").val(channel_information);
	$("#update_channel_access").val(channel_access).prop("selected",true);
}


//메세지방 추가 추가시
function addMessage() {
	
}
//메세지방 삭제시

//페이지 최초 로딩시 전체채팅방 DB호출
$(function(){
	var id = $("#member_id").val();
	console.log(id);
	callChatList(1, id);
});

//채팅방 입장
function callChatList(chnum) {
	$('#chatarea').children().remove();
	var user = $("#member_id").val();
	$.ajax ({
		url:"ChatController?command=callChatList&channel_num="+chnum,
		dataType: "json",
		method: "post",
		success:function(data){
			var list = data.result;
			var firstname = "";
			for (var i = 0; i < list[0].length; i++) {
				if (list[0][i].member_id == user) {
					
					if (list[0][i].member_id != firstname) {
						var who = document.createElement('div');
						//who.style["float"]="left";
						who.style["display"]="inline-block";
						who.style["font-weight"]="bold";
						who.style["font-size"]="14px";
						who.style["padding-top"]="5px;"
						who.style["padding-bottom"]="2px;"
						who.style["padding-left"]="10px;"
						who.append(list[0][i].member_id);
						
						var icon = document.createElement('span');
						icon.setAttribute("class","glyphicon glyphicon-user");
						icon.setAttribute("aria-hidden","true");
						icon.style["color"]="gold";
						icon.style["width"]="15px;";
						icon.style["height"]="15px;";
						
						
						
						document.getElementById('chatarea').appendChild(icon);
						document.getElementById('chatarea').appendChild(who);
						
						var clear = document.createElement('div');
						clear.style["clear"]="both";
						document.getElementById('chatarea').appendChild(clear);
						
						firstname = list[0][i].member_id;
						
					}
					
					var div = document.createElement('div');
					//div.style["float"]="left";
					div.style["display"]="block";
					//div.style["font-weight"]="bold";
					div.style["color"]="#1D1C1D";
					div.style["padding-left"]="10px";
					div.style["padding-top"]="3px;"
					div.style["padding-bottom"]="3px;"
					div.innerHTML = list[0][i].chat_content;
					document.getElementById('chatarea').appendChild(div);
					
					var clear = document.createElement('div');
					clear.style["clear"]="both";
					document.getElementById('chatarea').appendChild(clear);
					
				} else {
					
					if (list[0][i].member_id != firstname) {
						var who = document.createElement('div');
						//who.style["float"]="right";
						who.style["display"]="inline-block";
						who.style["font-weight"]="bold";
						who.style["font-size"]="14px";
						who.style["padding-top"]="5px;"
						who.style["padding-bottom"]="2px;"
						who.style["padding-left"]="10px;"
						who.innerHTML = list[0][i].member_id;
						
						var icon = document.createElement('span');
						//icon.style["float"]="right";
						icon.setAttribute("class","glyphicon glyphicon-user");
						icon.setAttribute("aria-hidden","true");
						icon.style["color"]="gray";
						icon.style["width"]="15px;";
						icon.style["height"]="15px;";
						
						document.getElementById('chatarea').appendChild(icon);
						document.getElementById('chatarea').appendChild(who);
						
						var clear = document.createElement('div');
						clear.style["clear"]="both";
						document.getElementById('chatarea').appendChild(clear);
						
						firstname = list[0][i].member_id;
						
					}
					
					var div = document.createElement('div');
					//div.style["float"]="right";
					div.style["display"]="block";
					//div.style["font-weight"]="bold";
					div.style["color"]="#1D1C1D";
					div.style["padding-left"]="10px";
					div.style["padding-top"]="3px;"
					div.style["padding-bottom"]="3px;"
					div.innerHTML = list[0][i].chat_content;
					document.getElementById('chatarea').appendChild(div);
					
					var clear = document.createElement('div');
					clear.style["clear"]="both";
					document.getElementById('chatarea').appendChild(clear);
					
				}
				
			}
			
			var div = document.createElement('div');
			div.style["clear"]="both";
			div.style["font-weight"]="bold";
			div.style["color"]="#1D1C1D";
			div.style["background"]="lightgray";
			div.style["display"]="block";
			div.style["text-align"]="center";
			div.innerHTML = "------------------------------이전 채팅은 여기까지 입니다.------------------------------";
			
			document.getElementById('chatarea').appendChild(div);
			
			chatarea.scrollTop = chatarea.scrollHeight;		
		},
		error:function(){
			alert("통신 실패")
		}		
	})
	$("#roominfo").children().remove();
	channelInfo(chnum);	
} 
// 채널정보 불러오는 ajax
function channelInfo(chnum) {
	
	$.ajax ({
		url:"RoomController?command=channelSelect&channel_num="+chnum,
		dataType: "text",
		method: "post",
		success:function(data){
			var res = data.split("|\\|");
			
			var channel_num = res[0];
			var channel_name = res[1];
			var channel_info = res[2];
			var channel_enabled = res[3];
			var channel_regdate = res[4];
			
			var input = document.createElement('input');
			input.id = "channel_num";
			input.type = "hidden";
			input.value = channel_num;
			document.getElementById('roominfo').appendChild(input);
			
			var input = document.createElement('input');
			input.id = "channel_enabled";
			input.type = "hidden";
			input.value = channel_enabled;
			document.getElementById('roominfo').appendChild(input);
			
			var span = document.createElement('span');
			span.style["font-size"]="10px";
			span.style["display"]="inline-block";

			span.innerHTML = 
				" 채널이름 : "+ channel_name + "<br>" +
				" 채널정보 : "+ channel_info + "<br>" +
				" 채널생성일 : "+ channel_regdate;
			
			document.getElementById('roominfo').appendChild(span);		
		},
		error:function(){
			alert("통신 실패")
		}
	})	
}

//채팅영역 Websocket
var webSocket = new WebSocket('ws://localhost:8787/semiPartOfChat/websocket');
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
	var div = document.createElement('div');
	div.innerHTML = member_name+"님이 나가셨습니다.";
};

function onMessage(event) {
	
	var message = event.data.split("|\|");
	
	if (message[0] != re_send) {
		var who = document.createElement('div');
		//who.style["float"]="right";
		who.style["display"]="inline-block";
		who.style["font-weight"]="bold";
		who.style["font-size"]="14px";
		who.style["padding-top"]="5px;"
		who.style["padding-bottom"]="2px;"
		who.style["padding-left"]="10px;"
		who.innerHTML = message[0];
		
		var icon = document.createElement('span');
		//icon.style["float"]="right";
		icon.setAttribute("class","glyphicon glyphicon-user");
		icon.setAttribute("aria-hidden","true");
		icon.style["color"]="gray";
		icon.style["width"]="15px;";
		icon.style["height"]="15px;";
		
		document.getElementById('chatarea').appendChild(icon);
		document.getElementById('chatarea').appendChild(who);
		
		var clear = document.createElement('div');
		clear.style["clear"]="both";
		document.getElementById('chatarea').appendChild(clear);
		
		re_send = message[0];
		
	}
	
	var div = document.createElement('div');
	//div.style["float"]="right";
	div.style["display"]="block";
	//div.style["font-weight"]="bold";
	div.style["color"]="#1D1C1D";
	div.style["padding-left"]="10px";
	div.style["padding-top"]="3px;"
	div.style["padding-bottom"]="3px;"
	
	div.innerHTML = message[1];
	document.getElementById('chatarea').appendChild(div);
	
	var clear=document.createElement('div');
	clear.style["clear"]="both";
	document.getElementById('chatarea').appendChild(clear);
	
	chatarea.scrollTop = chatarea.scrollHeight;

	
}

function onOpen() {
	
	var div = document.createElement('div');
	div.style["float"]="left";
	
	div.innerHTML = member_name + "님 이 채팅방에 입장하였습니다.";
	document.getElementById('chatarea').appendChild(div);
	
	var clear=document.createElement('div');
	clear.style["clear"]="both";
	document.getElementById('chatarea').appendChild(clear);
	
	webSocket.send(member_name+"님이 채팅방에 입장하였습니다.|\|메세지를 보내주세요.");
	
}

function onError(event) {
	console.log("서버 연결 에러" + event.data);
}

function send(msg) {
	
	var channel_num = $("#channel_num").val();
	var member_id = $("#member_id").val();
	var member_name = $("#member_name").val();
	
	var chat_content = msg;
	console.log(chat_content);
	
	if (chat_content.value != "") {
		
		function  getParameterValues() {
			
			return "?command=chatInsert&channel_num=" + channel_num + "&member_id=" + member_id + "&member_name=" + member_name + "&chat_content=" + chat_content;
		}
		
		$.ajax({
			url:"ChatController"+getParameterValues(),
			dataType: "text",
			method: "post",
			success:function(){
				console.log("메세지 저장 완료")
			},
			error: function(){
				alert("통신 실패")
			}	
		})	
	
		webSocket.send(member_id+"|\|" + chat_content);
		
		if (member_id != re_send) {
			var who = document.createElement('div');
			//who.style["float"]="left";
			who.style["display"]="inline-block";
			who.style["font-weight"]="bold";
			who.style["font-size"]="14px";
			who.style["padding-top"]="5px;"
			who.style["padding-bottom"]="2px;"
			who.style["padding-left"]="10px;"	
			who.innerHTML = member_id;
			
			var icon = document.createElement('span');
			icon.style["color"]="gold";
			icon.style["width"]="15px;";
			icon.style["height"]="15px;";
			icon.setAttribute("class","glyphicon glyphicon-user");
			icon.setAttribute("aria-hidden","true");
			
			document.getElementById('chatarea').appendChild(icon);
			document.getElementById('chatarea').appendChild(who);
			var clear = document.createElement('div');
			clear.style["clear"]="both";
			document.getElementById('chatarea').appendChild(clear);
			
			re_send = member_id;
			
		}
		
		var div=document.createElement('div');
		//div.style["float"]="left";
		div.style["display"]="block";
		//div.style["font-weight"]="bold";
		div.style["color"]="#1D1C1D";
		div.style["padding-left"]="10px";
		div.style["padding-top"]="3px;"
		div.style["padding-bottom"]="3px;"
		
		div.innerHTML = chat_content;
		document.getElementById('chatarea').appendChild(div);
		
		var clear = document.createElement('div');
		clear.style["clear"] = "both";
		document.getElementById('chatarea').appendChild(clear);
		
		chat_content.value = '';
		
		chatarea.scrollTop = chatarea.scrollHeight;
		
		re_send = member_id;
		
	}
	
}

//윈도우가 resize될때마다 backLayer를 조정 
$(window).resize(function() { 
	var width = $(window).width(); 
	var height = $(window).height(); 
	$("#backLayer").width(width).height(height); 

});

// 썸머노트 실행
$(document).ready(function() {
	
	  $('#summernote').summernote({
		  height: 50,                 // set editor height
		  minHeight: 30,             // set minimum height of editor
		  maxHeight: 100,             // set maximum height of editor
		  focus: true,                 // set focus to editable area after initializing summernote
		  dialogsInBody: true,
		  placeholder: '메세지를 입력해주세요.',
		  lang: 'ko-KR',
		  toolbar: [
			    // [groupName, [list of button]]
			    ['style', ['bold','underline']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['insert', ['link', 'picture', 'video']]
			  ],
		  	callbacks: {	//여기 부분이 이미지를 첨부하는 부분
		  		onImageUpload: function(files, editor, welEditable) {
				    sendFile(files[0], this);
				},
					onPaste: function (e) {
						var clipboardData = e.originalEvent.clipboardData;
						if (clipboardData && clipboardData.items && clipboardData.items.length) {
							var item = clipboardData.items[0];
							if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
								e.preventDefault();
							}
						}
					}
				}
	});
	  
	$('.note-statusbar').hide();
// 엔터 입력시 에어리어 초기화 및 값 전송	
	$('#summernote').on('summernote.enter', function() {		
				
				$(function() {
					var inputmsg = $('#summernote').val();
					var outmsg = inputmsg.replace(/<(\/?)p>/gi,'');
					inputmsg.replace('<br>','');
					$('#summernote').summernote('reset');
					send(outmsg);				
				})
		});
});

/**
    * 이미지 파일 업로드
    */
    function sendFile(file, editor) {
        // 파일 전송을 위한 폼생성
 		data = new FormData();
 	    data.append("uploadFile", file);
 	    $.ajax({ // ajax를 통해 파일 업로드 처리
 	        data : data,
 	        type : "POST",
 	        url : "FileController",
 	        cache : false,
 	        contentType : false,
 	        processData : false,
 	        success : function(data) { // 처리가 성공할 경우
                // 에디터에 이미지 출력

 	        	$(editor).summernote('editor.insertImage', data.url);
 	        }
 	    });
 	}
    
