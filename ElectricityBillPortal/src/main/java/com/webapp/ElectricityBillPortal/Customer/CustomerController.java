package com.webapp.ElectricityBillPortal.Customer;

import com.webapp.ElectricityBillPortal.Bill.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Electricity Bill | Add Customer");
        model.addAttribute("pageHeading", "Add New Customer");
        model.addAttribute("req", "0");
        return "new-customer";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("pageTitle", "Electricity Bill | Login");
        model.addAttribute("pageHeading", "Login to Continue");
        return "login";
    }

    @PostMapping(path = "/save/{reqType}")
    public String saveUser(Customer customer, @PathVariable Integer reqType, RedirectAttributes attributes) {
        try {
            service.validateEmail(customer, reqType);
            service.saveCustomer(customer);
            if(reqType == 0)
                attributes.addFlashAttribute("message1", "New Customer, " + customer.getCustomerName() + " has been Added Successfully.");
            else
                attributes.addFlashAttribute("message1", "Details for " + customer.getCustomerName() + " have been Updated Successfully.");
        } catch (UserAlreadyRegisteredException e) {
            attributes.addFlashAttribute("message2", e.getMessage());
        }
        return "redirect:/home";
    }

    @PostMapping("/validate")
    public String validateCustomer(Customer customer, RedirectAttributes attributes) {
        if (service.validateCustomer(customer)) {
            customer = service.findByEmail(customer.getEmail());
            attributes.addAttribute("id", customer.getId());
            return "redirect:/customer/portal/{id}";
        }
        attributes.addFlashAttribute("message", "Incorrect Credentials");
        return "redirect:/customer/login";
    }

    @GetMapping("/portal/{id}")
    public String showPortal(@PathVariable Integer id, Model model) {
        Customer customer = service.findById(id);
        model.addAttribute("customer", customer);
        Bill bill = new Bill();
        bill.setCustomerId(id);
        model.addAttribute("bill", bill);
        model.addAttribute("id", id);
        model.addAttribute("pageTitle", "Electricity Bill | Portal");
        model.addAttribute("pageHeading", "Welcome to the Electricity Bill Portal");
        return "portal";
    }

    @GetMapping(path = "/edit/{id}")
    public String showEditForm(@PathVariable Integer id, RedirectAttributes attributes, Model model) {

        Customer customer = service.findById(id);
        model.addAttribute("customer", customer);
        model.addAttribute("pageTitle", "Electricity Bill | Edit Customer");
        model.addAttribute("pageHeading", "Edit Customer Details");
        model.addAttribute("req", "1");
        attributes.addFlashAttribute("message", "Details Edited for Customer with ID: " + id);
        return "new-customer";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id, RedirectAttributes attributes, Model model) {
        service.deleteCustomer(id);
        attributes.addFlashAttribute("message1", "Customer with ID: " + id + " Deleted Successfully.");
        return "redirect:/home";
    }
}