package com.example.amicus;

import static com.example.amicus.MainActivity.logo;
import static com.example.amicus.TravelAdapter.author;

import android.app.FragmentTransaction;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.amicus.API.GetUserData;
import com.example.amicus.API.RetrofitAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UsersData extends Fragment {

    ImageView profile_image;
    TextView profile_name;
    TextView email_name;
    TextView regdata;
    TextView facebook;
    TextView phone_edit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users_data, container, false);
        Button back_btn = view.findViewById(R.id.back_btn);
        profile_image = view.findViewById(R.id.profile_image);
        profile_name = view.findViewById(R.id.profile_name);
        email_name = view.findViewById(R.id.email_name);
        regdata = view.findViewById(R.id.regdata);
        facebook = view.findViewById(R.id.facebook);
        phone_edit = view.findViewById(R.id.phone_edit);

        RetrofitAPI api = RetrofitAPI.getInstance();
        Call<GetUserData> call = api.getJSONApi().getUsData(author);
        call.enqueue(new Callback<GetUserData>() {
            @Override
            public void onResponse(Call<GetUserData> call, Response<GetUserData> response) {
                GetUserData getUserData = response.body();
                profile_name.setText(getUserData.getName());
                email_name.setText(getUserData.getMail());
                regdata.setText(getUserData.getRegdata());
                facebook.setText(getUserData.getFacebook());
                phone_edit.setText(getUserData.getPhone());
                Glide.with(UsersData.this).load(getUserData.getPhoto()).diskCacheStrategy(DiskCacheStrategy.NONE).into(profile_image);


            }

            @Override
            public void onFailure(Call<GetUserData> call, Throwable t) {

            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO УБИТЬ ФРАГМЕНТ
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}