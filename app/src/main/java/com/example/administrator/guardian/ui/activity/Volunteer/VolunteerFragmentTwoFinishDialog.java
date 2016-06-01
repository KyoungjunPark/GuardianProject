package com.example.administrator.guardian.ui.activity.Volunteer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.guardian.R;

public class VolunteerFragmentTwoFinishDialog extends Dialog {

    private TextView vftdf_Name;
    private TextView vftdf_Contents;


    private String name;
    private String gender;
    private String contents;

    private Button vftdf_button;

    public VolunteerFragmentTwoFinishDialog(Context context, String name, String gender, String contents) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);

        this.name = name;
        this.gender=gender;
        this.contents = contents;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.6f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_volunteer_fragment_two_finish_dialog);

        setLayout();
        setTitle(name);
        setContent(contents);



        vftdf_button = (Button)findViewById(R.id.vftdf_button);
        vftdf_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolunteerFragmentTwoFinishDialog.this.dismiss();
            }
        });
    }
    private void setTitle(String Name){
        if(gender.compareTo("남") == 0){
            Name = Name + " 할아버지";
        }
        else{
            Name = Name + " 할머니";
        }

        vftdf_Name.setText(Name);
    }

    private void setContent(String contents){

        vftdf_Contents.setText(contents);
    }

    /*
     * Layout
     */
    private void setLayout(){
        vftdf_Name = (TextView) findViewById(R.id.vftdf_name);
        vftdf_Contents = (TextView)findViewById(R.id.vftdf_content);
    }
}
