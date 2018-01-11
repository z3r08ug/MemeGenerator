package com.example.chris.memegenerator.view.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chris.memegenerator.LoginActivity;
import com.example.chris.memegenerator.R;
import com.example.chris.memegenerator.category.MemeInterestActivity;
import com.example.chris.memegenerator.util.FacebookHandler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash_screen);
        startHomeActivity();
    }

    public void startHomeActivity() {

        Handler splashScreenIntent = new Handler();
        splashScreenIntent.postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent loginActivity = new Intent(getApplicationContext(),LoginActivity.class);
                FacebookHandler facebookHandler = FacebookHandler.getInstance();
                if (facebookHandler.getAccessToken()==null) {
                    startActivity(loginActivity);
                    finish();
                }else{
                    Intent homeActivity = new Intent(getApplicationContext(), MemeInterestActivity.class);
                    startActivity(homeActivity);
                    finish();
                }
               
            }
        }, 3000);
        
    }
}
