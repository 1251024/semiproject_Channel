<%@page import="payment.biz.PaymentBizImpl"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="channel.member.dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="payment.biz.PaymentBiz"%>
<%@page import="payment.dto.PaymentDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- include 영역 -->
	<%@ include file="common.jsp"%>

	<!-- 결제여부확인 메소드  -->
	<%
	
	MemberDto memberdto =(MemberDto)session.getAttribute("loginDto");//(형변환)오브젝트상태로가져오는것-세션가져오기

		int id = memberdto.getMember_num();
		

	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date sysdate = new Date();

	
	
	PaymentBiz paybiz = new PaymentBizImpl();//paylist가져오기위해 비즈 가져온것
	List<PaymentDto> paylist = new ArrayList<>();
	paylist=paybiz.paymentList(id); // 보옥씨 payment 전체 리스트.
	//System.out.println("paylist"+paylist.get(0).toString());



		if (memberdto != null) {
			if (memberdto.getMember_type() == "ADMIN") {
				// admin.jsp
%>
				<!-- 관리자 페이지 -->	
				<h1>관리자 페이지!</h1>
					CHANNEL에 오신 것을 환영합니다.<br /> 
					관리자 페이지 입니다.<br />
					관리자모드에서 관리하세요.<br />
					
				<a href="admin.jsp"></a>

<%				
				
				
			} else {
				//일반 유저 구역.
				if (paylist.get(0).getPay_member_no()==(memberdto.getMember_num())) { // 로그인한 녀석이랑 같은 번호 녀석.<-결제정보
					//로그인한 멤버넘버가 페이먼트 테이블에 있다면
					String today = sf.format(sysdate);
					System.out.println("today:"+today);//오늘날짜
					
					String payday = sf.format(paylist.get(0).getPay_date());
					System.out.println("결제날짜:"+payday);//결제날짜
					
					//String pday = paydto.getPay_date();
					//Date payday = Date.valueOf(paydto.getPay_date());
					//payUtil putil = new payUtil();
					
					//Date pdate = valueOf​(paydto.getPay_date());
	//				java.sql.Date dd=  java.sql.Date .valueOf(paydto.getPay_date());
					
					
					//심플데이트포맷으로 둘다변경
					Date ddate = sf.parse(today);
					Date ppdate = sf.parse(payday);
					
					//long으로 
					long longDate = ddate.getTime() - ppdate.getTime();
					long longDays = longDate / (24 * 60 * 60 * 1000);
					//longDays = Math.abs(longDays);
					
					//언젠가 결제한적있음.
					System.out.println("차이"+longDays);
						
						if(longDays>30){
							//결제한지 30일지나면 다시결제해라
							if(paylist.get(0).getPay_type().equals("TEMP")){
								//페이타입이 temp면 체험판 끝 결제창
%>
								<!-- 무료체험 종료 후 결제 페이지 -->	
								<h1>무료체험 종료</h1>
									CHANNEL에 오신 것을 환영합니다.<br /> 
									채널에서 workspace를 추가하고, 함께할 동료들을 추가해보세요.<br />
									1대1 채팅, 단체 채팅으로 함께 의견을 나누고 협업하세요.<br />
									CHANNEL을 추가로 이용하시려면 결제해주세요.<br />
									
									<a href="pay_credit_checkout.jsp">첫 결제하러 가기</a>

<%	
							}else{
								//아니면 페이타입이 credit일때는 결제창
%>
								<!-- 이용종료 안내 후 추가 결제 페이지 -->	
								<h1>30일이 지났어요!</h1>
									CHANNEL에 오신 것을 환영합니다.<br /> 
									채널에서 workspace를 추가하고, 함께할 동료들을 추가해보세요.<br />
									1대1 채팅, 단체 채팅으로 함께 의견을 나누고 협업하세요.<br />
									CHANNEL을 추가로 이용하시려면 결제해주세요.<br />
									
									<a href="pay_credit_checkout.jsp">결제하러 가기</a>

<%	
												
							}	
						}else{
							//결제한지 30일 이내 --채널 이용 ㄱㄱ
							response.sendRedirect("workspace.jsp");
						}
					}else{
%>
						<!-- 체험판 결제 페이지 -->	
						<h1>Welcome!</h1>
							CHANNEL에 오신 것을 환영합니다.<br /> 
							채널에서 workspace를 추가하고, 함께할 동료들을 추가해보세요.<br />
							1대1 채팅, 단체 채팅으로 함께 의견을 나누고 협업하세요.<br />
							
							
							<a href="pay_trial_ordersheet">체험판 결제하기</a><br />
							<a href="pay_credit_checkout.jsp">신규이용 결제하기</a>

<%	
					}
					
				
			}
		}
		
	

	
%>
	

</body>
</html>