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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(
            "https://amicus-f81c5-default-rtdb.firebaseio.com");

    RecyclerView messageRecyclerView;
    MessagesAdapter messagesAdapter;
    String mobile;
    String name;
    List<MessagesList> messagesLists = new ArrayList<>();
    boolean dataSet = false;
    private int unseenmessages;
    private String lastMessage;
    private String chatKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messageRecyclerView = findViewById(R.id.messagesRecyclerView);
        CircleImageView userProfilePic = findViewById(R.id.usersProfilePic);
        mobile = MemoryData.getPhone(ChatActivity.this);
        name = MemoryData.getName(ChatActivity.this);
        messageRecyclerView.setHasFixedSize(true);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        messagesAdapter =new MessagesAdapter(messagesLists,ChatActivity.this);

        messageRecyclerView.setAdapter(messagesAdapter);


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Загрузка...");
        progressDialog.show();

        //get profilePic
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final String profilePic = snapshot.child("users").child(mobile).child("profile_pic").getValue(String.class);
                //set profile photo
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

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                messagesLists.clear();
                unseenmessages = 0;
                lastMessage = "";
                chatKey = "";
                for (DataSnapshot dataSnapshot : snapshot.child("users").getChildren()) {
                    final String getMobile = dataSnapshot.getKey();
                    dataSet = false;
                    if (!getMobile.equals(mobile)) {
                        final String getName = dataSnapshot.child("name").getValue(String.class);
                        final String getProfilePic = dataSnapshot.child("profile_pic").getValue(String.class);

                        databaseReference.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                int getChatCounts =(int)snapshot.getChildrenCount();

                                if (getChatCounts > 0) {
                                    for (DataSnapshot dataSnapshot1:snapshot.getChildren()) {
                                        String getKey = dataSnapshot1.getKey();
                                        chatKey = getKey;

                                        if (dataSnapshot1.hasChild("user_1")&&dataSnapshot1.hasChild("user_2")&&dataSnapshot1.hasChild("messages")){
                                            String getUserOne = dataSnapshot1.child("user_1").getValue(String.class);
                                            String getUserTwo = dataSnapshot1.child("user_2").getValue(String.class);

                                            if ((getUserOne.equals(getMobile) && getUserTwo.equals(mobile))||(getUserOne.equals(mobile)&&getUserTwo.equals(getMobile))) {
                                                for (DataSnapshot chatDataSnapshot: dataSnapshot1.child("messages").getChildren()) {

                                                    long getMessageKey = Long.parseLong(chatDataSnapshot.getKey());
                                                    long getLastSeenMessage = Long.parseLong(MemoryData.getLastMsgTS(ChatActivity.this,getKey));

                                                    lastMessage = chatDataSnapshot.child("msg").getValue(String.class);
                                                    if (getMessageKey>getLastSeenMessage){
                                                        unseenmessages++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (!dataSet) {
                                    dataSet = true;
                                    MessagesList messagesList = new MessagesList(getName,getMobile,lastMessage,getProfilePic,unseenmessages,chatKey);
                                    messagesLists.add(messagesList);
                                    messagesAdapter.updateData(messagesLists);
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}