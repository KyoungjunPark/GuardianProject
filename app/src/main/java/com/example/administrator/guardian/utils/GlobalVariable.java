package com.example.administrator.guardian.utils;

import android.app.Application;

/**
 * Created by 경태 on 2016-05-30.
 */
public class GlobalVariable extends Application{
	private int login_type;
	public int getLoginType(){
		return login_type;
	}
	public void setLoginType(int type){
		this.login_type = type;
	}
}
