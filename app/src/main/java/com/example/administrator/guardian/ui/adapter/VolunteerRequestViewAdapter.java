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
import com.example.administrator.guardian.datamodel.VolunteerRequestRecyclerItem;

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

        holder.vr_inputsname.setText(item.getName()+"");
        holder.vr_inputsage.setText(item.getAge()+"");
        holder.vr_inputsgender.setText(item.getGender());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getName()+"aa",Toast.LENGTH_SHORT).show();
                //implement onClick
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
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            vr_inputsname=(TextView)itemView.findViewById(R.id.vr_inputsname);
            vr_inputsage=(TextView)itemView.findViewById(R.id.s_inputsage);
            vr_inputsgender=(TextView)itemView.findViewById(R.id.vr_inputsgender);

            cardview = (CardView)itemView.findViewById(R.id.volunteer_request_cardview);

            /*itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(context,"aa",Toast.LENGTH_SHORT).show();
                    //write here the code for wathever you want to do with a card
                    //...
                }
            });*/
        }
    }
}
