package payment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import channel.member.dto.MemberDto;
import payment.biz.PaymentBiz;
import payment.biz.PaymentBizImpl;
import payment.dto.PaymentDto;

@WebServlet("/PaymentController.do")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaymentController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PaymentBiz paymentbiz = new PaymentBizImpl();

		String command = request.getParameter("command");

		if (command.equals("payment")) {
			// 보내준값받기
			String pay_price = request.getParameter("amount");
			String pay_email = request.getParameter("buyer_email");
			String pay_name = request.getParameter("buyer_name");
			String pay_phone = request.getParameter("buyer_tel");

			// 디비 호출
			PaymentDto dto = new PaymentDto();
			dto.setPay_price(pay_price);
			dto.setPay_email(pay_email);
			dto.setPay_name(pay_name);
			dto.setPay_phone(pay_phone);

			System.out.println(dto.getPay_price());
			System.out.println(dto.getPay_email());
			System.out.println(dto.getPay_name());
			System.out.println(dto.getPay_phone());

			int res = paymentbiz.insertcredit(dto);

			/*
			 * pay_credit_checkout.jsp에서 페이지 경로이동이 선으로 진행됨 if (res > 0) {
			 * response.sendRedirect("pay_res.jsp");
			 * System.out.println("servlet 결제성공-pay_res로 이동");
			 * 
			 * } else { response.sendRedirect("pay_credit_ordersheet.jsp");
			 * System.out.println("servlet-결제실패"); }
			 */
			System.out.println("servlet price:" + pay_price);

			
		} else if (command.equals("paymentList")) {
			//전체 리스트--테이블 결과 -payment 테이블 전체 row
			HttpSession session = request.getSession();
			MemberDto memberdto =(MemberDto)session.getAttribute("loginDto");
			int id = memberdto.getMember_num();
			System.out.println("id in Controller"+id);
		    //list객체에 id를 담아서
			List<PaymentDto> paylist = paymentbiz.paymentList(id);
			
			if (paylist != null) {//payment리스트가 있으면
			request.setAttribute("paylist", paylist);
			dispatch(request, response, "pay_res.jsp");
			System.out.println("paycontroller-paymentList로 로그인 세션값이 넘어오나..?");

			}else {
				System.out.println("11");
			}

		} else if (command.equals("pay_res")) {
			response.sendRedirect("pay_res.jsp");
			System.out.println("servlet-payment-res");

		} else if (command.equals("pay_error")) {
			response.sendRedirect("index.html");
			System.out.println("에러에러");

		}
		System.out.println("??");
	}

	public void dispatch(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);

	}

}
