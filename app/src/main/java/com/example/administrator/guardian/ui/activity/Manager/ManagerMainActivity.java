package com.example.administrator.guardian.ui.activity.Manager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.ui.activity.Login.LoginActivity;
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

public class ManagerMainActivity extends AppCompatActivity{
	private static final String TAG = "ManagerMainActivity";
	ListView lv;
	final ArrayList<Senior> list = new ArrayList<>();
	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.mtoolbar);
		setSupportActionBar(toolbar);

		lv = (ListView)findViewById(R.id.senior_list_view);
		getInfoFromServer();

		/*
		list.add(new Senior("1","이원세","19941222","남","서울시","01088888888"));
		SeniorAdapter adpt = new SeniorAdapter(ManagerMainActivity.this, R.layout.unit_senior_to_manage, list);
		lv.setAdapter(adpt);
		*/
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
		alert_confirm.setMessage("프로그램을 종료 하시겠습니까?").setCancelable(false).setPositiveButton("확인",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				}).setNegativeButton("취소",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 'No'
						return;
					}
				});
		AlertDialog alert = alert_confirm.create();
		alert.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add(0, 1, 0, "logout");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case 1:
				signOut();
				return true;
		}
		return false;
	}

	public void getInfoFromServer(){
		ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


			@Override
			protected void onPreExecute() {
			}
			protected void onPostExecute(Boolean params) {
				super.onPostExecute(null);
				SeniorAdapter adpt = new SeniorAdapter(ManagerMainActivity.this, R.layout.unit_senior_to_manage, list);
				lv.setAdapter(adpt);
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
					wr.close();
					BufferedReader rd =null;

					if(con.getResponseCode() == 200){
						//Sign up Success
						rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
						String resultValues = rd.readLine();
						JSONObject object = new JSONObject(resultValues);
						JSONArray dataArray = object.getJSONArray("data");
						for (int i=0; i<dataArray.length(); i++){
							String login_id = (String)dataArray.getJSONObject(i).get("login_id");
							String user_name= (String)dataArray.getJSONObject(i).get("user_name");
							String user_birthdate= (String)dataArray.getJSONObject(i).get("user_birthdate");
							String user_gender= (String)dataArray.getJSONObject(i).get("user_gender");
							String user_tel= (String)dataArray.getJSONObject(i).get("user_tel");

							Senior senior = new Senior(login_id, user_name, user_birthdate, user_gender, user_tel);
							list.add(senior);

						}


					} else {
						//Sign up Fail
						rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
						Log.d(TAG,"fail");
					}
				} catch (IOException | JSONException e) {
					e.printStackTrace();
				}
				return null;
			}



		});
		ConnectServer.getInstance().execute();
	}

	// Senior Object Def
	class Senior{
		String login_id;
		String user_name;
		String user_birthdate;
		String user_gender;
		String user_tel;

		Senior(String id, String name, String birthdate, String gender, String tel){
			this.login_id = id;
			this.user_name = name;
			this.user_birthdate=birthdate;
			this.user_gender=gender;
			this.user_tel=tel;
		}
	}

	// Adapter for Senior ListView
	class SeniorAdapter extends BaseAdapter{
		Context context;
		LayoutInflater inflater;
		ArrayList<Senior> seniorList;
		int layout;

		public SeniorAdapter(Context context, int layout, ArrayList<Senior> seniorList){
			this.context = context;
			this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.seniorList = seniorList;
			this.layout = layout;
		}
		@Override
		public int getCount(){
			return seniorList.size();
		}
		@Override
		public Object getItem(int position){
			return seniorList.get(position);
		}
		@Override
		public long getItemId(int position){
			return position;
		}
		@Override
		public View getView(final int position, View convertView, ViewGroup parent){
			if(convertView == null){
				convertView = inflater.inflate(layout, parent, false);
			}
			RelativeLayout show_senior_info_button = (RelativeLayout)convertView.findViewById(R.id.show_senior_info_button);
			ImageButton manage_senior_button = (ImageButton)convertView.findViewById(R.id.manage_senior_button);
			//show_senior_info_button.setText(seniorList.get(position).name);
			TextView tv_name = (TextView)convertView.findViewById(R.id.ustm_name);
			TextView tv_birthdate = (TextView)convertView.findViewById(R.id.ustm_inputbirthdate);
			TextView tv_gender = (TextView)convertView.findViewById(R.id.ustm_inputgender);

			tv_name.setText(seniorList.get(position).user_name);
			tv_birthdate.setText(seniorList.get(position).user_birthdate);
			tv_gender.setText(seniorList.get(position).user_gender);



			//-----------------test-----------------
			show_senior_info_button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent manageinfo = new Intent(getApplicationContext(),ManagerSeniorInfoTabActivity.class);
					manageinfo.putExtra("senior_id",seniorList.get(position).login_id);
					manageinfo.putExtra("senior_name",seniorList.get(position).user_name);
					manageinfo.putExtra("senior_birthdate",seniorList.get(position).user_birthdate);
					manageinfo.putExtra("senior_gender",seniorList.get(position).user_gender);
					startActivity(manageinfo);
				}
			});

			manage_senior_button.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					String Dial = "tel:"+seniorList.get(position).user_tel;//"tel:"+phoneNumber;
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(Dial));
					try {
						startActivity(intent);
					}catch(SecurityException e){
						Log.d("SecurityException","manage_senior_call");}
				}
			});
			//-----------------test-----------------

			return convertView;
		}
	}

	public void signOut(){
		ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


			@Override
			protected void onPreExecute() {

			}
			@Override
			protected void onPostExecute(Boolean params) {
				Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(logout);
				finish();
			}

			@Override
			protected Boolean doInBackground(String... params) {
				URL obj = null;
				try {
					obj = new URL(ConnectServer.getInstance().getURL("Sign_Out"));
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();

					con.setRequestMethod("POST");
					con.setRequestProperty("Content-Type", "application/json; charset=utf8");
					con.setRequestProperty("Accept", "application/json");

					con = ConnectServer.getInstance().setHeader(con);
					con.setDoOutput(true);

					JSONObject jsonObj = new JSONObject();

					OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
					wr.write(jsonObj.toString());

					wr.flush();

					BufferedReader rd =null;

					if(con.getResponseCode() == 200){
						rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

					} else {
						rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
						Log.d(TAG,"fail : " + rd.readLine());
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

		});
		ConnectServer.getInstance().execute();
	}
}
