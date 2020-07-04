package com.android.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    EditText editText_weight, editText_height;
    Spinner sex_spinner;
    Button button;
    Double answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        mInterstitialAd= new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2866595266782247/5827186067");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        editText_height = findViewById(R.id.editText_height);
        editText_weight = findViewById(R.id.editText_weight);
        button = findViewById(R.id.button);


        sex_spinner = findViewById(R.id.sexSpinner);

        ArrayList<String> sex_spinner_array = new ArrayList<>();

        sex_spinner_array.add("Male");
        sex_spinner_array.add("Female");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sex_spinner_array);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex_spinner.setAdapter(arrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText_height.getText().toString().isEmpty() && !editText_weight.getText().toString().isEmpty()) {
                    answer = Double.parseDouble(editText_weight.getText().toString()) /
                            Math.pow(Double.parseDouble(editText_height.getText().toString()),2);

                } else{
                    Toast.makeText(MainActivity.this, "Please enter weight and height", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent sendAnswer = new Intent(MainActivity.this, BMIActivity.class);
                sendAnswer.putExtra("bmi", answer);
                sendAnswer.putExtra("height", Double.parseDouble(editText_height.getText().toString()));
                startActivity(sendAnswer);
            }
        });

    }
}
