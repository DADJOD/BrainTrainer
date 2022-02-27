package com.example.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class FinalActivity extends Activity {
    private TextView textViewResultYour, textViewResultRecord, textViewResultFinal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_layout);

        textViewResultYour = findViewById(R.id.textViewResultYour);
        textViewResultRecord = findViewById(R.id.textViewResultRecord);
        textViewResultFinal = findViewById(R.id.textViewResultFinal);

        String result = getIntent().getStringExtra("result");
        textViewResultYour.setText(result);
    }

    public void onClickGoBack(View view) {
        Intent intent = new Intent(FinalActivity.this, StartActivity.class);
        startActivity(intent);
    }
}
