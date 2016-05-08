package com.example.administrator.guardian.ui.activity.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.ui.activity.Manager.ManagerMainActivity;
import com.example.administrator.guardian.ui.activity.Senior.SeniorTabActivity;
import com.example.administrator.guardian.ui.activity.Volunteer.VolunteerMainActivity;

public class LoginActivity extends AppCompatActivity {
    private Button loginbutton;
    private Button joinbutton;

    Button tov;//for volunteer activity test
    Button tom;//for manager activity test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbutton = (Button)findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent firstMainActivity = new Intent(getApplicationContext(), SeniorMainActivity.class);
                Intent firstMainActivity = new Intent(getApplicationContext(), SeniorTabActivity.class);
                startActivity(firstMainActivity);
            }
        });

        joinbutton = (Button)findViewById(R.id.joinbutton);
        joinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_joinActivity = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(Go_joinActivity);
            }
        });

        tov = (Button)findViewById(R.id.tov);
        tov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vtest = new Intent(getApplicationContext(), VolunteerMainActivity.class);
                startActivity(vtest);
            }
        });

        tom = (Button)findViewById(R.id.tom);
        tom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mtest = new Intent(getApplicationContext(), ManagerMainActivity.class);
                startActivity(mtest);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
