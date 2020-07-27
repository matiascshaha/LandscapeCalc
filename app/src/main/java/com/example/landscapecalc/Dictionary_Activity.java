package com.example.landscapecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Dictionary_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}