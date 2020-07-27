package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button GCD_Button;
    Button LCM_Button;
    Button Set_Math_Button;
    Button Prime_Count_Button;
    Button Decimal_Frac_Button;
    Button Modulus_Button;
    Button Dictionary_Button;
    Button Factorial_Button;
    Button Dec_2_Bin_Button;
    Button Dec_2_Hex_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GCD_Button = findViewById(R.id.GCD_Button);
        LCM_Button = findViewById(R.id.LCM_Button);
        Set_Math_Button = findViewById(R.id.Set_Math_Button);
        Prime_Count_Button = findViewById(R.id.Prime_Count_Button);
        Decimal_Frac_Button = findViewById(R.id.Decimal_Frac_Button);
        Modulus_Button = findViewById(R.id.Modulus_Button);
        Dictionary_Button = findViewById(R.id.Dictionary_Button);
        Factorial_Button = findViewById(R.id.Factorial_Button);
        Dec_2_Bin_Button = findViewById(R.id.Decimal_Binary_Button);
        Dec_2_Hex_Button = findViewById(R.id.Decimal_Hexadecimal_Button);

        GCD_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, GCD_Activity.class);
                startActivity(myIntent);

            }
        });

        LCM_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, LCM_Activity.class);
                startActivity(myIntent);

            }
        });

        Set_Math_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Set_Math_Activity.class);
                startActivity(myIntent);

            }
        });

        Prime_Count_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Prime_Count_Activity.class);
                startActivity(myIntent);
            }
        });

        Decimal_Frac_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Decimal_Frac_Activity.class);
                startActivity(myIntent);
            }
        });

        Modulus_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Modulus_Activity.class);
                startActivity(myIntent);
            }
        });

        Dictionary_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Dictionary_Activity.class);
                startActivity(myIntent);
            }
        });
        Factorial_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Factorial_Activity.class);
                startActivity(myIntent);
            }
        });

        Dec_2_Bin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Dec_to_Binary_Activity.class);
                startActivity(myIntent);
            }
        });

        Dec_2_Hex_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Dec_To_Hex_Activity.class);
                startActivity(myIntent);
            }
        });


    }
}