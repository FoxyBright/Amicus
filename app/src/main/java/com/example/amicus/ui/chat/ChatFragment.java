package com.example.amicus.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.amicus.AutoFragment;
import com.example.amicus.FragmentOneChat;
import com.example.amicus.R;
import com.example.amicus.databinding.FragmentChatBinding;
import com.example.amicus.ui.profile.ProfileFragment;


public class ChatFragment extends Fragment {

    private ChatViewModel dashboardViewModel;
    private FragmentChatBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(ChatViewModel.class);

        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        LinearLayout replace = root.findViewById(R.id.replace);
        LinearLayout layout_chats = root.findViewById(R.id.layout_chats);
        RelativeLayout chat1 = root.findViewById(R.id.chat1);
        RelativeLayout chat2 = root.findViewById(R.id.chat2);
        RelativeLayout chat3 = root.findViewById(R.id.chat3);

        chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fr_replace, new FragmentOneChat());
                ft.commit();
                replace.setVisibility(View.VISIBLE);
                layout_chats.setVisibility(View.INVISIBLE);
            }
        });

        chat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fr_replace, new DialogFragment());
                ft.commit();
                replace.setVisibility(View.VISIBLE);
                layout_chats.setVisibility(View.INVISIBLE);
            }
        });

        chat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fr_replace, new ProfileFragment());
                ft.commit();
                replace.setVisibility(View.VISIBLE);
                layout_chats.setVisibility(View.INVISIBLE);
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