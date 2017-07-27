package com.example.omistaja.crm;

// Luokka joka tulostaa kannan kaikki asiakkaat näkyviin

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class displayAllCustomers extends AppCompatActivity {

    dBHandler db = new dBHandler(this);
    ListView userList;
    CustomerCustomAdapter customerAdapter;
    ArrayList<Customer> customerArray = new ArrayList<Customer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_customers);

        List<Customer> customers = db.getAllCustomers();

        for (Customer customer : customers) {
            Customer addList = new Customer(customer.getID(), customer.getName(), customer.getAddress(), customer.getPhoneNumber());
            customerArray.add(addList);
        }

        customerAdapter = new CustomerCustomAdapter(displayAllCustomers.this, R.layout.row,
                customerArray);

        userList = (ListView) findViewById(R.id.listView);
        userList.setItemsCanFocus(false);
        userList.setAdapter(customerAdapter);
    }
}
