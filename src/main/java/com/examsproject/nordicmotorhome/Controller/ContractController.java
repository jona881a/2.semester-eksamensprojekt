package com.examsproject.nordicmotorhome.Controller;

import com.examsproject.nordicmotorhome.Model.Autocamper;
import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Model.CustomerDebt;
import com.examsproject.nordicmotorhome.Model.Extras;
import com.examsproject.nordicmotorhome.Service.AutocamperService;
import com.examsproject.nordicmotorhome.Service.ContractService;
import com.examsproject.nordicmotorhome.Service.CustomerDebtService;
import com.examsproject.nordicmotorhome.Service.ExtrasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

/**
 * @author jonaskunert
 */
@Controller
public class ContractController {


    @Autowired
    ContractService contractService;
    @Autowired
    AutocamperService autocamperService;
    @Autowired
    CustomerDebtService customerDebtService;
    @Autowired
    ExtrasService extrasService;

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

    /**@Author raskoe
     * Creation of a contract
     * The method sets the autocamper to not available when rented and calculates the price for the rent
     * @param c
     * @return
     */
    @PostMapping("/contract/contractCreate")
    public String contractCreate(@ModelAttribute Contract c, @ModelAttribute Extras e) {
        contractService.calculateTotalContractPrice(c,e,autocamperService);
        contractService.createContract(c,e);
        return "redirect:/contract/contractIndex";
    }

    /**
     * Metode til at slette en contract, når vi sletter en kontrakt så laver vi en customerdebt som regner cancellationprisen
     * @param contractID
     * @return to the homepage
     */
    @GetMapping("/contract/contractDelete/{contractID}")
    public String contractDelete(@PathVariable("contractID") int contractID){
        Contract c = contractService.findContractById(contractID);

        customerDebtService.createCustomerDebt(
                new CustomerDebt(contractID, contractID, c.getRentalStartDate(), c.getRentalEndDate(), "yes",
                        LocalDate.now().toString(), Period.between(LocalDate.now(), LocalDate.parse(c.getRentalStartDate())).getDays(),
                        contractService.calculateCancellationFee(c)));

        contractService.deleteContract(contractID);

        return "redirect:/contract/contractIndex";
    }

    /**
     * Updating of a contract
     * @param contractID the id on the contract to be updated
     * @return redirect to page
     */
    @GetMapping("/contract/contractUpdate/{contractID}")
    public String contractUpdate(@PathVariable("contractID") int contractID, Model model){
        Contract c = contractService.findContractById(contractID);
        Extras e = extrasService.findExtrastByID(c.getExtrasID());
        model.addAttribute("contract", c);
        model.addAttribute("extras", e);
        return "home/contract/contractUpdate";
    }

    /**
     * @TODO der skal updates pris hvis der ændres i dato (IKKE LØST)
     * @param contract
     * @param extras
     * @return
     */
    @PostMapping("/contract/contractUpdate")
    public String contractUpdate(@ModelAttribute Contract contract, @ModelAttribute Extras extras) {
        contractService.calculateTotalContractPrice(contract,extras,autocamperService); //Udregner prisen for de nye ændringer
        contractService.updateContract(contract.getContractID(), contract);
        extrasService.updateExtras(contract.getExtrasID(), extras);
        return "redirect:/contract/contractIndex";
    }
}
