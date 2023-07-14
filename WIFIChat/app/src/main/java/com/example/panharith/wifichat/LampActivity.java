package com.example.panharith.wifichat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class LampActivity extends AppCompatActivity
       // implements View.OnClickListener
{

    Button btnDatePicker, btnTimePicker , btnTimePickerStop;
    TextView txtDate, txtTime , txtTimeStop;
    ImageView imageView;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);
/*
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        btnTimePickerStop =(Button) findViewById(R.id.btn_stop_time) ;

        txtDate=(TextView) findViewById(R.id.in_date);
        txtTime=(TextView) findViewById(R.id.in_time);
        txtTimeStop =(TextView) findViewById(R.id.set_time_stop);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnTimePickerStop.setOnClickListener(this);

        ImageView imageView = (ImageView) findViewById(R.id.ic_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LampActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        */

    }



}