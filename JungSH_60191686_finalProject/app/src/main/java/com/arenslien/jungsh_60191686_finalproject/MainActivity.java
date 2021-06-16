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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 이미지, 상태, 색상
    String[] states = {"좋음", "보통", "나쁨", "매우나쁨"};
    int[] faceImages = {R.drawable.smile, R.drawable.soso, R.drawable.sad, R.drawable.angry};
    String[] colors = {"#aaccff", "#aaffaa", "#FFAF71", "#dd5a5a"};

    // 여러 위젯 선언
    TextView locationTxt, statusTxt, dustTxt1, dustTxt2;
    ImageView faceImg;
    LinearLayout mainLayout;

    // 현재 위치에 대한 좌표 정보
    double latitude = 0;
    double longitude = 0;

    // API 관련 변수
    String key = "Hhm2I7CQQjOyWV0c0GN815XLXfUm68GagTexgRLp9emqiuvPvFDjAfOBiCLCo1pIqMW8LyfBq1h2lPy9fHi9ig%3D%3D";
    String returnType = "xml";
    String numOfRows = "1";
    String pageNo = "1";
    String addr = "";
//    final String[] result = {""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미세먼지 정보 조회");

        // 1. 위치 정보 권한 설정
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        // 2. 여러 위젯 연결
        locationTxt = (TextView) findViewById(R.id.locationTxt);
        faceImg = (ImageView) findViewById(R.id.faceImg);
        statusTxt = (TextView) findViewById(R.id.statusTxt);
        dustTxt1 = (TextView) findViewById(R.id.dustTxt1);
        dustTxt2 = (TextView) findViewById(R.id.dustTxt2);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        // 3. 현재 위치 정보 가져오기
        setLatitude();
        setLongitude();
        addr = getLocationName(latitude, longitude);

        // 4. 인텐트 가져오기
        Intent intent = getIntent();
        String select = intent.getStringExtra("SelectedCityName");
        String favorite = intent.getStringExtra("FavoriteCityName");

        // 5. 만약 인텐트 정보가 있다면 인텐트 정보에 대해서 미세먼지 보여주기
        // 없다면 현재 위치 정보에 대한 미세먼지 정보 보여주기
        if (select != null) {
            setDustInformation(select, 77, 36);
        } else if (favorite != null) {
            setDustInformation(favorite, 32, 17);
        } else {
            setDustInformation(addr, 13, 8);
        }

        // 현재 지역 주변의 측정소 데이터 가져오기
        // getCurrentLocationInfo();
    }

    private void setDustInformation(String locationName, double dust1, double dust2) {
        // 미세먼지, 초미세먼지, 측정소 주소 데이터 가져오기
        String location = locationName;
        int stateI = 0;
        double dustValue1 = dust1;
        double dustValue2 = dust2;

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
        faceImg.setImageResource(faceImages[stateI]);
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
                Intent intent2 = new Intent(getApplicationContext(), FavoriteLocationListViewActivity.class);
                startActivity(intent2);
                return true;
            case R.id.currentLocationMenu:
                setLatitude();
                setLongitude();
                addr = getLocationName(latitude, longitude);
                // 현재 위치에 대한 정보로 변경
                setDustInformation(addr, 13, 8);
                Toast.makeText(getApplicationContext(), "위도: " + latitude + " 경도: " + longitude, Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    private String getLocationName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(MainActivity.this);
        String result = "";
        List<Address> list = null;
        try {
            list = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list.get(0).getLocality() != null) {
            result = list.get(0).getLocality();
        } else if (list.get(0).getThoroughfare() != null) {
            result = list.get(0).getThoroughfare();
        }
        return result;
    }

    private void setLatitude() {
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        latitude = location.getLatitude();
    }

    private void setLongitude() {
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
    }

//    private void requestAPI() {
//        String uri = "http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getMsrstnList?serviceKey=" + key + "&returnType=" + returnType + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&addr=" + addr;
//
//        // ...
//
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        result[0] = "Response is: "+ response.substring(0,500);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                result[0] = "That didn't work!";
//            }
//        });
//
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }
}