package com.examsproject.nordicmotorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Autocamper {

    @Id
    private int autocamperID;
    private String brand;
    private String model;
    private boolean isAvailable;
    private String size;
    private String numberPlate;

    public Autocamper() {}

    public Autocamper(int autocamperID, String brand, String model, boolean isAvailable, String size, String numberPlate) {
        this.autocamperID = autocamperID;
        this.brand = brand;
        this.model = model;
        this.isAvailable = isAvailable;
        this.size = size;
        this.numberPlate = numberPlate;
    }

    public int getAutocamperID() {
        return autocamperID;
    }

    public void setAutocamperID(int autocamperID) {
        this.autocamperID = autocamperID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

}
