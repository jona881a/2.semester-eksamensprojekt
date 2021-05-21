package com.examsproject.nordicmotorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContractFollowup {

    @Id
    private int contractFollowUpID;
    private int repairPrice;
    private String halfTank;
    private String extraDrivenKm;
    private String damages;

    public ContractFollowup() {}

    public ContractFollowup(int contractFollowUpID, int repairPrice, String halfTank,
                            String extraDrivenKm, String damages) {
        this.contractFollowUpID = contractFollowUpID;
        this.repairPrice = repairPrice;
        this.halfTank = halfTank;
        this.extraDrivenKm = extraDrivenKm;
        this.damages = damages;
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

    public String isExtraDrivenKm() {
        return extraDrivenKm;
    }

    public void setExtraDrivenKm(String extraDrivenKm) {
        this.extraDrivenKm = extraDrivenKm;
    }

    public String isDamages() {
        return damages;
    }

    public void setDamages(String damages) {
        this.damages = damages;
    }
}
