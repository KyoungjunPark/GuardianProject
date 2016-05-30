package com.example.administrator.guardian.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.SeniorScheduleRecyclerItem;
import com.example.administrator.guardian.ui.activity.Senior.SeniorFragmentThreeScheduleAcceptDialog;
import com.example.administrator.guardian.ui.activity.Senior.SeniorFragmentThreeScheduleFinishDialog;

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
        if(item.getName().compareTo("") == 0){
            holder.ssc_inputname.setText("-");
        }else{
            holder.ssc_inputname.setText(item.getName());
        }
        if(item.getAge() == 0){
            holder.ssc_inputage.setText("-");
        }else {
            holder.ssc_inputage.setText(item.getAge()+"");
        }
        if(item.getGender().compareTo("")== 0){
            holder.ssc_inputgender.setText("-");
        }else{
            holder.ssc_inputgender.setText(item.getGender());
        }
        //r c s
        //0 0 요청중 -0    //
        //0 1 요청완료 -1   //
        //1 0 수락대기 -2   //
        //1 1 수락완료 -3   //
        //  2 기간만료 -4   //
        //0 1 0 진행시간내 진행중 -5
        //1 1 0
        //    1 완료
        if(item.getType() == 0){
            holder.ssc_button.setText("요청중");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }else if(item.getType()==1){
            holder.ssc_button.setText("요청완료");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(item.getType()==2){
            holder.ssc_button.setText("확인대기");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCustomDialog1 = new SeniorFragmentThreeScheduleAcceptDialog(v.getContext(), item.getStartInfo(), item.getDetails(), item.getReqHour(), item.getName(), item.getAge(), item.getGender());
                    mCustomDialog1.show();
                }
            });
        }
        else if(item.getType()==3){
            holder.ssc_button.setText("확인완료");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(item.getType()==4){
            holder.ssc_button.setText("기간만료");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }else if(item.getType() == 5){
            holder.ssc_button.setText("진행중");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }else if(item.getType() == 6){
            holder.ssc_button.setText("완료");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            holder.ssc_button.setText("서명필요");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCustomDialog2 = new SeniorFragmentThreeScheduleFinishDialog(v.getContext(), item.getStartInfo(), item.getReqHour(), item.getName(), item.getDetails());
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
