package com.webapp.ElectricityBillPortal.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query(value="Select * From bill Where customer_id=:id" , nativeQuery = true)
    List<Bill> findAll(@Param("id") Integer id);
}
