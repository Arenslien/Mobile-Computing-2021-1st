package com.arenslien.computation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button btnAdd, btnMinus, btnMultiply, btnDiv;
    TextView textView;
    String num1, num2;
    Integer result;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단한 계산기");

        edit1 = (EditText) findViewById(R.id.inputNum1);
        edit2 = (EditText) findViewById(R.id.inputNum2);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        textView = (TextView) findViewById(R.id.result);

        btnAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = Integer.parseInt(num1) + Integer.parseInt(num2);
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "숫자가 바르게 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                textView.setText("계산 결과: " + result.toString());
                return true;
            }
        });

        btnMinus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = Integer.parseInt(num1) - Integer.parseInt(num2);
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "숫자가 바르게 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                textView.setText("계산 결과: " + result.toString());
                return true;
            }
        });

        btnMultiply.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = Integer.parseInt(num1) * Integer.parseInt(num2);
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "숫자가 바르게 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                textView.setText("계산 결과: " + result.toString());
                return true;
            }
        });

        btnDiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = Integer.parseInt(num1) / Integer.parseInt(num2);
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "숫자가 바르게 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }

                textView.setText("계산 결과: " + result.toString());
                return true;
            }
        });
    }
}