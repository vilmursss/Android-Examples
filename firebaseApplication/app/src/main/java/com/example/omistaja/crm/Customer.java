package com.example.omistaja.crm;

// Asiakasluokka, jonkalaisia asiakasolioita tässä sovelluksessa luodaan.

public class Customer {

    private String id;
    private String name;
    private String address;
    private String phoneNumber;


    public Customer() {

    }

    public Customer(String id, String name, String address, String phoneNumber) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }

    public void setID(String id) {

        this.id = id;

    }

    public String getID() {

        return id;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getName() {

        return name;

    }

    public void setAddress(String address) {

        this.address = address;

    }

    public String getAddress() {

        return address;

    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;

    }

    public String getPhoneNumber() {

        return phoneNumber;

    }


}