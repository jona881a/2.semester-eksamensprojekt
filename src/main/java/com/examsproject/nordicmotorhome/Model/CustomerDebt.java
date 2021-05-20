package com.examsproject.nordicmotorhome.Model;

import java.util.Date;

public class CustomerDebt {

    private int customerDebtID;
    private int contractID; //Kan det lade sig gøre i sql?
    private Date contractStartDate; //Start-og slutdato er der mest for overblikkets skyld.
    private Date contractEndDate; //Kan evt. fjernes
    private boolean wasCancelled;  //Hvis kontrakten blev afbrudt
    private Date cancellationDate; //Skal vise hvordan vi udregnede antal dage, kan være null.
    private int daysSinceCancellation; //For at dobbelttjekke
    private int totalPrice; //Er enten contract price eller cancellation fee.
}
