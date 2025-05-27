package com.tut.repository;

import com.tut.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByFirstName(String name);


}
