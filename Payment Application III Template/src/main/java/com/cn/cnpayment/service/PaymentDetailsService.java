package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.PaymentDetailsDAL;
import com.cn.cnpayment.entity.PaymentDetails;
import com.cn.cnpayment.exception.ElementAlreadyExistException;
import com.cn.cnpayment.exception.InvalidInputException;
import com.cn.cnpayment.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentDetailsService {

    // Auto-wire the PaymentDetailsDAL object
    @Autowired
    PaymentDetailsDAL paymentDetailsDAL;


    @Transactional
    public PaymentDetails getPaymentDetailsById(int id) {
        PaymentDetails paymentDetails = paymentDetailsDAL.getById(id);
        if (paymentDetails==null) throw new NotFoundException("NO Payment");
        return paymentDetails;
        // 1. This method fetches PaymentDetails for the given id.
        // 2. If no paymentDetails is found by the given id then it throws NotFoundException with custom message.
//        return null;
    }

    @Transactional
    public List<PaymentDetails> getAllPaymentDetails() {
        List<PaymentDetails> list = paymentDetailsDAL.getAllPaymentDetails();
//        if (list.isEmpty()) throw new NotFoundException("No payment found");
        return list;
        // 1. This method fetches the list of all PaymentDetails from the database.
        // 2. If the no paymentDetails data is found then it throws NotFoundException with custom message.
//        return null;
    }


    @Transactional
    public void savePaymentDetails(PaymentDetails newPaymentDetails) {

        List<PaymentDetails> list = getAllPaymentDetails();
        for (PaymentDetails pd : list) {
            if (pd.getId()==newPaymentDetails.getId()) throw new ElementAlreadyExistException("Already payemt detail exist");
        }

        // last = call to DB
        paymentDetailsDAL.save(newPaymentDetails);


        // 1. It first checks whether the given paymentDetails object exists in the database or not.
    // 2. If the given paymentDetails already exist in the database, then it throws ElementAlreadyExistException.
    // 3. If the given paymentDetails doesn't exist, it saves the new PaymentDetails into the database.
    }


    @Transactional
    public void delete(int id) {
        paymentDetailsDAL.delete(id);
        // 1. It deletes a paymentDetails for the given id from the database.
    }


    @Transactional
    public void update(PaymentDetails paymentDetails) {
        if (getPaymentDetailsById(paymentDetails.getId())==null) throw new NotFoundException("No Pay Details exist");


        paymentDetailsDAL.update(paymentDetails);
        // 1. It first checks if the given paymentDetails exists in the database or not.
    // 2. If the given paymentDetails object exists in the database, then it is simply updated.
    // 3. If not found, then it throws NotFoundException with custom message.

    }

    @Transactional
    public List<PaymentDetails> getByCurrency(String currency) {
        // 1. It fetches the list of all PaymentDetails from the database for the given id.
        // 2. It supports the following given currency only with any format i.e. UpperCase/LoweCase:
        // 3. "INR", "Rupee", "Dollar", "Yen", "Pound", "USD"
        // 4. It throws InvalidInputException if a currency different from the mentioned above is passed.
        List<String> clist = new ArrayList<>();
        clist.add("INR");
        clist.add("Rupee");
        clist.add("Dollar");
        clist.add("Yen");
        clist.add("Pound");
        clist.add("USD");
        boolean flag=false;
        for (String cur: clist) {
            if (cur.equalsIgnoreCase(currency)) flag=true;
        }
        if (flag==false) throw new InvalidInputException("Currency invalid");

        List<PaymentDetails> list = paymentDetailsDAL.getByCurrency(currency);
//        if (list.isEmpty()) throw new
        return list;
    }
}
