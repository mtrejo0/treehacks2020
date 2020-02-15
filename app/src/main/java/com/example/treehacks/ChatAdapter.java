package com.example.treehacks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Message> mMessages;
    private Context mContext;
    private String mUserId;

    public ChatAdapter(Context context, List<Message> messages) {
        mMessages = messages;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_chat, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = mMessages.get(position);
        final boolean doctor = message.doctor;

        if (!doctor) {

            holder.ivDoctor.setVisibility(View.INVISIBLE);
            holder.ivUser.setVisibility(View.VISIBLE);

        } else {
            holder.ivDoctor.setVisibility(View.VISIBLE);
            holder.ivUser.setVisibility(View.INVISIBLE);

        }
        holder.tvBody.setText(message.message);
    }


    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivDoctor;
        ImageView ivUser;
        TextView tvBody;;

        public ViewHolder(View itemView) {
            super(itemView);
            ivDoctor = (ImageView)itemView.findViewById(R.id.ivDoctor);
            ivUser = (ImageView)itemView.findViewById(R.id.ivUser);
            tvBody = (TextView)itemView.findViewById(R.id.tvBody);
        }
    }
}