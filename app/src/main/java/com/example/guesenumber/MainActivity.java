package com.example.guesenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // przypisanie guzików
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);

        // otwarcie nowej aktywności z grą
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });

        // zamknięcie aplikacji
        btnEnd.setOnClickListener(v -> finishAffinity());
    }
}