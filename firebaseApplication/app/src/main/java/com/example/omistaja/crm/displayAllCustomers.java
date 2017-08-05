package com.example.omistaja.crm;

// Luokka joka tulostaa kannan kaikki asiakkaat näkyviin

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class displayAllCustomers extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseDatabase;
    ListView userList;
    CustomerCustomAdapter customerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_customers);
        final ArrayList<Customer> customerArray = new ArrayList<Customer>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Haetaan asiakkaat kannasta

        mDatabase = mFirebaseDatabase.getReference().child("Customers");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Customer customer = postSnapshot.getValue(Customer.class);
                    String id = customer.getID();
                    String name = customer.getName();
                    String address = customer.getAddress();
                    String pNum = customer.getPhoneNumber();

                    customer = new Customer(id, name, address, pNum);

                    customerArray.add(customer);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        customerAdapter = new CustomerCustomAdapter(displayAllCustomers.this, R.layout.row,
                customerArray);

        userList = (ListView) findViewById(R.id.listView);
        userList.setItemsCanFocus(false);
        userList.setAdapter(customerAdapter);
    }

}
