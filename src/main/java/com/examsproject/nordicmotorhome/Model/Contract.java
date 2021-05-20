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
    private String pickupTime;
    private String rentalEndDate;
    private String dropoffTime;
    private int rentaldetailsID;
    private int extrasID;
    private String wasCancelled;
    private String cancelDate;
    private int contractFollowupID;

    public Contract () {}

    public Contract(int contractID, int autocamperID, int customerID, double rentalPrice, String rentalStartDate,
                    String pickupTime, String rentalEndDate, String dropoffTime, int extrasID,
                    int rentaldetailsID, int contractFollowupID) {
        this.contractID = contractID;
        this.autocamperID = autocamperID;
        this.customerID = customerID;
        this.rentalPrice = rentalPrice;
        this.rentalStartDate = rentalStartDate;
        this.pickupTime = pickupTime;
        this.rentalEndDate = rentalEndDate;
        this.dropoffTime = dropoffTime;
        this.extrasID = extrasID;
        this.rentaldetailsID = rentaldetailsID;
        this.contractFollowupID = contractFollowupID;
        this.wasCancelled = wasCancelled;
        this.cancelDate = cancelDate;
    }

    public String isWasCancelled() {
        return wasCancelled;
    }

    public void setWasCancelled(String wasCancelled) {
        this.wasCancelled = wasCancelled;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
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

    public void setRentalStartDate(String rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public String getRentalStartDate() {
        return rentalStartDate;
    }

    public String getRentalEndDate() {
        return rentalEndDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public void setRentalEndDate(String rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public String getDropoffTime() {
        return dropoffTime;
    }

    public void setDropoffTime(String dropoffTime) {
        this.dropoffTime = dropoffTime;
    }
    
    public int getContractFollowupID() {
        return contractFollowupID;
    }

    public void setContractFollowupID(int contractFollowupID) {
        this.contractFollowupID = contractFollowupID;
    }

    public int getExtrasID() {
        return extrasID;
    }

    public void setExtrasID(int extrasID) {
        this.extrasID = extrasID;
    }

    public int getRentaldetailsID() {
        return rentaldetailsID;
    }

    public void setRentaldetailsID(int rentaldetailsID) {
        this.rentaldetailsID = rentaldetailsID;
    }
}
