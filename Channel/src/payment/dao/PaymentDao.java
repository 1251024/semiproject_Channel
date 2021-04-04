package payment.dao;

import java.util.List;

import payment.dto.PaymentDto;

public interface PaymentDao {

	//결제내역 추가
	public int insertcredit(PaymentDto dto);

	//해당 아이디가 가진 채널 출력
	public List<PaymentDto> paymentList(int id);
	
	
}
