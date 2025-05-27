package com.tut.pagecontroller;

import com.tut.entity.Payment;
import com.tut.entity.Transaction;
import com.tut.repository.PaymentRepository;
import com.tut.repository.TransactionRepository;
import com.tut.repository.UserRepository;
import com.tut.service.PaymentService;
import com.tut.service.TransactionService;
import com.tut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControllers {

  @Autowired
  private UserService userService;
  @Autowired
  private TransactionService transactionService;
  @Autowired
  private PaymentService paymentService;

//    @GetMapping("/")
//    public String adminDashboard(Model model) {
//        // Monthly transaction summary
//        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
//        List<Transaction> monthlyTransactions = transactionRepo.findByDateAfter(startOfMonth);
//
//        double totalAmount = monthlyTransactions.stream().mapToDouble(Transaction::getAmount).sum();
//        double totalWeight = monthlyTransactions.stream().mapToDouble(Transaction::getWeight).sum();
//        double totalBags = totalWeight / 40;
//
//        // Monthly payments
//        List<Payment> monthlyPayments = paymentRepo.findByDateAfter(startOfMonth);
//        double totalPaid = monthlyPayments.stream().mapToDouble(Payment::getPaidAmount).sum();
//        double totalBalance = monthlyPayments.stream().mapToDouble(p -> p.getBalance() == null ? 0 : p.getBalance()).sum();
//
//        model.addAttribute("totalAmount", totalAmount);
//        model.addAttribute("totalBags", totalBags);
//        model.addAttribute("totalPaid", totalPaid);
//        model.addAttribute("totalBalance", totalBalance);
//
//        return "admin_dashboard";
//    }

    @Autowired
    private TransactionRepository transactionRepository;

//    @GetMapping("/dashboard")
//    public String adminDashboard(Model model) {
//        Double totalPurchaseWeight = transactionRepository.getTotalPurchasedWeight();
//        Double totalSaleWeight = transactionRepository.getTotalSoldWeight();
//        Double totalPurchaseAmount = transactionRepository.getTotalPurchasedAmount();
//        Double totalSaleAmount = transactionRepository.getTotalSoldAmount();
//
//        model.addAttribute("totalPurchaseWeight", totalPurchaseWeight != null ? totalPurchaseWeight : 0);
//        model.addAttribute("totalSaleWeight", totalSaleWeight != null ? totalSaleWeight : 0);
//        model.addAttribute("totalPurchaseAmount", totalPurchaseAmount != null ? totalPurchaseAmount : 0);
//        model.addAttribute("totalSaleAmount", totalSaleAmount != null ? totalSaleAmount : 0);
//
//        return "admin_dashboard";
//    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // Fetch stats
        long totalUsers = userService.count();
        long totalTransactions = transactionService.count();

        double totalTransactionAmount = transactionService.getTotalTransactionAmount();
        double totalPurchaseAmount = transactionService.getTotalAmountByType("Purchase");
        double totalSaleAmount = transactionService.getTotalAmountByType("Sale");

        double salePayments = paymentService.getTotalSalePayments();
        double purchasePayments = paymentService.getTotalPurchasePayments();

        model.addAttribute("totalSalePayments", salePayments);
        model.addAttribute("totalPurchasePayments", purchasePayments);

        double totalPurchaseWeight = transactionService.getTotalWeightByType("Purchase");
        double totalSaleWeight = transactionService.getTotalWeightByType("Sale");
        double stockInHand = totalPurchaseWeight - totalSaleWeight;

        double totalPayments = paymentService.getTotalPayments();

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalTransactions", totalTransactions);
        model.addAttribute("totalTransactionAmount", totalTransactionAmount);
        model.addAttribute("totalPurchaseAmount", totalPurchaseAmount);
        model.addAttribute("totalSaleAmount", totalSaleAmount);
        model.addAttribute("totalPurchaseWeight", totalPurchaseWeight);
        model.addAttribute("totalSaleWeight", totalSaleWeight);
        model.addAttribute("stockInHand", stockInHand);
        model.addAttribute("totalPayments", totalPayments);

        return "admin_dashboard";
    }


}
