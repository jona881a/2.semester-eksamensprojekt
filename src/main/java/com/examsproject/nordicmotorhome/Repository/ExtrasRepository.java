package com.examsproject.nordicmotorhome.Repository;

import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Model.Extras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExtrasRepository {

    @Autowired
    JdbcTemplate template;

    public List<Extras> fetchAll() {
        String sql = "SELECT * FROM extras";
        RowMapper<Extras> extras = new BeanPropertyRowMapper<>(Extras.class);

        return template.query(sql,extras);
    }
}
