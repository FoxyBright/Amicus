package com.example.amicus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

public class FragmentAppSettings extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_settings, container, false);

        Button back_bt = view.findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4= fm4.beginTransaction();
                ft4.replace(R.id.fragment_container, new ProfileFragment());
                ft4.commit();
            }
        });



        LinearLayout cash_clear = view.findViewById(R.id.cash_clear);
        cash_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCache(getContext());
                Toast.makeText(getActivity(), "Кэш успешно очищен!", Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout questions = view.findViewById(R.id.questions);
        questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "FAQ временно не доступно! Приносим свои извенения", Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout policy = view.findViewById(R.id.policy);
        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Сервис временно не доступен, пожалуйста попробуйте позже", Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout language = view.findViewById(R.id.language);
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Русский(Россия)", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

}