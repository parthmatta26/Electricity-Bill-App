package com.webapp.ElectricityBillPortal.Bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository repository;
    public void generateBill(Bill bill) {
        repository.save(bill);
    }
    public List<Bill> allBillById(Integer id) {
        return repository.findAll(id);
    }

    public boolean payBill(Integer billNo) {
        Bill bill = repository.findById(billNo).get();
        if(bill.getPaid()){
            return false;
        }
        bill.setPaid(true);
        repository.save(bill);
        return true;
    }

    public Integer getCustomerId(Integer billNo) {
        Bill bill = repository.findById(billNo).get();
        return bill.getCustomerId();
    }
}
