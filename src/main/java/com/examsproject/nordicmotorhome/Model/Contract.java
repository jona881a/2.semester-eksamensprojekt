package com.examsproject.nordicmotorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * the model for a contract
 * @author jonaskunert
 */
@Entity
public class Contract {

    @Id
    private int contractID;
    private int autocamperID;
    private int customerID;
    private double rentalPrice;
    private String rentalStartDate;
    private String rentalEndDate;

    public Contract () {}

    public Contract(int contractID, int autocamperID, int customerID, double rentalPrice, String rentalStartDate, String rentalEndDate) {
        this.contractID = contractID;
        this.autocamperID = autocamperID;
        this.customerID = customerID;
        this.rentalPrice = rentalPrice;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
    }

    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public int getAutocamperID() {
        return autocamperID;
    }

    public void setAutocamperID(int autocamperID) {
        this.autocamperID = autocamperID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(String rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public String getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(String rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }
}
