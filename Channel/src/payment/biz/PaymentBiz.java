package payment.biz;

import payment.dao.PaymentDao;
import payment.dto.PaymentDto;

public class PaymentBiz {

	PaymentDao dao = new PaymentDao();
	
	public int paymentres(PaymentDto dto) {
			
		return dao.paymentres(dto);
	}
	

}
