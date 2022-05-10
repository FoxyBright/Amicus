package com.example.amicus;


import static com.example.amicus.MainActivity.id;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amicus.API.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageFragment1 extends Fragment {

    RecyclerView recyclerView;
    PassagerAdapter passagerAdapter;
    List<PassagerData> passagerData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page1, container, false);
        recyclerView = view.findViewById(R.id.rec_passager);
        passagerData = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        passagerAdapter = new PassagerAdapter(getActivity(),passagerData);
        recyclerView.setAdapter(passagerAdapter);

        RetrofitAPI api = RetrofitAPI.getInstance();
        Call<List<PassagerData>> call = api.getJSONApi().getPassager(id);
        call.enqueue(new Callback<List<PassagerData>>() {
            @Override
            public void onResponse(Call<List<PassagerData>> call, Response<List<PassagerData>> response) {
                passagerData = response.body();
                passagerAdapter.setPassagerData(passagerData);

            }

            @Override
            public void onFailure(Call<List<PassagerData>> call, Throwable t) {

            }
        });

        return view;
    }
}