package com.example.administrator.guardian.ui.activity.Manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.utils.ConnectServer;
import com.example.administrator.guardian.utils.MakeUTF8Parameter;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ManagerMainActivity extends AppCompatActivity{
	ListView lv;

	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.mtoolbar);
		setSupportActionBar(toolbar);

		lv = (ListView)findViewById(R.id.senior_list_view);
		getInfoFromServer();
		updateSeniorList();
	}
	public void getInfoFromServer(){
		Log.d("ktk", "ktk");
		ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {
			@Override
			protected void onPreExecute() {
				Log.d("ktk", "ktk3");
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
					parameterMaker.setParameter("test", "test");

					OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
					wr.write(parameterMaker.getParameter());
					wr.flush();

					BufferedReader rd =null;

					if(con.getResponseCode() == 200){
						//Sign up Success
						rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
						Log.d("ktk","success");
					} else {
						//Sign up Fail
						rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
						new LovelyInfoDialog(getApplicationContext())
								.setTopColorRes(R.color.mdtp_red)
								.setIcon(R.mipmap.ic_not_interested_black_24dp)
								//This will add Don't show again checkbox to the dialog. You can pass any ID as argument
								.setNotShowAgainOptionEnabled(0)
								.setTitle("경고")
								.setMessage(rd.readLine())
								.show();
						rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
						Log.d("ktk","fail");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		ConnectServer.getInstance().execute();
		Log.d("ktk", "ktk2");
	}

	public void updateSeniorList(){
		ArrayList<Senior> list = new ArrayList<>();

		/*
		* Find person who is managed by manager
		* Add it to arrayList from DB
		*
		* DBManager dbm = new DBManager(this);
		* SQLiteDatabase db = dbm.getReadableDatabase();
		* Cursor cursor = new db.rawQuery( SELECT );
		* if(cursor.moveToFirst()){
		* 	do{
		* 		add it to list;
		*	} while(cursor.moveToNext());
		* }
		*
		* cursor.close();
		* db.close();
		* */

		//-----------------test-----------------
		Senior[] senior;

		senior = new Senior[5];
		senior[0]=new Senior("홍길동",80,"여","흑석","01012345678");
		senior[1]=new Senior("박길동",82,"여","흑석","01012345679");
		senior[2]=new Senior("박경동",98,"여","흑석","01012345670");
		senior[3]=new Senior("박경준",99,"여","흑석","01012345671");
		senior[4]=new Senior("백갱준",100,"여","흑석","01012345672");

		for(int i=0; i<5; i++){
			list.add(senior[i]);
		}
		//-----------------test-----------------

		SeniorAdapter adpt = new SeniorAdapter(ManagerMainActivity.this, R.layout.unit_senior_to_manage, list);
		lv.setAdapter(adpt);
	}

	// Senior Object Def
	class Senior{
		String name;
		int age;
		String gender;
		String address;
		String phoneNumber;
		Senior(String name, int age, String gender, String address, String phoneNumber){
			this.name = name;
			this.age=age;
			this.gender=gender;
			this.address=address;
			this.phoneNumber=phoneNumber;
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
			TextView tv_age = (TextView)convertView.findViewById(R.id.ustm_inputage);
			TextView tv_gender = (TextView)convertView.findViewById(R.id.ustm_inputgender);

			tv_name.setText(seniorList.get(position).name);
			tv_age.setText(seniorList.get(position).age+"");
			tv_gender.setText(seniorList.get(position).gender);

			//-----------------test-----------------
			show_senior_info_button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent manageinfo = new Intent(getApplicationContext(),ManagerSeniorInfoTabActivity.class);
					manageinfo.putExtra("seniorname",seniorList.get(position).name);
					manageinfo.putExtra("seniorage",seniorList.get(position).age);
					manageinfo.putExtra("seniorgender",seniorList.get(position).gender);
					manageinfo.putExtra("senioraddress",seniorList.get(position).address);
					manageinfo.putExtra("seniorphoneNumber",seniorList.get(position).phoneNumber);
					startActivity(manageinfo);
				}
			});
			manage_senior_button.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					String Dial = "tel:"+seniorList.get(position).phoneNumber;
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(Dial));
					try {
						startActivity(intent);
					}catch(SecurityException e){
						Log.d("SecurityException","try/catch gulrim");}
				}
			});
			//-----------------test-----------------

			return convertView;
		}
	}
}
