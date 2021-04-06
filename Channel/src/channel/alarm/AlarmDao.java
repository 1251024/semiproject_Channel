package channel.alarm;

import java.util.List;

import channel.chat.ChatDto;

public interface AlarmDao {
	
	public int selectAlarm(int num);


	public List<ChatDto> chatAlarm(int member_num);
	
	public List<ChatDto> chatAlarmList(int member_num);
}
