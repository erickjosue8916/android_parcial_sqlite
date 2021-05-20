package com.example.parcial3;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.parcial3.tasks.Task;
import com.example.parcial3.tasks.TaskService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetails extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "taskId";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int taskId;
    private String mParam2;

    String[] status = {"PENDING", "IN_PROGRESS", "COMPLETED"};

    private TaskService taskService;
    Context context;
    EditText inputTaskTitle;
    EditText inputTaskDescription;
    Button btnSave;
    Spinner spinnerStatus;
    View view;
    Task task;

    public TaskDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskDetails newInstance(String param1, String param2) {
        TaskDetails fragment = new TaskDetails();
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
            taskId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_task_details, container, false);
        context = getContext();
        taskService = new TaskService(context);
        inputTaskTitle = view.findViewById(R.id.inputTaskTitle);
        inputTaskDescription = view.findViewById(R.id.inputTaskDescription);
        spinnerStatus = view.findViewById(R.id.spintaskStatus);
        btnSave = view.findViewById(R.id.btnSaveTask);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });

        this.defineSpinner();
        this.setTask();

        return view;
    }

    private void defineSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, status);
        spinnerStatus.setAdapter(adapter);
    }

    private void setTask () {
        task = taskService.get(taskId);
        inputTaskTitle.setText(task.title);
        inputTaskDescription.setText(task.description);
        switch (task.status) {
            case "PENDING":
                spinnerStatus.setSelection(0);
                break;
            case "IN_PROGRESS":
                spinnerStatus.setSelection(1);
                break;
            default:
                spinnerStatus.setSelection(2);
                break;
        }
    }

    private void saveTask() {
        task.title = inputTaskTitle.getText().toString();
        task.description = inputTaskDescription.getText().toString();
        task.status = spinnerStatus.getSelectedItem().toString();
        taskService.update(task);
        Navigation.findNavController(view).navigate(R.id.action_taskDetails_to_tasksFragment);
    }
}