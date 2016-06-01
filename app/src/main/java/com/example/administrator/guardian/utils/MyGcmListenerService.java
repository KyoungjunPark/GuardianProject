package com.example.administrator.guardian.utils;

/**
 * Created by 경태 on 2016-06-01.
 */

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;


public class MyGcmListenerService extends GcmListenerService {

	@Override
	public void onMessageReceived(String from, Bundle data) {
		String result = data.getString("data");
		Log.e("result",result);

	}

}