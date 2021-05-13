package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Autocamper;
import com.examsproject.nordicmotorhome.Repository.AutocamperRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service class til autocamper
 *@author rasmuskoefoed
 */

@Service
public class AutocamperService {

    @Autowired
    AutocamperRepo autocamperRepo;

    public List<Autocamper> fetchAll(){
        return autocamperRepo.fetchAll();
    }
    public Autocamper createAutocamper(Autocamper a){
        return autocamperRepo.createAutocamper(a);
    }
    public Autocamper findAutocamperByID(int autocamperID){
        return autocamperRepo.findAutocamperByID(autocamperID);
    }
    public Boolean deleteAutocamper(int autocamperID){
        return autocamperRepo.deleteAutocamper(autocamperID);
    }
    public Autocamper updateAutocamper(int autocamperID, Autocamper a){
        return autocamperRepo.updateAutocamper(autocamperID,a);
    }
}
