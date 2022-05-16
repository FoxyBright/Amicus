package com.example.amicus;

import static com.example.amicus.MainActivity.id;

import android.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amicus.API.AutoResponce;
import com.example.amicus.API.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CardListFragment extends Fragment {

    CardListAdapter cardListAdapter;
    List<CardResponce> cardResponces;
    RecyclerView recyclerView;
    LinearLayout AddNalichka;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card_list, container, false);

        cardResponces = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rec_view_card);
        AddNalichka = view.findViewById(R.id.nalichka);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cardListAdapter = new CardListAdapter(getActivity(),cardResponces);
        recyclerView.setAdapter(cardListAdapter);

        RetrofitAPI api = RetrofitAPI.getInstance();
        Call<List<CardResponce>> call = api.getJSONApi().cardListResponce(id);
        call.enqueue(new Callback<List<CardResponce>>() {
            @Override
            public void onResponse(Call<List<CardResponce>> call, Response<List<CardResponce>> response) {
                cardResponces = response.body();
                cardListAdapter.setCardList(cardResponces);
            }

            @Override
            public void onFailure(Call<List<CardResponce>> call, Throwable t) {

            }
        });

        return view;
    }
}