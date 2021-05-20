package com.example.parcial3;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parcial3.tasks.Task;
import com.example.parcial3.tasks.TaskAdapter;
import com.example.parcial3.tasks.TaskService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TasksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TasksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TaskService taskService;

    ArrayList<Task> pendingtasks;
    ArrayList<Task> inProgressTasks;
    ArrayList<Task> completedtasks;

    ListView listPendingTasks;
    ListView listInProgressTasks;
    ListView listCompletedtasks;



    Context context;
    View view;

    public TasksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TasksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TasksFragment newInstance(String param1, String param2) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tasks, container, false);
        context = getContext();
        taskService = new TaskService(context);

        listPendingTasks = view.findViewById(R.id.listTaskPending);
        listInProgressTasks = view.findViewById(R.id.listTaskInProgress);
        listCompletedtasks = view.findViewById(R.id.listTasksCompleted);

        this.initializeTasks();
        return  view;
    }

    public void initializeTasks () {

        pendingtasks = taskService.getTasks("PENDING");
        inProgressTasks = taskService.getTasks("IN_PROGRESS");
        completedtasks = taskService.getTasks("COMPLETED");

        if (pendingtasks.size() == 0 && inProgressTasks.size() == 0 && completedtasks.size() == 0) {
            saveTasks();
        } else {
            pendingtasks = taskService.getTasks("PENDING");
            inProgressTasks = taskService.getTasks("IN_PROGRESS");
            completedtasks = taskService.getTasks("COMPLETED");
            defineListContent();
        }
    }

    public void saveTasks () {
        taskService.save(new Task("tarea 1", "descripcion 1", "PENDING"));
        taskService.save(new Task("tarea 2", "descripcion 2", "PENDING"));
        taskService.save(new Task("tarea 3", "descripcion 3", "IN_PROGRESS"));
        taskService.save(new Task("tarea 4", "descripcion 4", "IN_PROGRESS"));
        taskService.save(new Task("tarea 5", "descripcion 5", "COMPLETED"));
        taskService.save(new Task("tarea 6", "descripcion 6", "COMPLETED"));
        Toast.makeText(context, "Reopen application to show tasks", Toast.LENGTH_SHORT).show();
    }

    public void defineListContent() {
        TaskAdapter pedingAdapter = new TaskAdapter(context, pendingtasks);
        TaskAdapter inProgressAdapter = new TaskAdapter(context, inProgressTasks);
        TaskAdapter completedAdapter = new TaskAdapter(context, completedtasks);

        listPendingTasks.setAdapter(pedingAdapter);
        listInProgressTasks.setAdapter(inProgressAdapter);
        listCompletedtasks.setAdapter(completedAdapter);

    }
}