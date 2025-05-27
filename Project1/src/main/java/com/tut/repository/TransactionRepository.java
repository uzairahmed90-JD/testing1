package com.tut.repository;

import com.tut.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByBroker(String broker);
    List<Transaction> findByUser_UserId(Long user_id);

    List<Transaction> findByDateAfter(LocalDate date);

    @Query("SELECT SUM(t.amount) FROM Transaction t")
    Double getTotalTransactionAmount();

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = :type")
    Double getTotalAmountByType(@Param("type") String type);

    @Query("SELECT SUM(t.bags) FROM Transaction t WHERE t.type = :type")
    Double getTotalWeightByType(@Param("type") String type);

}
