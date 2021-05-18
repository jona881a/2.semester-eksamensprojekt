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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author jonaskunert
 */
@Controller
public class ContractController {

    private static final double MIDDLE_SEASON = 0.3;
    private static final double PEAK_SEASON= 0.6;

    @Autowired
    ContractService contractService;
    @Autowired
    AutocamperService autocamperService;

    /**
     * getting the index site of the contracts
     * @param model
     * @return
     */
    @GetMapping("/contract/contractIndex")
    public String contractIndex(Model model) {
        List<Contract> contractList = contractService.fetchAll();
        model.addAttribute("contracts",contractList);
        return "home/contract/contractIndex";
    }

    /**
     * getting the site for creating
     * @param model
     * @return
     */
    @GetMapping("/contract/contractCreate")
    public String contractCreate(Model model) {
        List<Autocamper> autocampers = autocamperService.fetchAllAvailable();
        model.addAttribute("autocampers",autocampers);
        return "home/contract/contractCreate";
    }

    /**
     * Creation of a contract
     * The method sets the autocamper to not available when rented and calculates the price for the rent
     * @param c
     * @return
     */
    @PostMapping("/contract/contractCreate")
    public String contractCreate(@ModelAttribute Contract c) {
        double rentalPrice = 0.0;

        /* @TODO vi skal have lavet det om så den kun er "ikke tilgængelig" i den periode den er lejet i
        Autocamper a = autocamperService.findAutocamperByID(c.getAutocamperID());
        a.setIsAvailable("no");
        autocamperService.updateAutocamper(c.getAutocamperID(),a);

         */
        contractService.createContract(c);
        return "redirect:/";
    }

    /**
     * Deletion of a contract
     * @param contractID the id on the contract to be deleted
     * @return to the homepage
     */
    @GetMapping("/contract/contractDelete/{contractID}")
    public String contractDelete(@PathVariable("contractID") int contractID){
        contractService.deleteContract(contractID);
        return "redirect:/";
    }
}
