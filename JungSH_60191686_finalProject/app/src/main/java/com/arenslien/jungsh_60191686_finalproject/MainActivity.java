package com.arenslien.jungsh_60191686_finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 기본 버튼
    LocationManager lm;
    LinearLayout mainLayout;
    TextView locationTxt, statusTxt, dustTxt1, dustTxt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미세먼지 정보 조회");

        // 위치 정보 권한 설정
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        // 텍스트뷰 연결
        locationTxt = (TextView) findViewById(R.id.locationTxt);
        statusTxt = (TextView) findViewById(R.id.statusTxt);
        dustTxt1 = (TextView) findViewById(R.id.dustTxt1);
        dustTxt2 = (TextView) findViewById(R.id.dustTxt2);

        // 레이아웃 연결
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        // 이미지, 상태, 색상
        String[] states = {"좋음", "보통", "나쁨", "매우나쁨"};
        int[] imgs = {R.drawable.smile, R.drawable.soso, R.drawable.sad, R.drawable.angry};
        String[] colors = {"#aaccff", "#aaffaa", "#FFAF71", "#dd5a5a"};

        // 현재 지역 주변의 측정소 데이터 가져오기
        // getCurrentLocationInfo();

        // 미세먼지, 초미세먼지, 측정소 주소 데이터 가져오기
        double dustValue1 = 0;
        double dustValue2 = 0;
        String location = "김포시 사우동";
        int stateI = 0;

        if (dustValue2 < 15) {
            stateI = 0;
        } else if (dustValue2 < 35) {
            stateI = 1;
        } else if (dustValue2 < 75) {
            stateI = 2;
        } else {
            stateI = 3;
        }

        // 미세먼지 정보 세팅
        locationTxt.setText(location);
        statusTxt.setText(states[stateI]);
        dustTxt1.setText(Double.toString(dustValue1));
        dustTxt2.setText(Double.toString(dustValue2));
        mainLayout.setBackgroundColor(Color.parseColor(colors[stateI]));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.selectLocationMenu:
                Intent intent = new Intent(getApplicationContext(), CityListViewActivity.class);
                startActivity(intent);
                return true;
            case R.id.favoriteLocationMenu:
                return true;
            case R.id.currentLocationMenu:
                // GPS Tracker 통해 위도 경도 가져오기
                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                @SuppressLint("MissingPermission")
                Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

                Toast.makeText(getApplicationContext(), getLocationName(latitude, longitude), Toast.LENGTH_LONG).show();

                Intent intent2 = new Intent(getApplicationContext(), InformationActivity.class);
//                startActivity(intent2);
                return true;
        }
        return false;
    }

    private String getLocationName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(MainActivity.this);
        List<Address> list = null;
        try {
            list = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.get(0).toString();
    }
}