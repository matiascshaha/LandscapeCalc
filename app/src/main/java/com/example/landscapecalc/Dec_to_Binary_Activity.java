package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Dec_to_Binary_Activity extends AppCompatActivity {

    Button resetButton;
    Button DecButton;
    Button BinButton;
    EditText enterNum;
    Button Reset_PreviousCalculationsDec_Button;
    ListView Dec_ListView;

    public static final String PREFS_NAME= "com.example.landscapecalc";

    //global array to track all previous calculations in the Dec Page
    ArrayList<String> Dec_PreviousCalculations;

    SharedPreferences settings;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dec_to__binary_);

        settings = getSharedPreferences(PREFS_NAME,0);
        editor = settings.edit();




        //add support for back button for function activities
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resetButton = findViewById(R.id.Dec_Reset_Button);
        DecButton = findViewById(R.id.Calculate_Dec_Button);
        BinButton = findViewById(R.id.Calculate_Binary_Button);
        enterNum = findViewById(R.id.Dec_2_Bin_Activity_EditText);
        Dec_ListView = findViewById(R.id.Dec_previousCalculations);
        Reset_PreviousCalculationsDec_Button = findViewById(R.id.reset_DecCalculations_Button);

        if(settings.getInt("Decarray_size",-1) == -1)
        {
            Dec_PreviousCalculations = new ArrayList<>();
        }
        else
        {
            Dec_PreviousCalculations = new ArrayList<>(read_Dec_SharedPreferences());
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,Dec_PreviousCalculations);
            Dec_ListView.setAdapter(myAdapter);
        }

        resetButton.setOnClickListener(new View.OnClickListener() { //resets prime counter
            @Override
            public void onClick(View v) {
                enterNum.setText("");
            }
        });
        DecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer numEntered = Integer.parseInt(enterNum.getText().toString());
                String ans = Integer.toBinaryString(numEntered);

                String final_output = "DecToBinary>> Decimal:" + enterNum.getText().toString() + " is " + ans + " in Binary";

                Dec_PreviousCalculations.add(final_output);
                update_Dec_SharedPreference();

                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_Dec_SharedPreferences());
                Dec_ListView.setAdapter(myAdapter);
            }
        });
        BinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer ans = Integer.parseInt(enterNum.getText().toString(),2);

                String final_output = "DecToBinary>> Binary:" + enterNum.getText().toString() + " is " + ans + " in Decimal";

                Dec_PreviousCalculations.add(final_output);
                update_Dec_SharedPreference();

                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_Dec_SharedPreferences());
                Dec_ListView.setAdapter(myAdapter);
            }


        });

        Reset_PreviousCalculationsDec_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int arrSize = settings.getInt("Decarray_size", -1);
                if(arrSize != -1)
                {
                    editor.remove("Decarray_size");
                    for(int i = 0;i < Dec_PreviousCalculations.size(); i++)
                        editor.remove("Decarray_" + i);
                    editor.commit();
                    Dec_ListView.setAdapter(null);
                    Dec_PreviousCalculations = new ArrayList<>();
                }
            }
        });

    }

    protected void update_Dec_SharedPreference()
    {
        editor.putInt("Decarray_size", Dec_PreviousCalculations.size());
        for(int i = 0;i < Dec_PreviousCalculations.size(); i++)
            editor.putString("Decarray_" + i, Dec_PreviousCalculations.get(i));
        editor.commit();
    }

    protected ArrayList<String> read_Dec_SharedPreferences()
    {
        int arrSize = settings.getInt("Decarray_size", -1);
        if(arrSize == -1)
            return null;        //returns null if the Dec calculations page is empty
        else
        {
            ArrayList<String> myArray = new ArrayList<>();
            for(int i = 0;i < arrSize; i++)
            {
                myArray.add(settings.getString("Decarray_" + i, null));
            }

            return myArray;
        }
    }
}