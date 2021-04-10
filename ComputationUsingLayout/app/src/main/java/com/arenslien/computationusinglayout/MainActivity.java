package com.arenslien.computationusinglayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editNum1, editNum2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    Button[] btnNums = new Button[10];
    Integer[] numBtnIds = {
        R.id.Btn0,
        R.id.Btn1,
        R.id.Btn2,
        R.id.Btn3,
        R.id.Btn4,
        R.id.Btn5,
        R.id.Btn6,
        R.id.Btn7,
        R.id.Btn8,
        R.id.Btn9,
    };
    TextView textViewResult;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("테이블 레이아웃 계산기");

        editNum1 = (EditText) findViewById(R.id.EditNum1);
        editNum2 = (EditText) findViewById(R.id.EditNum2);
        btnAdd = (Button) findViewById(R.id.BtnAdd);
        btnSub = (Button) findViewById(R.id.BtnSub);
        btnMul = (Button) findViewById(R.id.BtnMul);
        btnDiv = (Button) findViewById(R.id.BtnDiv);
        textViewResult = (TextView) findViewById(R.id.TextViewResult);

        for (i=0; i<10; i++) {
            final int index;
            index = i;
            // Button 매칭
            btnNums[i] = (Button) findViewById(numBtnIds[i]);

            // Button eventListener 추가
            btnNums[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(editNum1.isFocused()) {
                        String num1 = editNum1.getText().toString() + btnNums[index].getText().toString();
                        editNum1.setText(num1);
                    } else if(editNum2.isFocused()) {
                        String num2 = editNum2.getText().toString() + btnNums[index].getText().toString();
                        editNum2.setText(num2);
                    } else {
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editNum1.getText().toString().isEmpty() || editNum2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "먼저 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    Integer result = (Integer.parseInt(editNum1.getText().toString()) + Integer.parseInt(editNum2.getText().toString()));
                    textViewResult.setText("계산 결과 : " + result.toString());
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editNum1.getText().toString().isEmpty() || editNum2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "먼저 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    Integer result = (Integer.parseInt(editNum1.getText().toString()) - Integer.parseInt(editNum2.getText().toString()));
                    textViewResult.setText("계산 결과 : " + result.toString());
                }
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editNum1.getText().toString().isEmpty() || editNum2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "먼저 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    Integer result = (Integer.parseInt(editNum1.getText().toString()) * Integer.parseInt(editNum2.getText().toString()));
                    textViewResult.setText("계산 결과 : " + result.toString());
                }
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editNum1.getText().toString().isEmpty() || editNum2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "먼저 숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    Integer result = (Integer.parseInt(editNum1.getText().toString()) / Integer.parseInt(editNum2.getText().toString()));
                    textViewResult.setText("계산 결과 : " + result.toString());
                }
            }
        });
    }
}