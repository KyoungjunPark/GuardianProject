package com.example.administrator.guardian.utils;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.guardian.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by 경태 on 2016-06-01.
 */

public class RegistrationIntentService extends IntentService{
	private static final String TAG = "RegistrationIntentService";
	GlobalVariable globalVariable;
	public RegistrationIntentService(){
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent){
		InstanceID instanceID = InstanceID.getInstance(this);
		try{
			String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
			Log.e("token", token);
			globalVariable = (GlobalVariable)getApplication();
			globalVariable.setToken(token);
			//Log.d("bugfix88", "onHandleIntent: "+globalVariable.getToken());
			//ConnectServer.getInstance().setToken(token);

		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
