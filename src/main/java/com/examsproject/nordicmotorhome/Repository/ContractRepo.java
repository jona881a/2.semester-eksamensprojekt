package com.examsproject.nordicmotorhome.Repository;

import com.examsproject.nordicmotorhome.Model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * class that handles contract sql
 * @author jonaskunert
 */
@Repository
public class ContractRepo {

    @Autowired
    JdbcTemplate template;

    /**
     * queries the table for all contracts
     * @return list with all contracts in object form
     */
    public List<Contract> fetchAll() {
        String sql = "SELECT * FROM contracts";
        RowMapper<Contract> contracts = new BeanPropertyRowMapper<>(Contract.class);

        return template.query(sql,contracts);
    }

    /**
     * Inserts the chosen values into the database
     * @param c the contract
     * @return the contract
     */
    public Contract createContract(Contract c) {
        String sql = "INSERT INTO contracts(contractID,autocamperID,customerID,rentalPrice,rentalStartDate,rentalEndDate) VALUES(?,?,?,?,?,?)";
        template.update(sql,c.getContractID(),c.getAutocamperID(),c.getCustomerID(),
                c.getRentalPrice(),c.getRentalStartDate(),c.getRentalEndDate());

        return c;
    }

    /**
     * find the desired contract by its id
     * @param contractID the id on the contract
     * @return the contract
     */
    public Contract findContractByID(int contractID) {
        String sql = "SELECT * FROM contracts WHERE contractid = ?";
        RowMapper<Contract> contracts = new BeanPropertyRowMapper<>(Contract.class);
        Contract c = template.queryForObject(sql, contracts, contractID);

        return c;
    }

    /**
     * delete the desired contract by its id
     * @param contractID the id on the contract
     * @return boolean that indicates if it deleted succesfully
     */
    public Boolean deleteContract(int contractID) {
        String sql = "DELETE FROM contracts WHERE contractID = ?";

        return template.update(sql,contractID) < 0;
    }

    /**
     * updates any info that needs changing on the contract
     * @param contractID the id on the contract
     * @param c the contract
     * @return the contract
     */
    public Contract updateContract(int contractID, Contract c) {
        String sql = "UPDATE contracts SET contractID = ?,autocamperID = ?,customerID = ?" +
                ",rentalPrice = ?,rentalStartDate = ?,rentalEndDate = ? WHERE contractid = ?";
        template.update(sql,c.getContractID(),c.getAutocamperID(),c.getCustomerID(),
                c.getRentalPrice(),c.getRentalStartDate(),c.getRentalEndDate());
        return c;
    }

}
