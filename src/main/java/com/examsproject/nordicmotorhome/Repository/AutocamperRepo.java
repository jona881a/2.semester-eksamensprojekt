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
     * Henter alle autocamper data i SQL og laver om til objekter
     * @return liste af autocamperobjekter
     */
    public List<Autocamper> fetchAll() {
        String sql = "SELECT * FROM autocampers";
        RowMapper<Autocamper> autocampers = new BeanPropertyRowMapper<>(Autocamper.class);

        return template.query(sql,autocampers);
    }

    /**
     * Henter alle autocamper data i SQL og laver dem til objekter, men kun dem der er tilgængelige
     * @return liste af autocamperobjekter
     */
    public List<Autocamper> fetchAllAvailable() {
        String sql = "SELECT * FROM autocampers WHERE isavailable = 'yes'";
        RowMapper<Autocamper> autocampers = new BeanPropertyRowMapper<>(Autocamper.class);

        return template.query(sql,autocampers);
    }

    /**
     * Indsætter data fra objektet der blev oprettet i contractController
     * @param a autocamper
     * @return autocamper
     */
    public Autocamper createAutocamper(Autocamper a) {
        String sql = "INSERT INTO autocampers(autocamperID, brand, model, isAvailable,size, numberPlate, priceperday)" +
                " VALUES(?,?,?,?,?,?,?)";
        template.update(sql,a.getAutocamperID(),a.getBrand(),a.getModel(),a.getIsAvailable(),a.getSize(),a.getNumberplate(), a.getPriceperday());

        return a;
    }

    /**
     * Finder autocamper med valgte autocamperID
     * @param autocamperID
     * @return autocamper
     */
    public Autocamper findAutocamperByID(int autocamperID) {
        String sql = "SELECT * FROM autocampers WHERE autocamperID = ?";
        RowMapper<Autocamper> autocampers = new BeanPropertyRowMapper<>(Autocamper.class);
        Autocamper a = template.queryForObject(sql, autocampers, autocamperID);

        return a;
    }

    /**
     * Sletter autocamper med specifikt id
     * @param autocamperID
     * @return 1 hvis der er slettet eller 0 hvis der ikke er
     */
    public Boolean deleteAutocamper(int autocamperID) {
        String sql = "DELETE FROM autocampers WHERE autocamperID = ?";

        return template.update(sql,autocamperID) < 0;
    }

    /**
     * Opdatere alt information til det specifikke objekt defineret af ID'et
     * @param autocamperID
     * @param a autocamper
     * @return autocamper
     */
    public Autocamper updateAutocamper(int autocamperID, Autocamper a) {
        String sql = "UPDATE autocampers SET autocamperID = ?, brand = ?, model = ?, isAvailable = ?," +
                " size = ?, numberPlate = ?, priceperday = ? WHERE autocamperID = ?";
        template.update(sql,a.getAutocamperID(),a.getBrand(),a.getModel(),a.getIsAvailable(),a.getSize(),a.getNumberplate(),a.getPriceperday(),autocamperID);
        return a;
    }
}
