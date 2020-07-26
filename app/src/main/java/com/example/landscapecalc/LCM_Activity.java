package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class LCM_Activity extends AppCompatActivity {

    EditText LCM_EditText;
    Button LCM_Reset_Button;
    Button LCM_Calculate_Button;
    Button Reset_Previous_Calculations_Button;
    ListView LCM_ListView;

    public static final String PREFS_NAME= "com.example.landscapecalc";

    //global array to track all previous calculations in the LCM Page
    ArrayList<String> LCM_PreviousCalculations;

    SharedPreferences settings;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_c_m_);

        //add support for back button for function activities
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        settings = getSharedPreferences(PREFS_NAME,0);
        editor = settings.edit();

        LCM_EditText = findViewById(R.id.LCM_Activity_EditText);
        LCM_Calculate_Button = findViewById(R.id.Calcuate_LCM_Button);
        LCM_Reset_Button = findViewById(R.id.ResetLCM_Button);
        LCM_ListView = findViewById(R.id.LCM_ListView);
        Reset_Previous_Calculations_Button = findViewById(R.id.Reset_LCM_PreviousCalculations_Button);

        if(settings.getInt("LCMarray_size",-1) == -1)
        {
            LCM_PreviousCalculations = new ArrayList<>();
        }
        else
        {
            LCM_PreviousCalculations = new ArrayList<>(read_LCM_SharedPreferences());
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,LCM_PreviousCalculations);
            LCM_ListView.setAdapter(myAdapter);
        }

        LCM_Reset_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LCM_EditText.setText("");
            }
        });

        LCM_Calculate_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LCMEditTextString = LCM_EditText.getText().toString();
                //remove all whitespace
                LCMEditTextString = LCMEditTextString.trim();

                if(LCMEditTextString.matches("([0-9]+(\\s)*,(\\s)*[0-9]+)"))            //edit text that includes comma
                {
                    LCMEditTextString = LCMEditTextString.replaceAll("\\s", "");
                    String [] arr = LCMEditTextString.split(",");

                    int num1 = Integer.parseInt(arr[0]);
                    int num2 = Integer.parseInt(arr[1]);

                    String final_output = "LCM>> " + num1 + "," + num2 + " = " + lcm(num1,num2);

                    LCM_PreviousCalculations.add(final_output);

                    update_LCM_SharedPreference();
                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_LCM_SharedPreferences());
                    LCM_ListView.setAdapter(myAdapter);
                }
                else if(LCMEditTextString.matches("([0-9]+(\\s)+[0-9]+)"))          //editText input that doesn't include comma
                {
                    Scanner myScanner = new Scanner(LCMEditTextString);
                    int num1 = myScanner.nextInt();
                    int num2 = myScanner.nextInt();

                    String final_output = "LCM>> " + num1 + "," + num2 + " = " + lcm(num1,num2);

                    LCM_PreviousCalculations.add(final_output);
                    update_LCM_SharedPreference();
                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_LCM_SharedPreferences());
                    LCM_ListView.setAdapter(myAdapter);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter 2 non-zero integer numbers",Toast.LENGTH_LONG).show();
                }
            }
        });

        Reset_Previous_Calculations_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int arrSize = settings.getInt("LCMarray_size", -1);
                if(arrSize != -1)
                {
                    editor.remove("LCMarray_size");
                    for(int i = 0;i < LCM_PreviousCalculations.size(); i++)
                        editor.remove("LCMarray_" + i);
                    editor.commit();
                    LCM_ListView.setAdapter(null);
                    LCM_PreviousCalculations = new ArrayList<>();
                }

            }
        });




    }

    public static int lcm(int number1, int number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        int absNumber1 = Math.abs(number1);
        int absNumber2 = Math.abs(number2);
        int absHigherNumber = Math.max(absNumber1, absNumber2);
        int absLowerNumber = Math.min(absNumber1, absNumber2);
        int lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    protected void update_LCM_SharedPreference()
    {
        editor.putInt("LCMarray_size", LCM_PreviousCalculations.size());
        for(int i = 0;i < LCM_PreviousCalculations.size(); i++)
            editor.putString("LCMarray_" + i, LCM_PreviousCalculations.get(i));
        editor.commit();
    }

    protected ArrayList<String> read_LCM_SharedPreferences()
    {
        int arrSize = settings.getInt("LCMarray_size", -1);
        if(arrSize == -1)
            return null;        //returns null if the GCD calculations page is empty
        else
        {
            ArrayList<String> myArray = new ArrayList<>();
            for(int i = 0;i < arrSize; i++)
            {
                myArray.add(settings.getString("LCMarray_" + i, null));
            }

            return myArray;
        }
    }
}
