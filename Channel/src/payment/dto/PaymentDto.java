package payment.dto;

import java.util.Date;


public class PaymentDto {

	private int pay_member_no;
	private int pay_no;
	private String pay_name;
	private String pay_email;
	private String pay_phone;
	private String pay_type;
	private String pay_price;
	private Date pay_date;

	public PaymentDto() {

	}

	public PaymentDto(int pay_member_no, int pay_no, String pay_name, String pay_email, String pay_phone,
			String pay_type, String pay_price, Date pay_date) {

		this.pay_member_no = pay_member_no;
		this.pay_no = pay_no;
		this.pay_name = pay_name;
		this.pay_email = pay_email;
		this.pay_phone = pay_phone;
		this.pay_type = pay_type;
		this.pay_price = pay_price;
		this.pay_date = pay_date;
	}

	public int getPay_member_no() {
		return pay_member_no;
	}

	public void setPay_member_no(int pay_member_no) {
		this.pay_member_no = pay_member_no;
	}

	public int getPay_no() {
		return pay_no;
	}

	public void setPay_no(int pay_no) {
		this.pay_no = pay_no;
	}

	public String getPay_name() {
		return pay_name;
	}

	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}

	public String getPay_email() {
		return pay_email;
	}

	public void setPay_email(String pay_email) {
		this.pay_email = pay_email;
	}

	public String getPay_phone() {
		return pay_phone;
	}

	public void setPay_phone(String pay_phone) {
		this.pay_phone = pay_phone;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getPay_price() {
		return pay_price;
	}

	public void setPay_price(String pay_price) {
		this.pay_price = pay_price;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}

	@Override
	public String toString() {
		return "PaymentDto [pay_member_no=" + pay_member_no + ", pay_no=" + pay_no + ", pay_name=" + pay_name
				+ ", pay_email=" + pay_email + ", pay_phone=" + pay_phone + ", pay_type=" + pay_type + ", pay_price="
				+ pay_price + ", pay_date=" + pay_date + "]";
	}

}
