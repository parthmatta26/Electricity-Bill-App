package com.webapp.ElectricityBillPortal.Customer;

public class UserAlreadyRegisteredException extends Throwable {
    public UserAlreadyRegisteredException(String s) {
        super(s);
    }
}
