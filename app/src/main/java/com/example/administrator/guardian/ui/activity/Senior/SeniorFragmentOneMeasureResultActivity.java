package com.example.administrator.guardian.ui.activity.Senior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.administrator.guardian.R;

public class SeniorFragmentOneMeasureResultActivity extends AppCompatActivity {

    private Button measureback;
    private ProgressBar pulseProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_fragment_one_measure_result);

        measureback = (Button)findViewById(R.id.sfomrback);
        measureback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pulseProgressBar = (ProgressBar)findViewById(R.id.pulseProgressBar);

        //심박수 값이 들어가야함
        pulseProgressBar.setProgress(70);
        //progress bar가 40부터 시작해서 40을 빼줘야함
        pulseProgressBar.incrementProgressBy(-40);
    }
}
