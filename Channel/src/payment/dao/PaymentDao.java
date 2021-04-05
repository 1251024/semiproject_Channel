package payment.dao;


import payment.dto.PaymentDto;

public interface PaymentDao {

	//결제내역 추가
	public int insertcredit(PaymentDto paydto);

	//해당 아이디가 가진 채널 출력
	public PaymentDto selectPaystate(int pay_member_no);

	
	
}
