package com.example.hoangduy.svtour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private TextView textview;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        textview = (TextView) findViewById(R.id.tvSplash);
        imageview = (ImageView) findViewById(R.id.ivSplash);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash_screen_animation);
        textview.startAnimation(animation);
        imageview.startAnimation(animation);

        final Intent intent = new Intent(this,HomeActivity.class);
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }
}
