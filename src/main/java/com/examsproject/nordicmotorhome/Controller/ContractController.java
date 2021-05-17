package com.examsproject.nordicmotorhome.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContractController {

    @GetMapping("/contract/contractIndex")
    public String contractIndex() {
        return "home/contract/contractIndex";
    }

    @GetMapping("/contract/contractCreate")
    public String contractCreate() {
        return "home/contract/contractCreate";
    }
}
