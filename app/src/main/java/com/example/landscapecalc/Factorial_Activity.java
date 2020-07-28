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

public class Factorial_Activity extends AppCompatActivity {

    Button resetButton;
    Button factorialButton;
    EditText enterNum;
    Button Reset_PreviousCalculationsFactorial_Button;
    ListView Factorial_ListView;

    public static final String PREFS_NAME= "com.example.landscapecalc";

    //
    ArrayList<String> Factorial_PreviousCalculations;

    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial_);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        settings = getSharedPreferences(PREFS_NAME,0);
        editor = settings.edit();

        resetButton = findViewById(R.id.Factorial_Reset_Button);
        factorialButton = findViewById(R.id.Calculate_Factorial_Button);
        enterNum = findViewById(R.id.Factorial_Activity_EditText);
        Factorial_ListView = findViewById(R.id.Factorial_ListView);
        Reset_PreviousCalculationsFactorial_Button = findViewById(R.id.reset_FactorialCalculations_Button);

        if(settings.getInt("Factorialarray_size",-1) == -1)
        {
            Factorial_PreviousCalculations = new ArrayList<>();
        }
        else
        {
            Factorial_PreviousCalculations = new ArrayList<>(read_Factorial_SharedPreferences());
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,Factorial_PreviousCalculations);
            Factorial_ListView.setAdapter(myAdapter);
        }

        resetButton.setOnClickListener(new View.OnClickListener() { //resets prime counter
            @Override
            public void onClick(View v) {
                enterNum.setText("");
            }
        });
        factorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer numEntered = Integer.parseInt(enterNum.getText().toString()); //convert text
                //to string

                if(numEntered <= 1){
                    Toast.makeText(getApplicationContext(), "Enter a number larger than 1",
                            Toast.LENGTH_LONG).show();
                }
                if(numEntered > 1)
                {
                    int i,ans=1;
                    for(i=1;i<=numEntered;i++){
                        ans=ans*i;
                    }

                    String final_output = "Factorial>> " + String.valueOf(numEntered) + "! = " + String.valueOf(ans);

                    Factorial_PreviousCalculations.add(String.valueOf(final_output));
                    update_Factorial_SharedPreference();

                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, read_Factorial_SharedPreferences());
                    Factorial_ListView.setAdapter(myAdapter);

                }
            }


        });

        Reset_PreviousCalculationsFactorial_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int arrSize = settings.getInt("Factorialarray_size", -1);
                if(arrSize != -1)
                {
                    editor.remove("Factorialarray_size");
                    for(int i = 0;i < Factorial_PreviousCalculations.size(); i++)
                        editor.remove("Factorialarray_" + i);
                    editor.commit();
                    Factorial_ListView.setAdapter(null);
                    Factorial_PreviousCalculations = new ArrayList<>();
                }


            }
        });
    }

    protected void update_Factorial_SharedPreference()
    {
        editor.putInt("Factorialarray_size", Factorial_PreviousCalculations.size());
        for(int i = 0;i < Factorial_PreviousCalculations.size(); i++)
            editor.putString("Factorialarray_" + i, Factorial_PreviousCalculations.get(i));
        editor.commit();
    }

    protected ArrayList<String> read_Factorial_SharedPreferences()
    {
        int arrSize = settings.getInt("Factorialarray_size", -1);
        if(arrSize == -1)
            return null;        //returns null if the Factorial calculations page is empty
        else
        {
            ArrayList<String> myArray = new ArrayList<>();
            for(int i = 0;i < arrSize; i++)
            {
                myArray.add(settings.getString("Factorialarray_" + i, null));
            }

            return myArray;
        }
    }

}
