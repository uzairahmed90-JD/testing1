package com.tut.controller;

import com.tut.entity.User;
import com.tut.repository.UserRepository;
import com.tut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //get all users
    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    //get user by id
    @GetMapping("/{name}")
    public ResponseEntity<User> findByFirstName(@PathVariable String name){
        User user=userService.getByUserFirstName(name);
        if(user!=null) {
            return ResponseEntity.ok().body(user);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findById(@PathVariable int  id){
        User user=userService.findById(id);
        if(user!=null) {
            return ResponseEntity.ok().body(user);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
//    @GetMapping("/summary/{userId}")
//    public User getUserSummary(@PathVariable Long userId) {
//        return userService.findById(userId);
//
//    }
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User us=userService.createOrUpdateUser(user);
        if(us!=null){
            return ResponseEntity.ok().body(us);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
