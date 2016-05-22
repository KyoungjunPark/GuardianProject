package com.example.administrator.guardian.utils;

import android.os.AsyncTask;
import java.net.HttpURLConnection;

/**
 * Created by KJPARK on 2016-05-06.
 *
 * @since 0.1
 */

public class ConnectServer {
    private AsyncTask<String, Void, Boolean> task;

    private String token;
    private static String URL = "http://59.187.203.140:80";

    private String type;

    private static final ConnectServer instance = new ConnectServer();

    public ConnectServer() {
    }

    public static ConnectServer getInstance() {
        return instance;
    }

    public void setAsncTask(AsyncTask<String, Void, Boolean> task) {
        this.task = task;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void execute() {
        this.task.execute();
    }

    public HttpURLConnection setHeader(HttpURLConnection con) {
        con.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("token", this.token);

        return con;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getURL(String path) {
        String resultURL = null;

        switch (path) {
            case "Sign_Up":
                //The case which is Join Request
                resultURL = URL + "/" + "Sign_Up";
                break;
            case "Sign_In":
                resultURL = URL + "/" + "Sign_In";
                break;
        }
        return resultURL;
    }
}
