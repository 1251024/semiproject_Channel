package payment.dao;

import org.apache.ibatis.session.SqlSession;

import payment.db.SqlMapConfig;
import payment.dto.PaymentDto;

public class PaymentDao extends SqlMapConfig{

	
	public int paymentres(PaymentDto dto) {
        int res = 0;

        try(SqlSession session = getSqlSessionFactory().openSession(true);){
            res = session.insert("paymentmapper.paymentres", dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    
	}
}
