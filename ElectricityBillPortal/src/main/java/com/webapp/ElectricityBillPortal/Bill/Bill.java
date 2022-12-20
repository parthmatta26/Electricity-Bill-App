package com.webapp.ElectricityBillPortal.Bill;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "port_gen")
    @SequenceGenerator(name = "port_gen", sequenceName = "port_gen",  initialValue = 1001)
    private Integer billNo;
    private Integer customerId;
    private Long unitsConsumed;
    private LocalDate billDate;
    private Double billAmount;
    private Boolean paid = false;

    public Bill(Integer customerId, Long unitsConsumed, LocalDate billDate, Double billAmount, Boolean paid) {
        this.customerId = customerId;
        this.unitsConsumed = unitsConsumed;
        this.billDate = billDate;
        this.billAmount = calcBillAmount();
        this.paid = paid;
    }

    public void setBillNo(Integer billNo) {
        this.billNo = billNo;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setUnitsConsumed(Long unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = calcBillAmount();
    }

    public double calcBillAmount() {
        double billamount;
        if(unitsConsumed > 150) {
            billamount = 100*0.5 + 50*0.75 + (unitsConsumed - 150)*1;
        }
        else if(unitsConsumed > 100) {
            billamount = 100*0.5 + (unitsConsumed - 150)*0.75;
        }
        else {
            billamount = unitsConsumed*0.5;
        }
        return billamount;
    }
}
