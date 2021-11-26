package com.example.amicus.ui.my_travel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.amicus.R;

import com.example.amicus.databinding.FragmentMyTravelBinding;


public class My_travelFragment extends Fragment {

    private My_travelViewModel dashboardViewModel;
    private FragmentMyTravelBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(My_travelViewModel.class);

        binding = FragmentMyTravelBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.text;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

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