package com.example.parcial3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parcial3.database.Database;
import com.example.parcial3.user.User;
import com.example.parcial3.user.UserAdapter;
import com.example.parcial3.user.UserService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<User> users;
    private UserAdapter userAdapter;

    public UsersFragment() {
        // Required empty public constructor
        // users = new ArrayList<User>();
        // users.add(new User(1, "Erick Josue Saravia", "erick@gmail.com", 22));
        // users.add(new User(2, "Erick Josue Saravia", "erick@gmail.com", 22));
        // users.add(new User(3, "Erick Josue Saravia", "erick@gmail.com", 22));

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UsersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UsersFragment newInstance(String param1, String param2) {
        UsersFragment fragment = new UsersFragment();
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
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        Button btnUserActions = view.findViewById(R.id.btnUserActions);
        ListView usersList = view.findViewById(R.id.usersList);

        btnUserActions.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_usersFragment_to_newUserFragment));
        UserService userService = new UserService(getContext());
        users = userService.getUsers();

        userAdapter = new UserAdapter(getContext(), users);
        usersList.setAdapter(userAdapter);
        Context context = getContext();

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = users.get(i);
                Bundle bundle  = new Bundle();
                bundle.putInt("userId", user.id);
                Navigation.findNavController(view).navigate(R.id.action_usersFragment_to_newUserFragment, bundle);
            }
        });
        return view;
    }

    private ArrayList<User> getUsers(Context context) {
        Database base = new Database(context, "dbUsuario",null,1);
        SQLiteDatabase con = base.getWritableDatabase();
        users = new ArrayList<User>();
        Cursor query = con.rawQuery("SELECT * FROM users", null);
        while (query.moveToNext()){
            int id= query.getInt(query.getColumnIndex("id"));
            String name = query.getString(query.getColumnIndex("name"));
            String email = query.getString(query.getColumnIndex("email"));
            int age= query.getInt(query.getColumnIndex("age"));
            users.add(new User(id, name, email, age));
        }
        return users;
    }
}