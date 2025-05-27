package com.tut.service;

import com.tut.entity.Payment;
import com.tut.entity.User;
import com.tut.repository.PaymentRepository;
import com.tut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Payment> getAllPayments(){
        return  paymentRepository.findAll();
    }

    public  Payment createPayment(Payment payment){



            // Update User's balance and totalPaid
            if (payment.getUser() != null) {
                User user = payment.getUser();
                User existingUser = userRepository.findById(user.getUserId()).orElse(null);

                if (existingUser != null) {
                    double oldBalance = (existingUser.getBalance() != null ? existingUser.getBalance() : 0) ;
                    double newTotalPaid = existingUser.getTotalPaid()  + payment.getPaidAmount();
                    double newBalance = oldBalance - payment.getPaidAmount();
                    double usetTotalamount=existingUser.getTotalAmount();
                    existingUser.setBalance(newBalance);
                    existingUser.setTotalPaid(newTotalPaid);

                    userRepository.save(existingUser);
                    payment.setBalance(newBalance);
                    payment.setUser(existingUser);
                    payment.setTotalAmount(usetTotalamount);
                }
            }

            return paymentRepository.save(payment);
    }
    //find payment by broker
    public List<Payment> findByBroker(String broker_name){

        return paymentRepository.findByBroker(broker_name);
    }
    public List<Payment> findByUserUserId(Long userId) {
        return paymentRepository.findByUser_UserId(userId);
    }


    public double getTotalPayments() {
        Double total = paymentRepository.getTotalPayments();
        return total != null ? total : 0.0;
    }
    public double getTotalSalePayments() {
        Double total = paymentRepository.getTotalSalePayments();
        return total != null ? total : 0.0;
    }

    public double getTotalPurchasePayments() {
        Double total = paymentRepository.getTotalPurchasePayments();
        return total != null ? total : 0.0;
    }

    public long count() {
        return paymentRepository.count();
    }

}
