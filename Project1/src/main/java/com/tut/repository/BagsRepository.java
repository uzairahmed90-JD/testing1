package com.tut.repository;

import com.tut.entity.Bags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagsRepository extends JpaRepository<Bags,Long> {
    Bags findTopByOrderByDateDesc(); // Gets the last entered record by date
}
