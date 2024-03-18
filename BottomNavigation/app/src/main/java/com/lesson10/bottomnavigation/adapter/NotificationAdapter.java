package com.lesson10.bottomnavigation.adapter;

import static com.lesson10.bottomnavigation.Utils.notificationList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesson10.bottomnavigation.R;
import com.lesson10.bottomnavigation.model.Notification;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private Context context;

    public NotificationAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        holder.img.setImageResource(notification.getImg());
        holder.name.setText(notification.getName());
        holder.content.setText(notification.getContent());
        holder.time.setText(notification.getTime());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }


    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name, content, time;
        public NotificationViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            name = view.findViewById(R.id.name);
            content = view.findViewById(R.id.content);
            time = view.findViewById(R.id.time);
        }
    }
}
