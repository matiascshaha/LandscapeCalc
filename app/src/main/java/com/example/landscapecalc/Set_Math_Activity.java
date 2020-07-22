package com.example.landscapecalc;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Set_Math_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner SetMath_Spinner;
    EditText SetMathEditText1;
    EditText SetMathEditText2;
    TextView SetMathExpressionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__math_);

        //add support for back button for function activities
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SetMath_Spinner = findViewById(R.id.SetMathSpinner);
        SetMathEditText1 = findViewById(R.id.SetMath_editText1);
        SetMathEditText2 = findViewById(R.id.SetMath_editText2);
        SetMathExpressionTextView = findViewById(R.id.SetSpinnerExpressionTextView);

        ArrayAdapter<CharSequence> mySpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.SetMath_SpinnerArray,android.R.layout.simple_spinner_item);
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SetMath_Spinner.setAdapter(mySpinnerAdapter);
        SetMath_Spinner.setOnItemSelectedListener(this);

        Toast.makeText(this,"Input : Enter comma separated single characters",Toast.LENGTH_LONG).show();





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(SetMathEditText1.getText().toString().matches("[a-zA-Z]((,[a-zA-Z])+)?") && SetMathEditText2.getText().toString().matches("[a-zA-Z]((,[a-zA-Z])+)?"))
        {

            if (parent.getItemAtPosition(position).toString().equals("Union"))
            {
                String EditText1_String = SetMathEditText1.getText().toString();
                String EditText2_String = SetMathEditText2.getText().toString();

                SetMathExpressionTextView.setText("{" + EditText1_String + "}" + " ∪ " + "{" + EditText2_String + "}" + " = " + unionCalculator(EditText1_String,EditText2_String));
            }

            else if (parent.getItemAtPosition(position).toString().equals("Intersection"))
            {
                String EditText1_String = SetMathEditText1.getText().toString();
                String EditText2_String = SetMathEditText2.getText().toString();

                SetMathExpressionTextView.setText("{" + EditText1_String + "}" + " ∩ " + "{" + EditText2_String + "}" + " = " + intersectionCalculator(EditText1_String,EditText2_String));
            }

            else if (parent.getItemAtPosition(position).toString().equals("Difference"))
            {
                String EditText1_String = SetMathEditText1.getText().toString();
                String EditText2_String = SetMathEditText2.getText().toString();

                SetMathExpressionTextView.setText("{" + EditText1_String + "}" + " - " + "{" + EditText2_String + "}" + " = " + differenceCalculator(EditText1_String,EditText2_String));
            }

            else if (parent.getItemAtPosition(position).toString().equals("Symmetric Difference"))
            {
                String EditText1_String = SetMathEditText1.getText().toString();
                String EditText2_String = SetMathEditText2.getText().toString();

                SetMathExpressionTextView.setText("{" + EditText1_String + "}" + " ∆ " + "{" + EditText2_String + "}" + " = " + symmetricDifferenceCalculator(EditText1_String,EditText2_String));

            }
        }
        else
        {
            Toast.makeText(this,"Input must be comma separated single characters",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Union calculator function
    //1 paramater which is a comma separated string of elements to parse from
    public String unionCalculator(String firstSetString,String secondSetString)
    {
        //parsing process
        String [] set1Elements = firstSetString.split(",");
        String [] set2Elements = secondSetString.split(",");

        //array to set process
        Set<String> unionSet = new HashSet<>();
        unionSet.addAll(Arrays.asList(set1Elements));
        unionSet.addAll(Arrays.asList(set2Elements));

        String unionSetString = "{";
        int count = 0;
        for(String element : unionSet)
        {
            unionSetString += element;
            if(count < unionSet.size() - 1)     //dont add comma for last element
            {
                unionSetString += ",";
            }
            else
            {
                unionSetString += "}";
            }
            count++;
        }


        return unionSetString;

    }

    public String intersectionCalculator(String firstSetString, String secondSetString)
    {
        //parsing process
        String [] set1Elements = firstSetString.split(",");
        String [] set2Elements = secondSetString.split(",");

        //copy array to set process
        Set<String> firstActualSet = new HashSet<>(Arrays.asList(set1Elements));
        Set<String> secondActualSet = new HashSet<>(Arrays.asList(set2Elements));

        firstActualSet.retainAll(secondActualSet);

        int count = 0;
        String intersectionSetString = "{";

        for(String element : firstActualSet)
        {
            intersectionSetString += element;
            if(count < firstActualSet.size() - 1)     //dont add comma for last element
            {
                intersectionSetString += ",";
            }
            else
            {
                intersectionSetString += "}";
            }
            count++;
        }

        return intersectionSetString;

    }

    public String differenceCalculator(String firstSetString,String secondSetString)
    {
        //parsing process
        String [] set1Elements = firstSetString.split(",");
        String [] set2Elements = secondSetString.split(",");

        //copy array to set process
        Set<String> firstActualSet = new HashSet<>(Arrays.asList(set1Elements));
        Set<String> secondActualSet = new HashSet<>(Arrays.asList(set2Elements));

        firstActualSet.removeAll(secondActualSet);

        int count = 0;
        String differenceSetString = "{";


        for(String element : firstActualSet)
        {
            differenceSetString += element;
            if(count < firstActualSet.size() - 1)     //dont add comma for last element
            {
                differenceSetString += ",";
            }
            else
            {
                differenceSetString += "}";
            }
            count++;
        }

        return differenceSetString;

    }

    public String symmetricDifferenceCalculator(String firstSetString,String secondSetString)
    {
        //parsing process
        String [] setAElements = firstSetString.split(",");
        String [] setBElements = secondSetString.split(",");

        //array to set process
        Set<String> setA = new HashSet<>(Arrays.asList(setAElements));
        Set<String> setB = new HashSet<>(Arrays.asList(setBElements));

        Set<String> AminusB_Set = new HashSet<>(Arrays.asList(setAElements));
        AminusB_Set.removeAll(setB);
        Set<String> BminusA_Set = new HashSet<>(Arrays.asList(setBElements));
        BminusA_Set.removeAll(setA);

        //perform union of A - B and B - A
        AminusB_Set.addAll(BminusA_Set);

        int count = 0;
        String symmetricDifferenceSetString = "{";

        for(String element : AminusB_Set)
        {
            symmetricDifferenceSetString += element;
            if(count < AminusB_Set.size() - 1)     //dont add comma for last element
            {
                symmetricDifferenceSetString += ",";
            }
            else
            {
                symmetricDifferenceSetString += "}";
            }
            count++;
        }

        return symmetricDifferenceSetString;
    }
}

