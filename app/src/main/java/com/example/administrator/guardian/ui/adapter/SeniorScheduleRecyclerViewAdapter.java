package com.example.administrator.guardian.ui.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.SeniorRecyclerItem;
import com.example.administrator.guardian.datamodel.SeniorScheduleRecyclerItem;
import com.example.administrator.guardian.ui.activity.Senior.SeniorFragmentThreeScheduleAcceptDialog;
import com.example.administrator.guardian.ui.activity.Senior.SeniorFragmentThreeScheduleActivity;
import com.example.administrator.guardian.ui.activity.Senior.SeniorFragmentThreeScheduleFinishDialog;
import com.example.administrator.guardian.ui.activity.Senior.SeniorFragmentTwoDialog;

import java.util.List;

/**
 * Created by Administrator on 2016-05-22.
 */
public class SeniorScheduleRecyclerViewAdapter extends RecyclerView.Adapter<SeniorScheduleRecyclerViewAdapter.ViewHolder> {

    Context context;
    List<SeniorScheduleRecyclerItem> items;
    int item_layout;

    private SeniorFragmentThreeScheduleAcceptDialog mCustomDialog1;
    private SeniorFragmentThreeScheduleFinishDialog mCustomDialog2;

    public SeniorScheduleRecyclerViewAdapter(Context context, List<SeniorScheduleRecyclerItem> items, int item_layout) {

        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.senior_schedule_cardview, null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SeniorScheduleRecyclerItem item=items.get(position);

        holder.ssc_inputname.setText(item.getName());
        holder.ssc_inputage.setText(item.getAge()+"");
        holder.ssc_inputgender.setText(item.getGender());

        if(item.getType()==1){
            holder.ssc_inputname.setText("");
            holder.ssc_inputage.setText("");
            holder.ssc_inputgender.setText("");
            holder.ssc_age.setText("");
            holder.ssc_gender.setText("");
            holder.ssc_button.setText("신청중...");
        }
        else if(item.getType()==2){
            holder.ssc_button.setText("수락해줘");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCustomDialog1 = new SeniorFragmentThreeScheduleAcceptDialog(v.getContext(), item.getYear(), item.getMonth(), item.getDay(), item.getStartHour(), item.getStartMinute(), item.getFinishHour(), item.getFinishMinute(), item.getName(), item.getAge(), item.getGender());
                    mCustomDialog1.show();
                }
            });
        }
        else if(item.getType()==3){
            holder.ssc_button.setText("봉사예정");
        }
        else if(item.getType()==4){
            holder.ssc_button.setText("진행중...");
        }
        else{
            holder.ssc_button.setText("봉사완료!");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCustomDialog2 = new SeniorFragmentThreeScheduleFinishDialog(v.getContext(), item.getYear(), item.getMonth(), item.getDay(), item.getStartHour(), item.getStartMinute(), item.getFinishHour(), item.getFinishMinute(),item.getName(),item.getContents());
                    mCustomDialog2.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardview;
        TextView ssc_inputname;
        TextView ssc_inputage;
        TextView ssc_inputgender;
        TextView ssc_age;
        TextView ssc_gender;
        TextView ssc_button;

        public ViewHolder(View itemView) {
            super(itemView);

            ssc_age=(TextView)itemView.findViewById(R.id.ssc_age);
            ssc_gender=(TextView)itemView.findViewById(R.id.ssc_gender);
            ssc_inputname=(TextView)itemView.findViewById(R.id.ssc_inputname);
            ssc_inputage=(TextView)itemView.findViewById(R.id.ssc_inputage);
            ssc_inputgender=(TextView)itemView.findViewById(R.id.ssc_inputgender);
            ssc_button=(TextView)itemView.findViewById(R.id.ssc_button);

            cardview = (CardView)itemView.findViewById(R.id.senior_schedule_cardview);
        }
    }
}
