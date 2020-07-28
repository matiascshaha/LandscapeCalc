package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Modulus_Activity extends AppCompatActivity {

    Button modular_button;
    Button resetButton;
    EditText firstNum;
    EditText secondNum;
    EditText N;
    Button Reset_Modulus_PrevCalculations_Button;
    ListView Mod_ListView;

    public static final String PREFS_NAME= "com.example.landscapecalc";

    //global array to track all previous calculations in the Mod Page
    ArrayList<String> Mod_PreviousCalculations;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulus_);

        settings = getSharedPreferences(PREFS_NAME,0);
        editor = settings.edit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        modular_button = findViewById(R.id.modular_button);
        resetButton = findViewById(R.id.resetButton);
        firstNum = findViewById(R.id.firstNum);
        secondNum = findViewById(R.id.secondNum);
        N = findViewById(R.id.N);
        Reset_Modulus_PrevCalculations_Button = findViewById(R.id.Reset_Modular_PrevCalculations_Button);
        Mod_ListView = findViewById(R.id.Modulus_ListView);

        if(settings.getInt("Modarray_size",-1) == -1)
        {
            Mod_PreviousCalculations = new ArrayList<>();
        }
        else
        {
            Mod_PreviousCalculations = new ArrayList<>(read_Mod_SharedPreferences());
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,Mod_PreviousCalculations);
            Mod_ListView.setAdapter(myAdapter);
        }


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNum.setText("");
                secondNum.setText("");
                N.setText("");
            }
        });

        modular_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer num1 = Integer.parseInt(firstNum.getText().toString()); //convert text to int
                Integer num2 = Integer.parseInt(secondNum.getText().toString());
                Integer n = Integer.parseInt(N.getText().toString());

                Integer result1 = num1%n;
                Integer result2 = num2%n;

                String final_output;

                if(result1 == result2 || result1 == (n+result2)){
                    final_output = "Modulus>> " + String.valueOf(num1) + " is congruent to " + num2 + " mod " + String.valueOf(n);
                }
                else{
                    final_output = "Modulus>> " + String.valueOf(num1) + " is not congruent to " + num2 + " mod " + String.valueOf(n);

                }

                Mod_PreviousCalculations.add(final_output);
                update_Mod_SharedPreference();

                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_Mod_SharedPreferences());
                Mod_ListView.setAdapter(myAdapter);


            }
        });

        Reset_Modulus_PrevCalculations_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int arrSize = settings.getInt("Modarray_size", -1);
                if(arrSize != -1)
                {
                    editor.remove("Modarray_size");
                    for(int i = 0;i < Mod_PreviousCalculations.size(); i++)
                        editor.remove("Modarray_" + i);
                    editor.commit();
                    Mod_ListView.setAdapter(null);
                    Mod_PreviousCalculations = new ArrayList<>();
                }
            }
        });


    }

    protected void update_Mod_SharedPreference()
    {
        editor.putInt("Modarray_size", Mod_PreviousCalculations.size());
        for(int i = 0;i < Mod_PreviousCalculations.size(); i++)
            editor.putString("Modarray_" + i, Mod_PreviousCalculations.get(i));
        editor.commit();
    }

    protected ArrayList<String> read_Mod_SharedPreferences()
    {
        int arrSize = settings.getInt("Modarray_size", -1);
        if(arrSize == -1)
            return null;        //returns null if the Mod calculations page is empty
        else
        {
            ArrayList<String> myArray = new ArrayList<>();
            for(int i = 0;i < arrSize; i++)
            {
                myArray.add(settings.getString("Modarray_" + i, null));
            }

            return myArray;
        }
    }
}