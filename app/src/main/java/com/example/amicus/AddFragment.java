package com.example.amicus;

import static com.example.amicus.CountPassagersFragment.number;
import static com.example.amicus.CountPassagersFragmentAdd.number1;
import static com.example.amicus.DayChangeFragment.weekDays;
import static com.example.amicus.DayChangeFragmentAdd.weekDays1;
import static com.example.amicus.MainActivity.id;
import static com.example.amicus.MainActivity.logo;
import static com.example.amicus.MainActivity.name1;
import static com.example.amicus.MainActivity.phone;
import static com.example.amicus.TimeChangeFragment.timeFrom1;
import static com.example.amicus.TimeChangeFragment.time_to1;
import static com.example.amicus.TimeChangeFragmentAdd.timeFrom2;
import static com.example.amicus.TimeChangeFragmentAdd.time_to2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amicus.API.AddBody;
import com.example.amicus.API.AddTravelResponce;
import com.example.amicus.API.AuthorizationBody;
import com.example.amicus.API.AuthorizationResponce;
import com.example.amicus.API.AutoBody;
import com.example.amicus.API.RetrofitAPI;
import com.example.amicus.API.SaerchBody;
import com.example.amicus.API.SerachTravel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFragment extends Fragment {

    LinearLayout autoChange;
    TextView text_auto;
    TextView timeto;
    TextView timeFrom;
    TextView week;
    TextView pass;
    TextView weeks1;
    EditText departurePlace;
    EditText arrivalPlace;
    EditText cost;
    EditText description;
    Button save_bt;
    String myStr;
    int hourFrom,minuteFrom;
    int hourTo,minuteTo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        autoChange = view.findViewById(R.id.autoChange);
        text_auto = view.findViewById(R.id.text_auto);

        departurePlace = view.findViewById(R.id.departurePlace);
        arrivalPlace = view.findViewById(R.id.arrivalPlace);
        timeFrom = view.findViewById(R.id.timefrom);
        timeto = view.findViewById(R.id.timeto);
        pass = view.findViewById(R.id.pass);
        week = view.findViewById(R.id.week);
        cost = view.findViewById(R.id.cost);
        description = view.findViewById(R.id.description);

        text_auto = view.findViewById(R.id.text_auto);
        autoChange = view.findViewById(R.id.autoChange);
        save_bt = view.findViewById(R.id.save_bt);
        timeFrom.setText(timeFrom2);
        timeto.setText(time_to2);
        pass.setText(String.valueOf(number1));
        week.setText(weekDays1);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            myStr = getArguments().getString("auto");
            text_auto.setText(myStr);
        }



        LinearLayout calendar = view.findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new DayChangeFragmentAdd());
                ft4.commit();
            }
        });

        LinearLayout time = view.findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener1 = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedhour, int selectedminute) {
                        hourTo = selectedhour;
                        minuteTo= selectedminute;
                        timeto.setText(String.format(Locale.getDefault(), "%02d:%02d", hourTo, minuteTo));
                    }
                };
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(getActivity(), onTimeSetListener1, hourTo, hourTo, true);
                timePickerDialog1.show();

                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedhour, int selectedminute) {
                        hourFrom = selectedhour;
                        minuteFrom = selectedminute;
                        timeFrom.setText(String.format(Locale.getDefault(),"%02d:%02d",hourFrom,minuteFrom));
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),onTimeSetListener,hourFrom,minuteFrom,true);
                timePickerDialog.show();
            }
        });

        LinearLayout people = view.findViewById(R.id.people);
        people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new CountPassagersFragmentAdd());
                ft4.commit();
            }
        });

        autoChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new AutoChangeFragment());
                ft4.commit();
            }
        });

        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               String str_departure = departurePlace.getText().toString();
                String str_go_to = arrivalPlace.getText().toString();
                String str_from = timeFrom.getText().toString();
                String str_to = timeto.getText().toString();
                String str_pass = pass.getText().toString();
                String cost1 = cost.getText().toString();
                String description1 = description.getText().toString();
                if (str_departure.equals("") || str_go_to.equals("") || cost1.equals("")) {
                    Toast.makeText(getActivity(), "Заполните поля", Toast.LENGTH_SHORT).show();
                }else {
                    RetrofitAPI api = RetrofitAPI.getInstance();
                    AddBody addBody = new AddBody();
                    addBody.departureplace = str_departure;
                    addBody.arrivalplace = str_go_to;
                    addBody.departuretime = str_from;
                    addBody.arrivaltime = str_to;
                    addBody.membercount = str_pass;
                    addBody.weekday = weekDays1;
                    addBody.automobile = myStr;
                    addBody.price = cost1;
                    addBody.description = description1;
                    addBody.autor = phone;
                    addBody.autorphoto = logo;
                    addBody.autorname = name1;
                    Call<AddTravelResponce> call = api.getJSONApi().addTravel(addBody, id);
                    call.enqueue(new Callback<AddTravelResponce>() {
                        @Override
                        public void onResponse(Call<AddTravelResponce> call, Response<AddTravelResponce> response) {
                            Toast.makeText(getActivity(), "Поездка создана", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<AddTravelResponce> call, Throwable t) {

                        }
                    });

                }
            }
        });


        return view;
    }
}
