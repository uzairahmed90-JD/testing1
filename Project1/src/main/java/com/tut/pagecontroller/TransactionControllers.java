package com.tut.pagecontroller;


import com.tut.entity.Transaction;
import com.tut.entity.User;
import com.tut.service.TransactionService;
import com.tut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transactions")
public class TransactionControllers {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    // Show All Transactions
    @GetMapping
    public String getAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();

        model.addAttribute("transactions", transactions);
        model.addAttribute("transaction", new Transaction()); // form ke liye empty object
        return "Transaction"; // transaction.html
    }

//    @GetMapping("/user/{broker}")
//    public String getTransactionsByUserId(@PathVariable String broker, Model model) {
//        List<Transaction> transactions = transactionService.findByBroker(broker);
//
//        model.addAttribute("transactions", transactions);
//        return "UserTransactions"; // Thymeleaf page name
//    }

@GetMapping("/search")
public String searchTransactions(@RequestParam(required = false) Long userId,
                                 @RequestParam(required = false) String broker,
                                 Model model) {
    List<Transaction> transactions;

    if (userId != null) {
        transactions = transactionService.findByUserUserId(userId);
    } else if (broker != null && !broker.isEmpty()) {
        transactions = transactionService.findByBroker(broker);
    } else {
        transactions = new ArrayList<>();
    }

    model.addAttribute("users", userService.getAllUser());
    model.addAttribute("transactions", transactions);
    model.addAttribute("transaction", new Transaction()); // for form binding
    model.addAttribute("selectedUserId", userId); // pass selected user ID back

    return "UserTransactions";
}

    @GetMapping("/form")
    public String showTransactionForm(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "transaction-form"; // Thymeleaf HTML page
    }
//    // Save New Transaction
//    @PostMapping
//    public String addTransaction(@RequestParam String broker,
//                                 @RequestParam double rate,
//                                 @RequestParam double weight,
//                                 @RequestParam double bags,
//                                 @RequestParam double amount,
//                                 @RequestParam String truckNumber,
//                                 @RequestParam String referenceNumber,
//                                 @RequestParam String type,
//
//                                 @RequestParam Long userId) {
//        // Convert date string to LocalDate
//        LocalDate transactionDate =LocalDate.now();
//
//        // Fetch the User based on userId
//        User user = userService.findById(userId);
//        if (user==null) {
//            // Handle user not found
//            return "error";  // You can redirect to an error page
//        }
//
//        // Create a new Transaction object
//        Transaction transaction = new Transaction();
//        transaction.setBroker(broker);
//        transaction.setRate(rate);
//        transaction.setWeight(weight);
//        transaction.setBags(bags);
//        transaction.setAmount(amount);
//        transaction.setTruckNumber(truckNumber);
//        transaction.setReferenceNumber(referenceNumber);
//        transaction.setType(type);
//        transaction.setDate(transactionDate);
//
//        // Set the user to this transaction
//        transaction.setUser(user);
//
//        // Save the transaction
//        transactionService.createNewTransaction(transaction);
//
//        return "redirect:/transactions/search?uerId=" + userId;  // Redirect to the transaction list page
//    }
// Save New Transaction
@PostMapping
public String addTransaction(@ModelAttribute Transaction transaction, @RequestParam Long userId) {

    // Fetch the User based on userId
    User user = userService.findById(userId);

    // Set the user to this transaction
    transaction.setUser(user);

    // Save the transaction
    transactionService.createNewTransaction(transaction);

    return "redirect:/transactions/search?userId=" + user.getUserId();  // Redirect to the transaction list page
}
}
