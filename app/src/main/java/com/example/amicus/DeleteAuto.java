package com.example.amicus;

import static com.example.amicus.AutoChangeFragment.idAuto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.amicus.API.AddAuto;
import com.example.amicus.API.AddBody;
import com.example.amicus.API.RetrofitAPI;
import com.example.amicus.API.UpdateAuto;
import com.example.amicus.API.UpdateBody;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteAuto extends AppCompatActivity {

    EditText model;
    EditText color;
    EditText places;
    EditText statenumber;
    Button back;
    Button save;
    Button del;
    String idAuto1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_auto);
        model = findViewById(R.id.model);
        color = findViewById(R.id.color);
        places = findViewById(R.id.places);
        back = findViewById(R.id.back_bt);
        statenumber = findViewById(R.id.statenumber);
        save = findViewById(R.id.save_bt);
        del = findViewById(R.id.del_bt);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            String model1 = arguments.getString("model");
            String color1 = arguments.getString("color");
            String places1 = arguments.getString("places");
            String numb1 = arguments.getString("numb");
            idAuto1 = arguments.getString("idAuto");
            model.setText(model1);
            color.setText(color1);
            places.setText(places1);
            statenumber.setText(numb1);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteAuto.this,ProfileSetting.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitAPI api = RetrofitAPI.getInstance();
                UpdateBody addBody = new UpdateBody();
                addBody.setAutoid(idAuto1);
                addBody.setColor(color.getText().toString());
                addBody.setModel(model.getText().toString());
                addBody.setPlaces(places.getText().toString());
                addBody.setStatenumber(statenumber.getText().toString());
                Call<UpdateAuto> call = api.getJSONApi().updateAuto(addBody);
                call.enqueue(new Callback<UpdateAuto>() {
                    @Override
                    public void onResponse(Call<UpdateAuto> call, Response<UpdateAuto> response) {

                    }

                    @Override
                    public void onFailure(Call<UpdateAuto> call, Throwable t) {

                    }
                });

            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}