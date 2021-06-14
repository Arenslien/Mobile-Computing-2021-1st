package com.arenslien.study1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ExampleActivity extends Activity {
    Button btn1, btnInput;
    EditText editText1;
    CheckBox[] checkBoxes = new CheckBox[3];
    Integer[] checkBoxId = {R.id.checkBox, R.id.checkBox2, R.id.checkBox3};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn1 = (Button) findViewById(R.id.btnToMainActivity);
        btnInput = (Button) findViewById(R.id.btnInput);
        editText1 = (EditText) findViewById(R.id.editText1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), editText1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        for(int i=0; i<3; i++) {
            checkBoxes[i] = (CheckBox) findViewById(checkBoxId[i]);
            int finalI = i;
            checkBoxes[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(!checkBoxes[finalI].isChecked()) {
                        Toast.makeText(getApplicationContext(), checkBoxes[finalI].getText().toString() + " 비활성화", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), checkBoxes[finalI].getText().toString() + " 활성화", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
