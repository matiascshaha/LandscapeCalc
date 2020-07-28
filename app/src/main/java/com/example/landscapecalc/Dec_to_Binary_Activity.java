package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Dec_to_Binary_Activity extends AppCompatActivity {

    Button resetButton;
    Button DecButton;
    Button BinButton;
    EditText enterNum;
    Button Reset_PreviousCalculationsDec_Button;
    ListView Dec_ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dec_to__binary_);

        resetButton = findViewById(R.id.Dec_Reset_Button);
        DecButton = findViewById(R.id.Calculate_Dec_Button);
        BinButton = findViewById(R.id.Calculate_Binary_Button);
        enterNum = findViewById(R.id.Dec_2_Bin_Activity_EditText);
        Dec_ListView = findViewById(R.id.Dec_previousCalculations);
        Reset_PreviousCalculationsDec_Button = findViewById(R.id.reset_DecCalculations_Button);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                Toast.makeText(getApplicationContext(), ans,
                        Toast.LENGTH_LONG).show();
            }
        });
        BinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer ans = Integer.parseInt(enterNum.getText().toString(),2);
                Toast.makeText(getApplicationContext(), Integer.toString(ans),
                        Toast.LENGTH_LONG).show();
            }


        });

    }
}