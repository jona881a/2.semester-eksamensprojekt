package com.examsproject.nordicmotorhome.Controller;

import com.examsproject.nordicmotorhome.Model.*;
import com.examsproject.nordicmotorhome.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.Period;
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
    @Autowired
    ContractFollowupService contractFollowupService;

    /**
     * GetMapping for at hente contractIndex.html
     * @param model
     * @return contractIndex.html
     */
    @GetMapping("/contract/contractIndex")
    public String contractIndex(Model model) {
        List<Contract> contractList = contractService.fetchAll();
        model.addAttribute("contracts",contractList);
        return "home/contract/contractIndex";
    }

    /**
     * Getmapping for contractCreate.html
     * @param model
     * @return contractCreate.html
     */
    @GetMapping("/contract/contractCreate")
    public String contractCreate(Model model) {
        List<Autocamper> autocampers = autocamperService.fetchAllAvailable();
        model.addAttribute("autocampers",autocampers);
        return "home/contract/contractCreate";
    }

    /**@Author raskoe
     * PostMapping for contractCreate.html
     * Metoden tager dataet fra html siden og regner en pris på lejen og gemmer den i databasen
     * @param c
     * @return Redirect til contractIndex.html
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
     * @return contractIndex.html
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
     * Metode der henter siden til opdatering af contract
     * @param contractID
     * @return redirect til contractUpdate.html
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
     * Metode der updatere kontrakten og udregner prisen igen
     * @param contract
     * @param extras
     * @return redirect til contractIndex.html
     */
    @PostMapping("/contract/contractUpdate")
    public String contractUpdate(@ModelAttribute Contract contract, @ModelAttribute Extras extras) {
        contractService.calculateTotalContractPrice(contract,extras,autocamperService); //Udregner prisen for de nye ændringer
        contractService.updateContract(contract.getContractID(), contract);
        extrasService.updateExtras(contract.getExtrasID(), extras);
        return "redirect:/contract/contractIndex";
    }

    @GetMapping("/contract/contractDetails/{contractID}")
    public String contractDetails(@PathVariable("contractID") int contractID, Model model){
        model.addAttribute("contract", contractService.findContractById(contractID));
        return "home/contract/contractDetails";
    }

    //---------------------CONTRACT FOLLOWUP CONTROLLERPART-------------------------------------------------------------

    /**
     * Metode der henter contractFollowupCreate siden
     * @param contractID
     * @param model
     * @return contractFollowupCreate.html
     */
    @GetMapping("/contract/contractFollowup/contractFollowupCreate/{contractID}")
    public String contractFollowupCreate(@PathVariable("contractID") int contractID, Model model) {
        Contract c = contractService.findContractById(contractID);
        model.addAttribute("contract",c);
        return "home/contract/contractFollowup/contractFollowupCreate";
    }

    /**
     * Metode der gemmer contractFollowup til databasen på contracten
     * @param contractFollowUp
     * @return tilbage til contractIndex.html
     */
    @PostMapping("/contract/contractFollowup/contractFollowupCreate")
    public String contractFollowupCreate(@ModelAttribute ContractFollowup contractFollowUp, @ModelAttribute Contract c) {
        contractFollowupService.calculateTotalPrice(contractFollowUp);
        contractFollowupService.createContractFollowup(contractFollowUp,c.getContractID());

        return "redirect:/contract/contractIndex";
    }

    /**
     * Metode der henter siden til at update en contractFollowup
     * @param contractID
     * @param model
     * @return contractFollowupUpdate.html
     */
    @GetMapping("/contract/contractFollowup/contractFollowupUpdate/{contractID}")
    public String contractFollowupUpdate(@PathVariable("contractID") int contractID, Model model) {
        Contract contract =contractService.findContractById(contractID);
        ContractFollowup contractFollowup = contractFollowupService.findContractFollowupById(contract.getContractFollowupID());
        model.addAttribute("c",contractFollowup);

        return "home/contract/contractFollowup/contractFollowupUpdate";
    }

    /**
     * Metode der updater contractfollowup der er valgt
     * @param contractFollowUp
     * @return tilbage til contractIndex.html
     */
    @PostMapping("/contract/contractFollowup/contractFollowupUpdate")
    public String contractFollowupCreate(@ModelAttribute ContractFollowup contractFollowUp) {
        contractFollowupService.calculateTotalPrice(contractFollowUp);
        contractFollowupService.updateContractFollowup(contractFollowUp.getContractfollowupID(),contractFollowUp);
        return "redirect:/contract/contractIndex";
    }

    @GetMapping("/contract/contractFollowup/contractFollowupDetails/{contractID}")
    public String contractFollowupDetails(@PathVariable("contractID") int contractID, Model model) {
        Contract c = contractService.findContractById(contractID);
        model.addAttribute("contractFollowup",contractFollowupService.findContractFollowupById(c.getContractFollowupID()));
        return "home/contract/contractFollowup/contractFollowupDetails";
    }
}
