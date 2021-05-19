package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Extras;
import com.examsproject.nordicmotorhome.Repository.ExtrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtrasService {

    @Autowired
    ExtrasRepository extrasRepository;

    public List<Extras> fetchAll() {
        return extrasRepository.fetchAll();
    }
}
