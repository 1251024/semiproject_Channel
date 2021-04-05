package payment.dao;


import org.apache.ibatis.session.SqlSession;

import payment.db.SqlMapConfig;
import payment.dto.PaymentDto;

public class PaymentDaoImpl extends SqlMapConfig implements PaymentDao {

	private String namespace = "paymentmapper.";

	@Override
	public int insertcredit(PaymentDto paydto) {
		int res = 0;
		try (SqlSession session = getSqlSessionFactory().openSession(true);) {
			res = session.insert(namespace + "insertcredit", paydto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("daoimpl");
		return res;
	}

	@Override
	public PaymentDto selectPaystate(int pay_member_no) {

		PaymentDto paydto = new PaymentDto();

		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(false);
			paydto = session.selectOne(namespace + "paymentList", pay_member_no);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return paydto;
	}


}
