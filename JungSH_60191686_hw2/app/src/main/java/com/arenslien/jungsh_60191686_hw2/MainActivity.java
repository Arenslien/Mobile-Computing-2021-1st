package com.arenslien.jungsh_60191686_hw2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    AutoCompleteTextView autoCompleteTextView;
    Button btnResearch;
    RadioButton radioButtonDate, radioButtonTime;
    DatePicker datePicker;
    TimePicker timePicker;
    TextView textViewCategory, textViewReservation, textViewYear, textViewMonth, textViewDay, textViewHour, textViewMinute;
    int selYear, selMonth, selDay, selHour, selMinute;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("정성훈 과제2 실습 + autoCompleteTextView");

        // 위젯 기본 설정
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        btnResearch = (Button) findViewById(R.id.btnResearch);
        radioButtonDate = (RadioButton) findViewById(R.id.radioBtnDate);
        radioButtonTime = (RadioButton) findViewById(R.id.radioBtnTime);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        textViewCategory = (TextView) findViewById(R.id.textViewCategory);
        textViewReservation = (TextView) findViewById(R.id.textViewReservation);
        textViewYear = (TextView) findViewById(R.id.textViewYear);
        textViewMonth = (TextView) findViewById(R.id.textViewMonth);
        textViewDay = (TextView) findViewById(R.id.textViewDay);
        textViewHour = (TextView) findViewById(R.id.textViewHour);
        textViewMinute = (TextView) findViewById(R.id.textViewMinute);

        // AutoCompleteTextView 관련 변수 설정
        String[] items = {"class", "study", "exam", "homework", "quiz", "meeting", "wake-up"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items);



        // Visible 설정
        textViewCategory.setVisibility(View.INVISIBLE);
        textViewReservation.setVisibility(View.INVISIBLE);
        autoCompleteTextView.setVisibility(View.GONE);
        btnResearch.setVisibility(View.GONE);
        radioButtonTime.setVisibility(View.INVISIBLE);
        radioButtonDate.setVisibility(View.INVISIBLE);
        datePicker.setVisibility(View.GONE);
        timePicker.setVisibility(View.GONE);

        // Chronometer 클릭 리스너 등록
        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
                radioButtonTime.setVisibility(View.VISIBLE);
                radioButtonDate.setVisibility(View.VISIBLE);
                textViewCategory.setVisibility(View.VISIBLE);
                autoCompleteTextView.setVisibility(View.VISIBLE);
            }
        });

        // AutoCompleteTextView 어댑터 등록
        autoCompleteTextView.setAdapter(adapter);

        // AutoCompleteTextView 리스너 등록
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                textViewReservation.setVisibility(View.VISIBLE);
                autoCompleteTextView.setVisibility(View.GONE);
                autoCompleteTextView.setText("");
                textViewReservation.setText(adapter.getItem(position).toString());
            }
        });

        // Button 클릭 리스너 등록
        btnResearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewReservation.setVisibility(View.GONE);
                btnResearch.setVisibility(View.GONE);
                autoCompleteTextView.setText("");
                autoCompleteTextView.setVisibility(View.VISIBLE);
            }
        });


        // RadioButton 클릭 리스너 등록
        radioButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.GONE);
            }
        });

        radioButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.GONE);
                timePicker.setVisibility(View.VISIBLE);
            }
        });

        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                selYear = year;
                selMonth = month;
                selDay = day;
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                selHour = hour;
                selMinute = minute;
            }
        });

        textViewYear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                chronometer.stop();
                chronometer.setTextColor(Color.BLUE);
                textViewCategory.setVisibility(View.INVISIBLE);
                autoCompleteTextView.setVisibility(View.INVISIBLE);
                radioButtonTime.setVisibility(View.INVISIBLE);
                radioButtonDate.setVisibility(View.INVISIBLE);
                radioButtonDate.setChecked(false);
                radioButtonTime.setChecked(false);
                datePicker.setVisibility(View.GONE);
                timePicker.setVisibility(View.GONE);
                textViewYear.setText(Integer.toString(selYear));
                textViewMonth.setText(Integer.toString(selMonth + 1));
                textViewDay.setText(Integer.toString(selDay));
                textViewHour.setText(Integer.toString(selHour));
                textViewMinute.setText(Integer.toString(selMinute));


                return true;
            }
        });
    }
}