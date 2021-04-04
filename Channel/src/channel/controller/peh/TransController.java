package channel.controller.peh;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/transController")
public class TransController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("transController 실행");
	    request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");

	    //번역할 text 값을 받아오기
	    String original_str = (String)request.getParameter("original_str");

	    //결과값 보내기 위한것
	    PrintWriter out = response.getWriter();
	    out.print((String)korEng(original_str));
		System.out.println(original_str);
	    
		
	    //번역할 text 값을 받아오기
	    String original_str2 = (String)request.getParameter("original_str2");
	   
	    //결과값 보내기 위한것
	    PrintWriter out2 = response.getWriter();
	    out2.print((String)engKor(original_str2));
	    System.out.println(original_str2);
	    
	}
	

	// korEng의 함수를 통해서 한글 - > 영어로 번역
	public String korEng(String original_str){
	    
	    //애플리케이션 클라이언트 아이디값";
	    String clientId = "czQrw0pv4kxuAZT85dkW";
	    //애플리케이션 클라이언트 시크릿값";
	    String clientSecret = "jKs5328niN";
	    
	    String resultString ="";
	    try {
	        //original_str 값이 우리가 변환할 값
	        String text = URLEncoder.encode(original_str, "UTF-8");
	        
	        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("X-Naver-Client-Id", clientId);
	        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	        // post request
	        String postParams = "source=ko&target=en&text=" + text;
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(postParams);
	        wr.flush();
	        wr.close();
	        int responseCode = con.getResponseCode();
	        BufferedReader br;
	        if(responseCode==200) { // 정상 호출
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else { // 에러 발생
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	            response.append(inputLine);
	        }
	        br.close();
	        System.out.println(response.toString());
	        
	        resultString = response.toString();
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    
	    return resultString;



	}
	
	// engKor의 함수를 통해서 영어 - > 한글로 번역
		public String engKor(String original_str){
		    
		    //애플리케이션 클라이언트 아이디값";
		    String clientId = "czQrw0pv4kxuAZT85dkW";
		    //애플리케이션 클라이언트 시크릿값";
		    String clientSecret = "jKs5328niN";
		    
		    String resultString ="";
		    try {
		        //original_str 값이 우리가 변환할 값
		        String text = URLEncoder.encode(original_str, "UTF-8");
		        
		        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		        URL url = new URL(apiURL);
		        HttpURLConnection con = (HttpURLConnection)url.openConnection();
		        con.setRequestMethod("POST");
		        con.setRequestProperty("X-Naver-Client-Id", clientId);
		        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		        // post request
		        String postParams = "source=en&target=ko&text=" + text;
		        con.setDoOutput(true);
		        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		        wr.writeBytes(postParams);
		        wr.flush();
		        wr.close();
		        int responseCode = con.getResponseCode();
		        BufferedReader br;
		        if(responseCode==200) { // 정상 호출
		            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        } else { // 에러 발생
		            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		        }
		        String inputLine;
		        StringBuffer response = new StringBuffer();
		        while ((inputLine = br.readLine()) != null) {
		            response.append(inputLine);
		        }
		        br.close();
		        System.out.println(response.toString());
		        
		        resultString = response.toString();
		    } catch (Exception e) {
		        System.out.println(e);
		    }
		    
		    return resultString;



		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
