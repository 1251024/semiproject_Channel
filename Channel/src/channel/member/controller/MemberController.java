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
import java.util.ArrayList;
import java.util.List;

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
				response.sendRedirect("MemberController?command=member_login_page");
			} else {
				response.sendRedirect("index.html");
			}
			
		} else if (command.equals("member_update")) {
			
			String member_num = request.getParameter("member_num");
			String member_id = request.getParameter("member_id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String pscode = request.getParameter("pscode");
			String addr = request.getParameter("addr");
			String addrdt = request.getParameter("addrdt");
			
			MemberDto dto = new MemberDto();
			
			dto.setMember_num(Integer.parseInt(member_num));
			dto.setMember_id(member_id);
			dto.setMember_name(name);
			dto.setMember_email(email);
			dto.setMember_phone(phone);
			dto.setMember_pscode(pscode);
			dto.setMember_addr(addr);
			dto.setMember_addrdt(addrdt);
			
			int res = biz.updateUser(dto);
			MemberDto updateDto = biz.idCheck(member_id);
			HttpSession session = request.getSession();
			
			if(res > 0) {
				session.setAttribute("loginDto", updateDto);
				response.sendRedirect("welcome.jsp");
			} else {
				response.sendRedirect("member_update.jsp");
			}							
			
		} else if (command.equals("member_update_pw_page")){
			response.sendRedirect("member_update_pw.jsp");
			
		} else if (command.equals("member_update_pw")) {
			
			String num = request.getParameter("member_num");
			String id = request.getParameter("member_id");
			String pw_old = request.getParameter("pw_old");
			String pw_new = request.getParameter("pw_new");
			String pw_new2 = request.getParameter("pw_new2");
			
			MemberDto dto = biz.idCheck(id);
			int res = 0;
			HttpSession session = request.getSession();
			
			if(dto != null) {
				if(dto.getMember_pw().equals(biz.getSHA256(pw_old))) {
					if(pw_new.equals(pw_new2)) {
						MemberDto updatePwDto = new MemberDto();
						updatePwDto.setMember_num(Integer.parseInt(num));
						updatePwDto.setMember_pw(biz.getSHA256(pw_new));
						res = biz.updatePw(updatePwDto);
					}					
				}				
			}
			
			if(res > 0) {			
				MemberDto newPwDto = biz.idCheck(id);
				session.setAttribute("loginDto", newPwDto);
				response.sendRedirect("welcome.jsp");
			} else {
				response.sendRedirect("member_update_pw.jsp");
			}
						
		} else if (command.equals("member_login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDto dto = biz.login(id, biz.getSHA256(pw));
			HttpSession session = request.getSession();
			
			
			if (dto != null) {
				if(dto.getMember_type() == "ADMIN") {
					session.setAttribute("loginDto", dto);
					response.sendRedirect("welcome.jsp");
				} else {
					session.setAttribute("loginDto", dto);
					response.sendRedirect("welcome.jsp");					
				}				
			} else {
				response.sendRedirect("MemberController?command=member_login_page");
			}
						
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
			out.append(dto.getMember_num()+"");
			
		} else if (command.equals("naverlogin")) {
			
			String clientId = "BAEN9bUjlj2M_oHlFbAi";
			String clientSecret = "i9kW1qj1jF"; 
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			String redirectURI = URLEncoder.encode("welcome.jsp","UTF-8");
					
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
			    	//System.out.println(res.toString());
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
						
						MemberDto naverDto = biz.idCheck(email);
						HttpSession session = request.getSession();
						
						if(naverDto != null) {
							session.setAttribute("loginDto", naverDto);
							response.sendRedirect("welcome.jsp");							
						} else {
							int naverRes = biz.naverLoginInsert(email);
							if(naverRes > 0) {
								MemberDto naverLoginDto = biz.idCheck(email);
								session.setAttribute("loginDto", naverLoginDto);
								response.sendRedirect("welcome.jsp");
							} else {
								response.sendRedirect("MemberController?command=member_login_page");
							}
							
						}
												
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
			//System.out.println(tokenJson.toString());
			Gson gson = new Gson();
			MemberGoogleDto token = gson.fromJson(tokenJson, MemberGoogleDto.class);
			
			String retUri = "https://www.googleapis.com/oauth2/v1/userinfo?access_token="+token.getAccess_token();
			
			String ret = getHttpConnection(retUri);
			//System.out.println(ret);
			
			JsonElement element = JsonParser.parseString(ret);
			JsonObject jsonData = element.getAsJsonObject();
			
			String id = jsonData.get("id").getAsString();
			String email = jsonData.get("email").getAsString();
			String picture = jsonData.get("picture").getAsString();
						
			MemberDto googleDto = biz.idCheck(email);
			HttpSession session = request.getSession();
			
			if(googleDto != null) {
				session.setAttribute("loginDto", googleDto);
				response.sendRedirect("welcome.jsp");							
			} else {
				int googleRes = biz.googleLoginInsert(email);
				if(googleRes > 0) {
					MemberDto googleLoginDto = biz.idCheck(email);
					session.setAttribute("loginDto", googleLoginDto);
					response.sendRedirect("welcome.jsp");
				} else {
					response.sendRedirect("MemberController?command=member_login_page");
				}				
			}			
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
