package com.arenslien.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.pici_icon);
        setTitle("영화 선호도 투표");

        Button btnResult = (Button) findViewById(R.id.btnResult);

        //
        ImageView image[] = new ImageView[9];
        Integer imgId[] = {
                R.id.iv1, R.id.iv2, R.id.iv3,
                R.id.iv4, R.id.iv5, R.id.iv6,
                R.id.iv7, R.id.iv8, R.id.iv9
        };

        // 그림 제목 배열 선언
        final String imgName[] = {
                "독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
                "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매",
                "피아노 레슨"," 피아노 앞의 소녀들", "해변에서"
        };

        // 9개 그림에 대한 투표스 기록 배열 선언
        final int voteCnt[] = new int[9];
        for (int i=0; i< voteCnt.length; i++) voteCnt[i] = 0;

        for (int i=0; i<imgId.length; i++) {
            final int index = i;
            image[i] = (ImageView) findViewById(imgId[i]);

            image[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCnt[index]++;
                    String msg = imgName[index] + voteCnt[index] + " 표";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("ImageName", imgName);
                intent.putExtra("VoteCount", voteCnt);
                startActivity(intent);
            }
        });

    }
}