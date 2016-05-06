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
import com.example.administrator.guardian.datamodel.SeniorRecyclerItem;

import java.util.List;

/**
 * Created by Administrator on 2016-04-28.
 */
public class SeniorRecyclerViewAdapter extends RecyclerView.Adapter<SeniorRecyclerViewAdapter.ViewHolder> {
    Context context;
    List<SeniorRecyclerItem> items;
    int item_layout;

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
        holder.vcallbutton.setOnClickListener(new View.OnClickListener() {
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

        TextView name;
        TextView age;
        TextView gender;
        Button  vcallbutton;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.inputvname);
            age=(TextView)itemView.findViewById(R.id.inputvage);
            gender=(TextView)itemView.findViewById(R.id.inputvgender);
            vcallbutton=(Button)itemView.findViewById(R.id.vcallbutton);

            cardview=(CardView)itemView.findViewById(R.id.senior_cardview);
        }
    }
}