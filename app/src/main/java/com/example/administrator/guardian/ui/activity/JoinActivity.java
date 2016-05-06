package com.example.administrator.guardian.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.administrator.guardian.R;

public class JoinActivity extends AppCompatActivity {
    private Button join;
    private RadioButton senior;
    private RadioButton volunteer;
    private RadioButton manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        senior = (RadioButton)findViewById(R.id.senior);
        volunteer = (RadioButton)findViewById(R.id.volunteer);
        manager = (RadioButton)findViewById(R.id.manager);

        join = (Button)findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(senior.isChecked() || volunteer.isChecked()) {
                    Intent RegistrationActivity = new Intent(getApplicationContext(), RegistrationActivity.class);
                    if(senior.isChecked()){
                        RegistrationActivity.putExtra("type","senior");
                        startActivity(RegistrationActivity);
                    }
                    else{
                        RegistrationActivity.putExtra("type","volunteer");
                        startActivity(RegistrationActivity);
                    }
                    finish();
                }
                if(manager.isChecked()){
                    Intent ManagerRegistration = new Intent(getApplicationContext(), ManagerRegistrationActivity.class);
                    startActivity(ManagerRegistration);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_join, menu);
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
