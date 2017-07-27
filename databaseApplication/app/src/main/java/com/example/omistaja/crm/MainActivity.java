package com.example.omistaja.crm;

// Ohjelman aloitusikkuna, josta löyvät kaikki sovelluksen toiminnot ja painikkeet niille

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    dBHandler db = new dBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Näytä rivien määrä

    public void showRowCount(View view) {
        Intent customerCount = new Intent(this, DisplayRowCount.class);
        startActivity(customerCount);
    }

    // Poista kaikki asiakkaat

    public void deleteAllCustomers(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you wanna delete everything from database?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                db.deleteAllCustomers();
                dialog.dismiss();
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Customer delete");
                alertDialog.setMessage("All customers deleted!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    // Avaa lisää uusi asiakasluokka

    public void addNewCustomer(View view) {
        Intent addCustomer = new Intent(this, addCustomer.class);
        startActivity(addCustomer);
    }

    // Tulosta kaikki asiakkaat

    public void printAllCustomers(View view) {
        Intent displayAll = new Intent(this, displayAllCustomers.class);
        startActivity(displayAll);
    }

    // Etsi asiakkaita

    public void searchOrModify(View view) {
        Intent searchAndModify = new Intent(this, search.class);
        startActivity(searchAndModify);
    }

}
