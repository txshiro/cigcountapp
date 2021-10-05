package com.example.fuckyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StatsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_page);
    }

    public void OpenHomeActivity(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}