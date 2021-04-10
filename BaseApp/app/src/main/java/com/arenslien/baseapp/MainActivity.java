package com.arenslien.baseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button myButton1;
    Button myButton2;
    Button myButton3;
    Button myButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton1 = (Button) findViewById(R.id.btn1);
        myButton2 = (Button) findViewById(R.id.btn2);
        myButton3 = (Button) findViewById(R.id.btn3);
        myButton4 = (Button) findViewById(R.id.btn4);


        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent internetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"));
                startActivity(internetIntent);
            }
        });

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/1399"));
                startActivity(callIntent);
            }
        });

        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(galleryIntent);
            }
        });

        myButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "종료되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
