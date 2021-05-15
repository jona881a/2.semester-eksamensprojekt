package com.examsproject.nordicmotorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ContractFollowup {

    @Id
    private int contractFollowUpID;
    private int repairPrice;
    private boolean halfTank;
    private boolean extraDrivenKm;
    private boolean damages;
    private int dropOffDistance;

    public ContractFollowup() {}

    public ContractFollowup(int contractFollowUpID, int repairPrice, boolean halfTank, boolean extraDrivenKm, boolean damages, int dropOffDistance) {
        this.contractFollowUpID = contractFollowUpID;
        this.repairPrice = repairPrice;
        this.halfTank = halfTank;
        this.extraDrivenKm = extraDrivenKm;
        this.damages = damages;
        this.dropOffDistance = dropOffDistance;
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

    public boolean isHalfTank() {
        return halfTank;
    }

    public void setHalfTank(boolean halfTank) {
        this.halfTank = halfTank;
    }

    public boolean isExtraDrivenKm() {
        return extraDrivenKm;
    }

    public void setExtraDrivenKm(boolean extraDrivenKm) {
        this.extraDrivenKm = extraDrivenKm;
    }

    public boolean isDamages() {
        return damages;
    }

    public void setDamages(boolean damages) {
        this.damages = damages;
    }

    public int getDropOffDistance() {
        return dropOffDistance;
    }

    public void setDropOffDistance(int dropOffDistance) {
        this.dropOffDistance = dropOffDistance;
    }
}
