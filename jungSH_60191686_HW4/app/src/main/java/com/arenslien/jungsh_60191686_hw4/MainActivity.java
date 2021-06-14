package com.arenslien.jungsh_60191686_hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static int LINE = 1, CIRCLE = 2, RECTANGLE = 3, ROUNDRECTANGLE = 4;
    static int curShape = LINE;
    static int color = Color.RED;
    static int roundRadius = 10;
    static Paint.Style paintStyle = Paint.Style.STROKE;
    //Path[] paths = new Path[5];
    //int pathCnt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyCanvas(this));
        setTitle("정성훈 과제4 간단 그림판");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // MenuInflater inflater = getMenuInflater().inflate(R.menu.menu, menu);

        menu.add(0, 1, 0, "선 그리기");
        menu.add(0, 2, 0, "원 그리기");
        menu.add(0, 3, 0, "사각형 그리기");

        SubMenu sMenu1 = menu.addSubMenu("둥근 사각형 그리기");
        sMenu1.add(0, 4, 0, "10");
        sMenu1.add(0, 5, 0, "20");
        sMenu1.add(0, 6, 0, "30");

        SubMenu sMenu2 = menu.addSubMenu("색상 변경>>");
        sMenu2.add(0, 7, 0, "빨강");
        sMenu2.add(0, 8, 0, "초록");
        sMenu2.add(0, 9, 0, "파랑");

        SubMenu sMenu3 = menu.addSubMenu("내부 채우기");
        sMenu3.add(0, 10, 0, "채워서 그리기");
        sMenu3.add(0, 11, 0, "선만 그리기");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case 1:
                curShape = LINE; // 선
                return true;
            case 2:
                curShape = CIRCLE; // 원
                return true;
            case 3:
                curShape = RECTANGLE; // 사각형
                return true;
            case 4:
                curShape = ROUNDRECTANGLE;
                roundRadius = 10;
                return true;
            case 5:
                curShape = ROUNDRECTANGLE;
                roundRadius = 20;
                return true;
            case 6:
                curShape = ROUNDRECTANGLE;
                roundRadius = 30;
                return true;
            case 7:
                color = Color.RED;
                return true;
            case 8:
                color = Color.GREEN;
                return true;
            case 9:
                color = Color.BLUE;
                return true;
            case 10:
                paintStyle = Paint.Style.FILL;
                return true;
            case 11:
                paintStyle = Paint.Style.STROKE;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyCanvas extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyCanvas(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(paintStyle);
            paint.setColor(color);

            switch (curShape) {
                case LINE:
                    // 전에 그린 거 그리기
                    //for (int i = 0; i<pathCnt; i++) {
                    //    canvas.drawPath(paths[i], paint);
                    //}

                    // 지금 그리고 있는 거 표시
                    Path path1 = new Path();
                    path1.moveTo(startX, startY);
                    path1.lineTo(stopX, stopY);
                    canvas.drawPath(path1, paint);


                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                case RECTANGLE:
                    Rect rect = new Rect(startX, startY, stopX, stopY);
                    canvas.drawRect(rect, paint);
                    break;
                case ROUNDRECTANGLE:
                    RectF rect2 = new RectF(startX, startY, stopX, stopY);
                    canvas.drawRoundRect(rect2, roundRadius, roundRadius, paint);
                    break;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();

                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
            }
            return true;
        }
    }
}

