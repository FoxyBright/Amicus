package com.example.amicus;

import static com.example.amicus.MainActivity.id;
import static com.example.amicus.MainActivity.phone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.amicus.API.AuthorizationBody;
import com.example.amicus.API.AuthorizationResponce;
import com.example.amicus.API.AutoArray;
import com.example.amicus.API.AutoBody;
import com.example.amicus.API.AutoResponce;
import com.example.amicus.API.RetrofitAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AutoChange extends AppCompatActivity {

    AutoAdapter autoAdapter;
    List<AutoResponce> autoResponces;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_change);
        autoResponces = new ArrayList<>();
        recyclerView = findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        autoAdapter = new AutoAdapter(this,autoResponces);
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
               Toast.makeText(AutoChange.this, "Авто не найдены", Toast.LENGTH_SHORT).show();
           }
       });
    }
}