package com.example.bondcounter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Show_Screen extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_screen);
        double prf2 = getIntent().getDoubleExtra("Result", 0);
        TextView tv_result = findViewById(R.id.tv_result);
        TextView tv_result_p = findViewById(R.id.tv_result_p);
        if (prf2 > 0){
            tv_result.setText(R.string.profit);
            tv_result_p.setTextColor(Color.GREEN);
        }
        if (prf2 < 0){
            tv_result.setText(R.string.fail);
            tv_result_p.setTextColor(Color.RED);
        }
        if (prf2 == 0){
            tv_result.setText(R.string.profit);
            tv_result_p.setTextColor(Color.WHITE);
        }
        tv_result_p.setText(String.format("%.3f",prf2));
    }
}