package payment.biz;



import payment.dto.PaymentDto;

public interface PaymentBiz {

	//결제내역 추가
	public int insertcredit(PaymentDto paydto);
	
	
	//해당 아이디가 가진 채널 출력 결제상태확인리스트
	public PaymentDto selectPaystate(int pay_member_no);
	
	
	
}
