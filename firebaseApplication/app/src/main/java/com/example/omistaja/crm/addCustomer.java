package com.example.omistaja.crm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


// Asiakkaan lisäystä varten luotu luokka

public class addCustomer extends AppCompatActivity {

    fireBaseHandler db = new fireBaseHandler();
    String id;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        // Hae viimeksi lisätyn asiakkaan id, jotta ei tule päällekkäisyyksiä

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Customers");
        Query queryRef = mDatabase.orderByValue().limitToLast(3);
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String LastId = dataSnapshot.getKey().toString();
                int addOneMore = Integer.parseInt(LastId);
                addOneMore++;
                id = String.valueOf(addOneMore);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    // Lisää asiakas painike, ottaa tiedot kentistä ja tallentaa kantaan asiakkaan. Varmistaa myös, ettei kentät ole tyhjinä
    // Estetään myös tyhjien kenttien syöttäminen

    public void addCustomerBtn(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you wanna save this customer?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                EditText customerName = (EditText) findViewById(R.id.name);
                EditText customerAddress = (EditText) findViewById(R.id.address);
                EditText customerPnum = (EditText) findViewById(R.id.pnum);

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
                ;
                db.addCustomer(id, cName, cAdd, cPnum);

                customerName.setText("");
                customerAddress.setText("");
                customerPnum.setText("");
                dialog.dismiss();

                AlertDialog.Builder builder = new AlertDialog.Builder(addCustomer.this);

                builder.setTitle("Customer add");
                builder.setMessage("Customer added. Do you wanna add another customer?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();


                        Intent returnToMain = new Intent(addCustomer.this, MainActivity.class);
                        startActivity(returnToMain);
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText customerName = (EditText) findViewById(R.id.name);
                EditText customerAddress = (EditText) findViewById(R.id.address);
                EditText customerPnum = (EditText) findViewById(R.id.pnum);

                customerName.setText("");
                customerAddress.setText("");
                customerPnum.setText("");

                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

    // Palaa takaisin painike

    public void returnMain(View view) {

        Intent returnToMain = new Intent(addCustomer.this, MainActivity.class);
        startActivity(returnToMain);

    }
}
