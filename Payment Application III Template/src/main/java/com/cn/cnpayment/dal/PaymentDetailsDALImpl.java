package com.cn.cnpayment.dal;

import com.cn.cnpayment.entity.PaymentDetails;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import com.cn.cnpayment.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 # Complete the PaymentDetailsDALImpl class as mentioned below:

 	a. Autowire EntityManager.

 	b. Override the following methods:

 		1. getById(int id): This method fetches PaymentDetails for a specific id from the database.

	 	2. getAllPaymentDetails(): This method fetches the list of PaymentDetails from the database.

	 	3. save(PaymentDetails paymentDetails): This method saves the PaymentDetails entity into the database.

	 	4. delete(int id): This method deletes the PaymentDetails entity for a specific id.

	 	5. update(PaymentDetails paymentDetails): This method updates paymentDetails.

	 	6. getByCurrency(String currency): This method fetches the list of PaymentDetails from the database for
                                           a specific currency.
 **/


@Repository
public class PaymentDetailsDALImpl implements PaymentDetailsDAL {

	@Autowired
	EntityManager entityManager;

	@Override
	public PaymentDetails getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		PaymentDetails paymentDetails= session.get(PaymentDetails.class, id);
//		if (paymentDetails==null) throw new NotFoundException("Payment Details Not Found");
		return paymentDetails;
	}

	@Override
	public void save(PaymentDetails paymentDetails) {
		PaymentDetails paymentDetails1 = getById(paymentDetails.getId());
//todo logic ?
		//		if (paymentDetails1!=null) throw new ElementAlreadyExistException("Payment Details already exist");
		Session session = entityManager.unwrap(Session.class);
		session.save(paymentDetails);
	}

	@Override
	public void delete(int id) {
		PaymentDetails p = getById(id);
//		if (p != null) {
			Session session = entityManager.unwrap(Session.class);
			session.delete(p);
//		}
	}

	@Override
	public List<PaymentDetails> getAllPaymentDetails() {
		Session session = entityManager.unwrap(Session.class);
		List<PaymentDetails> paymentDetails = session.createQuery("from  PaymentDetails", PaymentDetails.class).getResultList();
//		if (paymentDetails.isEmpty()) throw new NotFoundException("No Payment exist");
		return paymentDetails;
	}

	@Override
	public void update(PaymentDetails paymentDetails) {
		PaymentDetails p = getById(paymentDetails.getId());
		p.setAmount(paymentDetails.getAmount());
		p.setCurrency(paymentDetails.getCurrency());
		p.setCreditAccount(paymentDetails.getCreditAccount());
		p.setDebitAccount(paymentDetails.getDebitAccount());
		Session session = entityManager.unwrap(Session.class);
		session.update(p);
	}

	@Override
	public List<PaymentDetails> getByCurrency(String currency) {
		List<PaymentDetails> allPayDetails = getAllPaymentDetails();
		List<PaymentDetails> result = new ArrayList<>();
		for (PaymentDetails paymentDetails: allPayDetails) {
			if (paymentDetails.getCurrency().equalsIgnoreCase(currency)) result.add(paymentDetails);
		}
		return result;
	}

	// Auto-wire the EntityManager object


}
