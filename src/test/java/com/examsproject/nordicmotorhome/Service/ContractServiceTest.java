package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Contract;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ContractServiceTest {

    Contract contract;
    @Autowired
    ContractService contractService;
    @Autowired
    AutocamperService autocamperService;

    @Test
    void calculateTotalContractPrice() {
        contract = new Contract();

    }

    @Test
    void calculateCancellationFee() {
    }
}