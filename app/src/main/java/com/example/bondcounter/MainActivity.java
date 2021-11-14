package com.example.bondcounter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Calendar dateAndTime = Calendar.getInstance();
    long dateAndTime_b = 0;
    long dateAndTime_e = 0;
    DatePickerDialog.OnDateSetListener d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et_nkd = findViewById(R.id.nkd_text);
        EditText et_commission = findViewById(R.id.commission_text);
        EditText et_commission_acc_mng = findViewById(R.id.commission_acc_mng_text);
        EditText et_date_repayment = findViewById(R.id.date_repayment_text);
        EditText et_coupon_size = findViewById(R.id.coupon_size_text);
        EditText et_start_value = findViewById(R.id.start_value_text);
        EditText et_nominal = findViewById(R.id.nominal_text);
        EditText et_coefficient = findViewById(R.id.coefficient_text);
        EditText et_coupon_frequency = findViewById(R.id.coupon_frequency_text);
        Button count = findViewById(R.id.bt_count);

        dateAndTime_b = Calendar.getInstance().getTimeInMillis();

        et_date_repayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    dateAndTime_e = dateAndTime.getTimeInMillis();
                    et_date_repayment.setText(sd);

            }
        };
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    long milliseconds1 = dateAndTime_e;
                    long milliseconds2 = dateAndTime_b;
                    long delta = milliseconds1 - milliseconds2;
                    int days = (int) (TimeUnit.MILLISECONDS.toDays(delta) + 1);
                    double start_value = Double.parseDouble(et_start_value.getText().toString());
                    int coefficient = Integer.parseInt(et_coefficient.getText().toString());
                    double nkd = Double.parseDouble(et_nkd.getText().toString());
                    double commission = Double.parseDouble(et_commission.getText().toString());
                    double broker_commission = Double.parseDouble(et_commission_acc_mng.getText().toString());
                    double coupon_size = Double.parseDouble(et_coupon_size.getText().toString());
                    double nominal = Double.parseDouble(et_nominal.getText().toString());
                    double coupon_frequency = Double.parseDouble(et_coupon_frequency.getText().toString());
                    double buy = (coefficient * (start_value + nkd + (start_value + nkd) * commission) + broker_commission);
                    double sell = (nominal + coupon_size * (days / coupon_frequency)) * coefficient;
                    double profit = sell - buy;
                    Intent intent = new Intent(MainActivity.this, Show_Screen.class);
                    intent.putExtra("Result", profit);
                    startActivity(intent);
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, getString(R.string.exception), Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}