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
import com.example.administrator.guardian.ui.activity.Volunteer.VolunteerFragmentOneDialogActivity;

import java.util.List;

/**
 * Created by Administrator on 2016-05-10.
 */

public class VolunteerRequestViewAdapter extends RecyclerView.Adapter<VolunteerRequestViewAdapter.ViewHolder> {
    Context context;
    List<VolunteerRequestRecyclerItem> items;
    int item_layout;
    private VolunteerFragmentOneDialogActivity mCustomDialog;

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

        holder.v_inputsname.setText(item.getName()+"");
        holder.v_inputsage.setText(item.getAge()+"");
        holder.v_inputsgender.setText(item.getGender());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomDialog = new VolunteerFragmentOneDialogActivity(context, item.getName(), item.getAge(), item.getGender(), item.getAddress());
                mCustomDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView v_inputsname;
        TextView v_inputsage;
        TextView v_inputsgender;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            v_inputsname=(TextView)itemView.findViewById(R.id.v_inputsname);
            v_inputsage=(TextView)itemView.findViewById(R.id.v_inputsage);
            v_inputsgender=(TextView)itemView.findViewById(R.id.v_inputsgender);

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
