package com.example.amicus.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.amicus.R;
import com.example.amicus.messages.MemoryData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
            "https://amicus-f81c5-default-rtdb.firebaseio.com");
    String getUserMobile="";
    String chatKey="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        ImageView sendBtn = findViewById(R.id.sendBtn);
        EditText messageEditText = findViewById(R.id.messageEditText);
        TextView nameTV = findViewById(R.id.name);
        ImageView backbtn = findViewById(R.id.backBtn);
        CircleImageView profilePic = findViewById(R.id.prodilePic);

        String getName = getIntent().getStringExtra("name");
        String getProfilePic = getIntent().getStringExtra("profile_pic");
        chatKey = getIntent().getStringExtra("chat_key");
        String getMobile = getIntent().getStringExtra("mobile");

        getUserMobile = MemoryData.getPhone(Chat.this);

        nameTV.setText(getName);
        Glide.with(Chat.this).load(getProfilePic).diskCacheStrategy(DiskCacheStrategy.NONE).into(profilePic);

        if (chatKey.isEmpty()) {

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    chatKey = "1";
                    if (snapshot.hasChild("chat")){
                        chatKey = String.valueOf(snapshot.child("chat").getChildrenCount()+1);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String getTxtMessage = messageEditText.getText().toString();

                    String currentTime = String.valueOf(System.currentTimeMillis()).substring(0,10);
                    MemoryData.saveLastMsgTS(currentTime,chatKey,Chat.this);
                    databaseReference.child("chat").child(chatKey).child("user_1").setValue(getUserMobile);
                    databaseReference.child("chat").child(chatKey).child("user_2").setValue(getMobile);
                    databaseReference.child("chat").child(chatKey).child("messages").child(currentTime).child("msg").setValue(getTxtMessage);
                    databaseReference.child("chat").child(chatKey).child("messages").child(currentTime).child("mobile").setValue(getUserMobile);
            }
        });
    }
}