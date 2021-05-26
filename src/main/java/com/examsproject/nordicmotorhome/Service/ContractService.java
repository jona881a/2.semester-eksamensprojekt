package com.examsproject.nordicmotorhome.Service;

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

    private static final double MIDDLE_SEASON = 1.3;
    private static final double PEAK_SEASON= 1.6;

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

    /**
     * metoden udregner den totale lejepris i den pågældende sæson og med ekstrapakker
     * @author: rasmuskoefoed
     * @param c
     * @param e
     * @param autocamperService
     * @return rentalPrice
     */

    public double calculateTotalContractPrice(Contract c, Extras e, AutocamperService autocamperService) {
        double rentalPrice = 0.0;
        LocalDate localDateStart = LocalDate.parse(c.getRentalStartDate());
        LocalDate localDateEnd = LocalDate.parse(c.getRentalEndDate());

        LocalDate date1 = LocalDate.of(localDateStart.getYear(), localDateStart.getMonth(), localDateStart.getDayOfMonth());
        LocalDate date2 = date1.with(Month.from(localDateEnd.getMonth())).withDayOfMonth(localDateEnd.getDayOfMonth());
        int numDays = Period.between(date1, date2).getDays(); //Vi tager afstanden imellem de to datoer
        if (numDays == 0) {
            numDays = 1;
        }
        //lægger antaldage ganget med prisen af autocamperen til prisen
        rentalPrice += numDays * autocamperService.findAutocamperByID(c.getAutocamperID()).getPriceperday();

        //Hvis ingen, en eller flere pakker er hakket af på siden så medtages prisen her
        if (!(e.getFamilyPackage() == null) && e.getFamilyPackage().equals("yes")) {
            rentalPrice += 75;
        } else if (!(e.getSportPackage() == null) && e.getSportPackage().equals("yes")) {
            rentalPrice += 50;
        } else if (!(e.getLuxuryPackage() == null) && e.getLuxuryPackage().equals("yes")) {
            rentalPrice += 100;
        } else if (!(e.getPicknickPackage() == null) && e.getPicknickPackage().equals("yes")) {
            rentalPrice += 25;
        }
        //Hvis det er mellem den 5 og 8 så regner den med højsæson (60%)
        //Hvis den er mellem 3 og 10 så er vi i midtesæsonen (30%)
        //Hvis vi er i 11 til 2 så er det lavsæson og der ligges ingen pris oveni
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

        return rentalPrice;
    }

    /**
     * Metoden regner det kunden får tilbage hvis der annulleres en udlejning pba. det antal dage der er tilbage
     * @author: rasmuskoefoed
     * @param c
     * @return cancellationFeePrice
     */
    public double calculateCancellationFee(Contract c) {
        LocalDate localDateStart = LocalDate.parse(c.getRentalStartDate());
        LocalDate dateCancelDate = LocalDate.now();
        double cancellationFeePrice = 0;

        LocalDate dateContractBeginDate = LocalDate.of(localDateStart.getYear(),
                localDateStart.getMonth(), localDateStart.getDayOfMonth());

        //Det antal dage der er imellem den dag den bliver aflyst og den dag contracten skulle have været gået i gang
        int numDays = Period.between(dateCancelDate, dateContractBeginDate).getDays();
        if (numDays == 0) {
            numDays = 1;
        }
        if (numDays >= 50) { //hvis der er mere end 50 dage til så får man 80% tilbage
            if (c.getRentalPrice() * 0.2 <= 200) {
                cancellationFeePrice += 200;
            } else {
                cancellationFeePrice = c.getRentalPrice() * 0.2;
            }
        } else if (numDays <= 49 && numDays >= 15) { //Hvis man aflyser imellem 49 og 15 dage før så får man 50% tilbage
            cancellationFeePrice = c.getRentalPrice() * 0.5;
        } else if (numDays < 15 && numDays > 1) { //hvis man aflyser imellem 15 og 1 dag før får man 20%
            cancellationFeePrice = c.getRentalPrice() * 0.8;
        } else if (numDays < 1) { //hvis det er på dagen får man kun 5% tilbage
            cancellationFeePrice = c.getRentalPrice() * 0.95;
        }

        return cancellationFeePrice; //Værdien er det som der skal trækkes fra i den originale pris og ikke det som de får tilbage
    }
}
