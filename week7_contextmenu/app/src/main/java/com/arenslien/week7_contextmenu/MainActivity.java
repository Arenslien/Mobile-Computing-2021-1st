package com.arenslien.week7_contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("컨텍스트 메뉴 보기");

        text = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);

        registerForContextMenu(text);
        registerForContextMenu(button);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        if (v == text) {
            menuInflater.inflate(R.menu.textmenu, menu);
        }
        if (v == button) {
            menuInflater.inflate(R.menu.buttonmenu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()) {
            case R.id.itemBlue:
                text.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.itemRed:
                text.setBackgroundColor(Color.RED);
                return true;
            case R.id.itemGreen:
                text.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.subRotate:
                button.setRotation(45);
                return true;
            case R.id.subSize:
                button.setScaleX(2);
                button.setScaleY(2);
                return true;
            default: return false;
        }
    }
}