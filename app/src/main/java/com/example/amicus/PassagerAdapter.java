package com.example.amicus;

import static com.example.amicus.MainActivity.logo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PassagerAdapter extends RecyclerView.Adapter<PassagerAdapter.PassagerViewHolder> {

    Context context;
    List<PassagerData> passagerData;

    public PassagerAdapter(Context mContext,List<PassagerData> passagerData){
        this.context = mContext;
        this.passagerData = passagerData;
    }

    public void setPassagerData(List<PassagerData> passagerData) {
        this.passagerData = passagerData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PassagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add, parent, false);

        return new PassagerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassagerViewHolder holder, int position) {
        holder.adr_trav.setText(passagerData.get(position).getDepartureplace());
        holder.adr_arr.setText(passagerData.get(position).getArrivalplace());
        holder.time_dep.setText(passagerData.get(position).getDeparturetime());
        holder.time_arr.setText(passagerData.get(position).getArrivaltime());
        holder.passagir.setText(String.valueOf(passagerData.get(position).getMembercount()));
        holder.dayz.setText(passagerData.get(position).getWeekday());
        holder.bablo.setText(passagerData.get(position).getPrice() + "₽");
        holder.autoname.setText(passagerData.get(position).getAutorname());
        holder.auto_name.setText(passagerData.get(position).getAutomobile());
        holder.description_travel.setText(passagerData.get(position).getDescription());
        Glide.with(context).load(passagerData.get(position).getAutorphoto()).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.autophoto);
    }

    @Override
    public int getItemCount() {
        return passagerData.size();
    }

    public class PassagerViewHolder extends RecyclerView.ViewHolder {

        TextView adr_trav;
        TextView adr_arr;
        TextView time_dep;
        TextView time_arr;
        TextView passagir;
        TextView description_travel;
        TextView dayz;
        TextView auto_name;
        TextView autoname;
        CircleImageView autophoto;
        Button bablo;
        LinearLayout other;


        public PassagerViewHolder(@NonNull View itemView) {
            super(itemView);
            adr_trav = itemView.findViewById(R.id.adr_trav);
            time_dep = itemView.findViewById(R.id.time_dep);

            adr_arr = itemView.findViewById(R.id.adr_arr);
            time_arr = itemView.findViewById(R.id.time_arr);
            passagir = itemView.findViewById(R.id.passagir);
            dayz = itemView.findViewById(R.id.dayz);
            auto_name = itemView.findViewById(R.id.auto_name);
            description_travel = itemView.findViewById(R.id.description_travel);
            bablo = itemView.findViewById(R.id.bablo);
            autoname = itemView.findViewById(R.id.autoname);
            autophoto = itemView.findViewById(R.id.autophoto);
            other = itemView.findViewById(R.id.other);

        }
    }
}
