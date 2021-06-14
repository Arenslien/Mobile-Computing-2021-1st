package com.arenslien.week9_study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // 변수 선언
    DatePicker datePicker;
    EditText diary;
    Button saveBtn;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단한 일기장");

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        diary = (EditText) findViewById(R.id.editText);
        saveBtn = (Button) findViewById(R.id.btnWrite);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        // Date Picker 초기화
        datePicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                fileName = year + "_" + month + "_" + day + ".txt";

                String str = readDiary(fileName);
                diary.setText(str);
                saveBtn.setEnabled(true);
            }
        });

        // Add Listener to button
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = diary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + " 이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {

                }
            }
        });
    }

    String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;

        try {
            inFs = openFileInput(fName);
            byte[] txt = new byte[1024];
            inFs.read(txt);
            inFs.close();

            diaryStr = (new String(txt)).trim();
            saveBtn.setText("수정하기");
        } catch (IOException e) {
            diary.setHint("일기 없음");
            saveBtn.setText("새로 저장");
        }
        return diaryStr;
    }

}