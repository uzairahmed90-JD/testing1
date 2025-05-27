package com.tut.pagecontroller;

import com.tut.entity.User;
import com.tut.repository.UserRepository;
import com.tut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserControllers {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserService service;

    @GetMapping
    public String  getAl(Model model){
        model.addAttribute("users",repository.findAll());
        model.addAttribute("user",new User());
        return "users";
    }

//    @PostMapping
//    public String addUser(@RequestParam String fname,@RequestParam String lname,@RequestParam String userType){
//        User us =new User();
//        us.setFirstName(fname);
//        us.setLastName(lname);
//        us.setUserType(userType);
//        us.setBalance(0.0);
//        us.setTotalPaid(0.0);
//        us.setTotalAmount(0.0);
//        repository.save(us);
//    return "redirect:/users";
//    }

    @PostMapping
    public String addUser(@ModelAttribute User user){
        User us =user;
        us.setBalance(0.0);
        us.setTotalPaid(0.0);
        us.setTotalAmount(0.0);
        repository.save(us);
        return "redirect:/users";
    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user){
            User us=service.findById(user.getUserId());
            us.setLastName(user.getLastName());
           service.UpdateUser(user);
        return "updateUser";
    }
    @GetMapping("/updateUse/{id}")
    public String  UpdateUserShow(@PathVariable("id") Long id, Model model){
        User us=service.findById(id);
        model.addAttribute("user",us);
        return "updateUser";
    }
}
