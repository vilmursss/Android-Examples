package com.example.omistaja.crm;

// Asiakasluokka, jonkalaisia asiakasolioita tässä sovelluksessa luodaan.

public class Customer {

    private int id;
    private String name;
    private String address;
    private String phoneNumber;


    public Customer() {

    }

    public Customer(int id, String name, String address, String phoneNumber) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }

    public void setID(int id) {

        this.id = id;

    }

    public int getID() {

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