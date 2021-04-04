package payment.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import payment.db.SqlMapConfig;
import payment.dto.PaymentDto;

public class PaymentDaoImpl extends SqlMapConfig implements PaymentDao {

	private String namespace = "paymentmapper.";
	
	@Override
	public int insertcredit(PaymentDto dto) {
		int res = 0; 
		try(SqlSession session = getSqlSessionFactory().openSession(true);){
			res = session.insert(namespace + "insertcredit", dto);
			         
			}catch(Exception e) {
			 e.printStackTrace();
			  }
			      
		return res;
	}

	@Override
	public List<PaymentDto> paymentList(int id) {
		
		List<PaymentDto> list = new ArrayList<PaymentDto>();
		
		SqlSession session = null;
		
		try {
		session = getSqlSessionFactory().openSession(false);
		list = session.selectList("paymentList",id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

}
