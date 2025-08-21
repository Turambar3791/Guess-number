package com.example.guesenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    private TextView tvWynik, tvBledy;
    private Button btnStartAgain, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        // przypisanie elementów
        tvWynik = findViewById(R.id.tvWynik);
        tvBledy = findViewById(R.id.tvBledy);
        btnStartAgain = findViewById(R.id.btnStartAgain);
        btnExit = findViewById(R.id.btnExit);

        // pobieranie danych z intenta
        Intent intent = getIntent();
        int liczbaMa = intent.getIntExtra("liczbaMa", 0);
        int bledy = intent.getIntExtra("błedy", 0);

        // wyświetlanie podsumowania gry
        tvWynik.setText(String.valueOf(liczbaMa));
        tvBledy.setText(String.valueOf(bledy));

        // akcja na guziku do ponownego rozpoczęcia gry
        btnStartAgain.setOnClickListener(v -> {
            Intent intent1 = new Intent(FinishActivity.this, GameActivity.class);
            startActivity(intent1);
        });

        // zamknięcie aplikacji
        btnExit.setOnClickListener(v -> {
            finishAffinity();
        });

    }
}