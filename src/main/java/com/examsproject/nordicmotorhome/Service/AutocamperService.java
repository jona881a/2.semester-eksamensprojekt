package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Autocamper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Autocamper addAutocamper(Autocamper a){
        return autocamperRepo.addAutocamper(a);
    }
    public Autocamper findAutocamperById(int autocamperID){
        return autocamperRepo.findAutocamperById(autocamperID);
    }
    public Boolean deleteAutocamper(int autocamperID){
        return autocamperRepo.deleteAutocamper(autocamperID);
    }
    public Autocamper updateAutocamper(int autocamperID, Autocamper a){
        return autocamperRepo.updateAutocamper(autocamperID,a);
    }
}
