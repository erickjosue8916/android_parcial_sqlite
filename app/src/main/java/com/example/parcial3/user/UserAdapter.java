package com.example.parcial3.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.parcial3.R;

import java.util.ArrayList;


public class UserAdapter extends BaseAdapter {
    private ArrayList<User> users;
    private Context context;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        User user = users.get(i);
        view = LayoutInflater.from(this.context).inflate(R.layout.user_item, null);
        TextView userName = view.findViewById(R.id.userName);
        TextView userEmail = view.findViewById(R.id.userEmail);
        TextView userAge = view.findViewById(R.id.userAge);

        userName.setText(user.name);
        userEmail.setText(user.email);
        userAge.setText(String.valueOf(user.age));
        return view;
    }
}
