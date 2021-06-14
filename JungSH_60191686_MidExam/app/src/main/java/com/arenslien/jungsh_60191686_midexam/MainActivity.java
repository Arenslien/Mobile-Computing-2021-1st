package com.arenslien.jungsh_60191686_midexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    // Component
    Button btnPrev, btnNext;
    ViewFlipper viewFlipper;
    TextView[] textViews = new TextView[5];
    Integer[] textViewId = {R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5};
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("정성훈 60191686 중간시험");

        // Connect Component
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        imageView = (ImageView) findViewById(R.id.imageView);

        // add Click Listener For Button
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showPrevious();
                imageView.setScaleX(1);
                imageView.setScaleY(1);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.showNext();
                imageView.setScaleX(1);
                imageView.setScaleY(1);
            }
        });

        // TextView Setting & add Click Listener
        for(int i=0; i<5; i++) {
            textViews[i] = (TextView) findViewById(textViewId[i]);
            int finalI = i;
            textViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), textViews[finalI].getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        // register Context Menu
        registerForContextMenu(imageView);

        // Add Long Click Listener For ImageView
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                imageView.showContextMenu();
                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        if(v == imageView) {
            menuInflater.inflate(R.menu.contextmenu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);

        switch (item.getItemId()) {
            case R.id.menu1:
                imageView.setScaleX(2);
                imageView.setScaleY(2);
                return true;
            case R.id.menu2:
                imageView.setScaleX(1);
                imageView.setScaleY(1);
                return true;
            default: return false;
        }
    }
}