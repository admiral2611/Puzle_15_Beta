package com.example.puzle_15_beta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DifficultActivity2 extends AppCompatActivity {
    private ImageView backDifficult, helpDifficult;
    private TextView btnEasy, btnNormal, btnHard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dificul2);
        loadDifficult();
        loadWidow();
    }

    private void loadWidow() {
        btnEasy.setOnClickListener(view -> {
            Intent intent = new Intent(DifficultActivity2.this, EasyActivity.class);
            startActivity(intent);
        });

        btnNormal.setOnClickListener(view -> {

            Intent intent = new Intent(DifficultActivity2.this, NormalActivity.class);
            startActivity(intent);
        });

        btnHard.setOnClickListener(view -> {
            Intent intent = new Intent(DifficultActivity2.this, HardActivity.class);
            startActivity(intent);
        });


        backDifficult.setOnClickListener(view -> {
            Intent intent = new Intent(DifficultActivity2.this, MainActivity.class);
            startActivity(intent);
        });
        helpDifficult.setOnClickListener(view -> {
            Intent intent = new Intent(DifficultActivity2.this, InfoActivity.class);
            startActivity(intent);
        });
    }

    private void loadDifficult() {
        btnEasy = findViewById(R.id.easyButton);
        btnNormal = findViewById(R.id.normalButton);
        btnHard = findViewById(R.id.hardButton);
        backDifficult = findViewById(R.id.backDificul);
        helpDifficult = findViewById(R.id.helpDificul);
    }
}