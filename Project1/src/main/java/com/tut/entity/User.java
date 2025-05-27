package com.tut.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String firstName;
    private String lastName;
    private String userType; // "Seller" or "Buyer"
    private Double balance;
    private Double totalPaid;

    private Double totalAmount;    // Total maal ka paisa (sab transactions ka)

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount=totalAmount;
    }

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference // 👈 yeh lagana zaruri hai
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Payment> payments;

    public Long getUserId() {
        return userId;
    }

    public void setUserId() {
        this.userId=userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance=balance;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid=totalPaid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType=userType;
    }

    public void setPayments(List<Payment> payments) {
        this.payments=payments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions=transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

}
