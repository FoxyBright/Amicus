package com.example.amicus.messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.amicus.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
            "https://amicus-f81c5-default-rtdb.firebaseio.com");

    RecyclerView messageRecyclerView;
    String mobile;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messageRecyclerView = findViewById(R.id.messagesRecyclerView);
        messageRecyclerView.setHasFixedSize(true);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CircleImageView userProfilePic = findViewById(R.id.usersProfilePic);
        mobile = MemoryData.getPhone(ChatActivity.this);
        name = MemoryData.getName(ChatActivity.this);


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Загрузка...");
        progressDialog.show();

        //get profilePic
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final String profilePic = snapshot.child("users").child(mobile).child("profile_pic").getValue(String.class);

                if (!profilePic.isEmpty()) {
                    Glide.with(ChatActivity.this).load(profilePic).diskCacheStrategy(DiskCacheStrategy.NONE).into(userProfilePic);
                }else{
                    Glide.with(ChatActivity.this).load("https://xn--80aaggtieo3biv.xn--p1ai/images/unload.jpg").diskCacheStrategy(DiskCacheStrategy.NONE).into(userProfilePic);
                }

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });
    }
}