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

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.VolunteerConfirmRecyclerItem;
import com.example.administrator.guardian.ui.activity.Volunteer.VolunteerFragmentTwoAcceptDialog;
import com.example.administrator.guardian.ui.activity.Volunteer.VolunteerFragmentTwoFinishDialog;

import java.util.List;

/**
 * Created by Administrator on 2016-05-21.
 */
public class VolunteerConfirmViewAdapter extends  RecyclerView.Adapter<VolunteerConfirmViewAdapter.ViewHolder>{

    Context context;
    List<VolunteerConfirmRecyclerItem> items;
    int item_layout;
    private VolunteerFragmentTwoAcceptDialog mCustomDialog1;
    private VolunteerFragmentTwoFinishDialog mCustomDialog2;

    public VolunteerConfirmViewAdapter(Context context, List<VolunteerConfirmRecyclerItem> items, int item_layout) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.volunteer_confirm_cardview, null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final VolunteerConfirmRecyclerItem item=items.get(position);

        holder.vcc_inputage.setText(item.getAge()+"");
        holder.vcc_inputgender.setText(item.getGender());

        if(item.getGender()=="남"){
            holder.vcc_inputname.setText(item.getName()+" 할아버지");
        }
        else{holder.vcc_inputname.setText(item.getName()+" 할머니");}

        if(item.getType()==1){
            holder.vcc_button.setText("신청중...");
        }
        else if(item.getType()==2){
            holder.vcc_button.setText("수락해줘");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCustomDialog1 = new VolunteerFragmentTwoAcceptDialog(context, item.getYear(), item.getMonth(), item.getDay(),
                            item.getStartHour(), item.getStartMinute(), item.getFinishHour(), item.getFinishMinute(), item.getName(), item.getAge(), item.getGender(), item.getAddress(), item.getContents());
                    mCustomDialog1.show();
                }
            });
        }
        else if(item.getType()==3){
            holder.vcc_button.setText("봉사예정");
        }
        else if(item.getType()==4){
            holder.vcc_button.setText("진행중...");
        }
        else{
            holder.vcc_button.setText("봉사완료!");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCustomDialog2 = new VolunteerFragmentTwoFinishDialog(context, item.getYear(), item.getMonth(), item.getDay(),
                            item.getStartHour(), item.getStartMinute(), item.getFinishHour(), item.getFinishMinute(),item.getName(),item.getGender(),item.getContents());
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

        TextView vcc_inputname;
        TextView vcc_inputage;
        TextView vcc_inputgender;
        TextView vcc_button;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            vcc_inputname=(TextView)itemView.findViewById(R.id.vcc_inputname);
            vcc_inputage=(TextView)itemView.findViewById(R.id.vcc_inputage);
            vcc_inputgender=(TextView)itemView.findViewById(R.id.vcc_inputgender);
            vcc_button=(TextView)itemView.findViewById(R.id.vcc_button);

            cardview = (CardView)itemView.findViewById(R.id.volunteer_confirm_cardview);
        }
    }
}