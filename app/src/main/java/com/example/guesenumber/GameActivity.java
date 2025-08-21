package com.example.guesenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private int wybraniec, min, max, wyb, bledy = 0, er;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear, btnExe;
    private TextView tvWpisywanaCyfra, tvZero, tvSto, tvX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // przypisanie elemntów
        tvWpisywanaCyfra = findViewById(R.id.tvWpisywanaCyfra);
        tvZero = findViewById(R.id.tvZero);
        tvX = findViewById(R.id.tvX);
        tvSto = findViewById(R.id.tvSto);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnClear = findViewById(R.id.btnClear);
        btnExe = findViewById(R.id.btnExe);

        // pobieranie z sharedPreferences
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        min = sharedPreferences.getInt("min", 0);
        max = sharedPreferences.getInt("max", 100);
        er = sharedPreferences.getInt("bledy", 0);
        wyb = sharedPreferences.getInt("wybraniec",  new Random().nextInt(99) + 1);
        tvZero.setText(String.valueOf(min));
        tvSto.setText(String.valueOf(max));
        wybraniec = wyb;
        bledy = er;

        // akcje na guzikach
        btn1.setOnClickListener(view -> klik(1, tvWpisywanaCyfra));
        btn2.setOnClickListener(view -> klik(2, tvWpisywanaCyfra));
        btn3.setOnClickListener(view -> klik(3, tvWpisywanaCyfra));
        btn4.setOnClickListener(view -> klik(4, tvWpisywanaCyfra));
        btn5.setOnClickListener(view -> klik(5, tvWpisywanaCyfra));
        btn6.setOnClickListener(view -> klik(6, tvWpisywanaCyfra));
        btn7.setOnClickListener(view -> klik(7, tvWpisywanaCyfra));
        btn8.setOnClickListener(view -> klik(8, tvWpisywanaCyfra));
        btn9.setOnClickListener(view -> klik(9, tvWpisywanaCyfra));
        btn0.setOnClickListener(view -> klik(0, tvWpisywanaCyfra));
        btnClear.setOnClickListener(view -> tvWpisywanaCyfra.setText(""));
        btnExe.setOnClickListener(view -> {

            // sprawdzaniec czy wpisaliśmy cyfrę
            if (!tvWpisywanaCyfra.getText().toString().trim().isEmpty()) {
                // if-y żeby wiedzieć, czy odgadliśmy, czy też nie
                int liczbaMa = Integer.parseInt(tvWpisywanaCyfra.getText().toString().trim());
                if (liczbaMa < wybraniec) {
                    if (liczbaMa > Integer.parseInt(tvZero.getText().toString())) {
                        tvZero.setText(String.valueOf(liczbaMa));
                        min = liczbaMa;
                    }
                    tvWpisywanaCyfra.setText("");
                    bledy += 1;
                } else if (liczbaMa > wybraniec) {
                    if (liczbaMa < Integer.parseInt(tvSto.getText().toString())) {
                        tvSto.setText(String.valueOf(liczbaMa));
                        max = liczbaMa;
                    }
                    tvWpisywanaCyfra.setText("");
                    bledy += 1;
                } else {
                    min = 0;
                    max = 100;
                    er = 0;
                    wybraniec = new Random().nextInt(99) + 1;
                    sharedPreferences.edit().clear().apply();
                    tvX.setText(String.valueOf(liczbaMa));
                    // wysyłanie danych do końcowej aktywności
                    Intent intent = new Intent(GameActivity.this, FinishActivity.class);
                    intent.putExtra("liczbaMa", liczbaMa);
                    intent.putExtra("błedy", bledy);
                    bledy = 0;
                    startActivity(intent);
                }
            } else {
                // komunikat o nie wpisaniu liczby
                String komunkikat = "";
                if (Locale.getDefault().getLanguage().equals("en")) komunkikat = "Please, choose a number";
                else komunkikat = "Proszę podać liczbę";
                Toast.makeText(this, komunkikat, Toast.LENGTH_SHORT).show();
            }

            // zapisywanie w sharedPreferences
            sharedPreferences.edit().putInt("min", min).apply();
            sharedPreferences.edit().putInt("max", max).apply();
            sharedPreferences.edit().putInt("wybraniec", wybraniec).apply();
            sharedPreferences.edit().putInt("bledy", bledy).apply();
        });
    }

    // funkcja do wpisywania cyfr, żeby nie powtarzać zbyt wiele
    void klik(Integer cyferka, TextView tv) {
        if (tvWpisywanaCyfra.length() < 2) tvWpisywanaCyfra.setText(tvWpisywanaCyfra.getText().toString().trim() + cyferka);
    }
}