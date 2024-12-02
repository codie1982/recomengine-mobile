package com.grnt.chatbot_test.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grnt.chatbot_test.R;
import com.grnt.chatbot_test.constant.DataType;
import com.grnt.chatbot_test.model.Message;

import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int SYSTEM_MESSAGE = 1;
    int HUMAN_MESSAGE = 0;
    List<Message> chatMessages;

    public ChatAdapter(List<Message> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == SYSTEM_MESSAGE){
            View view = inflater.inflate(R.layout.system_message_item_view,parent,false);
            return new SystemMessageViewholder(view);
        }else if(viewType == HUMAN_MESSAGE){
            View view = inflater.inflate(R.layout.human_message_item_view,parent,false);
            return new HumanMessageViewholder(view);
        }else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SystemMessageViewholder){
            ((SystemMessageViewholder) holder).txtSystemMessage.setText(chatMessages.get(position).getMessage());
        }else  if(holder instanceof HumanMessageViewholder){
            ((HumanMessageViewholder) holder).txtHumanMessage.setText(chatMessages.get(position).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatMessages.get(position).getType().equals(DataType.HUMAN_MESSAGE.getDescription())){
            return 0;
        }else if (chatMessages.get(position).getType().equals(DataType.SYSTEM_MESSAGE.getDescription())){
            return 1;
        }
        return 1;
    }

    public  class HumanMessageViewholder extends RecyclerView.ViewHolder{
        TextView txtHumanMessage;
        public HumanMessageViewholder(@NonNull View itemView) {
            super(itemView);
            txtHumanMessage = itemView.findViewById(R.id.txtHumanMessage);
        }
    }
    public  class SystemMessageViewholder extends RecyclerView.ViewHolder{
        TextView txtSystemMessage;
        public SystemMessageViewholder(@NonNull View itemView) {
            super(itemView);
            txtSystemMessage = itemView.findViewById(R.id.txtSystemMessage);
        }
    }

}
