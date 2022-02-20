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
import com.example.amicus.API.AutoResponce;
import com.example.amicus.API.SerachTravel;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {

    List<SerachTravel> serachTravels;
    Context context;

    public TravelAdapter(Context mContext,List<SerachTravel> serachTravels){
        this.context = mContext;
        this.serachTravels = serachTravels;
    }

    public void setSerachTravels(List<SerachTravel> serachTravels) {
        this.serachTravels = serachTravels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TravelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add, parent, false);

        return new TravelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelViewHolder holder, int position) {
        holder.adr_trav.setText(serachTravels.get(position).getDepartureplace());
        holder.adr_arr.setText(serachTravels.get(position).getArrivalplace());
        holder.time_dep.setText(serachTravels.get(position).getDeparturetime());
        holder.time_arr.setText(serachTravels.get(position).getArrivaltime());
        holder.passagir.setText(String.valueOf(serachTravels.get(position).getMembercount()));
        holder.dayz.setText(serachTravels.get(position).getWeekday());
        holder.bablo.setText(serachTravels.get(position).getPrice() + "₽");
        holder.autoname.setText(serachTravels.get(position).getAutorname());
        holder.auto_name.setText(serachTravels.get(position).getAutomobile());
        holder.description_travel.setText(serachTravels.get(position).getDescription());
        Glide.with(context).load(logo).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.autophoto);


    }

    @Override
    public int getItemCount() {
        return serachTravels.size();
    }

    public class TravelViewHolder extends RecyclerView.ViewHolder {

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


        public TravelViewHolder(@NonNull View itemView) {
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

            bablo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    other.setVisibility(View.VISIBLE);

                }
            });

        }
    }
}

