package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Model.ContractFollowup;
import com.examsproject.nordicmotorhome.Model.CustomerDebt;
import com.examsproject.nordicmotorhome.Model.Extras;
import com.examsproject.nordicmotorhome.Repository.CustomerDebtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDebtService {

    @Autowired
    CustomerDebtRepo customerDebtRepo;

    public List<CustomerDebt> fetchAll() {
        return customerDebtRepo.fetchAll();
    }

    public CustomerDebt createCustomerDebt(CustomerDebt c) {
        return customerDebtRepo.createCustomerDebt(c);
    }

    public CustomerDebt findCustomerDebtByID(int customerDebtID) {
        return customerDebtRepo.findCustomerDebtByID(customerDebtID);
    }

    public boolean deleteCustomerDebt(int customerDebtID) {
        return customerDebtRepo.deleteCustomerDebt(customerDebtID);
    }

    public CustomerDebt updateCustomerDebt(int customerDebtID, CustomerDebt c) {
        return customerDebtRepo.updateCustomerDebt(customerDebtID,c);
    }

    public double calculateTotalDebt(ContractService contractService, ContractFollowupService contractFollowupService,
                                   Contract c, Extras e, AutocamperService autocamperService, ContractFollowup cf) {
        double totalPrice = 0;

        totalPrice += contractService.calculateTotalContractPrice(c, e, autocamperService)
                + contractFollowupService.caluculateTotalPrice(cf);

        return totalPrice
    }
}
