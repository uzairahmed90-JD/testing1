package com.tut.repository;

import com.tut.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByBroker(String broker);
    List<Payment> findByUser_UserId(Long userId);

    List<Payment> findByDateAfter(LocalDate startOfMonth);
    @Query("SELECT SUM(p.paidAmount) FROM Payment p")
    Double getTotalPayments();

    @Query("SELECT SUM(p.paidAmount) FROM Payment p WHERE p.type = 'Sale'")
    Double getTotalSalePayments();

    @Query("SELECT SUM(p.paidAmount) FROM Payment p WHERE p.type = 'Purchase'")
    Double getTotalPurchasePayments();

}
