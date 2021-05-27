package com.examsproject.nordicmotorhome.Controller;
import com.examsproject.nordicmotorhome.Model.Customer;
import com.examsproject.nordicmotorhome.Model.CustomerDebt;
import com.examsproject.nordicmotorhome.Service.CustomerDebtService;
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

    @GetMapping("/customer/customerDetails/{customerID}")
    public String customerDetails(@PathVariable("customerID") int customerID, Model model){
        model.addAttribute("customer", customerService.findCustomerById(customerID));
        return "home/customer/customerDetails";
    }



    //----------------------  Controller til customerdebt  -------------------//

    @Autowired
    CustomerDebtService customerDebtService;

    @GetMapping("/customer/customerdebt/customerdebtIndex")
    public String customerdebtIndex(Model model) {
        List<CustomerDebt> customerdebts = customerDebtService.fetchAll();
        model.addAttribute("customerdebts",customerdebts);
        return "customerdebtDetails";
    }

    @GetMapping("/customer/customerdebt/customerdebtCreate")
    public String customerdebtCreate() {
        return "home/customer/customerdebt/customerdebtCreate";
    }

    @PostMapping("/customer/customerdebt/customerdebtCreate")
    public String customerdebtCreate(@ModelAttribute CustomerDebt customerDebt){
        customerDebtService.createCustomerDebt(customerDebt);
        return "redirect:/customer/customerdebt/customerdebtIndex";
    }

    @GetMapping("/customer/customerdebtDelete/{customerdebtID}")
    public String customerdebtDelete(@PathVariable("customerdebtID") int customerdebtID) {
        CustomerDebt c = customerDebtService.findCustomerDebtByID(customerdebtID);

        customerDebtService.deleteCustomerDebt(c.getCustomerDebtID());

        return "redirect:/customer/customerdebt/customerdebtIndex";
    }

    @GetMapping("/customer/customerdebt/customerdebtDetails/{customerdebtID}")
    public String customerdebtUpdate(@PathVariable("customerdebtID") int customerdebtID, Model model) {
        CustomerDebt c = customerDebtService.findCustomerDebtByID(customerdebtID);
        model.addAttribute("customerdebt",c);

        return "home/customer/customerdebt/customerdeptUpdate";
    }

    @PostMapping("/customer/customerdebt/customerdebtIndex/customerUpdate")
    public String customerdebtUpdate(@ModelAttribute CustomerDebt customerDebt) {
        customerDebtService.updateCustomerDebt(customerDebt.getCustomerDebtID(),customerDebt);

        return "redirect:/customer/customerdebt/customerdebtIndex";
    }
}