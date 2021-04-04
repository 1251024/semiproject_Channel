package payment.biz;

import java.util.List;

import payment.dao.PaymentDao;
import payment.dao.PaymentDaoImpl;
import payment.dto.PaymentDto;

public class PaymentBizImpl implements PaymentBiz {

	PaymentDao dao = new PaymentDaoImpl();
	
	
	@Override
	public int insertcredit(PaymentDto dto) {
		System.out.println("bizimpl완료");
		return dao.insertcredit(dto);
	}



	@Override
	public List<PaymentDto> paymentList(int id) {
		return dao.paymentList(id);
	}

}
