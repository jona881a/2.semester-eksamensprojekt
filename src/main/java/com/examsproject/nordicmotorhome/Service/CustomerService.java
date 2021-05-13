package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * service class til customer
 *@author rasmuskoefoed
 */

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;
    public List<Customer> fetchAll(){
        return customerRepo.fetchAll();
    }
    public Customer addCustomer(Customer c){
        return customerRepo.addCustomer(c);
    }
    public Customer findCustomerById(int customerID){
        return customerRepo.findCustomerByID(customerID);
    }
    public Boolean deleteCustomer(int customerID){
        return customerRepo.deleteCustomer(customerID);
    }
    public Customer updateCustomer(int customerID, Customer c){
        return customerRepo.updateCustomer(customerID,c);
    }
}
