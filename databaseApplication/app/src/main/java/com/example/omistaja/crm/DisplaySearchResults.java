package com.example.omistaja.crm;

// Luokka joka näyttää hausta saatujen asiakkaiden tiedot listassa

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class DisplaySearchResults extends AppCompatActivity {

    dBHandler db = new dBHandler(this);
    ListView userList;
    CustomerCustomAdapter customerAdapter;
    ArrayList<Customer> customerArray = new ArrayList<Customer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search_results);

        // Otetaan vastaan viime luokassa syötetyt parametrit

        int cId = getIntent().getExtras().getInt("id");
        String cName = getIntent().getExtras().getString("name");
        String cAddress = getIntent().getExtras().getString("address");
        String cPnum = getIntent().getExtras().getString("pnum");

        List<Customer> customers = db.searchCustomers(cId, cName, cAddress, cPnum);

        for (Customer customer : customers) {
            Customer addList = new Customer(customer.getID(), customer.getName(), customer.getAddress(), customer.getPhoneNumber());
            customerArray.add(addList);
        }

        customerAdapter = new CustomerCustomAdapter(DisplaySearchResults.this, R.layout.row,
                customerArray);

        userList = (ListView) findViewById(R.id.listView);
        userList.setItemsCanFocus(false);
        userList.setAdapter(customerAdapter);

    }
}

