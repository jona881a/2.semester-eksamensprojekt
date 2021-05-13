package com.examsproject.nordicmotorhome.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service class til contract
 *@author rasmuskoefoed
 */

@Service

public class ContractFollowupService {

    @Autowired
    ContractFollowup contractFollowup;
    public List<ContractFollowup> fetchAll(){
        return ContractFollowupRepo.fetchAll();
    }
    public ContractFollowup addContractFollowup(ContractFollowup c){
        return contractFollowupRepo.addContractFollowup(c);
    }
    public ContractFollowup findContractFollowupById(int contractFollowupID){
        return contractFollowupRepo.findContractFollowupByID(contractFollowupID);
    }
    public Boolean deleteContractFollowup(int contractFollowupID){
        return contractFollowupRepo.deleteContractFollowup(contractFollowupID);
    }
    public contractFollowup updateContractFollowup(int contractFollowupID, ContractFollowup c){
        return contractFollowupRepo.updateContractFollowup(contractFollowupID,c);
    }
}
