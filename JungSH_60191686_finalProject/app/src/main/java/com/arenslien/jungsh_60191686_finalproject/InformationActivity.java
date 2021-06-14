package com.arenslien.jungsh_60191686_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class InformationActivity extends Activity {

    TextView locationTxt, statusTxt, dustTxt1, dustTxt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        setTitle("지역 미세먼지 정보");

        locationTxt = (TextView) findViewById(R.id.locationTxt);
        statusTxt = (TextView) findViewById(R.id.statusTxt);
        dustTxt1 = (TextView) findViewById(R.id.dustTxt1);
        dustTxt2 = (TextView) findViewById(R.id.dustTxt2);

        Intent intent = getIntent();
        String city = intent.getStringExtra("SeletedCityName");
        locationTxt.setText(city);
    }
}
