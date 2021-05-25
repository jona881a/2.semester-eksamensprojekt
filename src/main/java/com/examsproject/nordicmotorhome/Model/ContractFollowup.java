package com.examsproject.nordicmotorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContractFollowup {

    @Id
    private int contractFollowUpID;
    private int repairPrice;
    private String halfTank;
    private int extraDrivenKm;
    private String damages;
    private int damageCost;

    public ContractFollowup() {}

    public ContractFollowup(int contractFollowUpID, int repairPrice, String halfTank,
                            int extraDrivenKm,String damages, int damageCost) {
        this.contractFollowUpID = contractFollowUpID;
        this.repairPrice = repairPrice;
        this.halfTank = halfTank;
        this.extraDrivenKm = extraDrivenKm;
        this.damages = damages;
        this.damageCost = damageCost;
    }

    public String getHalfTank() {
        return halfTank;
    }

    public int getExtraDrivenKm() {
        return extraDrivenKm;
    }

    public String getDamages() {
        return damages;
    }

    public int getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(int damageCost) {
        this.damageCost = damageCost;
    }

    public int getContractFollowUpID() {
        return contractFollowUpID;
    }

    public void setContractFollowUpID(int contractFollowUpID) {
        this.contractFollowUpID = contractFollowUpID;
    }

    public int getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(int repairPrice) {
        this.repairPrice = repairPrice;
    }

    public String isHalfTank() {
        return halfTank;
    }

    public void setHalfTank(String halfTank) {
        this.halfTank = halfTank;
    }

    public int isExtraDrivenKm() {
        return extraDrivenKm;
    }

    public void setExtraDrivenKm(int extraDrivenKm) {
        this.extraDrivenKm = extraDrivenKm;
    }

    public String isDamages() {
        return damages;
    }

    public void setDamages(String damages) {
        this.damages = damages;
    }

}
