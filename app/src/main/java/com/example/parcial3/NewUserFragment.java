package com.example.parcial3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.parcial3.database.Database;
import com.example.parcial3.user.User;
import com.example.parcial3.user.UserService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewUserFragment extends Fragment {
    EditText inputUserName;
    EditText inputUserEmail;
    EditText inputUserAge;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "userId";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String userId;
    private String mParam2;

    public NewUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewUserFragment newInstance(String param1, String param2) {
        NewUserFragment fragment = new NewUserFragment();
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
            userId = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        } else {
            userId = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View principalView = inflater.inflate(R.layout.fragment_new_user, container, false);

        Context context = getContext();
        UserService userService = new UserService(context);

        Button btnCancel = principalView.findViewById(R.id.btnUserActionCancel);
        Button btnSave = principalView.findViewById(R.id.btnUserActionSave);
        this.inputUserName = principalView.findViewById(R.id.inputName);
        this.inputUserEmail = principalView.findViewById(R.id.inputEmail);
        this.inputUserAge = principalView.findViewById(R.id.inputAge);


        btnCancel.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_newUserFragment_to_usersFragment));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User newUser = NewUserFragment.getUser(principalView);
                userService.saveUser(newUser);
                Navigation.findNavController(view).navigate(R.id.action_newUserFragment_to_usersFragment);
            }
        });
        return principalView;
    }

    static User getUser(View view) {
        EditText inputUserName = view.findViewById(R.id.inputName);
        EditText inputUserEmail = view.findViewById(R.id.inputEmail);
        EditText inputUserAge = view.findViewById(R.id.inputAge);

        String userName = String.valueOf(inputUserName.getText());
        String userEmail = String.valueOf(inputUserEmail.getText());
        int userAge = Integer.parseInt(String.valueOf(inputUserAge.getText()));


        return  new User(userName, userEmail, userAge);


    }

    private static boolean saveUser(Context context, User user) {
            Database base = new Database(context, "dbUsuario",null,1);
            SQLiteDatabase con = base.getWritableDatabase();
            ContentValues insertContent = new ContentValues();
            insertContent.put("name", user.name);
            insertContent.put("email", user.email);
            insertContent.put("age", user.age);
            con.insert("users", null, insertContent);
            con.close();
            return true;
    }
}