package com.example.administrator.guardian.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * Created by KJPARK on 2016-05-08.
 *
 * @since 0.1
 */
public class MakeUTF8Parameter {
    private String resultParameter = "";

    public MakeUTF8Parameter(){}

    public void setParameter(String key, String data){
        try {
            if(!resultParameter.equals("")) resultParameter += "&";
            resultParameter += URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void setParameter(String key, Integer data){
        try {
            if(!resultParameter.equals("")) resultParameter += "&";
            resultParameter += URLEncoder.encode(key, "UTF-8") + "=" + data;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public String getParameter(){return resultParameter;}
}
