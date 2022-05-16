package com.example.amicus;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amicus.API.AutoResponce;

import java.util.ArrayList;
import java.util.List;

public class AutoAdapterAdd extends RecyclerView.Adapter<AutoAdapterAdd.AutoViewHolder> {

    List<AutoResponce> autoModel;
    Context context;

    public AutoAdapterAdd(Context mContext,List<AutoResponce> autoModel){
        this.context = mContext;
        this.autoModel = autoModel;
    }

    public void setAutoList(List<AutoResponce> autoModel) {
        this.autoModel = autoModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AutoAdapterAdd.AutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_user_auto, parent, false);

        return new AutoAdapterAdd.AutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AutoAdapterAdd.AutoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(autoModel.get(position).getColor() + " " + autoModel.get(position).getModel() + " " +  autoModel.get(position).getStatenumber());
        String model = autoModel.get(position).getModel();
        String color = autoModel.get(position).getColor();
        String places = autoModel.get(position).getPlaces();
        String numb = autoModel.get(position).getStatenumber();
        String idAuto = autoModel.get(position).getId();
        holder.layout_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DeleteAuto.class);
                intent.putExtra("model",model);
                intent.putExtra("color",color);
                intent.putExtra("places",places);
                intent.putExtra("numb",numb);
                intent.putExtra("idAuto",idAuto);
                context.startActivity(intent);
                ((Activity)context).finish();
//                Intent intent = new Intent(context,AddActivity.class);
//                intent.putExtra("Autoname",autoModel.get(position).getColor() + " " + autoModel.get(position).getModel() + " " +  autoModel.get(position).getStatenumber());
//                context.startActivity(intent);
//                ((Activity)context).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return autoModel.size();
    }

    public class AutoViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        LinearLayout layout_rec;

        public AutoViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.machinka);
            layout_rec = itemView.findViewById(R.id.layout_rec);

        }
    }
}
