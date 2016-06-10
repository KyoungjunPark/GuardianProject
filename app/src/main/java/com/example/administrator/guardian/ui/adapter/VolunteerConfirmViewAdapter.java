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

        holder.vcc_inputage.setText(item.getSenior_age()+"");
        holder.vcc_inputgender.setText(item.getSenior_gender());

        if(item.getSenior_gender().compareTo("남") == 0){
            holder.vcc_inputname.setText(item.getSenior_name()+" 할아버지");
        }
        else{holder.vcc_inputname.setText(item.getSenior_name()+" 할머니");}

        // 수락대기, 수락완료, 요청중, 요청완료, 기간만료, 진행중, 완료, 서명필요
        if(item.getType() == 0){
            holder.vcc_button.setText("수락대기");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCustomDialog1 = new VolunteerFragmentTwoAcceptDialog(v.getContext(), item.getSenior_id() ,item.getStartInfo(), item.getReq_hour(), item.getSenior_name(), item.getSenior_age(), item.getSenior_gender(), item.getSenior_address(), item.getLatitude(), item.getLongitude(), item.getDetails());
                     mCustomDialog1.show();
                    notifyDataSetChanged();
                }
            });
        }
        else if(item.getType() == 1){
            holder.vcc_button.setText("수락완료");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(item.getType() == 2){
            holder.vcc_button.setText("요청중");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(item.getType() == 3){
            holder.vcc_button.setText("요청완료");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(item.getType() == 4){
            holder.vcc_button.setText("기간만료");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(item.getType() == 5){
            holder.vcc_button.setText("진행중");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(item.getType() == 6){
            holder.vcc_button.setText("완료");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCustomDialog2 = new VolunteerFragmentTwoFinishDialog(context, item.getSenior_name(), item.getSenior_gender(), item.getDetails());
                    mCustomDialog2.show();
                    notifyDataSetChanged();
                }
            });
        }
        else {
            holder.vcc_button.setText("서명필요");
            holder.cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, item.getDetails(), Toast.LENGTH_SHORT).show();
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