package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Modulus_Activity extends AppCompatActivity {

    Button modular_button;
    Button resetButton;
    EditText firstNum;
    EditText secondNum;
    EditText N;
    TextView TrueOrFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulus_);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        modular_button = findViewById(R.id.modular_button);
        resetButton = findViewById(R.id.resetButton);
        firstNum = findViewById(R.id.firstNum);
        secondNum = findViewById(R.id.secondNum);
        N = findViewById(R.id.N);
        TrueOrFalse = findViewById(R.id.TrueOrFalse);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNum.setText("");
                secondNum.setText("");
                N.setText("");
                TrueOrFalse.setText("");
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

                if(result1 == result2 || result1 == (n+result2)){
                    TrueOrFalse.setText("True");
                }
                else{
                    TrueOrFalse.setText("False");
                }
            }
        });


    }
}