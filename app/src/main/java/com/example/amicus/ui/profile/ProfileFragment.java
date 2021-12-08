package com.example.amicus.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.amicus.AutoFragment;
import com.example.amicus.MainActivity;
import com.example.amicus.R;

import com.example.amicus.databinding.FragmentProfileBinding;



public class ProfileFragment extends Fragment {

    private ProfileViewModel dashboardViewModel;
    private FragmentProfileBinding binding;
    private LinearLayout fragment_replace_layout;
    private LinearLayout profile_layout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        LinearLayout fragment_replace_layout = root.findViewById(R.id.fragment_replace_layout);
        LinearLayout profile_layout = root.findViewById(R.id.profile_layout);

        Button auto_set_bt = root.findViewById(R.id.auto_set_bt);
        auto_set_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_replace_layout.setVisibility(View.VISIBLE);
                profile_layout.setVisibility(View.INVISIBLE);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fr_replace, new AutoFragment());
                ft.commit();
            }
        });


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}