package com.example.amicus;


import android.content.Intent;
import android.os.Bundle;

import com.example.amicus.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNav.setItemIconTintList(null);



        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);
    }
}