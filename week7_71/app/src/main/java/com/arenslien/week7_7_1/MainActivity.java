package com.arenslien.week7_7_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("정성훈 과제3 메뉴 활용");

        editText = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.cat:
                imageView.setImageResource(R.drawable.cat);
                return true;
            case R.id.dog:
                imageView.setImageResource(R.drawable.dog);
                return true;
            case R.id.turtle:
                imageView.setImageResource(R.drawable.turtle);
                return true;
            case R.id.rotate:
                if(Character.isDigit(Integer.parseInt(editText.getText().toString()))) {
                    imageView.setRotation(Integer.parseInt(editText.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            default: return false;
        }
    }
}