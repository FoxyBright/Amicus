package com.example.amicus;

import static com.example.amicus.MainActivity.id;
import static com.example.amicus.SearchFragment.idpoezdki;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amicus.API.AddTravelToresponce;
import com.example.amicus.API.AutoResponce;
import com.example.amicus.API.RetrofitAPI;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    Context context;
    static List<CardResponce> cardResponces;

    public CardListAdapter(Context context,List<CardResponce> cardResponces) {
        this.context = context;
        this.cardResponces = cardResponces;
    }

    public void setCardList(List<CardResponce> cardResponces) {
        this.cardResponces = cardResponces;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_view, parent, false);

        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
    holder.name.setText(cardResponces.get(position).getOwner());
    holder.number.setText(cardResponces.get(position).getNumber());
    holder.date.setText(cardResponces.get(position).getDate());
    String bank = cardResponces.get(position).getBanksystem();

    switch (bank){
        case "МИР":
            holder.imageView.setImageResource(R.drawable.mir);
            break;
        case "American Express":
            holder.imageView.setImageResource(R.drawable.americanexpress);
            break;
        case "VISA":
            holder.imageView.setImageResource(R.drawable.visa);
            break;
        case "MasterCard":
            holder.imageView.setImageResource(R.drawable.mastercard_logo);
            break;
        case "China UnionPay":
            holder.imageView.setImageResource(R.drawable.unionpay);
            break;
        default:
            holder.imageView.setImageResource(R.drawable.phone);
    }
    holder.cardLay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RetrofitAPI api = RetrofitAPI.getInstance();
            Call<AddTravelToresponce> call = api.getJSONApi().humanAdd(idpoezdki,id);
            call.enqueue(new Callback<AddTravelToresponce>() {
                @Override
                public void onResponse(Call<AddTravelToresponce> call, Response<AddTravelToresponce> response) {
                    Toast.makeText(context, "вы добавлены в поездку", Toast.LENGTH_SHORT).show();
                    SearchFragment fragment = new SearchFragment();
                    FragmentManager fm4 = ((Activity)context).getFragmentManager();
                    FragmentTransaction ft4 = fm4.beginTransaction();
                    ft4.replace(R.id.fragment_container,fragment);
                    ft4.commit();

                }

                @Override
                public void onFailure(Call<AddTravelToresponce> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    });
    }


    @Override
    public int getItemCount() {
        return cardResponces.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView date;
        TextView number;
        ImageView imageView;
        LinearLayout cardLay;



        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.owner);
            date = itemView.findViewById(R.id.date);
            number = itemView.findViewById(R.id.number);
            imageView = itemView.findViewById(R.id.banksystem);
            cardLay = itemView.findViewById(R.id.card_layout);

        }
    }
}
