package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Decimal_Frac_Activity extends AppCompatActivity {

    Button reset;
    Button decToFrac;
    Button ResetListView_DecToFrac_Button;
    ListView DecToFrac_ListView;
    EditText enterDecimal;

    public static final String PREFS_NAME= "com.example.landscapecalc";

    //global array to track all previous calculations in the DecToFrac Page
    ArrayList<String> DecToFrac_PreviousCalculations;

    SharedPreferences settings;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimal__frac_);

        //add support for back button for function activities
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        settings = getSharedPreferences(PREFS_NAME,0);
        editor = settings.edit();

        reset = findViewById(R.id.reset);
        decToFrac = findViewById(R.id.decToFrac);
        enterDecimal = findViewById(R.id.enterDecimal);
        ResetListView_DecToFrac_Button = findViewById(R.id.Reset_PreviousCalculations_DecToFrac_Button);
        DecToFrac_ListView = findViewById(R.id.DecToFrac_ListView);

        if(settings.getInt("DecToFracarray_size",-1) == -1)
        {
            DecToFrac_PreviousCalculations = new ArrayList<>();
        }
        else
        {
            DecToFrac_PreviousCalculations = new ArrayList<>(read_DecToFrac_SharedPreferences());
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,DecToFrac_PreviousCalculations);
            DecToFrac_ListView.setAdapter(myAdapter);
        }


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterDecimal.setText("");
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

                String final_output = "DecToFrac>> The Fraction for " + enterDecimal.getText().toString() + " is " + final_numerator + "/" + final_denominator;

                DecToFrac_PreviousCalculations.add(final_output);
                update_DecToFrac_SharedPreference();

                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_DecToFrac_SharedPreferences());
                DecToFrac_ListView.setAdapter(myAdapter);
            }
        });

        ResetListView_DecToFrac_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("DecToFracarray_size");
                for(int i = 0;i < DecToFrac_PreviousCalculations.size(); i++)
                    editor.remove("DecToFracarray_" + i);
                editor.commit();
                DecToFrac_ListView.setAdapter(null);
                DecToFrac_PreviousCalculations = new ArrayList<>();
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

    protected void update_DecToFrac_SharedPreference()
    {
        editor.putInt("DecToFracarray_size", DecToFrac_PreviousCalculations.size());
        for(int i = 0;i < DecToFrac_PreviousCalculations.size(); i++)
            editor.putString("DecToFracarray_" + i, DecToFrac_PreviousCalculations.get(i));
        editor.commit();
    }

    protected ArrayList<String> read_DecToFrac_SharedPreferences()
    {
        int arrSize = settings.getInt("DecToFracarray_size", -1);
        if(arrSize == -1)
            return null;        //returns null if the DecToFrac calculations page is empty
        else
        {
            ArrayList<String> myArray = new ArrayList<>();
            for(int i = 0;i < arrSize; i++)
            {
                myArray.add(settings.getString("DecToFracarray_" + i, null));
            }

            return myArray;
        }
    }
}