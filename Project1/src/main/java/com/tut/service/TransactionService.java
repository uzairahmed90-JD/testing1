package com.tut.service;

import com.tut.entity.Transaction;
import com.tut.entity.User;
import com.tut.repository.TransactionRepository;
import com.tut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    public double getTotalTransactionAmount() {
        Double total = transactionRepository.getTotalTransactionAmount();
        return total != null ? total : 0.0;
    }
    public double getTotalAmountByType(String type) {
        Double total = transactionRepository.getTotalAmountByType(type);
        return total != null ? total : 0.0;
    }

    public double getTotalWeightByType(String type) {
        Double weight = transactionRepository.getTotalWeightByType(type);
        return weight != null ? weight : 0.0;
    }

    public long count() {
        return transactionRepository.count();
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction createNewTransaction(Transaction transaction){

        //calculate bag
        if(transaction.getWeight() != null){
            double bags=transaction.getWeight()/40;
            transaction.setBags(bags);

        }

        //calculate amount
        if(transaction.getRate() !=null){
            double amount=transaction.getRate()*transaction.getBags();
            transaction.setAmount(amount);
        }

        //update user balance remainder
        if(transaction.getUser() != null){
            User us=transaction.getUser();
            User existingUser=userRepository.findById(us.getUserId()).orElse(null);

            if (existingUser != null) {
                double updatedBalance = (existingUser.getBalance() != null ? existingUser.getBalance() : 0) + transaction.getAmount();
                double updatedTotalAmount = (existingUser.getTotalAmount() != null ? existingUser.getTotalAmount() : 0) + transaction.getAmount();
                existingUser.setBalance(updatedBalance);
                existingUser.setTotalAmount(updatedTotalAmount);
                userRepository.save(existingUser);
                transaction.setUser(existingUser); // attach updated user
            }
        }

        return  transactionRepository.save(transaction);
    }
    //find transaction by broker
    public List<Transaction> findByBroker(String broker_name){
        return transactionRepository.findByBroker(broker_name);
    }
    public List<Transaction> findByUserUserId(Long user_id){

        return transactionRepository.findByUser_UserId(user_id);
    }
}
