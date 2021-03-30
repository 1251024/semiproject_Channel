package channel.lyj_chat;

import java.util.List;

public interface ChatDao {
	
	// 채팅 DB에서 불러오기
	public List<ChatDto> callChatList(int channel_num);
	
	// 채팅 DB에 저장하기
	public int chatInsert(ChatDto dto);

	
}
