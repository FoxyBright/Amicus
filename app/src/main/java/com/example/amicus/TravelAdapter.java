package com.example.amicus;

import static com.example.amicus.DayChangeFragment.weekDays;
import static com.example.amicus.MainActivity.logo;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.FragmentActivity;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.amicus.API.AutoResponce;
import com.example.amicus.API.RetrofitAPI;
import com.example.amicus.API.SaerchBody;
import com.example.amicus.API.SerachTravel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.TravelViewHolder> {

    List<SerachTravel> serachTravels;
    Context context;

    String str_from;
    String str_to;
    String  str_pass;
    String str_departure;
    String str_go_to;
    int position1;
    public static int author;

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
        str_departure = serachTravels.get(position).getDepartureplace();
        str_go_to = serachTravels.get(position).getArrivalplace();
        str_from = serachTravels.get(position).getDeparturetime();
        str_to = serachTravels.get(position).getArrivaltime();
        str_pass = String.valueOf(serachTravels.get(position).getMembercount());
        holder.autophoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position1 = holder.getAdapterPosition();
                System.out.println("Позиция "+ position1);
                RetrofitAPI api = RetrofitAPI.getInstance();
                SaerchBody saerchBody = new SaerchBody();
                saerchBody.departureplace = str_departure;
                saerchBody.arrivalplace = str_go_to;
                saerchBody.membercount = str_pass;
                saerchBody.weekday = weekDays;
                saerchBody.departuretime = str_from;
                saerchBody.arrivaltime = str_to;

                Call<List<SerachTravel>> call = api.getJSONApi().searchTrav(saerchBody);
                call.enqueue(new Callback<List<SerachTravel>>() {
                    @Override
                    public void onResponse(Call<List<SerachTravel>> call, Response<List<SerachTravel>> response) {
                        String jsonstr = new Gson().toJson(serachTravels = response.body());

                        try {
                            JSONArray jsonArray = new JSONArray(jsonstr);
                            String jsonstr1 = new Gson().toJson(jsonArray.getJSONObject(position1));
                            String jsonstr2 = jsonstr1.substring(18,jsonstr1.length()-1);
                            SerachTravel serachTravel = new Gson().fromJson(jsonstr2,SerachTravel.class);
                            author = serachTravel.getAutor();
                            UsersData newsFragment = new UsersData();
                            AppCompatActivity activity = (AppCompatActivity) v.getContext();
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newsFragment).addToBackStack(null).commit();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(Call<List<SerachTravel>> call, Throwable t) {

                    }
                });
                System.out.println("Автор "+ author);


            }
        });


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

//            autophoto.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {

//                    UsersData newsFragment = new UsersData();
//                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newsFragment).addToBackStack(null).commit();
//                }
//            });

        }
    }
}

