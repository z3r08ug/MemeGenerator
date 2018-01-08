package com.example.chris.memegenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chris.memegenerator.catergory.MemeInterestActivity;
import com.example.chris.memegenerator.util.FacebookHandler;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity implements FacebookHandler.FacebookLoginListener{

    private LoginButton loginButton;
    private FacebookHandler facebookHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.login_button);
        facebookHandler = FacebookHandler.getInstance();
        facebookHandler.registerLoginButton(loginButton,this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FacebookHandler.getInstance().onActivityResult(requestCode, resultCode, data);
        if (facebookHandler.getAccessToken()!=null){
            Intent intent = new Intent(this, MemeInterestActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, MemeInterestActivity.class);
            startActivity(intent);
    }
}
