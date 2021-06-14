package com.arenslien.week11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView2(this));
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            Log.d("onDraw", Integer.toString(picX) + ", " + Integer.toString(picY));
            Log.d("onDraw", Integer.toString(this.getWidth()) + ", " + Integer.toString(this.getHeight()));
            Log.d("onDraw", Integer.toString(picture.getWidth()) + ", " + Integer.toString(picture.getHeight()));

            canvas.drawBitmap(picture, picX, picY, null);
            picture.recycle();

        }
    }

    private static class MyGraphicView2 extends View {
        public MyGraphicView2(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.test1);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            //canvas.rotate(45, cenX, cenY);
            //canvas.drawBitmap(picture, picX, picY, null);

            //canvas.translate(150, -150);
            //canvas.drawBitmap(picture, picX, picY, null);

            //canvas.scale(1, 1, cenX, cenY);
            //canvas.drawBitmap(picture, picX, picY, null);

            canvas.skew(0.3f, 0.3f);
            canvas.drawBitmap(picture, picX, picY, null);

            picture.recycle();
        }
    }

    private static class MyGraphicView3 extends View {
        public MyGraphicView3(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.test1);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            //canvas.rotate(45, cenX, cenY);
            //canvas.drawBitmap(picture, picX, picY, null);

            //canvas.translate(150, -150);
            //canvas.drawBitmap(picture, picX, picY, null);

            //canvas.scale(1, 1, cenX, cenY);
            //canvas.drawBitmap(picture, picX, picY, null);

            canvas.skew(0.3f, 0.3f);
            canvas.drawBitmap(picture, picX, picY, null);

            picture.recycle();
        }
    }
}