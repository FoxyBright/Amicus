package com.example.amicus;

import static com.example.amicus.MainActivity.id;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amicus.API.AutoResponce;
import com.example.amicus.API.RetrofitAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerAutoFragment extends Fragment {

    AutoAdapterAdd autoAdapter;
    RecyclerView recyclerView;
    List<AutoResponce> autoResponces;
    Button save_bt_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_auto, container, false);

        autoResponces = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rec_view_add_auto);
        save_bt_add = view.findViewById(R.id.save_bt_add);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        autoAdapter = new AutoAdapterAdd(getActivity(),autoResponces);
        recyclerView.setAdapter(autoAdapter);

        RetrofitAPI api = RetrofitAPI.getInstance();
        Call<List<AutoResponce>> call = api.getJSONApi().autoUser(id);
        call.enqueue(new Callback<List<AutoResponce>>() {
            @Override
            public void onResponse(Call<List<AutoResponce>> call, Response<List<AutoResponce>> response) {
                autoResponces = response.body();
                autoAdapter.setAutoList(autoResponces);
            }

            @Override
            public void onFailure(Call<List<AutoResponce>> call, Throwable t) {
                Toast.makeText(getActivity(), "Авто не найдены", Toast.LENGTH_SHORT).show();
            }
        });

        save_bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new AutoFragment());
                ft4.commit();
            }
        });

        return view;
    }
}