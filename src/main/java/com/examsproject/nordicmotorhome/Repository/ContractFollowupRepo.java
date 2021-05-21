package com.examsproject.nordicmotorhome.Repository;

import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Model.ContractFollowup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * class that handles contractFollowups sql
 *
 * @author jonaskunert
 */

@Repository
public class ContractFollowupRepo {

    @Autowired
    JdbcTemplate template;

    /**
     * queries the table for all contractfollowups
     *
     * @return list with all contractsfollowups in object form
     */
    public List<ContractFollowup> fetchAll() {
        String sql = "SELECT * FROM contractfollowups";
        RowMapper<ContractFollowup> contractFollowups = new BeanPropertyRowMapper<>(ContractFollowup.class);

        return template.query(sql, contractFollowups);
    }

    /**
     * Inserts the chosen values into the database
     *
     * @param c the contract followup
     * @return the contract followup
     */
    public ContractFollowup createContractFollowup(ContractFollowup c) {
        String sql = "INSERT INTO contractfollowups(contractFollowupID,repairPrice,halfTank," +
                "extraDrivenKm,damages,dropoffDistance) VALUES(?,?,?,?,?,?)";
        template.update(sql,c.getContractFollowUpID(),c.getRepairPrice(),c.isHalfTank(),c.isExtraDrivenKm(),
                c.isDamages());

        return c;
    }

    /**
     * find the desired contract by its id
     *
     * @param contractFollowupID the id on the contract followup
     * @return the contractfollowup
     */
    public ContractFollowup findContractFollowupByID(int contractFollowupID) {
        String sql = "SELECT * FROM contractfollowups WHERE contractFollowupID = ?";
        RowMapper<ContractFollowup> contractFollowups = new BeanPropertyRowMapper<>(ContractFollowup.class);
        ContractFollowup c = template.queryForObject(sql, contractFollowups, contractFollowupID);

        return c;
    }

    /**
     * delete the desired contract followup by its id
     *
     * @param contractFollowupID the id on the contract followup
     * @return boolean that indicates if it deleted succesfully
     */
    public Boolean deleteContractFollowup(int contractFollowupID) {
        String sql = "DELETE FROM contractfollowups WHERE contractFollowupID = ?";

        return template.update(sql, contractFollowupID) < 0;
    }

    /**
     * updates any info that needs changing on the contract followup
     *
     * @param contractFollowupID the id on the contract
     * @param c          the contract followup
     * @return the contract followup
     */
    public ContractFollowup updateContractFollowup(int contractFollowupID, ContractFollowup c) {
        String sql = "UPDATE contractfollowups SET contractFollowupID = ?,repairPrice = ?,HalfTank() = ?" +
                ",isExtraDrivenKm() = ?,isDamages() = ?,getDropOffDistance() = ?";
        template.update(sql,c.getContractFollowUpID(),c.getRepairPrice(),c.isHalfTank(),c.isExtraDrivenKm(),
                c.isDamages());
        return c;
    }
}
