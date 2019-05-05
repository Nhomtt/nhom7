package com.example.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Login extends AppCompatActivity {
    Animation animation;
    TextView natus;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);

        natus = (TextView) findViewById(R.id.natus);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_down);
        natus.setAnimation(animation);

        login = (Button) findViewById(R.id.login);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        login.setAnimation(animation);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Login.this, thanhcong.class);
                startActivity(intent);
            }
        });

    }
}
