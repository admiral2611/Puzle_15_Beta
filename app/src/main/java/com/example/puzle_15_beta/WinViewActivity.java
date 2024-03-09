package com.example.puzle_15_beta;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WinViewActivity extends AppCompatActivity {

    private TextView difficultWin, timeWin, stepWin;
    private EditText editText;
    private Button menuBack, menuSave;
    private Integer time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_view);
        loadView();
        loadToDataView();
        onClick();
    }

    private void onClick() {
        menuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WinViewActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        menuSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  int time1 = getIntent().getIntExtra("time", 0);
                Toast.makeText(WinViewActivity.this, "Save", Toast.LENGTH_SHORT).show();
                AppCache.getObject().saveTime(getIntent().getStringExtra("timeFormat"));

                if (!editText.getText().toString().isEmpty()){
                    AppCache.getObject().saveName(editText.getText().toString());
                    editText.setText("");

                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                /*trim bu textni oldi va orqasidagi bosh joylarni olib tashlaydi*/
                String text = charSequence.toString().trim();
                if (!text.isEmpty()){
                    Log.d("TAGeditor", "onTextChanged: "+text);
                }
                else {
                    text="";
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }

    private void loadToDataView() {
        timeWin.setText(getIntent().getStringExtra("timeFormat"));
        Integer temp = getIntent().getIntExtra("step", 0);
        stepWin.setText(temp.toString());

    }

    private void loadView() {
        editText=findViewById(R.id.textEdit);
        menuSave = findViewById(R.id.menuSave);
        stepWin = findViewById(R.id.stepWin);
        difficultWin = findViewById(R.id.dificulWin);
        timeWin = findViewById(R.id.timeWin);
        menuBack = findViewById(R.id.menuBack);
    }
}