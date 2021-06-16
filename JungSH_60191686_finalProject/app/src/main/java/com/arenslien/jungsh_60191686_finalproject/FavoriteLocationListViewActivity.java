package com.arenslien.jungsh_60191686_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;

public class FavoriteLocationListViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_location_list_view);

        // location.txt 파일 불러오기
        String[] favoriteLocations = getFavoriteLocation();

        // listView 연결
        ListView list = (ListView) findViewById(R.id.favoriteLocationListView);

        // Adapter 생성 & 연결
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, favoriteLocations);
        list.setAdapter(adapter);

        // List Item 선택에 대한 클릭 리스너
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // API KEY 와 Request URI
                String key = "Hhm2I7CQQjOyWV0c0GN815XLXfUm68GagTexgRLp9emqiuvPvFDjAfOBiCLCo1pIqMW8LyfBq1h2lPy9fHi9ig%3D%3D";
                String uri = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=" + favoriteLocations[i] + "&pageNo=1&numOfRows=100&returnType=xml&serviceKey=" + key + "&ver=1.0";

                // API 요청

                // Intent로 데이터 넘겨주기 -> 지역 이름, 미세먼지 상태, 미세먼지 수치, 초미세먼지 수치
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("FavoriteCityName", favoriteLocations[i]);
                startActivity(intent);
            }
        });
    }

    private String[] getFavoriteLocation() {
        String[] locations = {""};

        try {
            FileInputStream fileInputStream = openFileInput("location.txt");
            byte[] txt = new byte[500];
            fileInputStream.read(txt);
            String str = new String(txt).trim();
            fileInputStream.close();

            locations = str.split(",");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return locations;
    }


}
