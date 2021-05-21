package com.examsproject.nordicmotorhome.Controller;
import com.examsproject.nordicmotorhome.Model.Autocamper;
import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Model.Customer;
import com.examsproject.nordicmotorhome.Service.AutocamperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AutocamperController {

    @Autowired
    AutocamperService autocamperService;

        @GetMapping("/autocamper/autocamperIndex")
        public String autocamperIndex(Model model) {
            List<Autocamper> autocamperList = autocamperService.fetchAll();
            model.addAttribute("autocampers",autocamperList);
            return "home/autocamper/autocamperIndex";
        }

    @GetMapping("/autocamper/autocamperCreate")
    public String autocamperCreate(){
        return "home/autocamper/autocamperCreate";
    }

    @PostMapping("/autocamper/autocamperCreate")
    public String autocamperCreate(@ModelAttribute Autocamper autocamper){
        autocamperService.createAutocamper(autocamper);
        return "redirect:/autocamper/autocamperIndex";
    }

    @GetMapping("/autocamper/autocamperDelete/{autocamperID}")
    public String autocamperDelete(@PathVariable("autocamperID") int autocamperID) {
        Autocamper a = autocamperService.findAutocamperByID(autocamperID);

        autocamperService.deleteAutocamper(a.getAutocamperID());

        return "redirect:/autocamper/autocamperIndex";
    }

    @GetMapping("/autocamper/autocamperUpdate/{autocamperID}")
    public String autocamperUpdate(@PathVariable("autocamperID") int autocamperID, Model model) {
        Autocamper a = autocamperService.findAutocamperByID(autocamperID);
        model.addAttribute("autocamper",a);

        return "home/autocamper/autocamperUpdate";
    }

    @PostMapping("/autocamper/autocamperUpdate")
    public String autocamperUpdate(@ModelAttribute Autocamper autocamper) {
        autocamperService.updateAutocamper(autocamper.getAutocamperID(), autocamper);
        return "redirect:/autocamper/autocamperIndex";
    }

}
