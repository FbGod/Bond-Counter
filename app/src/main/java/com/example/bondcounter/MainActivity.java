package com.example.bondcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Calendar dateAndTime = Calendar.getInstance();
    long dateAndTime_b = 0;
    long dateAndTime_e = 0;
    DatePickerDialog.OnDateSetListener d;
    double year_profit = 0;
    double nkd = 0;
    double commission = 0;
    double commission_acc_mng = 0;
    double date_repayment = 0;
    double coupon_size = 0;
    double buy_date = 0;
    int i = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et_year_profit = findViewById(R.id.year_profit_text);
        EditText et_nkd = findViewById(R.id.nkd_text);
        EditText et_commission = findViewById(R.id.commission_text);
        EditText et_commission_acc_mng = findViewById(R.id.commission_acc_mng_text);
        EditText et_date_repayment = findViewById(R.id.date_repayment_text);
        EditText et_coupon_size = findViewById(R.id.coupon_size_text);
        EditText et_buy_date = findViewById(R.id.buy_date_text);
        Button count = findViewById(R.id.bt_count);
        et_buy_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;
                new DatePickerDialog(MainActivity.this, d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH)).show();

            }

        });
        et_date_repayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 1;
                new DatePickerDialog(MainActivity.this, d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH)).show();

            }

        });

        // установка обработчика выбора даты
        d = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setInitialDateTime();
            }

            private void setInitialDateTime() {
                String sd = (String.format(String.format(DateUtils.formatDateTime(MainActivity.this,
                        dateAndTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR) + ", " + DateFormat.format("EEEE", dateAndTime))));
                Toast.makeText(MainActivity.this, sd, Toast.LENGTH_LONG).show();
                if (i == 0){
                    dateAndTime_b = dateAndTime.getTimeInMillis();
                    et_buy_date.setText(sd);
                }
                if (i == 1){
                    dateAndTime_e = dateAndTime.getTimeInMillis();
                    et_date_repayment.setText(sd);
                }
            }
        };
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long milliseconds1 = dateAndTime_e;
                long milliseconds2 = dateAndTime_b;
                long delta = milliseconds1 - milliseconds2;
                Toast.makeText(MainActivity.this, String.valueOf(milliseconds1) + "  " + String.valueOf(milliseconds2) + "  " + String.valueOf(delta), Toast.LENGTH_LONG).show();
            }

        });


    }
}