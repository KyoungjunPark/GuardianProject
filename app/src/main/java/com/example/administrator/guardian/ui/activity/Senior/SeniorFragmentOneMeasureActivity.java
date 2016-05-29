package com.example.administrator.guardian.ui.activity.Senior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.hrules.circularprogressbar.CircularProgressBar;
import com.hrules.circularprogressbar.CircularProgressBarListener;

public class SeniorFragmentOneMeasureActivity extends AppCompatActivity implements CircularProgressBarListener {
    private static final String TAG = "SeniorFragmentOneMeasureActivity";

    private Button measureback;
    private TextView measuring;
    private int high_zone_2;
    private int low_zone_1;

    private CircularProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_fragment_one_measure);

        Intent intent = getIntent();
        high_zone_2 = intent.getExtras().getInt("high_zone_2");
        low_zone_1 = intent.getExtras().getInt("low_zone_1");

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
        onValueChanged(circularProgressBar.getValue());

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
        intent.putExtra("high_zone_2", high_zone_2);
        intent.putExtra("low_zone_1",low_zone_1);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStopSpinning() {
        circularProgressBar.setSpeed(0);
    }

    @Override
    public void onValueChanged(float value) {
        Log.d("value",value+"");
        if(value==435.0 || value==1050.0 || value==1650.0){
            try{onStopSpinning();
                Thread.sleep(1000);
                onStartSpinning();
            }catch(InterruptedException e){}
        }
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
