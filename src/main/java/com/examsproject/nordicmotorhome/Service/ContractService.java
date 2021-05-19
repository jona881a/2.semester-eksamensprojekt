package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Autocamper;
import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Model.Extras;
import com.examsproject.nordicmotorhome.Repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;

/**
 * service class til contract
 *@author rasmuskoefoed
 */

@Service
public class ContractService {

    private static final double MIDDLE_SEASON = 0.3;
    private static final double PEAK_SEASON= 0.6;

    @Autowired
    ContractRepo contractRepo;

    public List<Contract> fetchAll(){
        return contractRepo.fetchAll();
    }
    public Contract createContract(Contract c, Extras e){
        return contractRepo.createContract(c,e);
    }
    public Contract findContractById(int contractID){
        return contractRepo.findContractByID(contractID);
    }
    public Contract deleteContract(int contractID){
        return contractRepo.deleteContract(contractID);
    }
    public Contract updateContract(int contractID, Contract c){
        return contractRepo.updateContract(contractID,c);
    }

    public void calculateTotalContractPrice(Contract c, Extras e, AutocamperService autocamperService) {
        double rentalPrice = 0.0;
        LocalDate localDateStart = LocalDate.parse(c.getRentalStartDate());
        LocalDate localDateEnd = LocalDate.parse(c.getRentalStartDate());

        //Udregn samlede dage bilen er lejet og gang det med bilens daglige pris
        //Gang desuden beløbet med attributter hvis det er højsæson/middelsæson
        LocalDate date1 = LocalDate.of(localDateStart.getYear(), localDateStart.getMonth(), localDateStart.getDayOfMonth());
        LocalDate date2 = date1.with(Month.from(localDateEnd.getMonth())).withDayOfMonth(localDateEnd.getDayOfMonth());
        int numDays = Period.between(date1, date2).getDays();
        if (numDays == 0) {
            numDays = 1;
        }

        rentalPrice += numDays * autocamperService.findAutocamperByID(c.getAutocamperID()).getPriceperday();

        if (e.getFamilyPackage().equals("yes")) {
            rentalPrice += 750;
        } else if (e.getSportPackage().equals("yes")) {
            rentalPrice += 500;
        } else if (e.getLuxuryPackage().equals("yes")) {
            rentalPrice += 1000;
        } else if (e.getPicknickPackage().equals("yes")) {
            rentalPrice += 250;
        }

        if (localDateEnd.getMonthValue() == 5) {
            rentalPrice *= PEAK_SEASON;
        } else if (localDateEnd.getMonthValue() == 6) {
            rentalPrice *= PEAK_SEASON;
        } else if (localDateEnd.getMonthValue() == 7) {
            rentalPrice *= PEAK_SEASON;
        } else if (localDateEnd.getMonthValue() == 8) {
            rentalPrice *= PEAK_SEASON;
        } else if (localDateEnd.getMonthValue() == 3) {
            rentalPrice *= MIDDLE_SEASON;
        } else if (localDateEnd.getMonthValue() == 4) {
            rentalPrice *= MIDDLE_SEASON;
        } else if (localDateEnd.getMonthValue() == 9) {
            rentalPrice *= MIDDLE_SEASON;
        } else if (localDateEnd.getMonthValue() == 10) {
            rentalPrice *= MIDDLE_SEASON;
        }
        c.setRentalPrice(rentalPrice);
    }
}
