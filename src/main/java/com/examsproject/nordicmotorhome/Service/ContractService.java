package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Autocamper;
import com.examsproject.nordicmotorhome.Model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service class til autocamper
 *@author rasmuskoefoed
 */

@Service
public class ContractService {

    @Autowired
    ContractRepo contractRepo;
    public List<Contract> fetchAll(){
        return contractRepo.fetchAll();
    }
    public Contract addContract(Contract c){
        return contractRepo.addContract(c);
    }
    public Autocamper findContractById(int contractID){
        return contractRepo.findContractByID(contractID);
    }
    public Boolean deleteAutocamper(int autocamperID){
        return autocamperRepo.deleteAutocamper(autocamperID);
    }
    public Autocamper updateAutocamper(int autocamperID, Autocamper a){
        return autocamperRepo.updateAutocamper(autocamperID,a);
    }
}
