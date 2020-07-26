package com.example.landscapecalc;

import androidx.annotation.NonNull;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;


public class GCD_Activity extends AppCompatActivity {


    EditText GCD_EditText;
    Button Calculate_GCD_Button;
    Button Reset_Button;
    ListView GCD_PrevCalculation_ListView;
    Button Reset_Calculations_List_Button;

    public static final String PREFS_NAME= "com.example.landscapecalc";

    //global array to track all previous calculations in the GCD Page
    ArrayList<String> GCD_PreviousCalculations;

    SharedPreferences settings;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_c_d_);

        settings = getSharedPreferences(PREFS_NAME,0);
        editor = settings.edit();




        //add support for back button for function activities
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GCD_EditText = findViewById(R.id.GCD_Activity_EditText);
        Calculate_GCD_Button = findViewById(R.id.Calcuate_GCD_Button);
        Reset_Button = findViewById(R.id.Reset_Button);
        GCD_PrevCalculation_ListView = findViewById(R.id.GCD_previousCalculations);
        Reset_Calculations_List_Button = findViewById(R.id.reset_GCDCalculations_Button);

        if(settings.getInt("GCDarray_size",-1) == -1)
        {
            GCD_PreviousCalculations = new ArrayList<>();
        }
        else
        {
            GCD_PreviousCalculations = new ArrayList<>(read_GCD_SharedPreferences());
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,GCD_PreviousCalculations);
            GCD_PrevCalculation_ListView.setAdapter(myAdapter);
        }

        Calculate_GCD_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String GcdEditTextString = GCD_EditText.getText().toString();
                //remove all whitespace
                GcdEditTextString = GcdEditTextString.trim();

                if(GcdEditTextString.matches("([0-9]+(\\s)*,(\\s)*[0-9]+)"))            //edit text that includes comma
                {
                    GcdEditTextString = GcdEditTextString.replaceAll("\\s", "");
                    String [] arr = GcdEditTextString.split(",");

                    BigInteger num1_BigInt = new BigInteger(arr[0]);
                    BigInteger num2_BigInt = new BigInteger(arr[1]);

                    String final_output = "GCD>> " + num1_BigInt.toString() + "," + num2_BigInt.toString() + " = " + num1_BigInt.gcd(num2_BigInt).toString();

                    System.out.println("Final output: " + final_output);

                    GCD_PreviousCalculations.add(final_output);
                    update_GCD_SharedPreference();


                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_GCD_SharedPreferences());
                    GCD_PrevCalculation_ListView.setAdapter(myAdapter);



                }
                else if(GcdEditTextString.matches("([0-9]+(\\s)+[0-9]+)"))          //editText input that doesn't include comma
                {
                    Scanner myScanner = new Scanner(GcdEditTextString);
                    BigInteger num1_BigInt = myScanner.nextBigInteger();
                    BigInteger num2_BigInt = myScanner.nextBigInteger();

                    String final_output = "GCD>> " + num1_BigInt.toString() + "," + num2_BigInt.toString() + " = " + num1_BigInt.gcd(num2_BigInt).toString();

                    GCD_PreviousCalculations.add(final_output);
                    update_GCD_SharedPreference();

                    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,read_GCD_SharedPreferences());
                    GCD_PrevCalculation_ListView.setAdapter(myAdapter);


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter 2 non-zero integer numbers",Toast.LENGTH_LONG).show();
                }





            }
        });

        Reset_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GCD_EditText.setText("");
            }
        });

        Reset_Calculations_List_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int arrSize = settings.getInt("GCDarray_size", -1);
                if(arrSize != -1)
                {
                    editor.remove("GCDarray_size");
                    for(int i = 0;i < GCD_PreviousCalculations.size(); i++)
                        editor.remove("GCDarray_" + i);
                    editor.commit();
                    GCD_PrevCalculation_ListView.setAdapter(null);
                    GCD_PreviousCalculations = new ArrayList<>();
                }


            }
        });
    }

    protected void update_GCD_SharedPreference()
    {
        editor.putInt("GCDarray_size", GCD_PreviousCalculations.size());
        for(int i = 0;i < GCD_PreviousCalculations.size(); i++)
            editor.putString("GCDarray_" + i, GCD_PreviousCalculations.get(i));
        editor.commit();
    }

    protected ArrayList<String> read_GCD_SharedPreferences()
    {
        int arrSize = settings.getInt("GCDarray_size", -1);
        if(arrSize == -1)
            return null;        //returns null if the GCD calculations page is empty
        else
        {
            ArrayList<String> myArray = new ArrayList<>();
            for(int i = 0;i < arrSize; i++)
            {
                myArray.add(settings.getString("GCDarray_" + i, null));
            }

            return myArray;
        }
    }


}