package com.example.braintrainer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private TextView textViewCalculation, textViewResult;
    private ActionBar actionBar;
    private int number1, number2, numberResult, numberFalse, min = 0, max = 20; //можно вместо max min переделать на random int
    private final Random random = new Random();
    private long startTime, currentTime;
    private float timeResult;
    private int counterOfTrueAnswers;
    private boolean isTrueAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        startTime = System.currentTimeMillis();
        preferences = getSharedPreferences("Test", MODE_PRIVATE);
        textViewCalculation = findViewById(R.id.textViewCalculation);
        textViewResult = findViewById(R.id.textViewResult);
        textViewResult.setText("");
        actionBar = getSupportActionBar();
        generateNumbers();
    }

    private void generateNumbers() {
        number1 = (int) (Math.random() * (max + min));
        number2 = (int) (Math.random() * (max + min));
        numberFalse = (int) (Math.random() * (max + min));
        calcProcess();
    }

    private void calcProcess() {
        numberResult = number1 + number2;
        String result;

        if (counterOfTrueAnswers == 9) {
            Intent intent = new Intent(MainActivity.this, FinalActivity.class);
            intent.putExtra("result", actionBar.getTitle().toString());
            startActivity(intent);
        }

        if (random.nextBoolean()) {
            result = number1 + " + " + number2 + " = " + numberResult;
            isTrueAnswer = true;
        } else {
            result = number1 + " + " + number2 + " = " + random.nextInt(40);
            isTrueAnswer = false;
        }
        textViewCalculation.setText(result);
    }

    @SuppressLint("SetTextI18n")
    public void onClickTrue(View view) {
        if (isTrueAnswer) {
            processInClick();
            counterOfTrueAnswers++;
        } else {
            processInClick();
        }
        textViewResult.setText("Правильных ответов: " + counterOfTrueAnswers);
    }

    @SuppressLint("SetTextI18n")
    public void onClickFalse(View view) {
        if (!isTrueAnswer) {
            processInClick();
            counterOfTrueAnswers++;
        } else {
            processInClick();
        }
        textViewResult.setText("Правильных ответов: " + counterOfTrueAnswers);
    }

    private void processInClick() {
        generateNumbers();
        currentTime = System.currentTimeMillis();
        timeResult = (float) (currentTime - startTime) / 1_000;
        String returnTimeToActionBar = "Ваше время " + timeResult + "cек";
        actionBar.setTitle(returnTimeToActionBar);
    }
}