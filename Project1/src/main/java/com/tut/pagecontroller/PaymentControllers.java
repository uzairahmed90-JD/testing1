package com.tut.pagecontroller;

import com.tut.entity.Payment;
import com.tut.entity.Transaction;
import com.tut.entity.User;
import com.tut.repository.UserRepository;
import com.tut.service.PaymentService;
import com.tut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentControllers {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;
//    @GetMapping
//    public String getAllTransactions(Model model) {
//        List<Payment> payments = paymentService.getAllPayments();
//
//        model.addAttribute("payments",payments);
//        model.addAttribute("payment", new Payment()); // form ke liye empty object
//        return "Payment"; // transaction.html
//    }
    @GetMapping
    public String searchPayment(@RequestParam(required = false) Long userId,
                                     @RequestParam(required = false) String broker,
                                     Model model) {
        List<Payment> payments;

        if (userId != null) {
            payments = paymentService.findByUserUserId(userId);
        } else if (broker != null && !broker.isEmpty()) {
            payments = paymentService.findByBroker(broker);
        } else {
            payments = new ArrayList<>();
        }
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("payments", payments);
        model.addAttribute("payment", new Payment()); // form ke liye empty object
        model.addAttribute("selectedUserId", userId); // pass selected user ID back
        return "Payment";
    }


    @PostMapping
    public String savePayment(@ModelAttribute Payment payment,@RequestParam Long userId) {
        User user=service.findById(userId);

        payment.setUser(user);
        payment.setTotalAmount(user.getBalance());
        paymentService.createPayment(payment);
        return "redirect:/payments?userId="+ user.getUserId();
    }
}
