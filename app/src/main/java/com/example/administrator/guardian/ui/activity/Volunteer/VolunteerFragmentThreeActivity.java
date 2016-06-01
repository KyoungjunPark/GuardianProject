package com.example.administrator.guardian.ui.activity.Volunteer;

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
import com.example.administrator.guardian.datamodel.VolunteerTimeRecyclerItem;
import com.example.administrator.guardian.ui.adapter.VolunteerTimeRecyclerViewAdapter;
import com.example.administrator.guardian.utils.ConnectServer;
import com.example.administrator.guardian.utils.GlobalVariable;

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
public class VolunteerFragmentThreeActivity extends Fragment {

    Context mContext;
    RecyclerView recyclerView;
    List<VolunteerTimeRecyclerItem> items;
    View view;
    GlobalVariable gv;
    public VolunteerFragmentThreeActivity(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_volunteer_fragment_three, null);
        gv = (GlobalVariable)getActivity().getApplication();
        getVolunteerListInfo();

        return view;
    }

    void getVolunteerListInfo(){
            ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


                @Override
                protected void onPreExecute() {
                    recyclerView = (RecyclerView)view.findViewById(R.id.volunteer_time_recyclerview);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    items = new ArrayList<>();
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                }
                @Override
                protected void onPostExecute(Boolean params) {
                    super.onPostExecute(null);
                    recyclerView.setAdapter(new VolunteerTimeRecyclerViewAdapter(getContext(), items, R.layout.activity_volunteer_fragment_three));

                }
                @Override
                protected Boolean doInBackground(String... params) {
                    URL obj = null;
                    try {
                        obj = new URL(ConnectServer.getInstance().getURL("Request_List"));
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con = ConnectServer.getInstance().setHeader(con);
                        con.setDoOutput(true);

                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("type", 1);

                        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                        wr.write(jsonObj.toString());
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
                                String senior_id = (String)dataArray.getJSONObject(i).get("senior_id");
                                String senior_name = (String)dataArray.getJSONObject(i).get("user_name");
                                String startInfo = (String)dataArray.getJSONObject(i).get("date_from");
                                int req_hour = dataArray.getJSONObject(i).getInt("req_hour");

                                String year =startInfo.substring(0,4);
                                String month=startInfo.substring(4,6);
                                String day =startInfo.substring(6,8);
                                String hour = startInfo.substring(8,10);
                                String minute = startInfo.substring(10,12);

                                VolunteerTimeRecyclerItem vol = new VolunteerTimeRecyclerItem(gv.getUser_name(), senior_id, senior_name, startInfo, year, month, day, hour, minute, req_hour);
                                items.add(vol);
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

