package com.example.omistaja.crm;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

// Luokka jolla luodaan kustomoitu listview, jotta saadaan sinne painikkeet modifioinnille ja poistolle, kun asiakkaita haetaan

public class CustomerCustomAdapter extends ArrayAdapter<Customer> {

    Context context;
    int layoutResourceId;
    ArrayList<Customer> data = new ArrayList<Customer>();

    public CustomerCustomAdapter(Context context, int layoutResourceId, ArrayList<Customer> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    // Täytetään jokainen kenttä vastaanotetun olion tiedoilla

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        CustomerHolder holder = null;

        if (row == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new CustomerHolder();
            holder.textId = (TextView) row.findViewById(R.id.textView5);
            holder.textName = (TextView) row.findViewById(R.id.textView6);
            holder.textAddress = (TextView) row.findViewById(R.id.textView7);
            holder.textPhoneNumber = (TextView) row.findViewById(R.id.textView8);
            holder.btnEdit = (Button) row.findViewById(R.id.button1);
            holder.btnDelete = (Button) row.findViewById(R.id.button2);
            row.setTag(holder);

        } else {

            holder = (CustomerHolder) row.getTag();

        }
        Customer user = data.get(position);
        holder.textId.setText(user.getID());
        holder.textName.setText(user.getName());
        holder.textAddress.setText(user.getAddress());
        holder.textPhoneNumber.setText(user.getPhoneNumber());

        final String getID = user.getID();
        final String getName = user.getName();
        final String getAddress = user.getAddress();
        final String getPhoneNum = user.getPhoneNumber();

        // Edit painiketta painaessa otettaan tämän tietyn kentät datat ylös ja ne lähetetään luokkaan, jossa asiakkaan tietoja voi muokata

        holder.btnEdit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, modifyCustomer.class);
                intent.putExtra("name", getName);
                intent.putExtra("address", getAddress);
                intent.putExtra("pnum", getPhoneNum);
                intent.putExtra("id", getID);

                context.startActivity(intent);
            }
        });

        // Delete painiketta painamalla varmistetaan alertikkunoin, että käyttäjä on varma tämän tietyn asiakkaan poistosta

        holder.btnDelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Customer delete");
                builder.setMessage("Are you sure you wanna delete this " + getName + " customer ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        fireBaseHandler db = new fireBaseHandler();
                        db.deleteCustomer(getID);
                        dialog.dismiss();

                        // Luodaan toinen dialogi, jossa ilmaistaan tieto että asiakas on poistettu

                        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                        alertDialog.setTitle("Customer deleted");
                        alertDialog.setMessage("Customer deleted!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.dismiss();

                                        String currentClass = context.getClass().getSimpleName().toString();

                                        if (currentClass.equals("displayAllCustomers")) {
                                            Intent intent = new Intent(context, displayAllCustomers.class);
                                            context.startActivity(intent);
                                        }

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
        });

        return row;
    }

    // Kentät ja painikkeet, jotka kustomoidulle riville tulevat näkyviin

    static class CustomerHolder {

        TextView textId;
        TextView textName;
        TextView textAddress;
        TextView textPhoneNumber;
        Button btnEdit;
        Button btnDelete;

    }
}

