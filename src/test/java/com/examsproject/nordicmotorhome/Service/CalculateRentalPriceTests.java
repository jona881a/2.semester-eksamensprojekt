package com.examsproject.nordicmotorhome.Service;

import com.examsproject.nordicmotorhome.Model.Contract;
import com.examsproject.nordicmotorhome.Model.Extras;
import com.examsproject.nordicmotorhome.Repository.AutocamperRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class CalculateRentalPriceTests {

    //Contract contract1 = new Contract(1, 1, 1,0,"2021-05-30","12:00","Drejøgade 35",0,"2021-06-04","12:00","Østerport",5,1,1,"no","",0);
    Extras extras1 = new Extras();
    /*
    ContractService contractService = new ContractService();
    AutocamperService autocamperService = new AutocamperService();
    AutocamperRepo autocamperRepo = new AutocamperRepo();
     */

    @BeforeEach
    public void Setup() {
        ContractService contractService = mock(ContractService.class);
        AutocamperService autocamperService = mock(AutocamperService.class);
        AutocamperRepo autocamperRepo = mock(AutocamperRepo.class);
    }

    @Test
    void rentalPriceInPeakSeasonAndBetweenToMonths() {
        //Denne er uden extras og med 5 dages udlejning i et månedsskift i højsæson
        //double rentalPrice1 = contractService.calculateTotalContractPrice(contract1,extras1,autocamperService);

        //assertEquals(6400,rentalPrice1);
    }
}
