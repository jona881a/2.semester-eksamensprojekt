package com.examsproject.nordicmotorhome.Repository;

import com.examsproject.nordicmotorhome.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jonaskunert
 */
@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate template;

    /**
     * Henter alt data og laver objekter af customer modellen
     * @return liste af customer objekter
     */
    public List<Customer> fetchAll() {
        String sql = "SELECT * FROM customers";
        RowMapper<Customer> customers = new BeanPropertyRowMapper<>(Customer.class);

        return template.query(sql,customers);
    }

    /**
     * Indsætter data i databasen fra customerobjekt som er oprettet i Customercontroller
     * @param c customer
     * @return customer
     */
    public Customer createCustomer(Customer c) {
        String sql = "INSERT INTO customers(customerID,customerdebtID,firstname,lastname,email,phonenumber,address,zipcode) VALUES(?,?,?,?,?,?,?)";
        template.update(sql,c.getCustomerID(),c.getCustomerdebtID(),c.getFirstname(),c.getLastname(),c.getEmail(),c.getPhonenumber(),
                c.getAddress(),c.getZipcode());

        return c;
    }

    /**
     * Finder den specifikke Customer baseret på ID
     * @param customerID
     * @return customer
     */
    public Customer findCustomerByID(int customerID) {
        String sql = "SELECT * FROM customers WHERE customerID = ?";
        RowMapper<Customer> customers = new BeanPropertyRowMapper<>(Customer.class);
        Customer c = template.queryForObject(sql, customers, customerID);

        return c;
    }

    /**
     * Sletter Customer specificeret med ID
     * @param customerID
     * @return 1 hvis det lykkedes, 0 hvis det ikke lykkedes
     */
    public Boolean deleteCustomer(int customerID) {
        String sql = "DELETE FROM customers WHERE customerID = ?";

        return template.update(sql,customerID) < 0;
    }

    /**
     * Opdatere alt information til det specifikke Customer objekt fundet med ID
     * @param customerID
     * @param c customer
     * @return customer
     */
    public Customer updateCustomer(int customerID, Customer c) {
        String sql = "UPDATE customers SET customerID = ?, customerdebtID = ?, firstname = ?,lastname = ?," +
                "email = ?,phonenumber = ?,address = ?,zipcode = ? WHERE customerID = ?";
        template.update(sql,c.getCustomerID(), c.getCustomerdebtID(), c.getFirstname(),c.getLastname(),c.getEmail(),c.getPhonenumber(),
                c.getAddress(),c.getZipcode(), customerID);
        return c;
    }

}
