package com.example.administrator.guardian.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.VolunteerRequestRecyclerItem;
import com.example.administrator.guardian.datamodel.VolunteerTimeRecyclerItem;

import java.util.List;

/**
 * Created by Administrator on 2016-05-10.
 */

public class VolunteerRequestViewAdapter extends RecyclerView.Adapter<VolunteerRequestViewAdapter.ViewHolder> {
    Context context;
    List<VolunteerRequestRecyclerItem> items;
    int item_layout;

    public VolunteerRequestViewAdapter(Context context, List<VolunteerRequestRecyclerItem> items, int item_layout) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.volunteer_request_cardview, null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final VolunteerRequestRecyclerItem item=items.get(position);

        holder.vr_inputsname.setText(item.getName()+" 님께서 방문을 요청하셨습니다.");
        holder.vr_inputsage.setText(item.getAge()+"");
        holder.vr_inputsgender.setText(item.getGender());
        holder.vr_inputsaddress.setText(item.getAddress());

        holder.vr_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.vr_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView vr_inputsname;
        TextView vr_inputsage;
        TextView vr_inputsgender;
        TextView vr_inputsaddress;
        Button vr_ok;
        Button vr_no;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            vr_inputsname=(TextView)itemView.findViewById(R.id.vr_inputsname);
            vr_inputsage=(TextView)itemView.findViewById(R.id.vr_inputsage);
            vr_inputsgender=(TextView)itemView.findViewById(R.id.vr_inputsgender);
            vr_inputsaddress=(TextView)itemView.findViewById(R.id.vr_inputsaddress);
            vr_ok=(Button)itemView.findViewById(R.id.vr_ok);
            vr_no=(Button)itemView.findViewById(R.id.vr_no);

            cardview=(CardView)itemView.findViewById(R.id.volunteer_cardview);
        }
    }
}
