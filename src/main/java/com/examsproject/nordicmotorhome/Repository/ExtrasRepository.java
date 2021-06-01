package com.examsproject.nordicmotorhome.Repository;

import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Model.Extras;
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
public class ExtrasRepository {

    @Autowired
    JdbcTemplate template;

    public List<Extras> fetchAll() {
        String sql = "SELECT * FROM extras";
        RowMapper<Extras> extras = new BeanPropertyRowMapper<>(Extras.class);

        return template.query(sql,extras);
    }

    public Extras findExtrastByID(int extrasID) {
        String sql = "SELECT * FROM extras WHERE extrasID = ?";
        RowMapper<Extras> extras = new BeanPropertyRowMapper<>(Extras.class);
        Extras e = template.queryForObject(sql, extras, extrasID);

        return e;
    }

    public Extras updateExtras(int extrasID, Extras e) {
        String sql = "UPDATE extras SET extrasID = ?, luxurypackage = ?, sportpackage = ?, familypackage = ?," +
                " picknickpackage = ? WHERE extrasID = ?";
        template.update(sql,e.getExtrasID(),e.getLuxuryPackage(),e.getSportPackage(),e.getFamilyPackage(),
                e.getPicknickPackage(),extrasID);
        return e;
    }
}
