package com.webapp.ElectricityBillPortal.Customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq",  initialValue = 1001)
    private Integer id;
    @Column(unique = true)
    private String email;
    private String customerName;
    private Long areaCode;
    private Long contactNo;
    private String password;

    public Customer(String email, String customerName, Long areaCode, Long contactNo, String password) {
        this.email = email;
        this.customerName = customerName;
        this.areaCode = areaCode;
        this.contactNo = contactNo;
        this.password = password;
    }
}
