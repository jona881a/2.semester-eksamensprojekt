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
    private String pickupAddress;
    private int pickupDistance;
    private String rentalEndDate;
    private String dropoffTime;
    private String dropoffAddress;
    private int rentaldetailsID;
    private int extrasID;
    private String wasCancelled;
    private String cancelDate;
    private int contractFollowupID;

    public Contract () {}

    public Contract(int contractID, int autocamperID, int customerID, double rentalPrice,
                    String rentalStartDate, String pickupTime, String pickupAddress,
                    int pickupDistance, String rentalEndDate, String dropoffTime,
                    String dropoffAddress, int rentaldetailsID,
                    int extrasID, String wasCancelled, String cancelDate, int contractFollowupID) {
        this.contractID = contractID;
        this.autocamperID = autocamperID;
        this.customerID = customerID;
        this.rentalPrice = rentalPrice;
        this.rentalStartDate = rentalStartDate;
        this.pickupTime = pickupTime;
        this.pickupAddress = pickupAddress;
        this.pickupDistance = pickupDistance;
        this.rentalEndDate = rentalEndDate;
        this.dropoffTime = dropoffTime;
        this.dropoffAddress = dropoffAddress;
        this.rentaldetailsID = rentaldetailsID;
        this.extrasID = extrasID;
        this.wasCancelled = wasCancelled;
        this.cancelDate = cancelDate;
        this.contractFollowupID = contractFollowupID;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public int getPickupDistance() {
        return pickupDistance;
    }

    public void setPickupDistance(int pickupDistance) {
        this.pickupDistance = pickupDistance;
    }

    public String getDropoffAddress() {
        return dropoffAddress;
    }

    public void setDropoffAddress(String dropoffAddress) {
        this.dropoffAddress = dropoffAddress;
    }

    public String getWasCancelled() {
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
