package com.example.fuckyou;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    SharedPreferences sp;


    int cigsCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get objects by id
        progressBar = findViewById(R.id.progressBar);
        TextView timeAndDate = findViewById(R.id.timeAndDate);

        //here we get and format date
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        //and we set the text to formatted date
        timeAndDate.setText(date);

        //we get shared preferences so we can save things
        sp = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        CheckIfNewDay();
        checkifcigs();
    }

    @Override
    public void onResume(){
        super.onResume();
        CheckIfNewDay();
        checkifcigs();
    }

    //array for quotes
    String[] quotesArr = {
            "Why you do this",
            "Is it worth it?",
            "I like cocks",
            "Bla bla bla",
            "Lorem ipsum your mom",
            "idk wha tto do "
    };

    public void AddCigToCounter(View b) {
        //get text
        TextView quotesText = findViewById(R.id.quotes);

        //change text of cigs counter to integer cigsCounter
        cigsCounter++;

        //set progressBar to integer cigsCounter
        progressBar.setProgress(cigsCounter);

        //generate random
        Random rng = new Random();

        //get random number (the argument here is the array length)
        int number = rng.nextInt(quotesArr.length);

        //set random quote from the array
        quotesText.setText(quotesArr[number]);


        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("AmountOfCigs", cigsCounter);
        editor.apply();

        SetCigText();
    }

        public void SetCigText()
        {
            TextView cigsCount = findViewById(R.id.cigsCount);
            cigsCount.setText(String.valueOf(cigsCounter));

        }

        public void checkifcigs()
        {

            if (!sp.contains("initialized")) {

                SharedPreferences.Editor editor = sp.edit();

                //Indicate that the default shared prefs have been set
                editor.putBoolean("initialized", true);

                editor.putInt("AmountOfCigs", cigsCounter);
                editor.apply();

            }else
            {
                cigsCounter = sp.getInt("AmountOfCigs",-1);
                progressBar.setProgress(cigsCounter);

                SetCigText();
            }
        }

        public void ResetCigCount()
        {
            SharedPreferences.Editor editor = sp.edit();

            editor.putInt("AmountOfCigs", 0);
            editor.apply();

        }

        public void CheckIfNewDay(){

            Calendar c = Calendar.getInstance();

            int currentDayOfYear = c.get(Calendar.DAY_OF_YEAR);

            SharedPreferences prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

            int lastDay = prefs.getInt("lastDay", -1);

            if(lastDay != currentDayOfYear)
            {
                prefs.edit().putInt("lastDay", currentDayOfYear).apply();
                ResetCigCount();
            }

        }

        public void OpenStatsActivity(View v){
            Intent i = new Intent(this, StatsPage.class);
            startActivity(i);
        }
}
