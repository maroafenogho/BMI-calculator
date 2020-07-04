package com.android.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity {

    double getAnswer, height, parseAnswer;
    TextView message, responseText;
    ImageView imageView;
    DecimalFormat format;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        imageView = findViewById(R.id.image_ee);
        message = findViewById(R.id.congrats);
        responseText = findViewById(R.id.message);

        Intent receiveAnswer = getIntent();
        getAnswer = receiveAnswer.getDoubleExtra("bmi", 0.0);
        height = receiveAnswer.getDoubleExtra("height", 0.0);

        format = new DecimalFormat("0.00");
        parseAnswer = Double.parseDouble(format.format(getAnswer));


        if (getAnswer<=18.49){
            message.setText("Underweight");
            imageView.setImageResource(R.drawable.underweight_);
            responseText.setText("Your BMI is " + parseAnswer + "\nYou need to put on a little more weight. " + "\nYou are " + (18.5-parseAnswer)*Math.pow(height,2) + "kg away");
        } else if (getAnswer<=24.9) {
            message.setText("Congratulations");
            imageView.setImageResource(R.drawable.perfect_weight_bitmap);
            responseText.setText("Your BMI is " + parseAnswer + "\nYou are maintaining a healthy weight.\nKeep it up.");
        } else if (getAnswer<=29.9) {
            message.setText("Overweight");
            imageView.setImageResource(R.drawable.overweight_bitmap);
            responseText.setText("Your BMI is " + parseAnswer + "\nYou need to lose on a little more weight." + "\nYou need to lose " + (parseAnswer-25)*Math.pow(height,2) + "kg");
        }  else if (getAnswer>=30.0) {
            message.setText("Obese");
            imageView.setImageResource(R.drawable.obessed_);
            responseText.setText("Your BMI is " + parseAnswer + "\nYou need to shed " + (parseAnswer-25)*Math.pow(height,2) + "kg");
        }

    }
}
