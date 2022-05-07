package com.example.amicus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amicus.API.AddAuto;
import com.example.amicus.API.AddBodyAuto;
import com.example.amicus.API.RetrofitAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AutoFragment extends Fragment {

    EditText model;
    EditText color;
    EditText places;
    EditText statenumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auto, container, false);
        Button back_bt = view.findViewById(R.id.back_bt);
        model = view.findViewById(R.id.model);
        color = view.findViewById(R.id.color);
        places = view.findViewById(R.id.places);
        statenumber = view.findViewById(R.id.statenumber);

        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new ProfileFragment());
                ft.commit();
            }
        });

        Button save_bt = view.findViewById(R.id.save_bt);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String model1 = model.getText().toString();
                String color1 = color.getText().toString();
                String places1 = places.getText().toString();
                String statenumber1 = statenumber.getText().toString();

                RetrofitAPI api = RetrofitAPI.getInstance();
                AddBodyAuto addBodyAuto = new AddBodyAuto();
                addBodyAuto.setStatenumber(statenumber1);
                addBodyAuto.setPlaces(places1);
                addBodyAuto.setColor(color1);
                addBodyAuto.setModel(model1);
                addBodyAuto.setOwner(MainActivity.id);
                Call<AddAuto> call = api.getJSONApi().addauto(addBodyAuto);
                call.enqueue(new Callback<AddAuto>() {
                    @Override
                    public void onResponse(Call<AddAuto> call, Response<AddAuto> response) {
                    }

                    @Override
                    public void onFailure(Call<AddAuto> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new ProfileFragment());
                ft.commit();
            }

        });

        return view;




    }
}