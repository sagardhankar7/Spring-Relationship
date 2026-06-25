package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.PaymentReviewDAL;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import com.cn.cnpayment.exception.InvalidInputException;
import com.cn.cnpayment.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cn.cnpayment.entity.PaymentReview;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PaymentReviewService {

// Autowire the PaymentReviewDAL object.
	@Autowired
	PaymentReviewDAL paymentReviewDAL;

	@Transactional
	public PaymentReview getPaymentReviewById(int id) {
		PaymentReview pr = paymentReviewDAL.getById(id);
		if (pr==null) throw new NotFoundException("No Payment review Found");
		/**
		   1. This method fetches PaymentReview for a specific id.
		   2. If no paymentReview is found then it throws NotFoundException.
		**/
		return pr;
	}

	@Transactional
	public List<PaymentReview> getAllPaymentReviews() {
		List<PaymentReview> list = paymentReviewDAL.getAllPaymentReview();
		if (list.isEmpty()) throw new NotFoundException("No payment review found");

		/**
		 1. This method fetches the list of all PaymentReviews.
		 2. If no paymentReview is found then it throws NotFoundException.
		 **/
		return list;
	}

	@Transactional
	public void savePaymentReview(PaymentReview newPaymentReview) {

//		PaymentReview pr = getPaymentReviewById(newPaymentReview.getId());
		if (paymentReviewDAL.getById(newPaymentReview.getId()) !=null) throw new ElementAlreadyExistException("Payment Review already exist");
		paymentReviewDAL.save(newPaymentReview);
		/**
		 1. This method first checks if the given paymentReview exists or not.
		 2. If the given paymentReview is not found, then it saves the PaymentReview entity into the database.
		 3. If found then it throws ElementAlreadyExistException.
		 **/
	}

	@Transactional
	public void delete(int id) {
		PaymentReview pr = getPaymentReviewById(id);
		if (pr == null) throw new NotFoundException("No PR found");
		paymentReviewDAL.delete(id);
		/**
		 1. This method deletes PaymentReview for a specific id.
		 2. If no paymentReview is found for the given id, then it throws NotFoundException.
		 **/
	}

	@Transactional
	public List<PaymentReview> getPaymentReviewByQueryType(String queryType){
		List<PaymentReview> list = paymentReviewDAL.getByQueryType(queryType);
		if (list.isEmpty()) throw new InvalidInputException("Invalid ");
		return list;

//		boolean flag = true;
//		if (queryType.equalsIgnoreCase("Payment Issue") || queryType.equalsIgnoreCase("Bank Issue") || queryType.equalsIgnoreCase("Merchant Issue")) flag=true;
//		if (flag) throw new InvalidInputException("Invalid query type");
//
//		return paymentReviewDAL.getByQueryType(queryType);
		/**
		 1. This method fetches the list of PaymentReview based on the queryType received.
		 2. Passing an empty queryType throws InvalidInputException.
		 3. Only the following queryType are accepted in any format i.e. UpperCase/LowerCase
		        "Payment Issue","Bank Issue", "Merchant Issue"
		 **/
//       return null;
	}

}
