package com.example.amicus;

import static com.example.amicus.MainActivity.id;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amicus.API.RetrofitAPI;
import com.example.amicus.API.SaerchBody;
import com.example.amicus.API.SerachTravel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageFragment extends Fragment {

    RecyclerView recyclerView;
    VoditelAdapter voditelAdapter;
    List<VoditelData> voditelData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        recyclerView = view.findViewById(R.id.rec_voditel);
        voditelData = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        voditelAdapter = new VoditelAdapter(getActivity(),voditelData);
        recyclerView.setAdapter(voditelAdapter);

        RetrofitAPI api = RetrofitAPI.getInstance();
        Call <List<VoditelData>> call = api.getJSONApi().getVodila(id);
       call.enqueue(new Callback<List<VoditelData>>() {
           @Override
           public void onResponse(Call<List<VoditelData>> call, Response<List<VoditelData>> response) {
               voditelData = response.body();
               voditelAdapter.setVoditelData(voditelData);

           }

           @Override
           public void onFailure(Call<List<VoditelData>> call, Throwable t) {

           }
       });
        return view;
    }
}