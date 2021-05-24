package com.examsproject.nordicmotorhome.Controller;
import com.examsproject.nordicmotorhome.Model.Autocamper;
import com.examsproject.nordicmotorhome.Model.Customer;
import com.examsproject.nordicmotorhome.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;



/**
 * @author oliverskau
 */

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

    @GetMapping("/customer/customerDelete/{customerID}")
    public String customerDelete(@PathVariable("customerID") int customerID) {
        Customer c = customerService.findCustomerById(customerID);

        customerService.deleteCustomer(c.getCustomerID());

        return "redirect:/customer/customerIndex";
    }

    @GetMapping("/customer/customerUpdate/{customerID}")
    public String customerUpdate(@PathVariable("customerID") int customerID, Model model) {
        Customer c = customerService.findCustomerById(customerID);
        model.addAttribute("customer",c);

        return "home/customer/customerUpdate";
    }

    @PostMapping("/customer/customerUpdate")
    public String customerUpdate(@ModelAttribute Customer customer) {
        customerService.updateCustomer(customer.getCustomerID(),customer);

        return "redirect:/customer/customerIndex";
    }

}