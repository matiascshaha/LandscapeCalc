package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Dec_To_Hex_Activity extends AppCompatActivity {

    Button resetButton;
    Button DecButton;
    Button HexButton;
    EditText enterNum;
    Button Reset_PreviousCalculationsHex_Button;
    ListView Hex_ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dec__to__hex_);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resetButton = findViewById(R.id.Hex_Reset_Button);
        DecButton = findViewById(R.id.Calculate_Hex_Button);
        HexButton = findViewById(R.id.Calculate_Hex_2_Dec_Button);
        enterNum = findViewById(R.id.Dec_2_Hex_Activity_EditText);
        Hex_ListView = findViewById(R.id.Hex_previousCalculations);
        Reset_PreviousCalculationsHex_Button = findViewById(R.id.reset_HexCalculations_Button);

        resetButton.setOnClickListener(new View.OnClickListener() { //resets prime counter
            @Override
            public void onClick(View v) {
                enterNum.setText("");
            }
        });
        DecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer numEntered = Integer.parseInt(enterNum.getText().toString()); //convert text
                //to string
                String ans = Integer.toHexString(numEntered);
                Toast.makeText(getApplicationContext(), ans,
                        Toast.LENGTH_LONG).show();
            }
        });
        HexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer ans = Integer.parseInt(enterNum.getText().toString(),16);
                Toast.makeText(getApplicationContext(), Integer.toString(ans),
                        Toast.LENGTH_LONG).show();

            }


        });


    }
}