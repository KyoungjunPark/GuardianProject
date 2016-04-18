package com.example.administrator.guardian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class tempActivity extends AppCompatActivity {

    Button pulseButton;
    Button doorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_select_be_deleted);

        pulseButton = (Button) findViewById(R.id.pulseButton);
        doorButton = (Button) findViewById(R.id.doorButton);


        pulseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent firstMainActivity = new Intent(getApplicationContext(), SeniorMainActivity.class);
                Intent firstMainActivity = new Intent(getApplicationContext(), tempPulseActivity.class);
                startActivity(firstMainActivity);
            }
        });
        doorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent firstMainActivity = new Intent(getApplicationContext(), SeniorMainActivity.class);
                Intent firstMainActivity = new Intent(getApplicationContext(), tempDoorActivity.class);
                startActivity(firstMainActivity);
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
