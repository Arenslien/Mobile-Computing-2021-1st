package com.arenslien.week7_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        fragment2 = new Fragment2();
    }
    public void onFragmentChanged(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (index == 1) {
            transaction.replace(R.id.container, fragment1);
            transaction.commit();
        } else if (index == 2) {
            transaction.replace(R.id.container, fragment2);
            transaction.commit();
        }
    }
}