package com.examsproject.nordicmotorhome.Controller;
import com.examsproject.nordicmotorhome.Model.Autocamper;
import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Service.AutocamperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

}
