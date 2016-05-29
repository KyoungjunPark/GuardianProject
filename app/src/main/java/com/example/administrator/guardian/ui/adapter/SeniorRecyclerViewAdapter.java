package com.example.administrator.guardian.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.SeniorRecyclerItem;
import com.example.administrator.guardian.ui.activity.Senior.SeniorFragmentTwoDialog;
import com.example.administrator.guardian.ui.activity.Volunteer.VolunteerFragmentOneRequestActivity;

import java.util.List;

/**
 * Created by Administrator on 2016-04-28.
 */
public class SeniorRecyclerViewAdapter extends RecyclerView.Adapter<SeniorRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<SeniorRecyclerItem> items;
    int globalVariable;
    int item_layout;
    private SeniorFragmentTwoDialog mCustomDialog;

    public SeniorRecyclerViewAdapter(Context context, List<SeniorRecyclerItem> items, int item_layout, int globalVariable) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
        this.globalVariable = globalVariable;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.senior_cardview, null);

        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SeniorRecyclerItem item=items.get(position);

        holder.name.setText(item.getName());
        holder.age.setText(item.getAge()+"");
        holder.gender.setText(item.getGender());
        holder.distance.setText(item.getDistance().toString());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(globalVariable == 0){
                    mCustomDialog = new SeniorFragmentTwoDialog(context, item.getName(), item.getAge(), item.getGender(), item.getAddress(), item.getTel());
                    mCustomDialog.show();
                }else if(globalVariable == 1){
                    Intent volunteerRequest = new Intent(context, VolunteerFragmentOneRequestActivity.class);
                    volunteerRequest.putExtra("name",item.getName());
                    volunteerRequest.putExtra("age",item.getAge());
                    volunteerRequest.putExtra("gender",item.getGender());
                    volunteerRequest.putExtra("address",item.getAddress());
                    volunteerRequest.putExtra("latitude",item.getLatitude());
                    volunteerRequest.putExtra("longitude",item.getLongitude());
                    context.startActivity(volunteerRequest);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView age;
        TextView gender;
        TextView distance;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.s_inputsname);
            age=(TextView)itemView.findViewById(R.id.s_inputsage);
            gender=(TextView)itemView.findViewById(R.id.s_inputsgender);
            distance=(TextView)itemView.findViewById(R.id.s_inputsdistance);
            cardview=(CardView)itemView.findViewById(R.id.senior_cardview);
        }
    }
}