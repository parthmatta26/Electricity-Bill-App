package com.webapp.ElectricityBillPortal.Bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("/generate/{id}")
    public String generateBill(@PathVariable Integer id, Bill bill) {
        bill.setCustomerId(id);
        bill.setBillAmount(0.0);
        billService.generateBill(bill);
        return "redirect:/bill/history/{id}";
    }

    @GetMapping(path = "/history/{id}")
    public String showBillHistory(@PathVariable Integer id, Model model) {
        List<Bill> billList = billService.allBillById(id);
        model.addAttribute("billList", billList);
        model.addAttribute("id", id);
        model.addAttribute("pageTitle", "Electricity Bill | Bill History");
        model.addAttribute("pageHeading", "Bill History for Customer Id: " + id);
        return "bill-history";
    }

    @GetMapping("/pay/{billNo}")
    public String payBill(@PathVariable Integer billNo, RedirectAttributes attributes) {
        boolean paid = billService.payBill(billNo);
        Integer id = billService.getCustomerId(billNo);
        attributes.addAttribute("id", id);
        if(paid)
            attributes.addFlashAttribute("message1", "Bill No.: " + billNo + " paid Successfully");
        else
            attributes.addFlashAttribute("message2", "Bill No.: " + billNo + " Already Paid");
        return "redirect:/bill/history/{id}";
    }
}
