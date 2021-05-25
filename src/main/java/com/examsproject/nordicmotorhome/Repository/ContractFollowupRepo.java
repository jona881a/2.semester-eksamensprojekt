package com.examsproject.nordicmotorhome.Repository;

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
     * Indsætter den nyoprettede contractfollowup i databasen og opdatere contract så contractFollowupID passer
     *
     * @param c
     * @return contract followup
     */
    public ContractFollowup createContractFollowup(ContractFollowup c, int contractID) {
        String sqlCreateContractFollowup = "INSERT INTO contractfollowups(contractfollowupID,followupprice,halftank," +
                "extradrivenkm,damages,damagecost,dropoffdistance) VALUES(?,?,?,?,?,?,?)";
        //Bruger vi til at opdatere contracten så contractFollowupID er tilknyttet til contracten korrekt
        String sqlUpdateContractFollowupID = "UPDATE contracts SET contractfollowupID = ? WHERE contractID = ?";

        template.update(sqlCreateContractFollowup,c.getContractfollowupID(),c.getFollowupPrice(),c.isHalfTank(),c.isExtraDrivenKm(),
                c.isDamages(),c.getDamageCost(),c.getDropoffDistance());

        //Bruger vi til at hente contractfollowupID som lige er blevet oprettet
        String sqlContractFollowupID = "SELECT contractfollowupID FROM contractfollowups ORDER BY contractfollowupID DESC LIMIT 1";
        //Vi querier for den værdi contractFollowupID fik
        int contractFollowupID = template.queryForObject(sqlContractFollowupID,Integer.class);
        //Her opdatere vi contract så den referere til den id som passer med den opretted followup
        template.update(sqlUpdateContractFollowupID,contractFollowupID,contractID);

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
        String sql = "DELETE FROM contractfollowups WHERE contractfollowupID = ?";

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
        String sql = "UPDATE contractfollowups SET contractfollowupID = ?,followupprice = ?,halftank = ?" +
                ",extradrivenkm = ?,damages = ?,damagecost = ?, dropoffdistance = ? WHERE contractfollowupID = ?";
        template.update(sql,c.getContractfollowupID(),c.getFollowupPrice(),c.isHalfTank(),c.isExtraDrivenKm(),
                c.isDamages(),c.getDamageCost(),c.getDropoffDistance(), contractFollowupID);
        return c;
    }
}
