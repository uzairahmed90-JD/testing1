package com.tut.controller;

import com.tut.entity.Payment;
import com.tut.entity.Transaction;
import com.tut.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    //get all transactions
    @GetMapping
    public List<Payment> getALlPayments(){
        return service.getAllPayments();
    }

    //find by broker transactions
    @GetMapping("/{broker}")
    public List<Payment> findByBrokerPayment(@PathVariable String broker){
        List<Payment> tr=service.findByBroker(broker);
        if(tr ==null){
            System.out.println("there broker not available");
        }
        return  service.findByBroker(broker);
    }
    @GetMapping("/user/{userId}")
    public List<Payment> getPaymentsByUser(@PathVariable Long userId) {
        return service.findByUserUserId(userId);
    }

    // Create new transaction
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return service.createPayment(payment);
    }

}
