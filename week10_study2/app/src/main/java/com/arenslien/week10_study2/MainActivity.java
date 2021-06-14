package com.arenslien.week10_study2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btnPrev, btnNext;
    myPictureView myPictureView;
    int curNum = 1;
    File[] imageFiles;
    String imageFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Simple Image Viewer");

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        myPictureView = (myPictureView) findViewById(R.id.myPictureView);

        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures").listFiles();

        imageFname = imageFiles[1].toString();

        myPictureView.imagePath = imageFname;

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum >= 5) {
                    Toast.makeText(getApplicationContext(), "마지막 사진입니다", Toast.LENGTH_SHORT).show();
                } else {
                    curNum++;
                    imageFname = imageFiles[curNum].toString();
                    myPictureView.imagePath = imageFname;
                    myPictureView.invalidate();
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum <= 1) {
                    Toast.makeText(getApplicationContext(), "첫 번째 사진입니다", Toast.LENGTH_SHORT).show();
                } else {
                    curNum--;
                    imageFname = imageFiles[curNum].toString();
                    myPictureView.imagePath = imageFname;
                    myPictureView.invalidate();
                }
            }
        });
    }
}