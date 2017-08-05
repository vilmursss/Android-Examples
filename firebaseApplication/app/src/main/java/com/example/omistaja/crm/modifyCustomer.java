package com.example.omistaja.crm;

// Luokka jolla muutetaan asiakkaan tietoja

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class modifyCustomer extends AppCompatActivity {

    fireBaseHandler db = new fireBaseHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_customer);

        // Otetaan vastaan lähetetyt tiedot asiakkaasta ja täytetään kentät niillä

        String cId = getIntent().getExtras().getString("id");
        String cName = getIntent().getExtras().getString("name");
        String cAddress = getIntent().getExtras().getString("address");
        String cPNum = getIntent().getExtras().getString("pnum");

        TextView hiddenId = (TextView) findViewById(R.id.hidden_id);
        EditText customerName = (EditText) findViewById(R.id.name);
        EditText customerAddress = (EditText) findViewById(R.id.address);
        EditText customerPhoneNumber = (EditText) findViewById(R.id.pnum);

        hiddenId.setText(cId);
        customerName.setText(cName);
        customerAddress.setText(cAddress);
        customerPhoneNumber.setText(cPNum);


    }

    // Modify painike, jolla muutetaan asiakkaan tietoja, varmistetaan kuitenkin alert ikkunalla, että käyttäjä haluaa varmasti muuttaa tiedot
    // Varmistetaan myös, ettei tyhjiä kenttiä ole tallennuksen yhteydessä

    public void modifyBtn(View view) {

        EditText customerName = (EditText) findViewById(R.id.name);
        String cName = customerName.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you wanna update information of this " + cName + " customer?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                TextView hiddenId = (TextView) findViewById(R.id.hidden_id);
                EditText customerName = (EditText) findViewById(R.id.name);
                EditText customerAddress = (EditText) findViewById(R.id.address);
                EditText customerPnum = (EditText) findViewById(R.id.pnum);

                String id = hiddenId.getText().toString();
                String cName = customerName.getText().toString();
                String cAdd = customerAddress.getText().toString();
                String cPnum = customerPnum.getText().toString();

                if (TextUtils.isEmpty(cName)) {
                    customerName.setError("This field cannot be empty.");
                    return;
                }

                if (TextUtils.isEmpty(cAdd)) {
                    customerAddress.setError("This field cannot be empty.");
                    return;
                }

                if (TextUtils.isEmpty(cPnum)) {
                    customerPnum.setError("This field cannot be empty.");
                    return;
                }

                db.addCustomer(id, cName, cAdd, cPnum);

                AlertDialog alertDialog = new AlertDialog.Builder(modifyCustomer.this).create();
                alertDialog.setTitle("Customer update");
                alertDialog.setMessage("Customer info updated!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent returnToMain = new Intent(modifyCustomer.this, MainActivity.class);
                                startActivity(returnToMain);

                            }
                        });
                alertDialog.show();
                dialog.dismiss();
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

    public void deleteBtn(View view) {


        EditText customerName = (EditText) findViewById(R.id.name);

        String cName = customerName.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Customer delete");
        builder.setMessage("Are you sure you wanna delete this " + cName + " customer ?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                TextView hiddenId = (TextView) findViewById(R.id.hidden_id);
                int hId = Integer.parseInt(hiddenId.getText().toString());


                dialog.dismiss();

                AlertDialog alertDialog = new AlertDialog.Builder(modifyCustomer.this).create();
                alertDialog.setTitle("Customer delete");
                alertDialog.setMessage("Customer deleted!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent returnToMain = new Intent(modifyCustomer.this, MainActivity.class);
                                startActivity(returnToMain);
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
}


