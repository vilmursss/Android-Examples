package com.example.omistaja.calccc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Funktio, joka reagoi laske painikkeisiin ja jossa kaikki laskutoiminnot tapahtuvat

    public void ButtonOnClick(View v) {


     // Pluslaskun painike

        if(v.getId()==R.id.plusButton)
        {

            TextView plusResult = (TextView)findViewById(R.id.plusResult);

            EditText plusOne = (EditText)findViewById(R.id.plus1);
            EditText plusTwo = (EditText)findViewById(R.id.plus2);

            String  s1=plusOne.getText().toString();

            if(TextUtils.isEmpty(s1)) {
            plusOne.setError("This cannot be empty.");
            return;
        }


            String s2=plusTwo.getText().toString();

            if(TextUtils.isEmpty(s2)) {
                plusOne.setError("This cannot be empty.");
                return;
            }

            int i1=Integer.parseInt(s1);
            int i2=Integer.parseInt(s2);

            int calc = i1+i2;

            String s=String.valueOf(calc);

            plusResult.setText(s);
        }

        // miinuslaskun painike

        else if(v.getId()==R.id.minusButton)
        {

            TextView minusResult = (TextView)findViewById(R.id.minusResult);


            EditText minusOne = (EditText)findViewById(R.id.minus1);
            EditText minusTwo = (EditText)findViewById(R.id.minus2);

            String  s1=minusOne.getText().toString();

            if(TextUtils.isEmpty(s1)) {
                minusOne.setError("This cannot be empty.");
                return;
            }


            String s2=minusTwo.getText().toString();

            if(TextUtils.isEmpty(s2)) {
                minusOne.setError("This cannot be empty.");
                return;
            }

            int i1=Integer.parseInt(s1);
            int i2=Integer.parseInt(s2);

            int calc = i1-i2;

            String s=String.valueOf(calc);

            minusResult.setText(s);

            // Kertomisen painike

        }
        else if(v.getId()==R.id.timesButton)
        {

            TextView timesResult = (TextView)findViewById(R.id.timesResult);


            EditText timesOne = (EditText)findViewById(R.id.times1);
            EditText timesTwo = (EditText)findViewById(R.id.times2);

            String  s1=timesOne.getText().toString();

            if(TextUtils.isEmpty(s1)) {
                timesOne.setError("This cannot be empty.");
                return;
            }


            String s2=timesTwo.getText().toString();

            if(TextUtils.isEmpty(s2)) {
                timesOne.setError("This cannot be empty.");
                return;
            }

            int i1=Integer.parseInt(s1);
            int i2=Integer.parseInt(s2);

            int calc = i1*i2;

            String s=String.valueOf(calc);

            timesResult.setText(s);
        }

        // Jakolaskun painike

        else if(v.getId()==R.id.divideButton)
        {
            TextView divideResult = (TextView)findViewById(R.id.divideResult);


            EditText divideOne = (EditText)findViewById(R.id.divide1);
            EditText divideTwo = (EditText)findViewById(R.id.divide2);

            String  s1=divideOne.getText().toString();

            if(TextUtils.isEmpty(s1)) {
                divideOne.setError("This cannot be empty.");
                return;
            }


            String s2=divideTwo.getText().toString();

            if(TextUtils.isEmpty(s2)) {
            divideOne.setError("This cannot be empty.");
            return;
        }

        // ei anneta jakaa nollalla

            if(s2.matches("0")) {
                divideTwo.setError("You cannot divide by 0");
                return;
            }

            int i1=Integer.parseInt(s1);
            int i2=Integer.parseInt(s2);

            int calc = i1/i2;

            String s=String.valueOf(calc);

            divideResult.setText(s);
        }

        // Tyhjenna kaikki painike

        else {

            // Tyhjenna plussan kentat

            EditText plusOne = (EditText)findViewById(R.id.plus1);
            EditText plusTwo = (EditText)findViewById(R.id.plus2);
            TextView plusResult = (TextView)findViewById(R.id.plusResult);

            plusOne.getText().clear();
            plusTwo.getText().clear();
            plusResult.setText("0");

            // Tyhjenna miinuksen kentat

            EditText minusOne = (EditText)findViewById(R.id.minus1);
            EditText minusTwo = (EditText)findViewById(R.id.minus2);
            TextView minusResult = (TextView)findViewById(R.id.minusResult);

            minusOne.getText().clear();
            minusTwo.getText().clear();
            minusResult.setText("0");

            // Tyhjenna kertomisen kentat

            EditText timesOne = (EditText)findViewById(R.id.times1);
            EditText timesTwo = (EditText)findViewById(R.id.times2);
            TextView timesResult = (TextView)findViewById(R.id.timesResult);

            timesOne.getText().clear();
            timesTwo.getText().clear();
            timesResult.setText("0");

            // Tyhjenna jakamisen kentat

            EditText divideOne = (EditText)findViewById(R.id.divide1);
            EditText divideTwo = (EditText)findViewById(R.id.divide2);
            TextView divideResult = (TextView)findViewById(R.id.divideResult);

            divideOne.getText().clear();
            divideTwo.getText().clear();
            divideResult.setText("0");

        }

    }
}
