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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GCD_Button = findViewById(R.id.GCD_Button);
        LCM_Button = findViewById(R.id.LCM_Button);
        Set_Math_Button = findViewById(R.id.Set_Math_Button);
        Prime_Count_Button = findViewById(R.id.Prime_Count_Button);

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


    }
}