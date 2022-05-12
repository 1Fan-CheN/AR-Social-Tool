package com.example.argreeting.ui.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.argreeting.R;
import com.example.argreeting.bean.Message;

import java.util.List;

public class MessagesRecViewAdapter extends RecyclerView.Adapter<MessagesRecViewAdapter.ViewHolder> {

    private List<Message> messages;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_messages_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.senderAvatar.setImageResource(R.mipmap.ic_launcher_round);
//        holder.senderAvatar.setImageBitmap(messages.get(position).getUser().getAvatar());
        holder.senderName.setText(messages.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        if (messages != null) {
            return messages.size();
        }
        return 0;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView senderAvatar;
        private TextView senderName;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            senderAvatar = itemView.findViewById(R.id.itemMsgAvatar);
            senderName = itemView.findViewById(R.id.itemMsgSender);
            parent = itemView.findViewById(R.id.itemMsgParent);

        }
    }
}
