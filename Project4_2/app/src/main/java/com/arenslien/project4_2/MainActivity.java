package com.arenslien.project4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    CheckBox startCheckBox;
    TextView text1;
    RadioGroup radioGroup;
    RadioButton dogRadioButton, catRadioButton, turtleRadioButton;
    Button selectButton;
    ImageView petImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물 사진 보기");

        startCheckBox = (CheckBox) findViewById(R.id.checkbox1);
        text1 = (TextView) findViewById(R.id.text1);
        radioGroup = (RadioGroup) findViewById(R.id.RGroup);
        dogRadioButton = (RadioButton) findViewById(R.id.dogRbtn);
        catRadioButton = (RadioButton) findViewById(R.id.catRbtn);
        turtleRadioButton = (RadioButton) findViewById(R.id.turtleRbtn);
        selectButton = (Button) findViewById(R.id.selectBtn);
        petImageView = (ImageView) findViewById(R.id.petImageView);


        startCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(startCheckBox.isChecked()) {
                    text1.setVisibility(View.VISIBLE);
                    radioGroup.setVisibility(View.VISIBLE);
                    selectButton.setVisibility(View.VISIBLE);
                    petImageView.setVisibility(View.VISIBLE);
                } else {
                    text1.setVisibility(View.INVISIBLE);
                    radioGroup.setVisibility(View.INVISIBLE);
                    selectButton.setVisibility(View.INVISIBLE);
                    petImageView.setVisibility(View.INVISIBLE);
                }
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.dogRbtn:
                        petImageView.setImageResource(R.drawable.dog); break;
                    case R.id.catRbtn:
                        petImageView.setImageResource(R.drawable.cat); break;
                    case R.id.turtleRbtn:
                        petImageView.setImageResource(R.drawable.turtle); break;
                    default:
                        Toast.makeText(getApplicationContext(), "동물을 먼저 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}