package com.arenslien.project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView[] text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("투표 결과");

        Intent intent = getIntent();
        String[] imgName =intent.getStringArrayExtra("ImageName");
        int[] voteResult = intent.getIntArrayExtra("VoteCount");

        //
        TextView text[] = new TextView[imgName.length];
        RatingBar rating[] = new RatingBar[imgName.length];

        Integer textId[] = {
                R.id.txt1, R.id.txt2, R.id.txt3,
                R.id.txt4, R.id.txt5, R.id.txt6,
                R.id.txt7, R.id.txt8, R.id.txt9
        };
        Integer ratingId[] = {
                R.id.ratingBar1, R.id.ratingBar2, R.id.ratingBar3,
                R.id.ratingBar4, R.id.ratingBar5, R.id.ratingBar6,
                R.id.ratingBar7, R.id.ratingBar8, R.id.ratingBar9
        };



        for(int i=0; i<imgName.length; i++) {
            text[i] = (TextView) findViewById(textId[i]);
            text[i].setText(imgName[i]);
            rating[i] = (RatingBar) findViewById(ratingId[i]);
            rating[i].setRating((float) voteResult[i]);
        }

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}