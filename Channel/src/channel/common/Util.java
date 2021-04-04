package channel.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

private String todates;
	
	public String getTodates() {
		
		return todates;
	}
	
	public void setTodates(String mdate) {
		
		// yyyy-MM-dd hh:mm:00 형태로 바꾸자.
		
		String temp = mdate.substring(0, 4)+"-"
				    + mdate.substring(4, 6)+"-"
				    + mdate.substring(6, 8)+" "
				    + mdate.substring(8, 10)+":"
				    + mdate.substring(10)+":00";
		
		// yyyy년MM월dd일 HH시mm분 형태로 날짜/시간 패턴을 설정하여 sdf 변수에 대입
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		
		// yyyy-MM-dd hh:mm:00의 스트링값을 Timestamp객체로 담아준다.
		Timestamp tm = Timestamp.valueOf(temp);
		
		// SimpleDateFormat의 부모타입은 DateFormat의 메소드인 format을 사용하여 todates 변수에 해당 날짜를 
		// sdf에서 설정했던 yyyy년MM월dd일 HH시mm분 형태로 String타입 대입
		todates = sdf.format(tm);
		
	}
	
	public String getTostrings(Date date) {
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시mm분");
		
		String res = transFormat.format(date);
		
		return res;
	}
	
}
