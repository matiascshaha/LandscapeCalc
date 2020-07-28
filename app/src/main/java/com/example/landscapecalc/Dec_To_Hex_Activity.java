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

public class Dec_To_Hex_Activity extends AppCompatActivity {

    public static final String PREFS_NAME= "com.example.landscapecalc";

    Button resetButton;
    Button DecButton;
    Button HexButton;
    EditText enterNum;
    Button Reset_PreviousCalculationsHex_Button;
    ListView Hex_ListView;

    //global array to track all previous calculations in the Hex Page
    ArrayList<String> Hex_PreviousCalculations;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dec__to__hex_);

        settings = getSharedPreferences(PREFS_NAME,0);
        editor = settings.edit();

        //add support for back button for function activities
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resetButton = findViewById(R.id.Hex_Reset_Button);
        DecButton = findViewById(R.id.Calculate_Hex_Button);
        HexButton = findViewById(R.id.Calculate_Hex_2_Dec_Button);
        enterNum = findViewById(R.id.Dec_2_Hex_Activity_EditText);
        Hex_ListView = findViewById(R.id.DecToHex_ListView);
        Reset_PreviousCalculationsHex_Button = findViewById(R.id.reset_HexCalculations_Button);

        if(settings.getInt("Hexarray_size",-1) == -1)
        {
            Hex_PreviousCalculations = new ArrayList<>();
        }
        else
        {
            Hex_PreviousCalculations = new ArrayList<>(read_Hex_SharedPreferences());
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,Hex_PreviousCalculations);
            Hex_ListView.setAdapter(myAdapter);
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

                Integer numEntered = Integer.parseInt(enterNum.getText().toString()); //convert text
                //to string
                String ans = Integer.toHexString(numEntered);

                String final_output = "DecToHex>> Decimal: " + enterNum.getText().toString() + " is " + ans + " in Hexadecimal";
                Hex_PreviousCalculations.add(final_output);
                update_Hex_SharedPreference();

                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_Hex_SharedPreferences());
                Hex_ListView.setAdapter(myAdapter);
            }
        });
        HexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer ans = Integer.parseInt(enterNum.getText().toString(),16);

                String final_output = "DecToHex>> Hexadecimal: " + enterNum.getText().toString() + " is " + ans + " in Decimal";

                Hex_PreviousCalculations.add(final_output);
                update_Hex_SharedPreference();

                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_Hex_SharedPreferences());
                Hex_ListView.setAdapter(myAdapter);

            }


        });

        Reset_PreviousCalculationsHex_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int arrSize = settings.getInt("Hexarray_size", -1);
                if(arrSize != -1)
                {
                    editor.remove("Hexarray_size");
                    for(int i = 0;i < Hex_PreviousCalculations.size(); i++)
                        editor.remove("Hexarray_" + i);
                    editor.commit();
                    Hex_ListView.setAdapter(null);
                    Hex_PreviousCalculations = new ArrayList<>();
                }
            }
        });




    }

    protected void update_Hex_SharedPreference()
    {
        editor.putInt("Hexarray_size", Hex_PreviousCalculations.size());
        for(int i = 0;i < Hex_PreviousCalculations.size(); i++)
            editor.putString("Hexarray_" + i, Hex_PreviousCalculations.get(i));
        editor.commit();
    }

    protected ArrayList<String> read_Hex_SharedPreferences()
    {
        int arrSize = settings.getInt("Hexarray_size", -1);
        if(arrSize == -1)
            return null;        //returns null if the Hex calculations page is empty
        else
        {
            ArrayList<String> myArray = new ArrayList<>();
            for(int i = 0;i < arrSize; i++)
            {
                myArray.add(settings.getString("Hexarray_" + i, null));
            }

            return myArray;
        }
    }
}