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
     * queries tabellen for contracts
     * @return en liste med alle contracts som objekter
     */
    public List<Contract> fetchAll() {
        String sql = "SELECT * FROM contracts JOIN rentaldetails USING(rentaldetailsID)";
        RowMapper<Contract> contracts = new BeanPropertyRowMapper<>(Contract.class);

        return template.query(sql,contracts);
    }

    /**
     * Metoden indsætter i contract, rentaldetails og extras og linker de rigtige id'er til contract
     * @param c
     * @return c
     */
    public Contract createContract(Contract c, Extras e) {
        String sqlContracts = "INSERT INTO contracts(contractID,autocamperID,customerID," +
                "rentalprice,contractfollowupID) VALUES(?,?,?,?,?)";
        String sqlRentalDetails = "INSERT INTO rentaldetails(rentaldetailsID,rentalstartdate,rentalenddate," +
                "pickuptime,dropofftime,pickupaddress,dropoffaddress) VALUES(?,?,?,?,?,?,?)";
        String sqlExtras = "INSERT INTO extras(extrasID,luxurypackage,sportpackage,familypackage,picknickpackage)" +
                "VALUES(?,?,?,?,?)";


        try { //Der skal formatteres til den amerikanske dateformat fra html siden så den kan gemmes i SQL
            Date formattedStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(c.getRentalStartDate());
            Date formattedEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(c.getRentalEndDate());

            template.update(sqlExtras,c.getContractID(),e.getLuxuryPackage(),e.getSportPackage()
                    ,e.getFamilyPackage(),e.getPicknickPackage());
            template.update(sqlRentalDetails,c.getContractID(),formattedStartDate,formattedEndDate,c.getPickupTime(),
                    c.getDropoffTime(),c.getPickupAddress(),c.getDropoffAddress());
            template.update(sqlContracts,c.getContractID(),c.getAutocamperID(),c.getCustomerID(),
                    c.getRentalPrice(),c.getContractFollowupID());

        } catch (ParseException parseException) {
            parseException.printStackTrace();
            parseException.printStackTrace();
        }

        //Efter vi har indsat værdierne i rentaldetails og extras hiver vi fat i deres id'er så vi kan tilknytte dem til
        //den contract som de høre til
        String insertIDs = "UPDATE contracts SET rentaldetailsID = ?, extrasID = ? WHERE contractID = ?";
        String sqlRentaldetailID = "SELECT rentaldetailsID FROM rentaldetails ORDER BY rentaldetailsID DESC LIMIT 1";
        String sqlExtrasID = "SELECT extrasID FROM extras ORDER BY extrasID DESC LIMIT 1";
        String sqlContractID = "SELECT contractID FROM contracts ORDER BY contractID DESC LIMIT 1";

        //Vi querier nu de tre integers
        int rentaldetailID = template.queryForObject(sqlRentaldetailID,Integer.class);
        int extrasID = template.queryForObject(sqlExtrasID,Integer.class);
        int contractID = template.queryForObject(sqlContractID,Integer.class);

        template.update(insertIDs,rentaldetailID,extrasID,contractID);

        return c;
    }

    /**
     * finder den relevante kontrakt med den id man oplyser
     * @param contractID
     * @return kontrakten der er fundet
     */
    public Contract findContractByID(int contractID) {
        String sql = "SELECT * FROM contracts JOIN rentaldetails using(rentaldetailsID) WHERE contractID = ?";
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
        String sqlDeleteContractFollowup = "DELETE FROM contractfollowups WHERE contractfollowupID = ?";
        Contract c = findContractByID(contractID);

        template.update(sqlDeleteContracts,contractID);
        template.update(sqlDeleteRentalDetails,c.getRentaldetailsID());
        template.update(sqlDeleteExtras,c.getExtrasID());
        template.update(sqlDeleteContractFollowup,c.getContractFollowupID());

        return c;
    }

    /**
     * updates any info that needs changing on the contract
     * @param contractID the id on the contract
     * @param c the contract
     * @return the contract
     */
    public Contract updateContract(int contractID, Contract c) {
        String sqlContractUpdate = "UPDATE contracts SET contractID = ?,autocamperID = ?,customerID = ?" +
                ",rentalprice = ? WHERE contractid = ?";
        String sqlRentalDetailsUpdate = "UPDATE rentaldetails SET rentaldetailsID = ?,rentalstartdate = ?," +
                "rentalenddate = ?,pickuptime = ?,dropofftime = ?,pickupaddress = ?,dropoffaddress = ?" +
                "WHERE rentaldetailsID = ?";
        template.update(sqlContractUpdate,c.getContractID(),c.getAutocamperID(),c.getCustomerID(),
                c.getRentalPrice(),contractID);
        template.update(sqlRentalDetailsUpdate,c.getRentaldetailsID(),c.getRentalStartDate(),c.getRentalEndDate(),
                c.getPickupTime(),c.getDropoffTime(),c.getPickupAddress(),c.getDropoffAddress(),c.getRentaldetailsID());

        return c;
    }

}
