package com.loc.facebooklogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    // khởi tạo button giá trị cho nó
    TextView Info;
    // button facebook login
    LoginButton loginButton;
    // s
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        Clasview();
        // call vư

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // get user id and user  accesstoken
                Info.setText(
                        "My id :" + loginResult.getAccessToken().getUserId()
                                + "\n"
                                + "Auth token: " + loginResult.getAccessToken().getToken()
                );

            }

            @Override
            public void onCancel() {
                Info.setText("Login cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Info.setText("Login Error");
            }
        });
    }

    private void Clasview() {
        Info = (TextView) findViewById(R.id.info);
        loginButton = (LoginButton) findViewById(R.id.login_button);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
