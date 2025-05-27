package com.tut.pagecontroller;


import com.tut.entity.Bags;
import com.tut.entity.Transaction;
import com.tut.entity.User;
import com.tut.service.BagsService;
import com.tut.service.TransactionService;
import com.tut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bags")
public class BagsController {

    @Autowired
    private BagsService bagsService;
    @Autowired
    private UserService userService;

    // Show All Bags
    @GetMapping
    public String getAllBags(Model model) {
        //List<Transaction> transactions = transactionService.getAllTransactions();
        List<Bags> tbags=bagsService.getAllBags();
        model.addAttribute("bags", tbags);
        model.addAttribute("bag", new Bags()); // form ke liye empty object
        return "bags"; // transaction.html
    }
//
//@GetMapping("/search")
//public String searchTransactions(@RequestParam(required = false) Date date, Model model) {
//    List<Bags> bags;
//
//    if (date != null) {
//        bags = bagsService.findByDate(date);
//    }  else {
//        bags = new ArrayList<>();
//    }
//
//    model.addAttribute("users", userService.getAllUser());
//    model.addAttribute("transactions", transactions);
//    model.addAttribute("transaction", new Transaction()); // for form binding
//    model.addAttribute("selectedUserId", userId); // pass selected user ID back
//
//    return "UserTransactions";
//}

    @GetMapping("/form")
    public String showTransactionForm(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "transaction-form"; // Thymeleaf HTML page
    }

@PostMapping("/bagcreate")
public String addTransaction(@ModelAttribute Bags bag) {

    // Save the transaction
    bagsService.createNewBag(bag);

    return "redirect:/bags";  // Redirect to the transaction list page
}
    @PostMapping("/salebags")
    public String minusBags(@ModelAttribute Bags bag) {

        // Save the transaction

        bagsService.minusBags(bag);

        return "redirect:/bags";  // Redirect to the transaction list page
    }
}
