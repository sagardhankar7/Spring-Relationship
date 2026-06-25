package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.OrderDal;
import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.entity.Orders;
import com.cn.cnpayment.entity.Payment;
import com.cn.cnpayment.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 Complete the OrderService class as mentioned below:

 a. Autowire OrderDal

 b. Complete the following methods:

 1. getOrderById(int id): This method fetches an Order for a specific Id.

 2. getAllOrders(): This method fetches the list of Orders.

 3. saveOrder(Orders newOrder): This method saves an Order.

 4. delete(int id): This method deletes an Order for a specific Id.
 **/


@Service
public class OrderService {

    @Autowired
    OrderDal orderDal;
    // Autowire the OrderDal object.

    /**
     1. Complete the method body for getOrderById() by adding proper arguments.
     2. add proper annotation for registering this method as a Transaction
     **/
    @Transactional
    public Orders getOrderById(int id) {
        Orders order = orderDal.getById(id);
        if (order==null) throw new NotFoundException("No order");
        return order;
    }


    /**
     1. Complete the method body for getAllOrders().
     2. add proper annotation for registering this method as a Transaction
     **/
    @Transactional
    public List<Orders> getAllOrders() {

        List<Orders> list = orderDal.getAllOrders();
        if (list.isEmpty()) throw new NotFoundException("No order");
        return list;
    }


    /**
     1. Complete the method body for saveOrder() method by adding proper arguments.
     2. add proper annotation for registering this method as a Transaction
     **/
    @Autowired
    PaymentDAL paymentDAL;

    @Transactional
    public void saveOrder(Orders orders) {
//        Orders order = new Orders();
//        order.setAmount(orders.getAmount());
//        order.setName(orders.getName());
//        order.setCategory(orders.getCategory());
//        order.setQuantity(orders.getQuantity());

        List<Payment> paymentList = new ArrayList<>();
        for (Payment payment: orders.getPayments()) {
            Payment newPayment = paymentDAL.getById(payment.getId());
            paymentList.add(newPayment);
        }

        orders.setPayments(paymentList);

        orderDal.save(orders);
    }


    /**
     1. Complete the method body for delete() method by adding proper arguments.
     2. add proper annotation for registering this method as a Transaction
     **/
    @Transactional
    public void delete(int id) {
        orderDal.delete(id);
    }


}
