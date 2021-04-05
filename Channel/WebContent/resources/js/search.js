$(document).ready(function(){
	workspace_num = $("#search_workspace_num").val();
	channel_num = $("#search_channel_num").val();
	chat_num = $("#search_chat_num").val();
	member_num = $("#search_member_num").val();
	search = $("#search_value").val();
	
	console.log(workspace_num, channel_num, chat_num, member_num, search);
	
	if(search == 'y'){
		console.log(selectChatList(channel_num, member_num));
		setTimeout(function(){
			document.getElementById(chat_num).scrollIntoView();
			document.getElementById(chat_num).style.backgroundColor = "skyblue";
		}, 1000);
	}

});

