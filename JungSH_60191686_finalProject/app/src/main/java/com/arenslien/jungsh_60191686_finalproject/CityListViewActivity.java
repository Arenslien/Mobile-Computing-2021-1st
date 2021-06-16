package com.arenslien.jungsh_60191686_finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CityListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list_view);
        setTitle("지역 선택");

        final String[] cities = {
                "서울", "부산", "대구", "인천", "광주",
                "대전", "울산", "경기", "강원", "충북",
                "충남", "전북", "전남", "경북", "경남", "제주", "세종"
        };

        ListView list = (ListView) findViewById(R.id.locationListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String key = "Hhm2I7CQQjOyWV0c0GN815XLXfUm68GagTexgRLp9emqiuvPvFDjAfOBiCLCo1pIqMW8LyfBq1h2lPy9fHi9ig%3D%3D";
//                String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=" + cities[i] + "&pageNo=1&numOfRows=100&returnType=xml&serviceKey=" + key + "&ver=1.0";

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("SelectedCityName", cities[i]);
                startActivity(intent);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 선택한 지역이 이미 즐겨찾기 되어있는지 체크하기
                if(checkBeforeAdd(cities[i])) {
                    addFavoriteLocation(cities[i]);
                    Toast.makeText(getApplicationContext(), "지역 "+ cities[i] +"(이)가 즐겨찾기 되었습니다.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("SelectedCityName", cities[i]);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "이미 즐겨찾기된 지역입니다.", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    protected void addFavoriteLocation(String locationName) {
        String locationStr = getTxt() + locationName + ",";
        try {
            FileOutputStream fos = openFileOutput("location.txt", Context.MODE_PRIVATE);
            fos.write(locationStr.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String getTxt() {
        try {
            FileInputStream fis = openFileInput("location.txt");
            byte[] txt = new byte[500];
            fis.read(txt);
            String locationStr = new String(txt).trim();
            fis.close();
            return locationStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    protected String[] txtToArray(String txt) {
        String[] result = {};
        result = txt.split(",");
        return result;
    }

    protected String arrayToTxt(String[] array) {
        String txt = "";
        for(int i=0; i<array.length; i++) {
            txt = txt + array[i] + ",";
        }
        return txt;
    }

    protected boolean checkBeforeAdd(String item) {
        String[] array = txtToArray(getTxt());
        for (int i=0; i<array.length; i++) {
            if (array[i].equals(item)) {
                return false;
            }
        }
        return true;
    }
}
