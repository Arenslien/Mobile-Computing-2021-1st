package com.arenslien.week10_study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnRead, btnMkdir, btnRmdir, btnFileList;
    EditText edtSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnMkdir = (Button) findViewById(R.id.btnMkdir);
        btnRmdir = (Button) findViewById(R.id.btnRmdir);
        btnFileList = (Button) findViewById(R.id.btnFileList);
        edtSD = (EditText) findViewById(R.id.edtSD);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        // 현재 디렉토리
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File myDir = new File(strSDpath + "/myfile");

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream inFs = new FileInputStream("/sdcard/save_file.txt");

                    byte[] txt = new byte[inFs.available()];
                    inFs.read(txt);
                    edtSD.setText(new String(txt));
                    inFs.close();
                } catch (IOException e) {

                }
            }
        });

        btnMkdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDir.mkdir();
            }
        });

        btnRmdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDir.delete();
            }
        });

        btnFileList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sysDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                File[] sysFiles = new File(sysDir).listFiles();

                edtSD.setText("");
                String strFname;
                for(int i=0; i<sysFiles.length; i++) {
                    if(sysFiles[i].isDirectory()) {
                        strFname = "<폴더> " + sysFiles[i].toString();
                    } else {
                        strFname = "<폴더> " + sysFiles[i].toString();
                    }
                    edtSD.setText(edtSD.getText() + "\n" + strFname);
                }
            }
        });
    }
}