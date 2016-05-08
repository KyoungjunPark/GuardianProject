package com.example.administrator.guardian.ui.activity.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.example.administrator.guardian.R;


public class IntroActivity extends Activity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        handler = new Handler();

        handler.postDelayed(rIntent,2000);
    }
    Runnable rIntent = new Runnable(){
        @Override
        public void run(){

            Intent Login = new Intent(getApplicationContext(), LoginActivity.class);
            Login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(Login);
            finish();
        }
    };
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(rIntent);
    }
}
