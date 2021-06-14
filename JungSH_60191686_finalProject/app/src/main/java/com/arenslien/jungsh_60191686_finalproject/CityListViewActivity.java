package com.arenslien.jungsh_60191686_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
                String key = "Hhm2I7CQQjOyWV0c0GN815XLXfUm68GagTexgRLp9emqiuvPvFDjAfOBiCLCo1pIqMW8LyfBq1h2lPy9fHi9ig%3D%3D";
                String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=" + cities[i] + "&pageNo=1&numOfRows=100&returnType=xml&serviceKey=" + key + "&ver=1.0";

                Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                intent.putExtra("SeletedCityName", cities[i]);
                startActivity(intent);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 길게 누른 아이템 txt 파일에 저장

                Toast.makeText(getApplicationContext(), cities[i] + "이(가) 즐겨찾기에 등록되었습니다.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
