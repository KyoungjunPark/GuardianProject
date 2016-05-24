package com.example.administrator.guardian.ui.activity.Senior;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.administrator.guardian.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.Calendar;

import info.hoang8f.android.segmented.SegmentedGroup;

@SuppressLint("ValidFragment")
public class SeniorFragmentThreeActivity extends Fragment implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{
    private static final String TAG = "FragmentThreeActivity";

    private View view;
    private Button dateButton;
    private Button timeButton;
    private Button requestButton;
    private Button scheduleButton;
    private EditText infoEditText;

    private int year;
    private int month;
    private int day_of_month;
    private int hour_of_day;
    private int minute;

    private RadioButton radioButton01;
    private RadioButton radioButton02;
    private RadioButton radioButton03;

    Context mContext;

    public SeniorFragmentThreeActivity(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_senior_fragment_three, null);

        dateButton = (Button) view.findViewById(R.id.dateTextView);
        timeButton = (Button) view.findViewById(R.id.timeTextView);
        requestButton = (Button) view.findViewById(R.id.requestButton);
        scheduleButton = (Button) view.findViewById(R.id.scheduleButton);
        infoEditText = (EditText) view.findViewById(R.id.infoEditText);
        radioButton01 = (RadioButton) view.findViewById(R.id.radioButton01);
        radioButton02 = (RadioButton) view.findViewById(R.id.radioButton02);
        radioButton03 = (RadioButton) view.findViewById(R.id.radioButton03);

        //Set the ClickListener
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info_message;
                if (isAllEntered()) {
                    if (hour_of_day > 12) {
                        info_message = "일시 : " + year + "년 " + month + "월 " + day_of_month + "일 " + "오후" + (hour_of_day - 12) + "시 " + minute + "분 "
                                + "\n" + "요청시간 : " + (radioButton01.isChecked() ? "60분" : radioButton02.isChecked() ? "120분" : "180분")
                                + "\n" + "내용 : " + infoEditText.getText().toString();
                    } else {
                        info_message = "일시 : " + year + "년 " + month + "월 " + day_of_month + "일 " + "오전" + hour_of_day + "시 " + minute + "분 "
                                + "\n" + "요청시간 : " + (radioButton01.isChecked() ? "60분" : radioButton02.isChecked() ? "120분" : "180분")
                                + "\n" + "내용 : " + infoEditText.getText().toString();
                    }

                    new LovelyStandardDialog(getActivity())
                            .setTopColorRes(R.color.mdtp_light_gray)
                            .setButtonsColorRes(R.color.mdtp_transparent_black)
                            .setIcon(R.mipmap.ic_playlist_add_check_black_24dp)
                            .setTitle(R.string.info_title)
                            .setMessage(info_message)
                            .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //When OK button Clicked
                                }
                            })
                            .setNegativeButton(android.R.string.no, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //When NO button Clicked
                                    //Do nothing
                                }
                            })
                            .show();
                } else {
                    //When All input is not entered
                    new LovelyInfoDialog(getActivity())
                            .setTopColorRes(R.color.wallet_holo_blue_light)
                            .setIcon(R.mipmap.ic_not_interested_black_24dp)
                            //This will add Don't show again checkbox to the dialog. You can pass any ID as argument
                            .setTitle("경고")
                            .setMessage("요청시간을 입력해주세요.")
                            .show();
                }
            }
        });

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SeniorFragmentThreeScheduleActivity.class);
                startActivity(intent);
            }
        });

        //Set the current date & time
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        day_of_month = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        hour_of_day = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        minute = Calendar.getInstance().get(Calendar.MINUTE);

        dateButton.setText(year+"년 " + month +"월 " + day_of_month + "일 ");
        setTime();
        //Set the Date & Time ClickListener
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "HELLO", Toast.LENGTH_LONG);
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        SeniorFragmentThreeActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog dpd = TimePickerDialog.newInstance(
                        SeniorFragmentThreeActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                dpd.show(getActivity().getFragmentManager(), "Timepickerdialog");
            }
        });

        return view;
    }
    private boolean isAllEntered()
    {
        if(radioButton01.isChecked() || radioButton02.isChecked() || radioButton03.isChecked()){
            return true;
        }
        return false;
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear+1;
        this.day_of_month = dayOfMonth;
        dateButton.setText(year+"년 " + month +"월 " + day_of_month + "일 ");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        this.hour_of_day = hourOfDay;
        this.minute = minute;
        setTime();
    }
    private void setTime()
    {
        if(hour_of_day > 12) {
            timeButton.setText("오후" + (hour_of_day-12) + "시 " + minute + "분 ");
        } else {
            timeButton.setText("오전" + hour_of_day + "시 " + minute + "분 ");
        }
    }

}