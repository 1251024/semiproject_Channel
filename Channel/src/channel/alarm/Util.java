package channel.alarm;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Util {
	
	private String todates;
	
	public String getTodates() {
		return todates;
	}

	public void setTodates(String mdate) {
		// yyyy-MM-dd hh:mm:00 형태로 바꾸자.
		String temp = mdate.substring(0, 4)+"-"+mdate.substring(4,6)+"-"+mdate.substring(6,8)+" "
					+mdate.substring(8,10)+":"+mdate.substring(10)+":00";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		// sdf를 무조건 이 형식으로(내가 원하는 형태) 보내주겠다 
		Timestamp tm = Timestamp.valueOf(temp);
		// 타임스탬프는 yyyy-MM-dd hh:mm:00 형태로 들어와야함 / valueOf : 타임스탬프 형식으로 바꿔줌
		todates = sdf.format(tm); 
		// 부모 Dateformat을 확장? 문자열로 형식화 
		
		//202103201505를 2021년03월20일 15시05분 형태로
	}
	
	/*
	// 일정 제목이 긴 경우, 뒷부분을 ...으로
	public static String getCalView(int i, List<AlarmDto> list) {
		
		String d = isTwo(i+"");
		// 문자열로 바꿔줘야하니까 "" i=4 -> 04 -> "04"
		String res  = "";
		
		for(AlarmDto dto: list) {
			// 날짜
			if(dto.getMdate().substring(6,8).equals(d)) {
				res += "<p>"
					+ ((dto.getTitle().length() > 6)? dto.getTitle().substring(0,6)+"...":dto.getTitle())
					+ "</p>";
			}
		}
		
		
		return res;
	}
	*/
	
}
