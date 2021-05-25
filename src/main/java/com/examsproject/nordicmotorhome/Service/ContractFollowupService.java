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
    public ContractFollowup createContractFollowup(ContractFollowup c, int contractID){
        return contractFollowupRepo.createContractFollowup(c,contractID);
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

    public double calculateTotalPrice(ContractFollowup c) {
        double totalPrice = 0;

        if (c.getHalfTank().equals("yes")) {
            totalPrice += 70;
        }
        if (c.getDamages().equals("yes")) {
            totalPrice += c.getDamageCost();
        }
        if(c.getExtraDrivenKm() > 0.0) { //hvis der er kørt gennemsnittleigt over 400 km om dagen ganger vi med 5.21 pr km
            totalPrice += c.getExtraDrivenKm() * 5.21;
        }
        if(c.getDropoffDistance() > 0.0) { //hvis afleveringsstedet er udenfor Nordic motorhome ganger vi med 5.21
            // for hver km der er til vores garage
            totalPrice += c.getDropoffDistance() * 5.21;
        }
        c.setFollowupPrice(totalPrice);

        return totalPrice;
    }
}
