package com.example.amicus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_view, container, false);

        Button back_bt = view.findViewById(R.id.back_bt);

        LinearLayout pay_set_layout = view.findViewById(R.id.card_layout);
        LinearLayout view_data_card = view.findViewById(R.id.view_data_card);
        LinearLayout edit_card = view.findViewById(R.id.edit_card);
        TextView card_num = view.findViewById(R.id.card_num);
        TextView cvv = view.findViewById(R.id.cvv);
        TextView show_hide_data = view.findViewById(R.id.show_hide_data);

        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new FragmentPaySetting());
                ft.commit();

            }
        });

        view_data_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (show_hide_data.getText() == "Показать данные"){

                    card_num.setText("4950 4525 6485 4562");
                    cvv.setText("154");
                    show_hide_data.setText("Скрыть данные");

                }
                else {

                        card_num.setText("4950 45XX XXXX XXXX");
                        cvv.setText("XXX");
                        show_hide_data.setText("Показать данные");
                }
            }
        });

        edit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new CardFragment());
                ft.commit();

            }
        });

        Button save_bt = view.findViewById(R.id.save_bt);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new FragmentPaySetting());
                ft4.commit();
            }
        });

        return view;
    }
}