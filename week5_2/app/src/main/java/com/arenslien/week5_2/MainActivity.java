package com.arenslien.week5_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button btnReservation, btnEnd;
    RadioButton radioButtonCalendar, radioButtonTime;
    CalendarView calendarView;
    TimePicker timePicker;
    TextView textViewYear, textViewMonth, textViewDay, textViewHour, textViewMinute;

    int selYear, selMonth, selDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("정성훈_과제2");

        chronometer = (Chronometer) findViewById(R.id.chronometer1);
        btnReservation = (Button) findViewById(R.id.btnReservation);
        btnEnd = (Button) findViewById(R.id.btnEnd);
        radioButtonCalendar = (RadioButton) findViewById(R.id.radioBtnCalendar);
        radioButtonTime = (RadioButton) findViewById(R.id.radioBtnTime);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        textViewYear = (TextView) findViewById(R.id.textViewYear);
        textViewMonth = (TextView) findViewById(R.id.textViewMonth);
        textViewDay = (TextView) findViewById(R.id.textViewDay);
        textViewHour = (TextView) findViewById(R.id.textViewHour);
        textViewMinute = (TextView) findViewById(R.id.textViewMinute);

        calendarView.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);

        radioButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButtonCalendar.isChecked()) {
                    calendarView.setVisibility(View.VISIBLE);
                    timePicker.setVisibility(View.INVISIBLE);
                }
            }
        });

        radioButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButtonTime.isChecked()) {
                    timePicker.setVisibility(View.VISIBLE);
                    calendarView.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                chronometer.setTextColor(Color.BLUE);

                textViewYear.setText(Integer.toString(selYear));
                textViewMonth.setText(Integer.toString(selMonth+1));
                textViewDay.setText(Integer.toString(selDay));
                textViewHour.setText(Integer.toString(timePicker.getCurrentHour()));
                textViewMinute.setText(Integer.toString(timePicker.getCurrentMinute()));
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                selYear = year;
                selMonth = month;
                selDay = day;
            }
        });
    }
}