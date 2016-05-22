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
import com.example.administrator.guardian.datamodel.SeniorRecyclerItem;
import com.example.administrator.guardian.ui.activity.Senior.SeniorFragmentTwoDialog;

import java.util.List;

/**
 * Created by Administrator on 2016-04-28.
 */
public class SeniorRecyclerViewAdapter extends RecyclerView.Adapter<SeniorRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<SeniorRecyclerItem> items;
    int item_layout;
    private SeniorFragmentTwoDialog mCustomDialog;

    public SeniorRecyclerViewAdapter(Context context, List<SeniorRecyclerItem> items, int item_layout) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
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


        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCustomDialog = new SeniorFragmentTwoDialog(context, item.getName(), item.getAge(), item.getGender(), item.getAddress(), item.getNumber());
                mCustomDialog.show();

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
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.s_inputsname);
            age=(TextView)itemView.findViewById(R.id.s_inputsage);
            gender=(TextView)itemView.findViewById(R.id.s_inputsgender);

            cardview=(CardView)itemView.findViewById(R.id.senior_cardview);
        }
    }
}