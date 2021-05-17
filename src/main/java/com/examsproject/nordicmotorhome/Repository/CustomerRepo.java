package com.examsproject.nordicmotorhome.Repository;

import com.examsproject.nordicmotorhome.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * class that handles customer sql
 * @author jonaskunert
 */
@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate template;

    /**
     * queries the table for all customers
     * @return list with all customers in object form
     */
    public List<Customer> fetchAll() {
        String sql = "SELECT * FROM customers";
        RowMapper<Customer> customers = new BeanPropertyRowMapper<>(Customer.class);

        return template.query(sql,customers);
    }

    /**
     * Inserts the chosen values into the database
     * @param c the customer
     * @return the customer
     */
    public Customer createCustomer(Customer c) {
        String sql = "INSERT INTO customers(customerID,firstname,lastname,email,phonenumber,address,zipcode) VALUES(?,?,?,?,?,?,?)";
        template.update(sql,c.getCustomerID(),c.getFirstname(),c.getLastname(),c.getEmail(),c.getPhonenumber(),
                c.getAddress(),c.getZipcode());

        return c;
    }

    /**
     * find the desired customer by its id
     * @param customerID the id on the customer
     * @return the customer
     */
    public Customer findCustomerByID(int customerID) {
        String sql = "SELECT * FROM customers WHERE customerID = ?";
        RowMapper<Customer> customers = new BeanPropertyRowMapper<>(Customer.class);
        Customer c = template.queryForObject(sql, customers, customerID);

        return c;
    }

    /**
     * delete the desired customer by its id
     * @param customerID the id on the customer
     * @return boolean that indicates if it deleted succesfully
     */
    public Boolean deleteCustomer(int customerID) {
        String sql = "DELETE FROM customers WHERE customerID = ?";

        return template.update(sql,customerID) < 0;
    }

    /**
     * updates any info that needs changing on the customer
     * @param customerID the id on the customer
     * @param c the customer
     * @return the customer
     */
    public Customer updateCustomer(int customerID, Customer c) {
        String sql = "UPDATE customers SET customerID = ?,firstname = ?,lastname = ?," +
                "email = ?,phonenumber = ?,address = ?,zipcode WHERE customerID = ?";
        template.update(sql,c.getCustomerID(),c.getFirstname(),c.getLastname(),c.getEmail(),c.getPhonenumber(),
                c.getAddress(),c.getZipcode());
        return c;
    }

}
