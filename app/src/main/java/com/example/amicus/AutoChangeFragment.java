package com.example.amicus;

import static com.example.amicus.MainActivity.id;
import static com.example.amicus.MainActivity.phone;

import android.app.Fragment;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.amicus.API.AutoBody;
import com.example.amicus.API.AutoResponce;
import com.example.amicus.API.RetrofitAPI;
import com.example.amicus.API.SerachTravel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AutoChangeFragment extends Fragment {

    AutoAdapter autoAdapter;
    List<AutoResponce> autoResponces;
    RecyclerView recyclerView;
    static String idAuto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auto_change, container, false);
        autoResponces = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rec_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        autoAdapter = new AutoAdapter(getActivity(),autoResponces);
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

        return view;
    }
}