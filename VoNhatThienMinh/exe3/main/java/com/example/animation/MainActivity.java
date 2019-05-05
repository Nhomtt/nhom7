package com.example.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer;
    private ImageView hinh1, hinh2;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hinh1 =  (ImageView) findViewById(R.id.hinh1);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_down);
        hinh1.setAnimation(animation);

        hinh2 =  (ImageView) findViewById(R.id.hinh2);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right);
        hinh2.setAnimation(animation);




        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}
