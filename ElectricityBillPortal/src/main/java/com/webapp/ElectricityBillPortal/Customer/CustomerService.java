package com.webapp.ElectricityBillPortal.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    public void saveCustomer(Customer customer) {
        repository.save(customer);
    }

    public boolean validateEmail(Customer customer, Integer req) throws UserAlreadyRegisteredException {
        if(req == 1) return false;
        Optional<Customer> byEmail = repository.findByEmail(customer.getEmail());
        if (byEmail.isPresent()) {
            throw new UserAlreadyRegisteredException("Customer with Email: '" + customer.getEmail() + "' already registered.");
        }
        return false;
    }

    public boolean validateCustomer(Customer customer) {
        Optional<Customer> byEmail = repository.findByEmail(customer.getEmail());
        if(byEmail.isPresent()) {
            return customer.getPassword().equals(byEmail.get().getPassword());
        }
        return false;
    }

    public Customer findById(Integer id) {
        Optional<Customer> byId = repository.findById(id);
        return byId.get();
    }

    public Customer findByEmail(String email) {
        Optional<Customer> byEmail = repository.findByEmail(email);
        return byEmail.get();
    }

    public void deleteCustomer(Integer id) {
        repository.deleteById(id);
    }
}
