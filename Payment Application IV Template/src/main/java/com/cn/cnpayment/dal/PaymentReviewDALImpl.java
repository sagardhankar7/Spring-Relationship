package com.cn.cnpayment.dal;


import com.cn.cnpayment.entity.PaymentDetails;
import com.cn.cnpayment.entity.PaymentReview;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PaymentReviewDALImpl implements PaymentReviewDAL {
	@Autowired
	EntityManager entityManager;

	@Override
	public PaymentReview getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		PaymentReview paymentReview = session.get(PaymentReview.class, id);
		return paymentReview;
	}

	@Override
	public void save(PaymentReview paymentDetails) {
		Session session = entityManager.unwrap(Session.class);
		session.save(paymentDetails);
	}

	@Override
	public void delete(int id) {
		PaymentReview pr = getById(id);
		Session session = entityManager.unwrap(Session.class);
		session.delete(pr);
	}

	@Override
	public List<PaymentReview> getAllPaymentReview() {
		Session session = entityManager.unwrap(Session.class);
		List<PaymentReview> list = session.createQuery("from PaymentReview", PaymentReview.class).getResultList();
		return list;
	}

	@Override
	public List<PaymentReview> getByQueryType(String queryType) {
		List<PaymentReview> allPR = getAllPaymentReview();
		List<PaymentReview> result = new ArrayList<>();
		for (PaymentReview pr: allPR) {
			if (pr.getQueryType().equalsIgnoreCase(queryType)) result.add(pr);
		}

		return result;
	}

/**

 Complete the PaymentReviewDALImpl implementation class as mentioned below:

 	a. Autowire EntityManager.

 	b. Override the following methods:

 		1. getById(int id): This method fetches PaymentReview for a specific id.

 		2. getAllPaymentReview(): This method fetches the list of all PaymentReview from the database.

 		3. save(PaymentReview paymentReview): This method saves the PaymentReview entity into the database.

 		4. delete(int id): This method deletes the PaymentReview entity from the database for a specific id.

 		5. getByQueryType(String queryType): This method fetches the list of PaymentReview based on the
                                             queryType received.

 **/

}
