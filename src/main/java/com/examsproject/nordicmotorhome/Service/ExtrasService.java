package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Model.Extras;
import com.examsproject.nordicmotorhome.Repository.ExtrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtrasService {

    @Autowired
    ExtrasRepository extrasRepository;
    @Autowired
    JdbcTemplate template;

    public List<Extras> fetchAll() {
        return extrasRepository.fetchAll();
    }

    public Extras findExtrastByID(int extrasID) {
        return extrasRepository.findExtrastByID(extrasID);
    }

    public Extras updateExtras(int extrasID, Extras e) {
        return extrasRepository.updateExtras(extrasID,e);
    }
}
