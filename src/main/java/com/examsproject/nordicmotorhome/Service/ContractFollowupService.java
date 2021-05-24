package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.ContractFollowup;
import com.examsproject.nordicmotorhome.Repository.ContractFollowupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service class til contractFollowup
 *@author rasmuskoefoed
 */

@Service
public class ContractFollowupService {

    @Autowired
    ContractFollowupRepo contractFollowupRepo;

    public List<ContractFollowup> fetchAll(){
        return contractFollowupRepo.fetchAll();
    }
    public ContractFollowup createContractFollowup(ContractFollowup c){
        return contractFollowupRepo.createContractFollowup(c);
    }
    public ContractFollowup findContractFollowupById(int contractFollowupID){
        return contractFollowupRepo.findContractFollowupByID(contractFollowupID);
    }
    public Boolean deleteContractFollowup(int contractFollowupID){
        return contractFollowupRepo.deleteContractFollowup(contractFollowupID);
    }
    public ContractFollowup updateContractFollowup(int contractFollowupID, ContractFollowup c){
        return contractFollowupRepo.updateContractFollowup(contractFollowupID,c);
    }

    public double caluculateTotalPrice(ContractFollowup c) {
        double totalPrice = 0;

        if (c.getHalfTank().equals("yes")) {
            totalPrice += 70;
        }
        if (c.getDamages().equals("yes")) {
            totalPrice += c.getDamageCost();
        }

        totalPrice += c.getRepairPrice();
        return totalPrice;
    }
}
