package com.example.puzle_15_beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = findViewById(R.id.startGame);
        Button save = findViewById(R.id.saveGame);
        start.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DifficultActivity2.class);
            startActivity(intent);
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameOverActivity.class);
                startActivity(intent);
            }
        });
    }
}