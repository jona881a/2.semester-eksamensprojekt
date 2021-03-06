package com.examsproject.nordicmotorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerDebt {

    @Id
    private int customerDebtID;
    private int contractID;
    private String contractStartDate;
    private String contractEndDate;
    private String wasCancelled;
    private String cancellationDate;
    private int daysSinceCancellation;
    private double totalPrice;

    public CustomerDebt (int customerDebtID, int contractID, String contractStartDate, String contractEndDate,
                         String wasCancelled, String cancellationDate, int daysSinceCancellation, double totalPrice) {
        this.customerDebtID = customerDebtID;
        this.contractID = contractID;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.wasCancelled = wasCancelled;
        this.cancellationDate = cancellationDate;
        this.daysSinceCancellation = daysSinceCancellation;
        this.totalPrice = totalPrice;
    }

    public CustomerDebt() {}


    public int getCustomerDebtID() {
        return customerDebtID;
    }

    public void setCustomerDebtID(int customerDebtID) {
        this.customerDebtID = customerDebtID;
    }

    public int getContractID() {
        return contractID;
    }

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getWasCancelled() {
        return wasCancelled;
    }

    public void setWasCancelled(String wasCancelled) {
        this.wasCancelled = wasCancelled;
    }

    public String getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(String cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public int getDaysSinceCancellation() {
        return daysSinceCancellation;
    }

    public void setDaysSinceCancellation(int daysSinceCancellation) {
        this.daysSinceCancellation = daysSinceCancellation;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
