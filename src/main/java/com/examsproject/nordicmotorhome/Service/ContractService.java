package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Autocamper;
import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service class til contract
 *@author rasmuskoefoed
 */

@Service
public class ContractService {

    @Autowired
    ContractRepo contractRepo;

    public List<Contract> fetchAll(){
        return contractRepo.fetchAll();
    }
    public Contract createContract(Contract c){
        return contractRepo.createContract(c);
    }
    public Contract findContractById(int contractID){
        return contractRepo.findContractByID(contractID);
    }
    public Boolean deleteContract(int contractID){
        return contractRepo.deleteContract(contractID);
    }
    public Contract updateContract(int contractID, Contract c){
        return contractRepo.updateContract(contractID,c);
    }
}
