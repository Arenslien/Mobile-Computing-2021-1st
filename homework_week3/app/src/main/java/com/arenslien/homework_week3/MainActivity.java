package com.arenslien.homework_week3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch startSwitch;
    TextView textView;
    RadioGroup radioGroup;
    RadioButton markersRadioButton, j_usRadioButton, weloveRadioButton;
    ImageView ccmTeamImageView;
    Button resetButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("60191686 정성훈 3주차 과제");

        startSwitch = (Switch) findViewById(R.id.startSwitch);
        textView = (TextView) findViewById(R.id.favoriteCCMTextView);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        markersRadioButton = (RadioButton) findViewById(R.id.radioButton1);
        j_usRadioButton = (RadioButton) findViewById(R.id.radioButton2);
        weloveRadioButton = (RadioButton) findViewById(R.id.radioButton3);
        ccmTeamImageView = (ImageView) findViewById(R.id.imageView);
        resetButton = (Button) findViewById(R.id.resetButton);
        exitButton = (Button) findViewById(R.id.exitButton);


        startSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(startSwitch.isChecked()) {
                    textView.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    ccmTeamImageView.setVisibility(View.VISIBLE);
                    resetButton.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    ccmTeamImageView.setVisibility(View.INVISIBLE);
                    resetButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        markersRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(markersRadioButton.isChecked()) {
                    ccmTeamImageView.setImageResource(R.drawable.markers);
                }
            }
        });

        j_usRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(j_usRadioButton.isChecked()) {
                    ccmTeamImageView.setImageResource(R.drawable.j_us);
                }
            }
        });

        weloveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(weloveRadioButton.isChecked()) {
                    ccmTeamImageView.setImageResource(R.drawable.welove);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                markersRadioButton.setChecked(false);
                j_usRadioButton.setChecked(false);
                weloveRadioButton.setChecked(false);
                startSwitch.setChecked(false);
                ccmTeamImageView.setImageResource(R.drawable.no_image);
                textView.setVisibility(View.INVISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
                ccmTeamImageView.setVisibility(View.INVISIBLE);
                resetButton.setVisibility(View.INVISIBLE);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}