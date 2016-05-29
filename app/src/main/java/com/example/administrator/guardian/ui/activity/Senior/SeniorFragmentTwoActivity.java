package com.example.administrator.guardian.ui.activity.Senior;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.SeniorRecyclerItem;
import com.example.administrator.guardian.ui.adapter.SeniorRecyclerViewAdapter;
import com.example.administrator.guardian.utils.ConnectServer;
import com.example.administrator.guardian.utils.MakeUTF8Parameter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class SeniorFragmentTwoActivity extends Fragment {

    RecyclerView recyclerView;
    private View view;
    List<SeniorRecyclerItem> items ;

    Context mContext;
    public SeniorFragmentTwoActivity(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_senior_fragment_two, null);




        getInfoFromServer();

        return view;
    }


    public void getInfoFromServer(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


            @Override
            protected void onPreExecute() {
                recyclerView = (RecyclerView)view.findViewById(R.id.senior_recyclerView);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                items = new ArrayList<>();
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);
            }
            protected void onPostExecute(Boolean params) {
                super.onPostExecute(null);
                SeniorRecyclerViewAdapter adpt = new SeniorRecyclerViewAdapter(SeniorFragmentTwoActivity.this.getContext(), items, R.layout.activity_senior_fragment_two);
                recyclerView.setAdapter(adpt);
            }
            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("Senior_List"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con = ConnectServer.getInstance().setHeader(con);
                    con.setDoOutput(true);

                    //set Request parameter
                    MakeUTF8Parameter parameterMaker = new MakeUTF8Parameter();

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(parameterMaker.getParameter());
                    wr.flush();

                    BufferedReader rd =null;

                    if(con.getResponseCode() == 200){
                        //Sign up Success
                        rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                        String resultValues = rd.readLine();
                        JSONObject object = new JSONObject(resultValues);
                        JSONArray dataArray = object.getJSONArray("data");
                        Log.d("ktk", object.toString());

                        for (int i=0; i<dataArray.length(); i++){
                            String login_id = (String)dataArray.getJSONObject(i).get("login_id");
                            String user_name= (String)dataArray.getJSONObject(i).get("user_name");
                            String user_gender= (String)dataArray.getJSONObject(i).get("user_gender");
                            String user_address= (String)dataArray.getJSONObject(i).get("user_address");
                            String user_tel= (String)dataArray.getJSONObject(i).get("user_tel");
                            String user_birthdate= (String)dataArray.getJSONObject(i).get("user_birthdate");
                            Double distance;
                            try {
                                distance = (Double) dataArray.getJSONObject(i).get("distance");
                            } catch(Exception e){
                                distance = 0.0;
                            }
                            int user_age = (20179999 - Integer.parseInt(user_birthdate))/10000;

                            // login_id, user_name, user_birthdate, user_age, user_gender, user_address, user_tel

                            SeniorRecyclerItem senior = new SeniorRecyclerItem(login_id, user_name, user_birthdate, user_age, user_gender, user_address, user_tel, distance);
                            items.add(senior);
                        }


                    } else {
                        //Sign up Fail
                        rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                        Log.d("ktk","fail");
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }



        });
        ConnectServer.getInstance().execute();
    }

}
