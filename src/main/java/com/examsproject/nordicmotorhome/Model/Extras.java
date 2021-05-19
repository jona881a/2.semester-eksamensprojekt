package com.examsproject.nordicmotorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Extras {

    @Id
    private int extrasID;
    private String luxuryPackage;
    private String sportPackage;
    private String familyPackage;
    private String picknickPackage;

    public Extras(int extrasID, String luxuryPackage, String sportPackage, String familyPackage, String picknickPackage) {
        this.extrasID = extrasID;
        this.luxuryPackage = luxuryPackage;
        this.sportPackage = sportPackage;
        this.familyPackage = familyPackage;
        this.picknickPackage = picknickPackage;
    }

    public Extras() {}

    public int getExtrasID() {
        return extrasID;
    }

    public void setExtrasID(int extrasID) {
        this.extrasID = extrasID;
    }

    public String getLuxuryPackage() {
        return luxuryPackage;
    }

    public void setLuxuryPackage(String luxuryPackage) {
        this.luxuryPackage = luxuryPackage;
    }

    public String getSportPackage() {
        return sportPackage;
    }

    public void setSportPackage(String sportPackage) {
        this.sportPackage = sportPackage;
    }

    public String getFamilyPackage() {
        return familyPackage;
    }

    public void setFamilyPackage(String familyPackage) {
        this.familyPackage = familyPackage;
    }

    public String getPicknickPackage() {
        return picknickPackage;
    }

    public void setPicknickPackage(String picknickPackage) {
        this.picknickPackage = picknickPackage;
    }
}
