package com.example.amicus.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amicus.R;
import com.example.amicus.messages.MemoryData;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    List<ChatList> chatLists;
    Context context;
    String userMobile;

    public ChatAdapter(List<ChatList> chatLists, Context context) {
        this.chatLists = chatLists;
        this.context = context;
        this.userMobile = MemoryData.getPhone(context);
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_adapter_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {

        ChatList list2 =chatLists.get(position);

        if (list2.getMobile().equals(userMobile)) {
            holder.myLayout.setVisibility(View.VISIBLE);
            holder.oppoLayout.setVisibility(View.GONE);

            holder.myMessage.setText(list2.getMessage());
            holder.myMsgTime.setText(list2.getDate()+" "+list2.getTime());
        }else{
            holder.myLayout.setVisibility(View.GONE);
            holder.oppoLayout.setVisibility(View.VISIBLE);

            holder.oppoMessage.setText(list2.getMessage());
            holder.oppoMsgTime.setText(list2.getDate()+" "+list2.getTime());
        }

    }

    @Override
    public int getItemCount() {
        return chatLists.size();
    }

    public void updateChatList( List<ChatList> chatLists){
        this.chatLists = chatLists;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout oppoLayout;
        LinearLayout myLayout;
        TextView oppoMessage;
        TextView myMessage;
        TextView oppoMsgTime;
        TextView myMsgTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            oppoLayout =itemView.findViewById(R.id.oppoLayout);
            myLayout =itemView.findViewById(R.id.myLayout);
            oppoMessage =itemView.findViewById(R.id.oppoMessage);
            myMessage =itemView.findViewById(R.id.myMessage);
            oppoMsgTime =itemView.findViewById(R.id.oppoMsgTime);
            myMsgTime =itemView.findViewById(R.id.myMsgTime);
        }
    }
}
