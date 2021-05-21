package com.examsproject.nordicmotorhome.Controller;
import com.examsproject.nordicmotorhome.Model.Autocamper;
import com.examsproject.nordicmotorhome.Model.Customer;
import com.examsproject.nordicmotorhome.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/customerIndex")
    public String customerIndex(Model model) {
        List<Customer> customerList = customerService.fetchAll();
        model.addAttribute("customers",customerList);
        return "home/customer/customerIndex";
    }

    @GetMapping("/customer/customerCreate")
    public String customerCreate() {
        return "home/customer/customerCreate";
    }
    @PostMapping("/customer/customerCreate")
    public String customerCreate(@ModelAttribute Customer customer){
        customerService.createCustomer(customer);
        return "redirect:/customer/customerIndex";
    }

}