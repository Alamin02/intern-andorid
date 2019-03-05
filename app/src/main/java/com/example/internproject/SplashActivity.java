package com.example.internproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.parse.ParseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        // 1.5 seconds delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(ParseUser.getCurrentUser() == null){
                    Intent signup = new Intent(SplashActivity.this, SignupActivity.class);
                    SplashActivity.this.startActivity(signup);
                    SplashActivity.this.finish();
                }
                else {
                    Intent signin = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(signin);
                    SplashActivity.this.finish();
                }
            }
        }, 1500);
    }
}
