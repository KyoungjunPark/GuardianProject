package com.example.administrator.guardian.ui.activity.Senior;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hrules.circularprogressbar.CircularProgressBar;
import com.hrules.circularprogressbar.CircularProgressBarListener;

import com.example.administrator.guardian.R;

import java.io.IOException;

public class SeniorFragmentOneMeasureActivity extends AppCompatActivity implements CircularProgressBarListener {
    private static final String TAG = "SeniorFragmentOneMeasureActivity";

    private Button measureback;
    private TextView measuring;
    private CircularProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_fragment_one_measure);

        measureback = (Button)findViewById(R.id.measureback);
        measureback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        circularProgressBar = (CircularProgressBar) findViewById(R.id.circularProgressBar);
        circularProgressBar.setListener(this);

        measuring = (TextView)findViewById(R.id.measuring);

        circularProgressBar.setThickness(20);
        circularProgressBar.setMaxValue(1900);
        circularProgressBar.setValue(0, true);
        circularProgressBar.setValue(circularProgressBar.getMaxValue(), true);

        onPercentValueChanged(circularProgressBar.getValue()/19);



    }


    @Override
    public void onClick() {
        circularProgressBar.setFilled(!circularProgressBar.isFilled());

    }

    @Override
    public void onStartSpinning() {
        circularProgressBar.setSpeed(5);
    }

    @Override
    public void onFinish() {
        Intent intent = new Intent(getApplicationContext(), SeniorFragmentOneMeasureResultActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStopSpinning() {
        circularProgressBar.setSpeed(0);
    }

    @Override
    public void onValueChanged(float value) {

    }

    @Override
    public void onPercentValueChanged(float percent) {
        int truePercent;
        float p;
        p=percent*100;
        truePercent=(int)p;
        measuring.setText(truePercent+"%");
    }
}
