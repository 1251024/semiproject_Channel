<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
    
<%
	 //어트리뷰트만 오브젝트타입이라 형변환 필요
	 int member_num = Integer.parseInt(request.getParameter("member_num"));
     String name = request.getParameter("name");
     String email = request.getParameter("email");
     String phone = request.getParameter("phone");
     String type = request.getParameter("type");
     String totalPrice = request.getParameter("totalPrice");
    
     System.out.println("member_num: "+member_num);
     System.out.println("name: "+name);
     System.out.println("email: "+email);
     System.out.println("phone: "+phone);
     System.out.println("type: "+type);
     System.out.println("totalPrice: "+totalPrice);
     System.out.println("ordersheet에서 checkout으로 받아온값 완료");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>kakao</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>

	<input type="hidden" id="pay_member_num" value="<%=member_num%>"/>
	<input type="hidden" id="pay_name" value="<%=name%>"/>
	<input type="hidden" id="pay_email" value="<%=email%>"/>
	<input type="hidden" id="pay_phone" value="<%=phone%>"/>
	<input type="hidden" id="pay_type" value="<%=type%>"/>
	<input type="hidden" id="pay_totalPrice" value="<%=totalPrice%>"/>

    <script>
    var  member_num = $("#pay_member_num").val();
    var  name = $("#pay_name").val();
    var  email = $("#pay_email").val();
    var  phone = $("#pay_phone").val();
    var  type = $("#pay_type").val();
    var  totalPrice = $("#pay_totalPrice").val();
    $(function(){
    	
    	
        var IMP = window.IMP; // 생략가능
        IMP.init('imp98397380'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : 'CHANNEL 이용료 결제',
            buyer_name : name,
            buyer_email : email,
            buyer_tel : phone,
            pay_type : type,
            	amount : totalPrice
            //m_redirect_url : 'http://www.naver.com'
        }, function(rsp) {
            if ( rsp.success ) {
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                jQuery.ajax({
                    url: "PaymentController.do", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    dataType: 'json',
                    data: {
                    	command : "payment",
                        imp_uid : rsp.imp_uid,
                        member_num : member_num,
                        buyer_name : name,
                    	buyer_email : email,
                  	  	buyer_tel : phone,
                  	  	pay_type : type,
                  	  	amount : totalPrice
                        //기타 필요한 데이터가 있으면 추가 전달
                    }
                }).done(function(data) {
                    //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                    if ( everythings_fine ) {
                        msg = '결제가 완료되었습니다.';
                        msg += '\n고유ID : ' + rsp.imp_uid;
                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                        msg += '\결제 금액 : ' + rsp.paid_amount;
                        msg += '카드 승인번호 : ' + rsp.apply_num;
                        
                        alert(msg);
                    } else {
                        //[3] 아직 제대로 결제가 되지 않았습니다.
                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                    }
                });
                //성공시 이동할 페이지
                alert("결제 성공!")
                <%--
                location.href="PaymentController.do?command=pay_res";	
                --%>

            	
            	location.href="PaymentController.do?command=pay_res&member_num="+member_num+"&name="+name+"&email="+email+"&phone="+phone+"&type="+type+"&totalPrice="+totalPrice;
            			
            	
               
                
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                location.href="pay_error.jsp";
                alert(msg);
            }
        });
        
    });
    </script> 
    

    
    
</body>
</html>