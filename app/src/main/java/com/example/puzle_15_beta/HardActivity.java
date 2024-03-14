package com.example.puzle_15_beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class HardActivity extends AppCompatActivity {

    private TextView timeGame;
    private ImageView menuHamburger, replayGame, pauseGame;
    private RelativeLayout relativeGroup, emptyLayout;
    private final TextView[][] buttons = new TextView[5][5];
    private final ArrayList<String> numbers = new ArrayList<>();
    private Integer emptyI = 4;
    private Integer emptyJ = 4;
    private Integer timerCount = 0, step = 0;
    private CountDownTimer timer;
    private String timeFormat;
    private Boolean isStop = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);

        loadView();
        if (savedInstanceState != null) {

            timerCount = savedInstanceState.getInt(Key_Time);

            timer = new CountDownTimer(100000_000L, 1_000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerCount++;
                    setTime();
                }

                @Override
                public void onFinish() {

                }
            };
            timer.start();
            emptyI = savedInstanceState.getInt(Key_EmptyI);
            emptyJ = savedInstanceState.getInt(Key_EmptyJ);
            String[] number1 = savedInstanceState.getStringArray(Key_Number);

            for (int i = 0; i < 25; i++) {
                buttons[i / 5][i % 5].setText(number1[i]);
                buttons[i / 5][i % 5].setVisibility(View.VISIBLE);
            }
            buttons[emptyI][emptyJ].setVisibility(View.INVISIBLE);

            isStop = savedInstanceState.getBoolean(Key_isStop);

            if (isStop) {
                emptyLayout.setVisibility(View.GONE);
                relativeGroup.setVisibility(View.VISIBLE);
                timer.start();
                isStop = false;

            } else {
                relativeGroup.setVisibility(View.GONE);
                emptyLayout.setVisibility(View.VISIBLE);
                setTime();
                timer.cancel();
                isStop = true;
            }

        } else {
            loadNumbers();
            loadDataToView();
        }


        loadAction();
        controllerMenu();
    }

    private void controllerMenu() {

        pauseGame.setOnClickListener(view -> {
            if (isStop) {
                emptyLayout.setVisibility(View.GONE);
                relativeGroup.setVisibility(View.VISIBLE);
                timer.start();
                isStop = false;

            } else {
                relativeGroup.setVisibility(View.GONE);
                emptyLayout.setVisibility(View.VISIBLE);
                timer.cancel();
                isStop = true;
            }
        });
        replayGame.setOnClickListener(view -> {
            timerCount = 0;
            loadNumbers();
            emptyI = 4;
            emptyJ = 4;
            timer.cancel();
            loadDataToView();
        });
        /*menuHamburger.setOnClickListener(view -> {
            // menuHamburger.setVisibility(View.GONE);
            //menuOpen.setVisibility(View.VISIBLE);
        });*/
       /* menuOpen.setOnClickListener(view -> {
            menuOpen.setVisibility(View.GONE);
            menuHamburger.setVisibility(View.VISIBLE);
        });*/
    }

    private void loadAction() {
        for (int i = 0; i < 25; i++) {
            buttons[i / 5][i % 5].setOnClickListener(view -> {
                step = 0;

                TextView button = (TextView) view;
                String tag = button.getTag().toString();

                int i1 = Integer.parseInt(tag.split(":")[0]);
                int j = Integer.parseInt(tag.split(":")[1]);


                int deltaI = Math.abs(emptyI - i1);
                int deltaJ = Math.abs(emptyJ - j);

                if (deltaI + deltaJ == 1) {
                    buttons[emptyI][emptyJ].setText(buttons[i1][j].getText());
                    buttons[emptyI][emptyJ].setVisibility(View.VISIBLE);


                    buttons[i1][j].setText("");
                    buttons[i1][j].setVisibility(View.INVISIBLE);

                    emptyI = i1;
                    emptyJ = j;

                    if (emptyI == 4 && emptyJ == 4) {
                        checkWin();
                    }
                    step++;
                    step.toString();
                }


            });

        }
    }

    private void checkWin() {
        for (int i = 0; i < 23; i++) {
            int current = Integer.parseInt(buttons[i / 5][i % 5].getText().toString());
            int next;
            next = Integer.parseInt(buttons[(i + 1) / 5][(i + 1) % 5].getText().toString());

            if (current > next) {
                return;
            }

        }
        /*Keyingi oynaga otkazadi*/
        Intent intent = new Intent(HardActivity.this, WinViewActivity.class);
        intent.putExtra("timeFormat", timeFormat);
        intent.putExtra("time", timerCount);
        intent.putExtra("step", step);
        startActivity(intent);
        finish();
    }

    private void loadDataToView() {

        timer = new CountDownTimer(100000_000L, 1_000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerCount++;
                setTime();
            }

            @Override
            public void onFinish() {

            }
        };

        timer.start();

        for (int i = 0; i < 24; i++) {
            buttons[i / 5][i % 5].setText(numbers.get(i));
            buttons[i / 5][i % 5].setVisibility(View.VISIBLE);
        }
        buttons[emptyI][emptyJ].setVisibility(View.INVISIBLE);
    }

    private void setTime() {
        timeFormat = converter(timerCount);
        timeGame.setText(timeFormat);
    }

    @SuppressLint("DefaultLocale")
    private String converter(Integer n) {
        int hour = n / 3600;
        int minute = n % 3600 / 60;
        int second = n % 60;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    private void loadNumbers() {
        numbers.clear();
        for (int i = 1; i < relativeGroup.getChildCount(); i++) {
            numbers.add(String.valueOf(i));
        }

        Collections.shuffle(numbers);

    }

    private void loadView() {
        /* winUserText=findViewById(R.id.winUserText);*/
        // helpGame = findViewById(R.id.helpGameNormal);
        //menuOpen = findViewById(R.id.menuOpen);
        emptyLayout = findViewById(R.id.emptyLayoutHard);
        replayGame = findViewById(R.id.replayGameHard);
        pauseGame = findViewById(R.id.pauseGameHard);
        /*playGame = findViewById(R.id.playGame);*/
        timeGame = findViewById(R.id.timeGameHard);
        relativeGroup = findViewById(R.id.relativeLayoutHard);
        for (int i = 0; i < relativeGroup.getChildCount(); i++) {
            TextView textButton = (TextView) relativeGroup.getChildAt(i);
            buttons[i / 5][i % 5] = textButton;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        String[] texts = new String[25];
        for (int i = 0; i < 25; i++) {
            TextView child = (TextView) relativeGroup.getChildAt(i);
            String text = child.getText().toString();
            Log.d("TAGtext", "onSaveInstanceState: " + text);
            texts[i] = text;

        }
        outState.getBoolean(Key_isStop, isStop);

        outState.putInt(Key_Time, timerCount);
        outState.putInt(Key_EmptyI, emptyI);
        outState.putInt(Key_EmptyJ, emptyJ);
        outState.putStringArray(Key_Number, texts);

        super.onSaveInstanceState(outState);

    }

    private final String Key_Time = "time";
    private final String Key_isStop = "isStop";
    private final String Key_Number = "number";

    private final String Key_EmptyI = "emptyI";
    private final String Key_EmptyJ = "emptyJ";
}