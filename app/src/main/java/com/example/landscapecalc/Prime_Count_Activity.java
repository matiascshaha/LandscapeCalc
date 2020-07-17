package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Prime_Count_Activity extends AppCompatActivity {

    Button resetButton;
    Button primeButton;
    Button backToMain2;
    TextView numOfPrimes;
    TextView inputNum;
    EditText enterNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime__count_);

        resetButton = findViewById(R.id.resetButton);
        primeButton = findViewById(R.id.primeButton);
        backToMain2 = findViewById(R.id.backToMain2);
        numOfPrimes = findViewById(R.id.numOfPrimes);
        inputNum = findViewById(R.id.inputNum);
        enterNum = findViewById(R.id.enterNum);

        resetButton.setOnClickListener(new View.OnClickListener() { //resets prime counter
            @Override
            public void onClick(View v) {
                enterNum.setText("");
                numOfPrimes.setText("");
                inputNum.setText("");
            }
        });

        primeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer numEntered = Integer.parseInt(enterNum.getText().toString()); //convert text
                    //to string
                int counter = 0;

                if(enterNum.getText().toString().length() >= 7){    //runs successfully for numbers
                    // larger than 7 digits but the time complexity is not super great
                    Toast.makeText(getApplicationContext(),
                            "Enter a number less than 7 digits", Toast.LENGTH_LONG).show();
                }

                if(numEntered <= 1){
                    Toast.makeText(getApplicationContext(), "Enter a number larger than 1",
                            Toast.LENGTH_LONG).show();
                }
                if(numEntered > 1 && enterNum.getText().toString().length() < 7) {
                    for (int i = 2; i <= numEntered; i++) {
                        if(isPrime(i)){
                            counter++;  //counts primes less than given number
                        }
                    }
                }

                if(counter > 0){
                    inputNum.setText(enterNum.getText().toString());
                    numOfPrimes.setText(Integer.toString(counter));
                }
            }
        });

        backToMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Prime_Count_Activity.this,
                        MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

    public boolean isPrime(int num){
        for (int i = 2; i < num; i++) {
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }
}

