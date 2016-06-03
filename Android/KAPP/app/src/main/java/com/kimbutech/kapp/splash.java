package com.kimbutech.kapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by falle on 1/23/2016.
 */
public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_screen);
     final Intent intent = new Intent(this,MainActivity.class);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startActivity(intent);
                finish();
            }
        }, 400);

               /* Thread splashThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1500);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        };
        splashThread.start();
*/

    }
}
