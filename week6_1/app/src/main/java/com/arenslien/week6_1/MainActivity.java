package com.arenslien.week6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RatingBar ratingBar1;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRatingBar();
    }

    public void setupRatingBar() {
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar);
        textView = (TextView) findViewById(R.id.textView);

        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                textView.setText(Float.toString(v));
            }
        });
    }
}