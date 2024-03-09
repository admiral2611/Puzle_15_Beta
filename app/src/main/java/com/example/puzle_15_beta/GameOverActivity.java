package com.example.puzle_15_beta;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    private ImageView backDificul, helpDificul;
    private TextView timeEasy,nameScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        findBtn();
        clickBtn();
        setView();
    }

    private void setView() {
       // Integer time = AppCache.getObject().getTime();
        timeEasy.setText(AppCache.getObject().getTime());
        nameScore.setText(AppCache.getObject().getName());
    }

    private void clickBtn() {
        backDificul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        helpDificul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOverActivity.this,InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findBtn() {
        nameScore=findViewById(R.id.nameScore);
        timeEasy=findViewById(R.id.timeEasy);
        backDificul = findViewById(R.id.backDificul);
        helpDificul = findViewById(R.id.helpDificul);
    }


}