package com.tut.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    private String broker;
    private Double rate;
    private Double weight;
    private Double bags;
    private Double amount;
    private String truckNumber;
    private String referenceNumber;
    private String type; // Sale or Purchase
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId=transactionId;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker=broker;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate=rate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight=weight;
    }

    public Double getBags() {
        return bags;
    }

    public void setBags(Double bags) {
        this.bags=bags;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount=amount;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber=truckNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber=referenceNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date=date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user=user;
    }
// Getters and Setters
}
