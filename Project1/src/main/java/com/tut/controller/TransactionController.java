package com.tut.controller;

import com.tut.entity.Transaction;
import com.tut.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService service;

    //get all transactions
    @GetMapping
    public List<Transaction> getALlTransaction(){
        return service.getAllTransactions();
    }

    //find by broker transactions
    @GetMapping("/{broker}")
    public List<Transaction> findByBrokerTransaction(@PathVariable String broker){
         List<Transaction> tr=service.findByBroker(broker);
         if(tr ==null){
             System.out.println("there broker not available");
         }
        return  service.findByBroker(broker);
    }

    // Create new transaction
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return service.createNewTransaction(transaction);
    }

}
