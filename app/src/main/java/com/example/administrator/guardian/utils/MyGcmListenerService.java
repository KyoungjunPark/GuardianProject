package com.example.administrator.guardian.utils;

/**
 * Created by 경태 on 2016-06-01.
 */

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.ui.activity.Login.IntroActivity;
import com.google.android.gms.gcm.GcmListenerService;


public class MyGcmListenerService extends GcmListenerService {
	String result;
	Bitmap bitmap;
	Uri soundUri;
	Intent intent;
	PendingIntent pendingIntent;
	NotificationCompat.Builder notificationBuilder;

	@Override
	public void onMessageReceived(String from, Bundle data) {

		result = data.getString("data");
		Resources res = getResources();
		bitmap = BitmapFactory.decodeResource(res, R.drawable.icon);
		soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
		if(pref.getString("userType", "").compareTo("volunteer") == 0){
			ConnectServer.getInstance().setToken(pref.getString("token",""));
			GlobalVariable globalVariable = (GlobalVariable)getApplication();
			globalVariable.setUser_name(pref.getString("userName",""));
			globalVariable.setLoginType(1);

			intent = new Intent(getApplicationContext(), IntroActivity.class);
			pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
			//PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


			notificationBuilder = new NotificationCompat.Builder(this)
					.setContentTitle("방문요청알림")
					.setContentText("요청을 확인해주세요.")
					.setTicker("요청을 확인해주세요.")
					.setSmallIcon(R.mipmap.ic_launcher)
					.setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.icon))
					.setContentIntent(pendingIntent)
					.setAutoCancel(true)
					.setWhen(System.currentTimeMillis())
					.setDefaults( Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE|Notification.DEFAULT_LIGHTS)
					.setNumber(13);

		}else if(pref.getString("userType", "").compareTo("senior") == 0){
			ConnectServer.getInstance().setToken(pref.getString("token",""));
			GlobalVariable globalVariable = (GlobalVariable)getApplication();
			globalVariable.setUser_name(pref.getString("userName",""));
			globalVariable.setLoginType(1);

			intent = new Intent(getApplicationContext(), IntroActivity.class);
			pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
			//PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			notificationBuilder = new NotificationCompat.Builder(this)
					.setContentTitle("방문요청알림")
					.setContentText("요청을 확인해주세요.")
					.setTicker("요청을 확인해주세요.")
					.setSmallIcon(R.mipmap.ic_launcher)
					.setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.icon))
					.setContentIntent(pendingIntent)
					.setAutoCancel(true)
					.setWhen(System.currentTimeMillis())
					.setDefaults( Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE|Notification.DEFAULT_LIGHTS)
					.setNumber(13);
		}


		//노티피케이션 빌더 : 위에서 생성한 이미지나 텍스트, 사운드등을 설정해줍니다.


		NotificationManager notificationManager =
				(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		//노티피케이션을 생성합니다.
		notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
	}



}