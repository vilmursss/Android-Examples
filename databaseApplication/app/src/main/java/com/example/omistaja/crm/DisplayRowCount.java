package com.example.omistaja.crm;

// Ilmaisee nykyisen tietokannan rivikoon, eli asiakkaiden sen hetkisen määrän

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayRowCount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_row_count);

        dBHandler db = new dBHandler(this);
        TextView rowCountView = (TextView) findViewById(R.id.rowAmount);
        rowCountView.setText(String.valueOf(db.getRowCount()));
    }

    public void returnMain(View view) {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
}
