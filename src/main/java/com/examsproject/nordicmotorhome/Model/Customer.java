package com.examsproject.nordicmotorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

    @Id
    private int customerID;
    private int customerdebtID;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private String address;
    private String zipcode;


    public Customer(int customerID, int customerdebtID, String firstname, String lastname, String email, String phonenumber,
                    String address, String zipcode) {
        this.customerID = customerID;
        this.customerdebtID = customerdebtID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.zipcode = zipcode;
    }

    public Customer() {}

    public int getCustomerdebtID() {
        return customerdebtID;
    }

    public void setCustomerdebtID(int customerdebtID) {
        this.customerdebtID = customerdebtID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
