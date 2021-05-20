package com.example.parcial3.tasks;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.parcial3.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends BaseAdapter {
    public ArrayList<Task> tasks;
    public Context context;

    public  TaskAdapter(Context context, ArrayList<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Task task = tasks.get(i);
        view =  LayoutInflater.from(this.context).inflate(R.layout.item_task_list, null);
        TextView title = view.findViewById(R.id.taskTilte);
        TextView description = view.findViewById(R.id.taskDescription);
        Button status = view.findViewById(R.id.taskStatus);

        title.setText(task.title);
        description.setText(task.description);
        status.setText(task.status);

        Bundle bundle = new Bundle();
        bundle.putInt("taskId", task.id);

        status.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_tasksFragment_to_taskDetails, bundle));

        return view;
    }
}