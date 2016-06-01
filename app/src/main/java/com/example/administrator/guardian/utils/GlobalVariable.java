package com.example.administrator.guardian.utils;

import android.app.Application;

/**
 * Created by 경태 on 2016-05-30.
 */
public class GlobalVariable extends Application{
	private int login_type;
	private String user_name;
	private String token;
	public int getLoginType(){
		return login_type;
	}
	public String getUser_name(){return user_name;}
	public String getToken(){return token;}
	public void setLoginType(int type){
		this.login_type = type;
	}
	public void setUser_name(String name){
		this.user_name = name;
	}
	public void setToken(String token){this.token = token;}
}
