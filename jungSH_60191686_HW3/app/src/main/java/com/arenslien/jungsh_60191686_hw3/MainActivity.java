package com.arenslien.jungsh_60191686_hw3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
                try {
                    imageView.setRotation(Integer.parseInt(editText.getText().toString()));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "숫자를 제대로 입력해주세요",Toast.LENGTH_SHORT).show();
                }

                return true;

            default: return false;
        }
    }
}