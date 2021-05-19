package com.examsproject.nordicmotorhome.Repository;

import com.examsproject.nordicmotorhome.Model.Autocamper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AutocamperRepo {

    @Autowired
    JdbcTemplate template;

    /**
     * queries the table for all autocampers
     * @return list with all autocampers in object form
     */
    public List<Autocamper> fetchAll() {
        String sql = "SELECT * FROM autocampers";
        RowMapper<Autocamper> autocampers = new BeanPropertyRowMapper<>(Autocamper.class);

        return template.query(sql,autocampers);
    }

    /**
     * queries the autocampers that is available and puts them in list
     * @return list with autocampers available
     */
    public List<Autocamper> fetchAllAvailable() {
        String sql = "SELECT * FROM autocampers WHERE isavailable = 'yes'";
        RowMapper<Autocamper> autocampers = new BeanPropertyRowMapper<>(Autocamper.class);

        return template.query(sql,autocampers);
    }

    /**
     * Inserts the chosen values into the database
     * @param a the autocamper
     * @return the autocamper
     */
    public Autocamper createAutocamper(Autocamper a) {
        String sql = "INSERT INTO autocampers(autocamperID, brand, model, isAvailable,size, numberPlate, priceperday)" +
                " VALUES(?,?,?,?,?,?,?)";
        template.update(sql,a.getAutocamperID(),a.getBrand(),a.getModel(),a.getIsAvailable(),a.getSize(),a.getNumberplate(), a.getPrice());

        return a;
    }

    /**
     * find the desired autocamper by its id
     * @param autocamperID the id on the autocamper
     * @return the autocamper
     */
    public Autocamper findAutocamperByID(int autocamperID) {
        String sql = "SELECT * FROM autocampers WHERE autocamperID = ?";
        RowMapper<Autocamper> autocampers = new BeanPropertyRowMapper<>(Autocamper.class);
        Autocamper a = template.queryForObject(sql, autocampers, autocamperID);

        return a;
    }

    /**
     * delete the desired autocamper by its id
     * @param autocamperID the id on the autocamper
     * @return boolean that indicates if it deleted succesfully
     */
    public Boolean deleteAutocamper(int autocamperID) {
        String sql = "DELETE FROM autocampers WHERE autocamperID = ?";

        return template.update(sql,autocamperID) < 0;
    }

    /**
     * updates any info that needs changing on the autocamper
     * @param autocamperID the id on the autocamper
     * @param a the autocamper
     * @return the autocamper
     */
    public Autocamper updateAutocamper(int autocamperID, Autocamper a) {
        String sql = "UPDATE autocampers SET autocamperID = ?, brand = ?, model = ?, isAvailable = ?," +
                " size = ?, numberPlate = ?, priceperday = ? WHERE autocamperID = ?";
        template.update(sql,a.getAutocamperID(),a.getBrand(),a.getModel(),a.getIsAvailable(),a.getSize(),a.getNumberplate(),a.getPrice(),autocamperID);
        return a;
    }
}
