package com.tut.service;

import com.tut.entity.User;
import com.tut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public User createOrUpdateUser(User user){
        return  userRepository.save(user);
    }
    public User UpdateUser(User user){
        if(user.getUserId()!=null){
            return userRepository.save(user);
        }
        else{
            return null;
        }
    }
    public User findById(long id){
        return userRepository.findById(id).orElse(null);
    }
    public  User getByUserFirstName(String first_name){
        return  userRepository.findByFirstName(first_name);
    }

    public long count() {
        return 12;
    }
}
