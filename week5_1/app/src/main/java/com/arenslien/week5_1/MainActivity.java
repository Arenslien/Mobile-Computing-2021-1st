package com.arenslien.week5_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class BtnClickListener implements View.OnClickListener {
        int num;
        BtnClickListener(int num) {
            this.num = num;
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "버튼 "+ num +"이 눌렸습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton imgBtn = (ImageButton) findViewById(R.id.imageButton);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);

        BtnClickListener btnClickListener2 = new BtnClickListener(2);
        btn2.setOnClickListener(btnClickListener2);

        BtnClickListener btnClickListener3 = new BtnClickListener(3);
        btn3.setOnClickListener(btnClickListener3);
    }


    public void btn1ClickListener(View v) {
        Toast.makeText(getApplicationContext(), "버튼 1이 눌렸습니다.", Toast.LENGTH_SHORT).show();
    }
}