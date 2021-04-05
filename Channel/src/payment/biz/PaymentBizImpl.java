package payment.biz;



import payment.dao.PaymentDao;
import payment.dao.PaymentDaoImpl;
import payment.dto.PaymentDto;

public class PaymentBizImpl implements PaymentBiz {

	PaymentDao dao = new PaymentDaoImpl();
	
	
	@Override
	public int insertcredit(PaymentDto paydto) {
		//System.out.println("bizimpl완료");
		return dao.insertcredit(paydto);
	}


	@Override
	public PaymentDto selectPaystate(int pay_member_no) {
		return dao.selectPaystate(pay_member_no);
	}


}
