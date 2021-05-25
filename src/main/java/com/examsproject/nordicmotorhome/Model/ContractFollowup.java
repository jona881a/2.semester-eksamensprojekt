package com.examsproject.nordicmotorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContractFollowup {

    @Id
    private int contractFollowUpID;
    private double followupPrice;
    private String halfTank;
    private double extraDrivenKm;
    private String damages;
    private double damageCost;
    private double dropoffDistance;

    public ContractFollowup() {}

    public ContractFollowup(int contractFollowUpID, int followupPrice, String halfTank,
                            int extraDrivenKm, String damages, int damageCost, int dropoffDistance) {
        this.contractFollowUpID = contractFollowUpID;
        this.followupPrice = followupPrice;
        this.halfTank = halfTank;
        this.extraDrivenKm = extraDrivenKm;
        this.damages = damages;
        this.damageCost = damageCost;
        this.dropoffDistance = dropoffDistance;
    }

    public String getHalfTank() {
        return halfTank;
    }

    public double getExtraDrivenKm() {
        return extraDrivenKm;
    }

    public String getDamages() {
        return damages;
    }

    public double getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(double damageCost) {
        this.damageCost = damageCost;
    }

    public double getContractFollowUpID() {
        return contractFollowUpID;
    }

    public void setContractFollowUpID(int contractFollowUpID) {
        this.contractFollowUpID = contractFollowUpID;
    }

    public double getFollowupPrice() {
        return followupPrice;
    }

    public void setFollowupPrice(double repairPrice) {
        this.followupPrice = repairPrice;
    }

    public String isHalfTank() {
        return halfTank;
    }

    public void setHalfTank(String halfTank) {
        this.halfTank = halfTank;
    }

    public double isExtraDrivenKm() {
        return extraDrivenKm;
    }

    public void setExtraDrivenKm(double extraDrivenKm) {
        this.extraDrivenKm = extraDrivenKm;
    }

    public String isDamages() {
        return damages;
    }

    public void setDamages(String damages) {
        this.damages = damages;
    }

    public double getDropoffDistance() {
        return dropoffDistance;
    }

    public void setDropoffDistance(double dropoffDistance) {
        this.dropoffDistance = dropoffDistance;
    }
}
