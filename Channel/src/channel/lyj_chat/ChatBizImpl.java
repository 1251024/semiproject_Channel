package channel.lyj_chat;

import java.util.List;

public class ChatBizImpl implements ChatBiz {
	
	ChatDao dao = new ChatDaoImpl();
	
	@Override
	public List<ChatDto> callChatList(int channel_num) {
		return dao.callChatList(channel_num);
	}

	@Override
	public int chatInsert(ChatDto dto) {
		return dao.chatInsert(dto);
	}

}
