package com.lesson10.bottomnavigation.fragment;

import static com.lesson10.bottomnavigation.Utils.notificationList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesson10.bottomnavigation.R;
import com.lesson10.bottomnavigation.adapter.NotificationAdapter;
import com.lesson10.bottomnavigation.model.Notification;

public class FragmentNotification extends Fragment {
    private NotificationAdapter notificationAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        notificationList.add(new Notification("Thắm", "Em ăn cơm chưa", "03:15", R.drawable.icon_char_b));
        notificationList.add(new Notification("Thi", "Mày ăn cơm chưa", "04:15", R.drawable.icon_char_b));
        notificationList.add(new Notification("Đạt", "Bạn ăn cơm chưa", "05:15", R.drawable.icon_char_b));
        notificationList.add(new Notification("Thành", "Anh ăn cơm chưa", "06:15", R.drawable.icon_char_b));
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false);
        notificationAdapter = new NotificationAdapter(view.getContext());
        recyclerView.setAdapter(notificationAdapter);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
