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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.amicus.API.RetrofitAPI;
import com.example.amicus.API.SaerchBody;
import com.example.amicus.API.SerachTravel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;

public class VoditelAdapter extends RecyclerView.Adapter<VoditelAdapter.VoditelViewHolder> {

    Context context;
    List<VoditelData> voditelData;

    public VoditelAdapter(Context mContext,List<VoditelData> serachTravels){
        this.context = mContext;
        this.voditelData = serachTravels;
    }

    public void setVoditelData(List<VoditelData> serachTravels) {
        this.voditelData = serachTravels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VoditelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add, parent, false);

        return new VoditelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoditelViewHolder holder, int position) {
        holder.adr_trav.setText(voditelData.get(position).getDepartureplace());
        holder.adr_arr.setText(voditelData.get(position).getArrivalplace());
        holder.time_dep.setText(voditelData.get(position).getDeparturetime());
        holder.time_arr.setText(voditelData.get(position).getArrivaltime());
        holder.passagir.setText(String.valueOf(voditelData.get(position).getMembercount()));
        holder.dayz.setText(voditelData.get(position).getWeekday());
        holder.bablo.setText(voditelData.get(position).getPrice() + "₽");
        holder.autoname.setText(voditelData.get(position).getAutorname());
        holder.auto_name.setText(voditelData.get(position).getAutomobile());
        holder.description_travel.setText(voditelData.get(position).getDescription());
        Glide.with(context).load(voditelData.get(position).getAutorphoto()).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.autophoto);
    }

    @Override
    public int getItemCount() {
        return voditelData.size();
    }

    public class VoditelViewHolder extends RecyclerView.ViewHolder {

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


    public VoditelViewHolder(@NonNull View itemView) {
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