package channel.lyj_chat;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import channel.db_lyj.SqlMapConfig;

public class ChatDaoImpl extends SqlMapConfig implements ChatDao {

	@Override
	public List<ChatDto> callChatList(int channel_num) {
		SqlSession session = null;
		List<ChatDto> list = new ArrayList<ChatDto>();

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper-chat.callChatList", channel_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public int chatInsert(ChatDto dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper-chat.chatInsert", dto);
			
			if (res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return res;
	}

}
