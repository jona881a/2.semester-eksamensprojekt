package com.examsproject.nordicmotorhome.Controller;

import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContractController {

    @Autowired
    ContractService contractService;

    @GetMapping("/contract/contractIndex")
    public String contractIndex() {
        return "home/contract/contractIndex";
    }

    @GetMapping("/contract/contractCreate")
    public String contractCreate() {
        return "home/contract/contractCreate";
    }

    @PostMapping("/contract/contractIndex")
    public String contractCreate(@ModelAttribute Contract c) {
        contractService.calculateRentalPrice(c);
        return "home/contract/contractCreate";
    }
}
