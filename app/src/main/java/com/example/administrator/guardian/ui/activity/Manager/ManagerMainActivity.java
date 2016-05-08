package com.example.administrator.guardian.ui.activity.Manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.guardian.R;

import java.util.ArrayList;

/**
 * Created by 경태 on 2016-04-10.
 */
public class ManagerMainActivity extends AppCompatActivity{
	ListView lv;

	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.mtoolbar);
		setSupportActionBar(toolbar);

		lv = (ListView)findViewById(R.id.senior_list_view);
		updateSeniorList();
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
		String sample_name;
		Senior senior;
		for(int i=0; i<10; i++){
			sample_name = "홍길동";
			sample_name += " "+i;
			senior = new Senior(sample_name);
			list.add(senior);
		}
		//-----------------test-----------------

		SeniorAdapter adpt = new SeniorAdapter(ManagerMainActivity.this, R.layout.unit_senior_to_manage, list);
		lv.setAdapter(adpt);
	}

	// Senior Object Def
	class Senior{
		String name;
		Senior(String name){
			this.name = name;
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
			Button show_senior_info_button = (Button)convertView.findViewById(R.id.show_senior_info_button);
			ImageButton manage_senior_button = (ImageButton)convertView.findViewById(R.id.manage_senior_button);
			show_senior_info_button.setText(seniorList.get(position).name);

			//-----------------test-----------------
			show_senior_info_button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent manageinfo = new Intent(getApplicationContext(),ManagerManageInfoActivity.class);
					startActivity(manageinfo);
				}
			});
			manage_senior_button.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					Intent managepulse = new Intent(getApplicationContext(),ManagerManagePulseActivity.class);
					startActivity(managepulse);
				}
			});
			//-----------------test-----------------

			return convertView;
		}
	}
}
