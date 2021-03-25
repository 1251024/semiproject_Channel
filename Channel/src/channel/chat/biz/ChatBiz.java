package channel.chat.biz;

import java.util.List;

import channel.chat.dao.ChatDao;
import channel.chat.dto.ChatDto;

public class ChatBiz {

	ChatDao dao = new ChatDao();
	
	public List<ChatDto> callChatList(int channel_num) {
		return dao.callChatList(channel_num);
	}
	
	public int insertChat(ChatDto dto) {
		return dao.insertChat(dto);
	}
	
}
