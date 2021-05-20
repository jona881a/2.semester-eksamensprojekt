package com.examsproject.nordicmotorhome.Repository;

import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Model.Extras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String sql = "SELECT * FROM contracts JOIN rentaldetails USING(rentaldetailsID)";
        RowMapper<Contract> contracts = new BeanPropertyRowMapper<>(Contract.class);

        return template.query(sql,contracts);
    }

    /**
     * Inserts the chosen values into the database
     * @param c the contract
     * @return the contract
     */
    public Contract createContract(Contract c, Extras e) {
        String sqlContracts = "INSERT INTO contracts(contractID,autocamperID,customerID," +
                "rentalprice,contractfollowupID) VALUES(?,?,?,?,?)";
        String sqlRentalDetails = "INSERT INTO rentaldetails(rentaldetailsID,rentalstartdate,rentalenddate," +
                "pickuptime,dropofftime,duration) VALUES(?,?,?,?,?,?)";
        String sqlExtras = "INSERT INTO extras(extrasID,luxurypackage,sportpackage,familypackage,picknickpackage)" +
                "VALUES(?,?,?,?,?)";


        try {
            Date formattedStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(c.getRentalStartDate());
            Date formattedEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(c.getRentalEndDate());

            template.update(sqlExtras,c.getContractID(),e.getLuxuryPackage(),e.getSportPackage()
                    ,e.getFamilyPackage(),e.getPicknickPackage());
            template.update(sqlRentalDetails,c.getContractID(),formattedStartDate,formattedEndDate,c.getPickupTime(),
                    c.getDropoffTime());
            template.update(sqlContracts,c.getContractID(),c.getAutocamperID(),c.getCustomerID(),
                    c.getRentalPrice(),c.getContractFollowupID());

        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        String insertIDs = "UPDATE contracts SET rentaldetailsID = ?, extrasID = ? WHERE contractID = ?";
        String sqlRentaldetailID = "SELECT rentaldetailsID FROM rentaldetails ORDER BY rentaldetailsID DESC LIMIT 1";
        String sqlExtrasID = "SELECT extrasID FROM extras ORDER BY extrasID DESC LIMIT 1";
        String sqlContractID = "SELECT contractID FROM contracts ORDER BY contractID DESC LIMIT 1";

        int rentaldetailID = template.queryForObject(sqlRentaldetailID,Integer.class);
        int extrasID = template.queryForObject(sqlExtrasID,Integer.class);
        int contractID = template.queryForObject(sqlContractID,Integer.class);

        template.update(insertIDs,rentaldetailID,extrasID,contractID);

        return c;
    }

    /**
     * find the desired contract by its id
     * @param contractID the id on the contract
     * @return the contract
     */
    public Contract findContractByID(int contractID) {
        String sql = "SELECT * FROM contracts WHERE contractID = ?";
        RowMapper<Contract> contracts = new BeanPropertyRowMapper<>(Contract.class);
        Contract c = template.queryForObject(sql, contracts, contractID);

        return c;
    }

    /**
     * delete the desired contract by its id
     * @param contractID the id on the contract
     * @return boolean that indicates if it deleted succesfully
     */
    public Contract deleteContract(int contractID) {
        String sqlDeleteContracts = "DELETE FROM contracts WHERE contractID = ?";

        String sqlDeleteRentalDetails = "DELETE FROM rentaldetails WHERE rentaldetailsID = ?";
        String sqlDeleteExtras = "DELETE FROM extras WHERE extrasID = ?";

        template.update(sqlDeleteRentalDetails,findContractByID(contractID).getRentaldetailsID());
        template.update(sqlDeleteExtras,findContractByID(contractID).getExtrasID());
        template.update(sqlDeleteContracts,contractID);

        return null;
    }

    /**
     * updates any info that needs changing on the contract
     * @param contractID the id on the contract
     * @param c the contract
     * @return the contract
     */
    public Contract updateContract(int contractID, Contract c) {
        String sql = "UPDATE contracts SET contractID = ?,autocamperID = ?,customerID = ?" +
                ",rentalPrice = ?,rentalStartDate = ?,pickuptime = ?,rentalEndDate = ?, dropofftime = ? WHERE contractid = ?";
        template.update(sql,c.getContractID(),c.getAutocamperID(),c.getCustomerID(),
                c.getRentalPrice(),c.getRentalStartDate(),c.getPickupTime(),c.getRentalEndDate(),c.getDropoffTime(),c.getContractFollowupID());
        return c;
    }

}
