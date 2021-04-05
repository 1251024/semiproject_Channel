package payment.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

			// checkout에서 rsp.success할 때 보내준값받기
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			String pay_name = request.getParameter("buyer_name");
			String pay_price = request.getParameter("amount");
			String pay_email = request.getParameter("buyer_email");
			String pay_phone = request.getParameter("buyer_tel");
			String pay_type = request.getParameter("pay_type");

			// 디비 호출해서 paydto에 넣어주기
			PaymentDto paydto = new PaymentDto();
			paydto.setPay_member_no(member_num);
			paydto.setPay_name(pay_name);
			paydto.setPay_price(pay_price);
			paydto.setPay_email(pay_email);
			paydto.setPay_phone(pay_phone);
			paydto.setPay_type(pay_type);

			System.out.println("servlet paydto:" + paydto.getPay_member_no());
			System.out.println("servlet paydto:" + paydto.getPay_name());
			System.out.println("servlet paydto:" + paydto.getPay_price());
			System.out.println("servlet paydto:" + paydto.getPay_email());
			System.out.println("servlet paydto:" + paydto.getPay_phone());
			System.out.println("servlet paydto:" + paydto.getPay_type());

			// payment insert
			int res = paymentbiz.insertcredit(paydto);
			System.out.println("res:" + res);
		} else if (command.equals("trial")) {
		
			//HttpSession session = request.getSession();
			//MemberDto memberdto =(MemberDto)session.getAttribute("loginDto");
			
			
			// welcome 보내준값받기
			
			int member_num = Integer.parseInt(request.getParameter("member_num"));//
			String pay_name = request.getParameter("pay_name");//
			String pay_price = request.getParameter("pay_price");
			String pay_email = request.getParameter("pay_email");//
			String pay_phone = request.getParameter("pay_phone");//
			String pay_type = request.getParameter("pay_type");
			
			System.out.println("trial_받은값_member_num  : "+member_num);
			System.out.println("trial_받은값_pay_name  : "+pay_name);
			System.out.println("trial_받은값_pay_price  : "+pay_price);
			System.out.println("trial_받은값_pay_email  : "+pay_email);
			System.out.println("trial_받은값_pay_phone  : "+pay_phone);
			System.out.println("trial_받은값_pay_type  : "+pay_type);
			
			
			// 디비 호출해서 paydto에 넣어주기
			PaymentDto paydto = new PaymentDto();
			paydto.setPay_member_no(member_num);
			paydto.setPay_name(pay_name);
			paydto.setPay_price(pay_price);
			paydto.setPay_email(pay_email);
			paydto.setPay_phone(pay_phone);
			paydto.setPay_type(pay_type);

			System.out.println("trial memberno paydto:" + paydto.getPay_member_no());
			System.out.println("trial name paydto:" + paydto.getPay_name());
			System.out.println("trial price paydto:" + paydto.getPay_price());
			System.out.println("trial email paydto:" + paydto.getPay_email());
			System.out.println("trial phone paydto:" + paydto.getPay_phone());
			System.out.println("trial type paydto:" + paydto.getPay_type());

			//3. 화면에 전달할 값이 있으면, request 객체에 담아준다.
			int res = paymentbiz.insertcredit(paydto);
			System.out.println("res:" + res);
			//4. 보낸다.
			response.sendRedirect("workspace.jsp");
			System.out.println("trial_이용");

		} else if (command.equals("pay_res")) {
			int member_num = Integer.parseInt(request.getParameter("member_num"));
			String pay_name = request.getParameter("name");
			String pay_price = request.getParameter("totalPrice");
			String pay_email = request.getParameter("email");
			String pay_phone = request.getParameter("phone");
			String pay_type = request.getParameter("type");

			Date paydate = new Date();
			PaymentDto paydto = new PaymentDto();
			paydto.setPay_member_no(member_num);
			paydto.setPay_name(pay_name);
			paydto.setPay_price(pay_price);
			paydto.setPay_email(pay_email);
			paydto.setPay_phone(pay_phone);
			paydto.setPay_type(pay_type);
			paydto.setPay_date(paydate);

			// 보낼때 이름지어서 보내기
			request.setAttribute("paydto", paydto);

			dispatch(request, response, "pay_res.jsp");
			// response.sendRedirect("pay_res.jsp");

			System.out.println("결과페이지 만들기");
			System.out.println("paydtocontroller:" + paydto.getPay_name());

		} else {
			response.sendRedirect("welcome.jsp");
			System.out.println("pay_error");

		}

	}

	public void dispatch(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);

	}

}
