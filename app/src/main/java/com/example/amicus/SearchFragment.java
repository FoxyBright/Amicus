package com.example.amicus;

import static com.example.amicus.CountPassagersFragment.number;

import static com.example.amicus.DayChangeFragment.weekDays;
import static com.example.amicus.TimeChangeFragment.timeFrom1;
import static com.example.amicus.TimeChangeFragment.time_to1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amicus.API.RetrofitAPI;
import com.example.amicus.API.SaerchBody;
import com.example.amicus.API.SerachTravel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    TextView timeto;
    TextView timeFrom;
    TextView week;
    TextView pass;
    TextView weeks1;
    TextView error;
    EditText departurePlace;
    EditText arrivalPlace;
    RecyclerView recyclerView;
    TravelAdapter autoAdapter;
    List<SerachTravel> serachTravels;

    String str_from;
    String str_to;
    String  str_pass;
    String str_departure;
    String str_go_to;
    static int author;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        departurePlace = view.findViewById(R.id.departurePlace);
        arrivalPlace = view.findViewById(R.id.arrivalPlace);
        timeFrom = view.findViewById(R.id.timefrom);
        timeto = view.findViewById(R.id.timeto);
        pass = view.findViewById(R.id.pass);
        week = view.findViewById(R.id.week);
        recyclerView = view.findViewById(R.id.rec_view_tr);
        error = view.findViewById(R.id.error);
        timeFrom.setText(timeFrom1);
        timeto.setText(time_to1);
        pass.setText(String.valueOf(number));
        week.setText(weekDays);






        LinearLayout calendar = view.findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new DayChangeFragment());
                ft4.commit();
            }
        });

        LinearLayout time = view.findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new TimeChangeFragment());
                ft4.commit();
            }
        });

        LinearLayout people = view.findViewById(R.id.people);
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new CountPassagersFragment());
                ft4.commit();
            }
        });

        Button search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                serachTravels = new ArrayList<>();
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                autoAdapter = new TravelAdapter(getActivity(),serachTravels);
                recyclerView.setAdapter(autoAdapter);
                departurePlace.setText("Обнинск");
                arrivalPlace.setText("Москва");
                str_departure = departurePlace.getText().toString();
                str_go_to = arrivalPlace.getText().toString();
                str_from = timeFrom.getText().toString();
                str_to = timeto.getText().toString();
                str_pass = pass.getText().toString();
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
                        serachTravels = response.body();

                        if (response.body().size() == 0) {
                            recyclerView.setVisibility(View.GONE);
                            error.setVisibility(View.VISIBLE);
                        }else {
                            recyclerView.setVisibility(View.VISIBLE);
                            error.setVisibility(View.GONE);
                            autoAdapter.setSerachTravels(serachTravels);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<SerachTravel>> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
//                FragmentManager fm4 = getFragmentManager();
//                FragmentTransaction ft4 = fm4.beginTransaction();
//                ft4.replace(R.id.fragment_container, new SearchResultFragment());
//                ft4.commit();
            }
        });


        return view;
    }

}
