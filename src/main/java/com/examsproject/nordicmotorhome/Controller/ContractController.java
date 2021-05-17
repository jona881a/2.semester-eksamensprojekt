package com.examsproject.nordicmotorhome.Controller;

import com.examsproject.nordicmotorhome.Model.Autocamper;
import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Service.AutocamperService;
import com.examsproject.nordicmotorhome.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContractController {

    @Autowired
    ContractService contractService;
    @Autowired
    AutocamperService autocamperService;

    @GetMapping("/contract/contractIndex")
    public String contractIndex() {
        return "home/contract/contractIndex";
    }

    @GetMapping("/contract/contractCreate")
    public String contractCreate(Model model) {
        List<Autocamper> autocampers = autocamperService.fetchAll();
        model.addAttribute("autocampers",autocampers);
        return "home/contract/contractCreate";
    }

    @PostMapping("/contract/contractCreate")
    public String contractCreate(@ModelAttribute Contract c) {
        return "home/contract/contractCreate";
    }
}
