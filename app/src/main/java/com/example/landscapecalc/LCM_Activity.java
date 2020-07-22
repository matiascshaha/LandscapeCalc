package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigInteger;
import java.util.Scanner;

public class LCM_Activity extends AppCompatActivity {

    EditText LCM_EditText;
    Button LCM_Reset_Button;
    Button LCM_Calculate_Button;
    DatabaseReference myDatabaseRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference LCM_Snapshot_ofDatabase = myDatabaseRootReference.child("LCM");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_c_m_);

        //add support for back button for function activities
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LCM_EditText = findViewById(R.id.LCM_Activity_EditText);
        LCM_Calculate_Button = findViewById(R.id.Calcuate_LCM_Button);
        LCM_Reset_Button = findViewById(R.id.ResetLCM_Button);

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

                    System.out.println("num1: " + num1);
                    System.out.println("num2: " + num2);

                    System.out.println("LCM: " + lcm(num1, num2));

                    String final_output = "LCM>> " + num1 + "," + num2 + " = " + lcm(num1,num2);

                    System.out.println("Final output: " + final_output);
                    LCM_Snapshot_ofDatabase.push().setValue(final_output);
                }
                else if(LCMEditTextString.matches("([0-9]+(\\s)+[0-9]+)"))          //editText input that doesn't include comma
                {
                    Scanner myScanner = new Scanner(LCMEditTextString);
                    int num1 = myScanner.nextInt();
                    int num2 = myScanner.nextInt();

                    System.out.println("num1: " + num1);
                    System.out.println("num2: " + num2);

                    System.out.println("LCM: " + lcm(num1,num2));

                    String final_output = "LCM>> " + num1 + "," + num2 + " = " + lcm(num1,num2);

                    System.out.println("Final output: " + final_output);
                    LCM_Snapshot_ofDatabase.push().setValue(final_output);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter 2 non-zero integer numbers",Toast.LENGTH_LONG).show();
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
}
