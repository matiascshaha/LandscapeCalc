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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Prime_Count_Activity extends AppCompatActivity {

    Button resetButton;
    Button primeButton;
    EditText enterNum;
    Button Reset_PreviousCalculationsPrimeCount_Button;
    ListView PrimeCount_ListView;

    public static final String PREFS_NAME= "com.example.landscapecalc";

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    //global array to track all previous calculations in the PrimeCount Page
    ArrayList<String> PrimeCount_PreviousCalculations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime__count_);

        //add support for back button for function activities
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        settings = getSharedPreferences(PREFS_NAME,0);
        editor = settings.edit();

        resetButton = findViewById(R.id.resetButton);
        primeButton = findViewById(R.id.primeButton);
        enterNum = findViewById(R.id.enterNum);
        Reset_PreviousCalculationsPrimeCount_Button = findViewById(R.id.Reset_PreviousCalculations_PrimeCount_Button);
        PrimeCount_ListView = findViewById(R.id.PrimeCount_ListView);

        if(settings.getInt("PrimeCountarray_size",-1) == -1)
        {
            PrimeCount_PreviousCalculations = new ArrayList<>();
        }
        else
        {
            PrimeCount_PreviousCalculations = new ArrayList<>(read_PrimeCount_SharedPreferences());
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,PrimeCount_PreviousCalculations);
            PrimeCount_ListView.setAdapter(myAdapter);
        }

        resetButton.setOnClickListener(new View.OnClickListener() { //resets prime counter
            @Override
            public void onClick(View v) {
                enterNum.setText("");
            }
        });

        primeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer numEntered = Integer.parseInt(enterNum.getText().toString()); //convert text
                    //to string
                int counter = 0;

                if(enterNum.getText().toString().length() >= 7){    //runs successfully for numbers
                    // larger than 7 digits but the time complexity is not super great
                    Toast.makeText(getApplicationContext(),
                            "Enter a number less than 7 digits", Toast.LENGTH_LONG).show();
                }

                if(numEntered <= 1){
                    Toast.makeText(getApplicationContext(), "Enter a number larger than 1",
                            Toast.LENGTH_LONG).show();
                }
                if(numEntered > 1 && enterNum.getText().toString().length() < 7) {
                    for (int i = 2; i <= numEntered; i++) {
                        if(isPrime(i)){
                            counter++;  //counts primes less than given number
                        }
                    }
                }

                if(counter > 0){
                    String full_output = "PrimeCount>> There are " + Integer.toString(counter) + " primes less than " + enterNum.getText().toString();

                    PrimeCount_PreviousCalculations.add(full_output);
                    update_PrimeCount_SharedPreference();

                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_PrimeCount_SharedPreferences());
                    PrimeCount_ListView.setAdapter(myAdapter);
                }
            }
        });

        Reset_PreviousCalculationsPrimeCount_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("PrimeCountarray_size");
                for(int i = 0;i < PrimeCount_PreviousCalculations.size(); i++)
                    editor.remove("PrimeCountarray_" + i);
                editor.commit();
                PrimeCount_ListView.setAdapter(null);
                PrimeCount_PreviousCalculations = new ArrayList<>();
            }
        });

    }

    public boolean isPrime(int num){
        for (int i = 2; i < num; i++) {
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }

    protected void update_PrimeCount_SharedPreference()
    {
        editor.putInt("PrimeCountarray_size", PrimeCount_PreviousCalculations.size());
        for(int i = 0;i < PrimeCount_PreviousCalculations.size(); i++)
            editor.putString("PrimeCountarray_" + i, PrimeCount_PreviousCalculations.get(i));
        editor.commit();
    }

    protected ArrayList<String> read_PrimeCount_SharedPreferences()
    {
        int arrSize = settings.getInt("PrimeCountarray_size", -1);
        if(arrSize == -1)
            return null;        //returns null if the GCD calculations page is empty
        else
        {
            ArrayList<String> myArray = new ArrayList<>();
            for(int i = 0;i < arrSize; i++)
            {
                myArray.add(settings.getString("PrimeCountarray_" + i, null));
            }

            return myArray;
        }
    }
}

