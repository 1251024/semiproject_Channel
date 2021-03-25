package channel.chat.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import channel.chat.dto.ChatDto;
import channel.db.SqlMapConfig;

public class ChatDao extends SqlMapConfig {

	public List<ChatDto> callChatList(int channel_num) {

		SqlSession session = null;
		List<ChatDto> list = new ArrayList<ChatDto>();

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList("channelmapper.callChatList", channel_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;

	}

	public int insertChat(ChatDto dto) {

		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert("channelmapper.insertChat", dto);
			
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
