package com.example.omistaja.crm;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class fireBaseHandler extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabase;

    public void addCustomer(String id, String name, String address, String pNum) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference().child("Customers").child(id);
        Customer addCu = new Customer(id, name, address, pNum);
        mDatabase.setValue(addCu);
    }

    public void deleteCustomer(String id) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference().child("Customers").child(id);

        mDatabase.removeValue();
    }

}
