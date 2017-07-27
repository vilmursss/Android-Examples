package com.example.omistaja.crm;

// Luokka jolla etsitään asiakkaita

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    // Otetaan kenttien tiedot ja lähetetään ne eteenpäin, kuitenkin varmistaen ettei kaikki ole tyhjiä kenttiä

    public void searchCustomerBtn(View view) {

        EditText searchId = (EditText) findViewById(R.id.id);
        EditText searchName = (EditText) findViewById(R.id.name);
        EditText searchAddress = (EditText) findViewById(R.id.address);
        EditText searchPnum = (EditText) findViewById(R.id.phone);

        String cId = searchId.getText().toString();
        String name = searchName.getText().toString();
        String address = searchAddress.getText().toString();
        String pNum = searchPnum.getText().toString();

        if (name.trim().equals("") && cId.trim().equals("") && address.trim().equals("") && pNum.trim().equals("")) {

            searchId.setError("All fields cannot be empty.");
            return;
        } else {

            int id = 0;
            if (!cId.trim().equals("")) {
                
                id = Integer.parseInt(cId);
            }

            Intent searchResults = new Intent(this, DisplaySearchResults.class);
            searchResults.putExtra("id", id);
            searchResults.putExtra("name", name);
            searchResults.putExtra("address", address);
            searchResults.putExtra("pnum", pNum);
            startActivity(searchResults);
        }


    }

    public void returnMain(View view) {

        Intent returnToMain = new Intent(search.this, MainActivity.class);
        startActivity(returnToMain);
    }
}
