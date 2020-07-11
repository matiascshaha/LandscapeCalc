package com.example.landscapecalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigInteger;
import java.util.Scanner;


public class GCD_Activity extends AppCompatActivity {


    EditText GCD_EditText;
    Button Calculate_GCD_Button;
    Button Reset_Button;
    DatabaseReference myDatabaseRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference GCD_Snapshot_ofDatabase = myDatabaseRootReference.child("GCD");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_c_d_);

        GCD_EditText = findViewById(R.id.GCD_Activity_EditText);
        Calculate_GCD_Button = findViewById(R.id.Calcuate_GCD_Button);
        Reset_Button = findViewById(R.id.Reset_Button);

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

                    System.out.println("num1: " + num1_BigInt);
                    System.out.println("num2: " + num2_BigInt);

                    System.out.println("GCD: " + num1_BigInt.gcd(num2_BigInt));

                    String final_output = "GCD>> " + num1_BigInt + "," + num2_BigInt + " = " + num1_BigInt.gcd(num2_BigInt);

                    System.out.println("Final output: " + final_output);
                    GCD_Snapshot_ofDatabase.push().setValue(final_output);
                }
                else if(GcdEditTextString.matches("([0-9]+(\\s)+[0-9]+)"))          //editText input that doesn't include comma
                {
                    Scanner myScanner = new Scanner(GcdEditTextString);
                    BigInteger num1_BigInt = myScanner.nextBigInteger();
                    BigInteger num2_BigInt = myScanner.nextBigInteger();

                    System.out.println("num1: " + num1_BigInt);
                    System.out.println("num2: " + num2_BigInt);

                    System.out.println("GCD: " + num1_BigInt.gcd(num2_BigInt));

                    String final_output = "GCD>> " + num1_BigInt + "," + num2_BigInt + " = " + num1_BigInt.gcd(num2_BigInt);

                    System.out.println("Final output: " + final_output);
                    GCD_Snapshot_ofDatabase.push().setValue(final_output);
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
    }
}