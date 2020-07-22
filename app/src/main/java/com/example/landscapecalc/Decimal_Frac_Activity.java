package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Decimal_Frac_Activity extends AppCompatActivity {

    Button reset;
    Button decToFrac;
    TextView fracDisplay;
    EditText enterDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimal__frac_);

        //add support for back button for function activities
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reset = findViewById(R.id.reset);
        decToFrac = findViewById(R.id.decToFrac);
        fracDisplay = findViewById(R.id.fracDisplay);
        enterDecimal = findViewById(R.id.enterDecimal);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterDecimal.setText("");
                fracDisplay.setText("");
            }
        });

        decToFrac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = enterDecimal.getText().toString();    //store text in string
                int index = temp.indexOf(".");  //create index character
                double decimal = Double.parseDouble(temp);  //stores original decimal value

                int numOfDigits = temp.length() - 1 - index;
                int denominator = 1;    //denominator to be used for fraction

                for (int i = 0; i < numOfDigits; i++) { //get rid of decimal by multiplying by 10
                    decimal *= 10;
                    denominator *= 10;
                }

                int numerator = (int) decimal;  //cast double to int
                int gcd = GCD(numerator, denominator);  //find the greatest common denominator
                numerator = numerator/gcd;  //simplify numerator
                denominator = denominator/gcd;  //simplify denominator
                String final_numerator = Integer.toString(numerator);   //cast int to string
                String final_denominator = Integer.toString(denominator);

                fracDisplay.setText(final_numerator + "/" + final_denominator);
            }
        });

    }

    public int GCD(int one, int two){   //recursive implementation of GCD
        // code from geeksforgeeks.com
        if(two == 0){
            return one;
        }
        return GCD(two, one%two);
    }
}