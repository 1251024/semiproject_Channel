package channel.member.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import channel.lyj_room.RoomBiz;
import channel.lyj_room.RoomBizImpl;
import channel.lyj_room.RoomMemberDto;
import channel.member.biz.MemberBiz;
import channel.member.dto.MemberDto;
import channel.member.dto.MemberGoogleDto;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MemberController() {      
    }	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MemberBiz biz = new MemberBiz();
		RoomBiz roomBiz = new RoomBizImpl();
		String command = request.getParameter("command");
		
		if (command.equals("member_login_page")) {
			response.sendRedirect("member_login.jsp");
			
		} else if (command.equals("member_insert_page")) {
			response.sendRedirect("member_insert.jsp");
			
		} else if (command.equals("member_insert")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String re_pw = request.getParameter("re_pw");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String pscode = request.getParameter("pscode");
			String addr = request.getParameter("addr");
			String addrdt = request.getParameter("addrdt");
			
			MemberDto dto = new MemberDto();
			if (pw.equals(re_pw)) {
				dto.setMember_id(id);
				dto.setMember_pw(biz.getSHA256(pw));
				dto.setMember_name(name);
				dto.setMember_email(email);
				dto.setMember_phone(phone);
				dto.setMember_pscode(pscode);
				dto.setMember_addr(addr);
				dto.setMember_addrdt(addrdt);
				
			} else {
				response.sendRedirect("MemberController?command=member_insert_page");
			}
			
			int res = biz.insertUser(dto);
			
			
			
			if(res > 0) {
				// 회원가입 성공시 전채채팅방 맴버에 해당 회원 정보 추가
				RoomMemberDto roomDto = new RoomMemberDto();
				roomDto.setChannel_name("전체채팅방");
				roomDto.setMember_id(id);
				roomDto.setMember_name(name);
				int roomRes = roomBiz.roomMemberAdd(roomDto);
				
				if (roomRes > 0) {
					response.sendRedirect("MemberController?command=member_login_page");
				} else {
					request.setAttribute("msg", "전체채팅방 입장에 실패하였습니다. 관리자에게 문의해주세요.");
					response.sendRedirect("MemberController?command=member_login_page");
				}
					
			} else {
				response.sendRedirect("index.html");
			}
			
		} else if (command.equals("member_update")) {
			System.out.println("회원정보 업데이트");
			
			
			
			
			
		} else if (command.equals("member_update_pw")){
			response.sendRedirect("member_update_pw.jsp");
			System.out.println("비밀번호 업데이트");	
			
			
			
			
			
		} else if (command.equals("member_login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDto dto = biz.login(id, biz.getSHA256(pw));
			HttpSession session = request.getSession();
			
			// 로그인 세션과 채널리스트 세션을 담아서 같이 전송한다.
			
//			if (dto != null) {
//				if(dto.getMember_type() == "ADMIN") {
//					session.setAttribute("loginDto", dto);
//					response.sendRedirect("admin.jsp");
//				} else {
//					session.setAttribute("loginDto", dto);
//					
//					response.sendRedirect("main.jsp");					
//				}				
//			}
			
			if (dto != null) {
				if(dto.getMember_type() == "ADMIN") {
					session.setAttribute("loginDto", dto);
					// 관리자는 모든 채널리스트를 list에 담아서 전송
					// 메세지리스트도 list에 담아서 전송
					response.sendRedirect("RoomController?command=channelAdminList");
				} else {
					session.setAttribute("loginDto", dto);
					// 사용자는 본인 id가 매칭된 채널의 리스트만 list에 담아서 전송
					// 메세지리스트도 list에 담아서 전송
					/*
					PAYMENT 테이블에서 PAY_TYPE 조건 확인하여 결제됬는지 안됬는지 확인하기
					if (결제한 사람 : CREDIT) {
					} else (임시체험자 : TEMP) { 
					} else(결제안한사람:NULL) {
					 */
					response.sendRedirect("RoomController?command=channelList&member_id="+id);					
				}				
			}
			
			else {
				response.sendRedirect("MemberController?command=member_login_page");
			}
			
			// 주석이다. 주석...
			// 이 부분이 수정된 부분이다.
			String test = "깃허브테스트";
			System.out.println(test);
			
			
		} else if (command.equals("idcheck")) {
			String id = request.getParameter("id");
			MemberDto dto = biz.idCheck(id);
			String str = "";
			System.out.println(id);
			if(dto != null) {
				str = "ID가 존재합니다.";
			} else {
				str = "회원 가입이 가능한 ID 입니다.";
			}
			
			PrintWriter out = response.getWriter();
			out.append(str);
			
		} else if (command.equals("member_statement")) {
			String member_num = request.getParameter("member_num");
			String member_statement = request.getParameter("member_statement");
			System.out.println(member_num);
			System.out.println(member_statement);
			
			MemberDto dto = new MemberDto();
			dto.setMember_num(Integer.parseInt(member_num));
			dto.setMember_statement(member_statement);
			
			int res = biz.updateStatement(dto);
			
		} else if (command.equals("member_numdata")) {
			HttpSession session = request.getSession();
			MemberDto dto = (MemberDto)session.getAttribute("loginDto");
			PrintWriter out = response.getWriter();
			out.append(""+dto.getMember_num());
			
		} else if (command.equals("naverlogin")) {
			
			String clientId = "BAEN9bUjlj2M_oHlFbAi";
			String clientSecret = "i9kW1qj1jF"; 
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			String redirectURI = URLEncoder.encode("main.jsp","UTF-8");
					
			StringBuffer apiURL = new StringBuffer();
			apiURL.append("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&");
			apiURL.append("client_id=" + clientId);
			apiURL.append("&client_secret=" + clientSecret);
			apiURL.append("&redirect_uri=" + redirectURI);
			apiURL.append("&code=" + code);
			apiURL.append("&state=" + state);
			String access_token = "";
			String refresh_token = ""; //나중에 이용합시다
					
			try { 
				  URL url = new URL(apiURL.toString());
			      HttpURLConnection con = (HttpURLConnection)url.openConnection();
			      con.setRequestMethod("GET");
			      int responseCode = con.getResponseCode();
			      BufferedReader br;
			      System.out.print("responseCode="+responseCode);
			      if(responseCode==200) { // 정상 호출
			        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			      } else {  // 에러 발생
			        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			      }
			      String inputLine;
			      StringBuffer res = new StringBuffer();
			      while ((inputLine = br.readLine()) != null) {
			        res.append(inputLine);
			      }
			      br.close();
			      if(responseCode==200) {
			    	System.out.println(res.toString());
			    	JsonElement element = JsonParser.parseString(res.toString());
			    	JsonObject jsonData = element.getAsJsonObject();
			    				    	
			    	access_token = jsonData.get("access_token").getAsString();
			    	refresh_token = jsonData.get("refresh_token").getAsString();
			    	
			      }
			    } catch (Exception e) {
			      System.out.println(e);
			    }
				if(access_token != null) { // access_token을 잘 받아왔다면
					try {
						 String header = "Bearer " + access_token;
						 String apiurl = "https://openapi.naver.com/v1/nid/me";
						 URL url = new URL(apiurl);
						HttpURLConnection con = (HttpURLConnection)url.openConnection();
						con.setRequestMethod("GET");
						con.setRequestProperty("Authorization", header);
						int responseCode = con.getResponseCode();
						BufferedReader br;
						if(responseCode==200) { // 정상 호출
						 br = new BufferedReader(new InputStreamReader(con.getInputStream()));
						} else {  // 에러 발생
						br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
						}
						String inputLine;
						StringBuffer res = new StringBuffer();
						 while ((inputLine = br.readLine()) != null) {
						res.append(inputLine);
						}
						br.close();
						
						JsonElement element = JsonParser.parseString(res.toString());
						JsonObject jsonData = element.getAsJsonObject();
						JsonElement records = jsonData.get("response");	
												
						String id = records.getAsJsonObject().get("id").getAsString();
						String nickname = records.getAsJsonObject().get("nickname").getAsString();
						String email = records.getAsJsonObject().get("email").getAsString();
						
						request.setAttribute("id", id);
						request.setAttribute("nickname", nickname);
						request.setAttribute("email", email);
						request.setAttribute("records", records);
						request.setAttribute("access_token", access_token);
						request.setAttribute("refresh_token", refresh_token);
						
						RequestDispatcher dispatch = request.getRequestDispatcher("main.jsp");
						dispatch.forward(request, response);
												
				    } catch (Exception e) {
				    	e.printStackTrace();
				    }
				}
		} else if(command.equals("googlelogin")) {
			
			String code = request.getParameter("code");
			String query = "code=" + code;
			query += "&client_id=" + "533483186463-e0gd75qd2j8d8pko7p48mvee4md6p0d5.apps.googleusercontent.com";
			query += "&client_secret=" + "MFAidZ3fXwmpY3cdAW0FYBA1";
			query += "&redirect_uri=" + "http://localhost:8787/Channel/MemberController?command=googlelogin";
			query += "&grant_type=authorization_code";
			String tokenJson = getHttpConnection("https://accounts.google.com/o/oauth2/token", query);
			System.out.println(tokenJson.toString());
			Gson gson = new Gson();
			MemberGoogleDto token = gson.fromJson(tokenJson, MemberGoogleDto.class);
			
			String retUri = "https://www.googleapis.com/oauth2/v1/userinfo?access_token="+token.getAccess_token();
			
			String ret = getHttpConnection(retUri);
			System.out.println(ret);
			
			JsonElement element = JsonParser.parseString(ret);
			JsonObject jsonData = element.getAsJsonObject();
			
			System.out.println(jsonData);
			
			String id = jsonData.get("id").getAsString();
			System.out.println(id);
			String email = jsonData.get("email").getAsString();
			System.out.println(email);
			String picture = jsonData.get("picture").getAsString();
			System.out.println(picture);
			
			request.setAttribute("google_id", id);
			request.setAttribute("google_email", email);
			request.setAttribute("google_picture", picture);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
		
		
		
		
		
		
	}	

	private String getHttpConnection(String uri) throws ServletException, IOException {
		URL url = new URL(uri);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int responseCode = conn.getResponseCode();
		System.out.println(responseCode);
		String line;
		StringBuffer buffer = new StringBuffer();
		try (InputStream stream = conn.getInputStream()) {
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
				while ((line = rd.readLine()) != null) {
					buffer.append(line);
					buffer.append('\r');
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	private String getHttpConnection(String uri, String param) throws ServletException, IOException {
		URL url = new URL(uri);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		try (OutputStream stream = conn.getOutputStream()) {
			try (BufferedWriter wd = new BufferedWriter(new OutputStreamWriter(stream))) {
				wd.write(param);
			}
		}
		int responseCode = conn.getResponseCode();
		System.out.println(responseCode);
		String line;
		StringBuffer buffer = new StringBuffer();
		try (InputStream stream = conn.getInputStream()) {
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
				while ((line = rd.readLine()) != null) {
					buffer.append(line);
					buffer.append('\r');
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
