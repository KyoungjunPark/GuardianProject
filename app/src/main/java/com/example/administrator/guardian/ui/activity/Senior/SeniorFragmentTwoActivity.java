package com.example.administrator.guardian.ui.activity.Senior;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.services.BluetoothService;

@SuppressLint("ValidFragment")
public class SeniorFragmentTwoActivity extends Fragment {
    private static final String TAG = "SeniorMainActivity";
    private static final int REQUEST_ENABLE_BT = 6666;
    private static final int REQUEST_CONNECT_DEVICE = 6667;

    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    Button door;
    boolean door_open=true;
    TextView a;
    private View view;
    Context mContext;

    private BluetoothService btService = null;
    private TextView textPulseValue;
    /*
    private final Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    break;
                case MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    //btService.getDeviceState();
                    // construct a string from the valid bytes in the buffer

                    String readMessage = new String(readBuf, 0, msg.arg1);
                    Log.d(TAG, "readMessage: "+ readMessage);

                    if(readMessage.equals("1")) {
                        door.setBackgroundResource(R.drawable.dooropen);
                        door_open=false;
                    }
                    else{
                        door.setBackgroundResource(R.drawable.doorclose);
                        door_open=true;
                    }

                    break;
            }
        }

    };*/

    public SeniorFragmentTwoActivity(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_senior_fragment_two);


        /*if(btService == null) {
            btService = new BluetoothService(this, mHandler);
        }
        if(btService.getDeviceState()) {
            // 블루투스가 지원 가능한 기기일 때
            btService.enableBluetooth();
        } else {
            finish();
        }
        door = (Button)findViewById(R.id.door);
        */
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_senior_fragment_two, null);

        //a= (TextView)view.findViewById(R.id.what);
        //a.setText("mymy");

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    //Next Step
                    btService.scanDevice();
                } else {
                    // 취소 눌렀을 때
                    Log.d(TAG, "Bluetooth is not enabled");
                }
                break;
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    btService.getDeviceInfo(data);
                }
                break;

        }
    }
}