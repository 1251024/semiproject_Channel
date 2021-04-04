package payment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.biz.PaymentBiz;
import payment.dto.PaymentDto;

@WebServlet("/PaymentController")
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

		PaymentBiz biz = new PaymentBiz();

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

			int res = biz.paymentres(dto);

			if (res > 0) {
				System.out.println("servlet 결제성공-paycheck로 이동");
				response.sendRedirect("paymentcheck.jsp");
			} else {
				response.sendRedirect("boardlist.jsp");
			}

			System.out.println("servlet price:" + pay_price);
			
		}else if(command.equals("paymentcheck")) {
			response.sendRedirect("paymentcheck.jsp");

			
		}else if(command.equals("payerror")) {
			response.sendRedirect("payindex.jsp");

		}
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(path);
		dispatch.forward(request, response);
	}

	private void jsResponse(HttpServletResponse response, String url, String msg) throws IOException {
		String s = "<script type = 'text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";
		response.getWriter().print(s);
	}
}
